package pl.parser.nbp;

import junit.framework.TestCase;

import org.joda.time.DateTime;
import org.junit.Test;

public class DateValidatorTest{

	private DateValidator dateValidator = new DateValidator();
	
	@Test(expected = DateValidationException.class)
	public void testBothNullInputs() throws Exception{
		dateValidator.validateDates(null, null);
	}
	
	@Test(expected = DateValidationException.class)
	public void testFirstParameterNull() throws Exception{
		dateValidator.validateDates(new DateTime(), null);
	}
	
	@Test(expected = DateValidationException.class)
	public void testSecondParameterNull() throws Exception{
		dateValidator.validateDates(new DateTime(), null);
	}
	
	@Test(expected = DateValidationException.class)
	public void testDateFromAfterDateTo() throws Exception{
		dateValidator.validateDates(new DateTime(), new DateTime(2015,2,2,0,0));
	}
	
	@Test(expected = DateValidationException.class)
	public void testDateFromAfterCurrentDate() throws Exception{
		dateValidator.validateDates(new DateTime().plusDays(1), new DateTime().plusDays(2));
	}
	
	@Test
	public void testEqualDates() throws Exception{
		dateValidator.validateDates(new DateTime(2015,2,2,0,0), new DateTime(2015,2,2,0,0));
	}
	
	@Test
	public void testDates() throws Exception{
		dateValidator.validateDates(new DateTime(2015,2,2,0,0), new DateTime(2016,2,2,0,0));
	}

}
