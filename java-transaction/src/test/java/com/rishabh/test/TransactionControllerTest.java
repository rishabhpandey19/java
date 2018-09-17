package com.rishabh.test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rishabh.dto.TransactionDTO;

/**
 * The Class TransactionControllerTest.
 */
@WebAppConfiguration
public class TransactionControllerTest extends AbstractTest {

	/** The mock. */
	private MockMvc mock;
	
	/** The context. */
	@Autowired
	private WebApplicationContext context;

	/**
	 * Setup.
	 */
	@Before
	public void setup() {
		this.mock = MockMvcBuilders.webAppContextSetup(this.context).build();
	}

	/**
	 * Context loads.
	 */
	@Test
	public void contextLoads() {
		assertNotNull(context);
	}

	/**
	 * Test create transaction API when parameter is not valid.
	 *
	 * @throws Exception the exception
	 */
	@Test
    public void testCreateTransactionAPIWhenParameterIsNotValid() throws Exception {
    	ObjectMapper mapper =  new ObjectMapper();
    	TransactionDTO dto = new TransactionDTO();
    	dto.setAmount("190.5");
    	dto.setTimestamp(null);
    	String timeZero = mapper.writeValueAsString(dto);
    	//when timestamp is zero        
    	mock.perform(post("/transactions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(timeZero))
                    .andDo(print())
                    .andExpect(status().isUnprocessableEntity()); 
    	//when amount is null
          dto.setAmount(null);
          dto.setTimestamp(getCurrentDateTime());
    	String amountNull = mapper.writeValueAsString(dto);    	
    	mock.perform(post("/transactions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(amountNull))
                        .andDo(print())
                        .andExpect(status().isUnprocessableEntity());
    	//when amount is empty
    	dto.setAmount("");
     	String amountEmpty = mapper.writeValueAsString(dto); 
    	mock.perform(post("/transactions").contentType(MediaType.APPLICATION_JSON).content(amountEmpty)).andDo(print()).andExpect(status().isUnprocessableEntity()); 
    	//when transaction is older than 60 seconds
    	dto.setAmount("195.0");
    	dto.setTimestamp(getCurrentDateTimeBeforeXSeconds(70));
    	String olderTx = mapper.writeValueAsString(dto);
    	mock.perform(post("/transactions").contentType(MediaType.APPLICATION_JSON).content(olderTx)).andDo(print()).andExpect(status().isNoContent()); 
    }

	/**
	 * Test create transaction API when all good.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testCreateTransactionAPIWhenAllGood() throws Exception {
		ObjectMapper mapper =  new ObjectMapper();
    	TransactionDTO dto = new TransactionDTO();
    	dto.setAmount("190.5");
    	dto.setTimestamp(getCurrentDateTime());
    	String mockRequest = mapper.writeValueAsString(dto);

		mock.perform(post("/transactions").contentType(MediaType.APPLICATION_JSON).content(mockRequest)).andDo(print())
				.andExpect(status().isCreated());
	}

	/**
	 * Test delete transaction API.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testDeleteTransactionAPI() throws Exception {
		// create one transaction
		ObjectMapper mapper =  new ObjectMapper();
    	TransactionDTO dto = new TransactionDTO();
    	dto.setAmount("190.5");
    	dto.setTimestamp(getCurrentDateTime());
    	String mockCreateRequest = mapper.writeValueAsString(dto);		
		mock.perform(post("/transactions").contentType(MediaType.APPLICATION_JSON).content(mockCreateRequest))
				.andDo(print()).andExpect(status().isCreated());

		// delete all
		mock.perform(delete("/transactions").accept(MediaType.APPLICATION_JSON)).andExpect(status().isNoContent());

		// check count is zero
		mock.perform(get("/statistics").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andDo(print())
				.andExpect(jsonPath("$.count", is(0)));
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
