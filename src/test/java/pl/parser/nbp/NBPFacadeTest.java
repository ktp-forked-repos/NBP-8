package pl.parser.nbp;

import java.math.BigDecimal;

import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.junit.Assert.*;

@Category(IntegrationTests.class)
public class NBPFacadeTest {

	@Test
	public void testResultFromTaskExample() throws Exception{
		NBPFacade facade = new NBPFacade();
		DateTime dateFrom = new DateTime("2013-01-28");
		DateTime dateTo = new DateTime("2013-01-31");
		CurrencyCode currencyCode = CurrencyCode.EUR;
		TableType tableType = TableType.BID_AND_OFFER;
		BigDecimal averageFromExample = new BigDecimal("4.1505");
		BigDecimal standardDeviationFromExample = new BigDecimal("0.0125");
		
		BigDecimal average = facade.calculateAverageBuyingRateByCurrencyCodeAndTableType(dateFrom, dateTo, currencyCode, tableType);
		BigDecimal standardDeviation = facade.calculateStandardDeviationOfSellingRateByCurrencyCodeAndTableType(dateFrom, dateTo, currencyCode, tableType);
		
		assertNotNull(average);
		assertNotNull(standardDeviation);
		assertTrue(average.equals(averageFromExample));
		assertTrue(standardDeviation.equals(standardDeviationFromExample));
	}
}
