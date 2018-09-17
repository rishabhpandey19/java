package com.rishabh.test;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.math.BigDecimal;
import java.time.Instant;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.rishabh.entity.TransactionEntity;
import com.rishabh.service.StorageService;
import com.rishabh.service.impl.TransactionManagerServiceImpl;


/**
 * The Class TransactionManagerServiceTest.
 */
public class TransactionManagerServiceTest extends AbstractTest{
	
	/** The storage service. */
	@Mock 
	private StorageService storageService;
	
	/** The tx mgr. */
	@InjectMocks
	private TransactionManagerServiceImpl txMgr = new TransactionManagerServiceImpl();
	
	
	
	
	/**
	 * Test create transaction when all good.
	 */
	@Test
	public void testCreateTransactionWhenAllGood(){
		TransactionEntity mockEntity = new TransactionEntity();
		mockEntity.setAmount(new BigDecimal(240.0));
		mockEntity.setTimestamp(Instant.now().toEpochMilli());
		doNothing().when(storageService).addInStorage(mockEntity);
		txMgr.createTransaction(mockEntity);
		verify(storageService,times(1)).addInStorage(mockEntity);
	}

}
