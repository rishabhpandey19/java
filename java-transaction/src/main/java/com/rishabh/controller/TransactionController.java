package com.rishabh.controller;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rishabh.dto.TransactionDTO;
import com.rishabh.entity.TransactionEntity;
import com.rishabh.exception.FutureTransactionException;
import com.rishabh.exception.InvalidParamException;
import com.rishabh.exception.NullParamException;
import com.rishabh.exception.OldTransactionException;
import com.rishabh.service.TransactionManagerService;
import com.rishabh.service.TransactionValidationService;

/**
 * The Class TransactionController.End point to create transactions 
 */
@RestController
public class TransactionController {
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(TransactionController.class);
	
	/** The Constant EMPTY_STRING. */
	private static final String EMPTY_STRING = "";

	/** The transaction mgr. */
	@Autowired
	private TransactionManagerService transactionMgr;
	@Autowired
	private TransactionValidationService validationService;

	/**
	 * Adds the transaction.
	 * <ul>Does following thing</ul>
	 * <li>map dto to entity</li>
	 * <li>validate request</li>
	 * <li>create transaction</li>
	 *
	 * @param transaction the transaction request object
	 * @return the response entity containing the response code for add operation
	 */
	@PostMapping("/transactions")
	public ResponseEntity<?> addTransaction(@RequestBody TransactionDTO transaction) {
		TransactionEntity entity;
		LOGGER.debug("Creating new transaction for : "+transaction);
		try {
			validateParam(transaction);			
			entity = covertToEntity(transaction);
			transactionMgr.createTransaction(entity);
		} catch (ParseException e) {
			LOGGER.error("Request is unparseable :" +e.getMessage());
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}catch (InvalidParamException e) {
			LOGGER.error("Request param  is invalid :" +e.getMessage());
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}catch (NumberFormatException e) {
			LOGGER.error("Request Format  is invalid :" +e.getMessage());
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}catch (NullParamException e) {
			LOGGER.error("Request param  is null :" +e.getMessage());
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}catch (FutureTransactionException e) {
			LOGGER.error("Transaction date is in future :" +e.getMessage());
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}catch (OldTransactionException e) {
			LOGGER.error("Transaction is older than 60 seconds :" +e.getMessage());
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}catch(Exception e){
			LOGGER.error("Exception occurred :" +e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		LOGGER.info("Created new transaction successfully for: "+transaction);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	/**
	 * Covert to entity.
	 *
	 * @param transaction the transaction
	 * @return the transaction entity
	 * @throws ParseException the parse exception
	 * @throws InvalidParamException the invalid param exception
	 * @throws java.text.ParseException 
	 * @throws java.text.ParseException 
	 */	
	private TransactionEntity covertToEntity(TransactionDTO transaction) throws NullParamException,InvalidParamException, java.text.ParseException {
		LOGGER.debug("Converting dto to "+transaction+" entity");
		SimpleDateFormat sdf=null;
		if(!transaction.getTimestamp().contains(".")){		
			sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
			sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
		}else{
			sdf=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
		}
		Date txDate = sdf.parse(transaction.getTimestamp());
		Date curDate = sdf.parse(sdf.format(new Date()));
		long currentTime = curDate.getTime();
		long timeInMillis =  txDate.getTime();
		 if(timeInMillis>currentTime)
			 throw new FutureTransactionException();
		TransactionEntity entity =  new TransactionEntity();
		entity.setAmount(new BigDecimal(transaction.getAmount()));
		entity.setTimestamp(timeInMillis);
		LOGGER.debug("Converted dto "+transaction+" to entity for: "+entity);
		return entity;
	}

	private void validateParam(TransactionDTO transaction) throws OldTransactionException, ParseException {
		if (transaction.getAmount() == null || transaction.getTimestamp() == null)
			throw new NullParamException();
		if(EMPTY_STRING.equals(transaction.getAmount()))
			throw new InvalidParamException("Params are not valid");
		validationService.validateTransaction(transaction);
	}
	
	/**
	 * Delete all transaction from map.
	 *
	 * @return the response entity
	 */
	@DeleteMapping("/transactions")
	public ResponseEntity<?> deleteAllTransaction() {	
		LOGGER.debug("deleting all transactions.");
		transactionMgr.deleteAllTransactions();
		LOGGER.info("deleted all transactions.");
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);		
	}

}
