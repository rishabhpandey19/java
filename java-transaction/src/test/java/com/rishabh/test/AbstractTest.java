package com.rishabh.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rishabh.Application;



@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class AbstractTest {
	
	//do some common used activity
	@Test
	public void testApp(){
		//do nothing
	}
}
