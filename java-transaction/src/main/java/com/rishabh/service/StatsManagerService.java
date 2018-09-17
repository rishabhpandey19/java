package com.rishabh.service;

import com.rishabh.entity.StatsEntity;

/**
 * The Interface StatsManagerService.APIs to get statistics of transactions.
 */
public interface StatsManagerService {
	
	/**
	 * Gets the transaction stats.
	 *
	 * @return the transaction stats
	 */
	StatsEntity getTransactionStats();

}
