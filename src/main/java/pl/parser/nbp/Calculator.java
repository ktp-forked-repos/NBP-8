package pl.parser.nbp;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class Calculator {
	
	public BigDecimal calculateAverage(List<BigDecimal> items){
		if(items != null && items.size() > 0){
			BigDecimal sum = new BigDecimal(0);
			for(BigDecimal item : items){
					sum = sum.add(item);
				}
			return sum.divide(new BigDecimal(items.size()), 6, RoundingMode.HALF_UP).setScale(4, RoundingMode.HALF_UP);
		}
		return null;
	}
	
	public BigDecimal calculateVariance(List<BigDecimal> items){
		if(items != null && items.size() > 0){
			BigDecimal average = calculateAverage(items);
			BigDecimal temp = new BigDecimal(0);
			for(BigDecimal item : items){
				temp = temp.add((item.subtract(average)).multiply(item.subtract(average)));
			}
			return temp.divide(new BigDecimal(items.size()), 6, RoundingMode.HALF_UP);
		}
		return null;
	}
	
	public BigDecimal calculateStandardDeviation(List<BigDecimal> items){
		if(items != null && items.size() > 0){
			return new BigDecimal(Math.sqrt(calculateVariance(items).doubleValue())).setScale(4, RoundingMode.HALF_UP);
		}
		return null;
	}
}
