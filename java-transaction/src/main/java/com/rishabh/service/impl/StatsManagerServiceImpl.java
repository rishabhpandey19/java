package com.rishabh.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rishabh.entity.StatsEntity;
import com.rishabh.service.StatsManagerService;
import com.rishabh.service.StorageService;

/**
 * The Interface StatsManagerService.APIs to get statistics of transactions.
 */
@Service
public class StatsManagerServiceImpl implements StatsManagerService {

	@Autowired
	private StorageService storageService;

	/**
	 * Gets the transaction stats.
	 *
	 * @return the transaction stats
	 */
	@Override
	public StatsEntity getTransactionStats() {
		return storageService.getStats();
	}

}
