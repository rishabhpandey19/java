package com.rishabh.test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rishabh.dto.TransactionDTO;
import com.rishabh.service.StorageService;


/**
 * The Class StatsControllerTest.
 */
public class StatsControllerTest extends AbstractTest{
	
	/** The mock. */
	private MockMvc mock;
	
	/** The context. */
	@Autowired
	private WebApplicationContext context;
    
	/** The storage. */
	@Autowired
	private StorageService storage;
	
    /**
     * Setup.
     */
    @Before
    public void setup() {
        mock = MockMvcBuilders.webAppContextSetup(this.context).build();
        storage.clearStorage();
    }
    
	/**
	 * Context loads.
	 */
	@Test
	public void contextLoads() {
		assertNotNull(context);
	}
	
    /**
     * Test when storage is empty.
     *
     * @throws Exception the exception
     */
    @Test
    public void testWhenStorageIsEmpty() throws Exception{
    	mock.perform(get("/statistics").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andDo(print()).andExpect(jsonPath("$.count", is(0)));                   
    }
    
    /**
     * Test when storage is not empty.
     *
     * @throws Exception the exception
     */
    @Test
    public void testWhenStorageIsNotEmpty() throws Exception{ 
    	
		ObjectMapper mapper =  new ObjectMapper();
    	TransactionDTO dto = new TransactionDTO();
    	dto.setAmount("190.5");
    	dto.setTimestamp(getCurrentDateTime());
    	String mockCreateRequest = mapper.writeValueAsString(dto);	
         
    	 //add one in stoarge
        mock.perform(post("/transactions").contentType(MediaType.APPLICATION_JSON).content(mockCreateRequest));
    	
    	mock.perform(get("/statistics").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andDo(print()).andExpect(jsonPath("$.count", is(1)));                   
    }

	/**
	 * Gets the current date time.
	 *
	 * @return the current date time
	 */
	private String getCurrentDateTime() {
		String pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";
    	SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
    	return simpleDateFormat.format(new Date());
	}

}
