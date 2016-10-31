package pl.parser.nbp;

import org.joda.time.DateTime;

public class DateValidator {
	
	public void validateDates(DateTime dateFrom, DateTime dateTo) throws DateValidationException{
		if(dateFrom == null || dateTo == null){
			throw new DateValidationException("Both date from and date to cannot be null");
		}
		
		if(dateFrom.isAfter(dateTo)){
			throw new DateValidationException("Date from cannot be greater than date to");
		}
		
		if(dateFrom.isAfter(new DateTime())){
			throw new DateValidationException("Date from cannot be greater than current date");
		}
	}
}
