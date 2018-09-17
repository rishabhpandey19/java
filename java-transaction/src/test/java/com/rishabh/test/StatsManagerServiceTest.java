package com.rishabh.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.rishabh.entity.StatsEntity;
import com.rishabh.service.StorageService;
import com.rishabh.service.impl.StatsManagerServiceImpl;

/**
 * The Class StatsManagerServiceTest.
 */
public class StatsManagerServiceTest extends AbstractTest {
	
	/** The storage service. */
	@Mock
	private StorageService storageService;
	
	/** The stats mgr. */
	@InjectMocks
	private final StatsManagerServiceImpl statsMgr = new StatsManagerServiceImpl();
	
	/**
	 * Test get stats.
	 */
	@Test
	public void testGetStats(){
		final StatsEntity mock = new StatsEntity();
		mock.setAvg(new BigDecimal(20));
		mock.setCount(10);
		mock.setMax(new BigDecimal(2));
		mock.setMin(new BigDecimal(1));
		mock.setSum(new BigDecimal(10));
		when(storageService.getStats()).thenReturn(mock);		
		final StatsEntity result = statsMgr.getTransactionStats();
		verify(storageService,times(1)).getStats();
		assertNotNull(result);
		assertEquals(Double.valueOf(20.0),Double.valueOf(result.getAvg().toString()),0);
	}
	
}
