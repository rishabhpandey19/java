package com.rishabh.service;

import java.text.ParseException;

import com.rishabh.dto.TransactionDTO;
import com.rishabh.exception.OldTransactionException;


/**
 * The Interface TransactionValidationService.APIs to validate transactions against business rules.
 */
public interface TransactionValidationService {
	
	/**
	 * Validate transaction.
	 *
	 * @param transaction the entity
	 * @throws OldTransactionException the old transaction exception
	 * @throws ParseException when Parsing fails
	 */
	void validateTransaction(TransactionDTO transaction)  throws OldTransactionException, ParseException;

}
