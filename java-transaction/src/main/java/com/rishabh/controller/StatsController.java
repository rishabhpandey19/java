package com.rishabh.controller;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rishabh.dto.StatsDTO;
import com.rishabh.dto.StatsStringDTO;
import com.rishabh.entity.StatsEntity;
import com.rishabh.service.StatsManagerService;

/**
 * The Class StatsController. Endpoint to get the statistics of transactions
 */
@RestController
public class StatsController {
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(StatsController.class);
	
	/** The stats mgr. */
	@Autowired
	StatsManagerService statsMgr;	
	
	/**
	 * Gets the statistics detail of last 60 seconds.
	 *
	 * @return the statistics detail
	 */
	@GetMapping("/statistics")
	public StatsStringDTO getStatisticsDetailInString() {
		LOGGER.debug("Getting stats for all transactions in last 60 seconds.");
		final StatsEntity entity = statsMgr.getTransactionStats();
		LOGGER.info("Stats for all transactions in last 60 seconds: "+entity);		
		StatsStringDTO dto = convertToStringDTO(entity);
		return dto;
	}
	
	@GetMapping("/statistics/bigdecimal")
	public StatsDTO getStatisticsDetailInBigDecimal() {
		LOGGER.debug("Getting stats for all transactions in last 60 seconds.");
		final StatsEntity entity = statsMgr.getTransactionStats();
		LOGGER.info("Stats for all transactions in last 60 seconds: "+entity);
		StatsDTO dto = convertToDTO(entity);		
		return dto;
	}

	private StatsStringDTO convertToStringDTO(StatsEntity entity) {
		StatsStringDTO dto = new StatsStringDTO();
		dto.setAvg(String.valueOf(doRounding(entity.getAvg())));
		dto.setCount(entity.getCount());
		dto.setMax(String.valueOf(doRounding(entity.getMax())));
		dto.setMin(String.valueOf(doRounding(entity.getMin())));
		dto.setSum(String.valueOf(doRounding(entity.getSum())));
		return dto;
	}

	private StatsDTO convertToDTO(StatsEntity entity) {
		StatsDTO dto = new StatsDTO();
		dto.setAvg(doRounding(entity.getAvg()));
		dto.setCount(entity.getCount());
		dto.setMax(doRounding(entity.getMax()));
		dto.setMin(doRounding(entity.getMin()));
		dto.setSum(doRounding(entity.getSum()));
		return dto;
	}

	private BigDecimal doRounding(BigDecimal bd) {
		return bd.setScale(2, BigDecimal.ROUND_HALF_UP);
	}
}
