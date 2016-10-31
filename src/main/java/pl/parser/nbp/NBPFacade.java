package pl.parser.nbp;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.joda.time.DateTime;

public class NBPFacade {
	

	private NBPTablesCreator tablesCreator = new NBPTablesCreator();
	private Calculator calculator = new Calculator();
	public static final String AVERAGE_KEY = "avg";
	public static final String STD_DEVIATION_KEY = "std";
	
	public BigDecimal calculateAverageBuyingRateByCurrencyCodeAndTableType(DateTime dateFrom, DateTime dateTo, CurrencyCode currencyCode, TableType tableType) throws DateValidationException{
		List<NBPTable> nbpTables = tablesCreator.getTables(dateFrom, dateTo, tableType);
		List<NBPTablePosition> filteredPositions = nbpTables.stream().flatMap(t -> t.getTablePostitons().stream()).filter(p -> p.getCurrencyCode().equals(currencyCode)).collect(Collectors.toList());
		List<BigDecimal> items = filteredPositions.stream().map(p-> p.getBuyingRate()).collect(Collectors.toList());
		return calculator.calculateAverage(items);
	}
	
	public BigDecimal calculateStandardDeviationOfSellingRateByCurrencyCodeAndTableType(DateTime dateFrom, DateTime dateTo, CurrencyCode currencyCode, TableType tableType) throws DateValidationException{
		List<NBPTable> nbpTables = tablesCreator.getTables(dateFrom, dateTo, tableType);
		List<NBPTablePosition> filteredPositions = nbpTables.stream().flatMap(t -> t.getTablePostitons().stream()).filter(p -> p.getCurrencyCode().equals(currencyCode)).collect(Collectors.toList());
		List<BigDecimal> items = filteredPositions.stream().map(p-> p.getSellingRate()).collect(Collectors.toList());
		return calculator.calculateStandardDeviation(items);
	}
	
	public Map<String, BigDecimal> calculateAverageBuyingRateAndSellingRateByCurrencyCodeAndTableType(DateTime dateFrom, DateTime dateTo, CurrencyCode currencyCode, TableType tableType) throws DateValidationException{
		List<NBPTable> nbpTables = tablesCreator.getTables(dateFrom, dateTo, tableType);
		List<NBPTablePosition> filteredPositions = nbpTables.stream().flatMap(t -> t.getTablePostitons().stream()).filter(p -> p.getCurrencyCode().equals(currencyCode)).collect(Collectors.toList());
		List<BigDecimal> buyinRateItems = filteredPositions.stream().map(p-> p.getBuyingRate()).collect(Collectors.toList());
		List<BigDecimal> sellingRateItems = filteredPositions.stream().map(p-> p.getSellingRate()).collect(Collectors.toList());
		BigDecimal average =  calculator.calculateAverage(buyinRateItems);
		BigDecimal standardDeviation = calculator.calculateStandardDeviation(sellingRateItems);
		Map<String, BigDecimal> resultMap = new HashMap<String, BigDecimal>();
		resultMap.put(AVERAGE_KEY, average);
		resultMap.put(STD_DEVIATION_KEY, standardDeviation);
		return resultMap;
	}
}
