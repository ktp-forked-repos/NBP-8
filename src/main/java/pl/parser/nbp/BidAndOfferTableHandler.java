package pl.parser.nbp;

import java.math.BigDecimal;

import org.joda.time.DateTime;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

public class BidAndOfferTableHandler extends NBPTableHandler {

	private static final String BID_AND_OFFER_TYPE = "C";
	private NBPTable nbpTable = null;
	//private BidAndOfferTablePosition currentBidAndOfferTablePosition = null;
	private NBPTablePosition currentBidAndOfferTablePosition = null;
	
	boolean isListingDate;
	boolean isPublicationDate;
	boolean isTableNumber;
	//boolean isPosition;
	boolean isCurrencyName;
	boolean isConversionRate;
	boolean isCurrencyCode;
	boolean isBuyingRate;
	boolean isSeelingRate;
	
	@Override
    public void startElement(String uri, String localName, String qName, Attributes attributes)
            throws SAXException {
 
        if (qName.equalsIgnoreCase(NBPXMLConstants.TABLE) && 
        		attributes.getValue(NBPXMLConstants.TYPE) != null &&
        		!attributes.getValue(NBPXMLConstants.TYPE).equalsIgnoreCase(BID_AND_OFFER_TYPE)) {
         
            throw new IncorrectTableTypeException(String.format("Incorrect table type. Expected: %s, found: %s",
            		BID_AND_OFFER_TYPE, attributes.getValue(NBPXMLConstants.TYPE)));
            
        } else if (qName.equalsIgnoreCase(NBPXMLConstants.TABLE) && 
        		attributes.getValue(NBPXMLConstants.TYPE) != null &&
        		attributes.getValue(NBPXMLConstants.TYPE).equalsIgnoreCase(BID_AND_OFFER_TYPE)) {
         
            nbpTable = new NBPTable();
            nbpTable.setTableType(TableType.BID_AND_OFFER);
            
        } else if (qName.equalsIgnoreCase(NBPXMLConstants.TABLE_NUMBER)) {
            isTableNumber = true;
        } else if (qName.equalsIgnoreCase(NBPXMLConstants.LIST_DATE)) {
            isListingDate = true;
        } else if (qName.equalsIgnoreCase(NBPXMLConstants.PUBLICATION_DATE)) {
            isPublicationDate = true;
        } else if (qName.equalsIgnoreCase(NBPXMLConstants.POSITION)) {
        	currentBidAndOfferTablePosition = new NBPTablePosition();
        } else if (qName.equalsIgnoreCase(NBPXMLConstants.CURRENCY_NAME)) {
            isCurrencyName = true;
        } else if (qName.equalsIgnoreCase(NBPXMLConstants.CONVERSION_RATE)) {
            isConversionRate = true;
        } else if (qName.equalsIgnoreCase(NBPXMLConstants.CURRENCY_CODE)) {
            isCurrencyCode = true;
        } else if (qName.equalsIgnoreCase(NBPXMLConstants.BUYING_RATE)) {
            isBuyingRate = true;
        } else if (qName.equalsIgnoreCase(NBPXMLConstants.SELLING_RATE)) {
            isSeelingRate = true;
        }
    }
	
	 @Override
	    public void characters(char ch[], int start, int length) throws SAXException {
	 
	        if (isTableNumber) {
	            nbpTable.setTableNumber(new String(ch, start, length));
	            isTableNumber = false;
	        } else if (isListingDate) {
	        	nbpTable.setListDate(new DateTime(new String(ch, start, length)));
	            isListingDate = false;
	        } else if (isPublicationDate) {
	        	nbpTable.setPublicationDate(new DateTime(new String(ch, start, length)));
	        	isPublicationDate = false;
	        } else if (isCurrencyName) {
	        	currentBidAndOfferTablePosition.setCurrencyName(new String(ch, start, length));
	        	isCurrencyName = false;
	        } else if (isConversionRate) {
	        	currentBidAndOfferTablePosition.setConversionRate(Integer.parseInt(new String(ch, start, length)));
	        	isConversionRate = false;
	        } else if (isCurrencyCode) {
	        	currentBidAndOfferTablePosition.setCurrencyCode(CurrencyCode.fromString(new String(ch, start, length)));
	        	isCurrencyCode = false;
	        } else if (isBuyingRate) {
	        	currentBidAndOfferTablePosition.setBuyingRate(new BigDecimal(new String(ch, start, length).replaceAll(",", ".")));
	        	isBuyingRate = false;
	        } else if (isSeelingRate) {
	        	currentBidAndOfferTablePosition.setSellingRate(new BigDecimal(new String(ch, start, length).replaceAll(",", ".")));
	        	isSeelingRate = false;
	        }
	    }
	
	@Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equalsIgnoreCase(NBPXMLConstants.POSITION)) {
            //add Employee object to list
            nbpTable.getTablePostitons().add(currentBidAndOfferTablePosition);
        }
    }
	
	@Override
	public NBPTable getNBPTable() {
		return nbpTable;
	}
}
