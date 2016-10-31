package pl.parser.nbp;

import java.math.BigDecimal;

public class BidAndOfferTablePosition extends NBPTablePosition{

	protected CurrencyCode currencyCode;
	protected BigDecimal buyingRate;
	protected BigDecimal sellingRate;
	
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
