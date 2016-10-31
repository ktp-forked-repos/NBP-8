package pl.parser.nbp;

public enum CurrencyCode {
	USD, EUR, CHF, GBP, UNKNOWN;

	public static CurrencyCode fromString(String text) {
		if (text != null) {
			for (CurrencyCode code : CurrencyCode.values()) {
				if (text.equalsIgnoreCase(code.name())) {
					return code;
				}
			}
		}
		return UNKNOWN;
	}
}
