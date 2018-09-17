package com.rishabh.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;
import java.time.Instant;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.rishabh.entity.StatsEntity;
import com.rishabh.entity.TransactionEntity;
import com.rishabh.service.StorageService;

/**
 * The Class StorageServiceTest.
 */
public class StorageServiceTest extends AbstractTest {
	
	/** The storage service. */
	@Autowired
	private StorageService storageService;
	
	/**
	 * Before.
	 */
	@Before
	public void before(){
		storageService.clearStorage();
	}
	
	/**
	 * Test get stats.
	 */
	@Test
	public void testGetStats() {
		TransactionEntity mockEntity = new TransactionEntity();
		mockEntity.setAmount(new BigDecimal(240.0));
		mockEntity.setTimestamp(Instant.now().toEpochMilli());
		storageService.addInStorage(mockEntity);
		StatsEntity entity = storageService.getStats();
		
		assertNotNull(entity);
		assertEquals(1,entity.getCount() );
	}
	
	/**
	 * Test get stats when no transactions.
	 */
	@Test
	public void testGetStatsWhenNoTransactions() {
		StatsEntity entity = storageService.getStats();		
		assertNotNull(entity);
		assertEquals(0,entity.getCount() );
	}
}
