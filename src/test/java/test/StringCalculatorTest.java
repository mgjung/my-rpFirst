package test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class StringCalculatorTest {

	StringCalculator strCal;
	
	@Before
	public void setup(){
		strCal = new StringCalculator();
	}
	
	@Test
	public void add_null() {
		assertThat(strCal.add(""), is(0));
	}
	@Test
	public void add_숫자하나() {
		assertThat(strCal.add("1"), is(1));
	}
	
	@Test
	public void add_예약구분자_덧셈() throws Exception {
		assertThat(strCal.add("1,2,3"), is(6));
	}
	
	@Test
	public void add_예약구분자_덧셈2() throws Exception {
		assertThat(strCal.add("1:2:3"), is(6));
	}
	
	@Test
	public void add_커스구분자() throws Exception {
		assertThat(strCal.add("//;\n1;2;3"), is(6));
	}
	@Test (expected = RuntimeException.class)
	public void add_음수값전() throws Exception {
		assertThat(strCal.add("//;\n-1;2;3"), is(6));
	}
	
	
}
