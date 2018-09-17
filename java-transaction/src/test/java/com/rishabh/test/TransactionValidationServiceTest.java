package com.rishabh.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.rishabh.dto.TransactionDTO;
import com.rishabh.exception.OldTransactionException;
import com.rishabh.service.TransactionValidationService;

/**
 * The Class TransactionValidationServiceTest.
 */
public class TransactionValidationServiceTest extends AbstractTest{
	
	/** The validation service. */
	@Autowired
	private TransactionValidationService validationService;
	
	/**
	 * Test validate transaction when transaction is old.
	 *
	 * @throws OldTransactionException the old transaction exception
	 * @throws ParseException the parse exception
	 */
	@Test(expected=OldTransactionException.class )
	public void testValidateTransactionWhenTransactionIsOld() throws OldTransactionException, ParseException{
		TransactionDTO mock = new TransactionDTO();
		mock.setAmount("240.0");
		mock.setTimestamp(getCurrentDateTimeBeforeXSeconds(70));
		validationService.validateTransaction(mock);
	}
	
	/**
	 * Test validate transaction when all good.
	 *
	 * @throws OldTransactionException the old transaction exception
	 * @throws ParseException the parse exception
	 */
	public void testValidateTransactionWhenAllGood() throws OldTransactionException, ParseException{
		TransactionDTO mockEntity = new TransactionDTO();
		mockEntity.setAmount("240.0");
		mockEntity.setTimestamp(getCurrentDateTime());
		validationService.validateTransaction(mockEntity);
	}
	
	/**
	 * Gets the current date time.
	 *
	 * @return the current date time
	 */
	private String getCurrentDateTime() {
		SimpleDateFormat simpleDateFormat = dateFormat();
		return simpleDateFormat.format(new Date());
	}

	/**
	 * Date format.
	 *
	 * @return the simple date format
	 */
	private SimpleDateFormat dateFormat() {
		String pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";//"yyyy-MM-dd'T'HH:mm:ss.SSSZ";//"yyyy-MM-dd'T'HH:mm:ss'Z'";//
    	SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		return simpleDateFormat;
	}

	/**
	 * Gets the current date time before X seconds.
	 *
	 * @param seconds the seconds
	 * @return the current date time before X seconds
	 */
	private String getCurrentDateTimeBeforeXSeconds(long seconds) {
		SimpleDateFormat simpleDateFormat = dateFormat();
		return simpleDateFormat.format(Date.from(Instant.now().minusSeconds(seconds)));
	}

}
