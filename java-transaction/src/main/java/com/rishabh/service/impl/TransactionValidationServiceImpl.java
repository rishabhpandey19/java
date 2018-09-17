package com.rishabh.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.rishabh.dto.TransactionDTO;
import com.rishabh.exception.OldTransactionException;
import com.rishabh.service.TransactionValidationService;

/**
 * The Interface TransactionValidationService.APIs to validate transactions against business rules.
 */
@Service
public class TransactionValidationServiceImpl implements TransactionValidationService {

	
	@Value("${time.millis.max}")
	private int maxTimeMills;
	
	/**
	 * Validate transaction.
	 *
	 * @param entity the entity
	 * @throws OldTransactionException the old transaction exception
	 * @throws ParseException when Parsing fails
	 */
	@Override
	public void validateTransaction(TransactionDTO transaction) throws OldTransactionException, ParseException {
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
		if (currentTime-timeInMillis>=maxTimeMills)
			throw new OldTransactionException();
		
	}

}
