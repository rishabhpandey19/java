package com.rishabh.service.impl;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.rishabh.entity.StatsEntity;
import com.rishabh.entity.TransactionEntity;
import com.rishabh.service.StorageService;
/**
 * The Interface StorageService. APIs to manage transactions.
 */
@Service
public class StorageServiceImpl implements StorageService {

	private final ConcurrentHashMap<UUID, TransactionEntity> tempStorage = new ConcurrentHashMap<>();

	/**
	 * Adds the in storage.
	 *
	 * @param entity the entity
	 */
	@Override
	public synchronized void addInStorage(final TransactionEntity entity) {
			tempStorage.put(UUID.randomUUID(), entity);			
	}
	
	/**
	 * Gets the stats.
	 *
	 * @return the stats
	 */
	@Override
	public StatsEntity getStats() {
		final long currentTime = Instant.now().toEpochMilli();

		final List<BigDecimal> filteredList = tempStorage.entrySet().stream()
				.filter(t -> t.getValue().getTimestamp() > currentTime - 60000) 		
				.map(t -> t.getValue().getAmount()).collect(Collectors.toList());

		final BigDecimal sum = filteredList.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
		final long count = filteredList.stream().count();
		final BigDecimal max =  filteredList.size()>0?filteredList.stream().max(Comparator.naturalOrder()).get():BigDecimal.ZERO;
		final BigDecimal min = filteredList.size()>0?filteredList.stream().min(Comparator.naturalOrder()).get():BigDecimal.ZERO;
		BigDecimal avg;
		if(count==0 || sum==BigDecimal.ZERO){
			avg=BigDecimal.ZERO;
		}else{
			 avg = sum.divide(new BigDecimal(filteredList.size()),5,BigDecimal.ROUND_HALF_UP);			
		}
		final StatsEntity entity = setEntity(currentTime, sum,count,max,min,avg);
		return entity;
	}

	/**
	 * Sets the entity.
	 *
	 * @param currentTime the current time
	 * @param avg the average
	 * @param min  the minimum
	 * @param max  the maximum
	 * @param count the count
	 * @param sum  the sum
	 * @return the stats entity
	 */
	private StatsEntity setEntity(final long currentTime, final BigDecimal sum, final long count, final BigDecimal max, final BigDecimal min, final BigDecimal avg) {
		final StatsEntity entity = new StatsEntity();
		entity.setAvg(avg);
		entity.setCount(count);
		entity.setMax(max);
		entity.setMin(min);
		entity.setSum(sum);
		entity.setTimeNow(currentTime);
		return entity;
	}
	
	/**
	 * Clear storage.
	 */
	@Override
	public void clearStorage() {
		tempStorage.clear();		
	}

}
