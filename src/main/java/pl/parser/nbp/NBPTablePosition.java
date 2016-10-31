package pl.parser.nbp;

import java.math.BigDecimal;

public class NBPTablePosition {

	protected String currencyName;
	protected Integer conversionRate;
	
	protected CurrencyCode currencyCode;
	protected BigDecimal buyingRate;
	protected BigDecimal sellingRate;
	
	
	public String getCurrencyName() {
		return currencyName;
	}
	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}
	public Integer getConversionRate() {
		return conversionRate;
	}
	public void setConversionRate(Integer conversionRate) {
		this.conversionRate = conversionRate;
	}
	
	public CurrencyCode getCurrencyCode() {
		return currencyCode;
	}
	public void setCurrencyCode(CurrencyCode currencyCode) {
		this.currencyCode = currencyCode;
	}
	public BigDecimal getBuyingRate() {
		return buyingRate;
	}
	public void setBuyingRate(BigDecimal buyingRate) {
		this.buyingRate = buyingRate;
	}
	public BigDecimal getSellingRate() {
		return sellingRate;
	}
	public void setSellingRate(BigDecimal sellingRate) {
		this.sellingRate = sellingRate;
	}
}
