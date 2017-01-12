package test;



import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert .*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CalculatorTest {
	Calculator cal;

	@Before
	public void setup(){
		
		cal = new Calculator();
		
	}
	
	@Test
	public void add() {
		
		assertThat(cal.add(3, 4), is(7));
		assertEquals(5, cal.add(2, 3));
	}
	
	@Test
	public void minus(){
		
		assertEquals(1, cal.minus(4, 3));
	}
	
	@Test
	public void testName() throws Exception {
		
	}
	
	@After
	public void treardown(){
		
	}

}
