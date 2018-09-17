package com.rishabh.service;

import com.rishabh.entity.StatsEntity;
import com.rishabh.entity.TransactionEntity;


/**
 * The Interface StorageService. APIs to manage transactions.
 */
public interface StorageService {
	
	/**
	 * Adds the in storage.
	 *
	 * @param entity the entity
	 */
	void addInStorage(TransactionEntity entity);
	
	/**
	 * Gets the stats.
	 *
	 * @return the stats
	 */
	StatsEntity getStats();

	/**
	 * Clear storage.
	 */
	void clearStorage();

}
