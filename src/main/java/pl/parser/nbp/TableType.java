package pl.parser.nbp;

public enum TableType {
	AVERAGE_EXCHANGE_RATES_OF_FOREIGN_CURRENCIES("a", null),
	AVERAGE_EXCHANGE_RATES_OF_INCORVERTIBLE_FOREIGN_CURRENCIES("b", null),
	BID_AND_OFFER("c", new BidAndOfferTableHandler()),
	UNIT_OF_ACCOUNTS("h", null);
	
	private String symbol;
	private NBPTableHandler tableHandler;
	
	TableType(String symbol, NBPTableHandler tableHandler){
		this.symbol = symbol;
		this.tableHandler = tableHandler;
	}
	
	public String getSymbol(){
		return this.symbol;
	}

	public NBPTableHandler getTableHandler() {
		return tableHandler;
	}
	
	
}
