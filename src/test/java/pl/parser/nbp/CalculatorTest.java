package pl.parser.nbp;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

import static org.junit.Assert.*;

public class CalculatorTest {

	@Test
	public void testAverage(){
		BigDecimal correctResult = new BigDecimal("4.2344");
		List<BigDecimal> items = new ArrayList<BigDecimal>();
		items.add(new BigDecimal("4.2135"));
		items.add(new BigDecimal("4.2461"));
		items.add(new BigDecimal("4.2370"));
		items.add(new BigDecimal("4.2409"));
		
		Calculator calculator = new Calculator();
		BigDecimal average = calculator.calculateAverage(items);
		
		assertNotNull(average);
		assertTrue(average.equals(correctResult));
	}
	
	@Test
	public void testVariance(){
		BigDecimal correctResult = new BigDecimal("0.000156");
		List<BigDecimal> items = new ArrayList<BigDecimal>();
		items.add(new BigDecimal("4.2135"));
		items.add(new BigDecimal("4.2461"));
		items.add(new BigDecimal("4.2370"));
		items.add(new BigDecimal("4.2409"));
		
		Calculator calculator = new Calculator();
		BigDecimal variance = calculator.calculateVariance(items);
		
		assertNotNull(variance);
		assertTrue(variance.equals(correctResult));
	}
	
	@Test
	public void testStandardDeviation(){
		BigDecimal correctResult = new BigDecimal("0.0125");
		List<BigDecimal> items = new ArrayList<BigDecimal>();
		items.add(new BigDecimal("4.2135"));
		items.add(new BigDecimal("4.2461"));
		items.add(new BigDecimal("4.2370"));
		items.add(new BigDecimal("4.2409"));
		
		Calculator calculator = new Calculator();
		BigDecimal standardDeviation = calculator.calculateStandardDeviation(items);
		
		assertNotNull(standardDeviation);
		assertTrue(standardDeviation.equals(correctResult));
	}
	
	@Test
	public void testAverageWithNullInput(){
	
		Calculator calculator = new Calculator();
		assertNull(calculator.calculateAverage(null));
		
	}
	
	@Test
	public void testVarianceWithNullInput(){
	
		Calculator calculator = new Calculator();
		assertNull(calculator.calculateVariance(null));
		
	}
	
	@Test
	public void testStandardDeviationWithNullInput(){
	
		Calculator calculator = new Calculator();
		assertNull(calculator.calculateStandardDeviation(null));
		
	}
	
	@Test
	public void testAverageWithEmptyInput(){
	
		Calculator calculator = new Calculator();
		assertNull(calculator.calculateAverage(Collections.EMPTY_LIST));
		
	}
	
	@Test
	public void testVarianceWithEmptyInput(){
	
		Calculator calculator = new Calculator();
		assertNull(calculator.calculateVariance(Collections.EMPTY_LIST));
		
	}
	
	@Test
	public void testStandardDeviationWithEmptyInput(){
	
		Calculator calculator = new Calculator();
		assertNull(calculator.calculateStandardDeviation(Collections.EMPTY_LIST));
		
	}
}
