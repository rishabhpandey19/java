package com.rishabh.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rishabh.entity.TransactionEntity;
import com.rishabh.exception.OldTransactionException;
import com.rishabh.service.StorageService;
import com.rishabh.service.TransactionManagerService;

/**
 * The Interface TransactionManagerService. API to create and delete transactions.
 */
@Service
public class TransactionManagerServiceImpl implements TransactionManagerService {
	
	@Autowired 
	private StorageService storageService;
	
	
	/**
	 * Creates the transaction.
	 *
	 * @param transaction the transaction
	 * @throws OldTransactionException the old transaction exception
	 */
	@Override
	public void createTransaction(TransactionEntity transaction)  throws OldTransactionException {		
		storageService.addInStorage(transaction);
		
	}


	/**
	 * Delete all transactions.
	 */
	@Override
	public void deleteAllTransactions() {
		storageService.clearStorage();		
	}

	

}
