package pl.parser.nbp;

import java.math.BigDecimal;
import java.util.Map;

import org.joda.time.DateTime;

public class MainClass {

	
	public static void main(String[] args) {
		
		NBPFacade facade = new NBPFacade();
		DateUtils dateUtils = new DateUtils();
		
		if(args.length != 3){
			System.out.println("Please specify exact 3 required parameters !");
			return;
		}
		CurrencyCode currencyCode = CurrencyCode.fromString(args[0]);
		String dateFromString = args[1];
		String dateToString = args[2];
		if(currencyCode.equals(CurrencyCode.UNKNOWN)){
			System.out.println("Incorrect currency Code !");
			return;
		} 
		try{
			DateTime dateFrom = dateUtils.convertStringToDate(dateFromString, "yyyy-MM-dd");
			DateTime dateTo = dateUtils.convertStringToDate(dateToString, "yyyy-MM-dd");
			Map<String, BigDecimal> resultMap = facade.calculateAverageBuyingRateAndSellingRateByCurrencyCodeAndTableType(dateFrom, dateTo, currencyCode, TableType.BID_AND_OFFER);
			BigDecimal average =  resultMap.get(facade.AVERAGE_KEY);
			BigDecimal standardDeviation = resultMap.get(facade.STD_DEVIATION_KEY);
			if(average == null || standardDeviation == null){
				System.out.println("No results found!");
				return;
			}
			System.out.println("Average buying rate: " + average);
			System.out.println("Standard deviation of selling rate: " + standardDeviation);
		} catch(IllegalArgumentException e){
			System.out.println("Please specify date in yyyy-MM-dd format !");
			return;
		} catch (DateValidationException e) {
			System.out.println(String.format("Exception during dates validation. Message: %s", e.getMessage()));
			return;
		}
		
	}

}
