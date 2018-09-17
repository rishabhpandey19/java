package com.rishabh.service;

import com.rishabh.entity.TransactionEntity;
import com.rishabh.exception.OldTransactionException;


/**
 * The Interface TransactionManagerService. API to create and delete transactions.
 */
public interface TransactionManagerService {
	
	/**
	 * Creates the transaction.
	 *
	 * @param transaction the transaction
	 * @throws OldTransactionException the old transaction exception
	 */
	void createTransaction(TransactionEntity transaction)  throws OldTransactionException;
	
	/**
	 * Delete all transactions.
	 */
	void deleteAllTransactions();

}
