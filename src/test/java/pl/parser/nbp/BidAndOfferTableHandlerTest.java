package pl.parser.nbp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.StringReader;
import java.math.BigDecimal;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.joda.time.DateTime;
import org.junit.Test;
import org.xml.sax.InputSource;

public class BidAndOfferTableHandlerTest {
	
	TableType tableType = TableType.BID_AND_OFFER;
	String tableNumber = "73/C/NBP/2007";
	DateTime listDate = new DateTime("2007-04-12");
	DateTime publicationDate = new DateTime("2007-04-13");
			
	String firstCurrencyName = "dolar amerykañski";
	Integer firstConversionRate = 1;
	CurrencyCode firstCurrencyCode = CurrencyCode.USD;
	BigDecimal firstBuyingRate = new BigDecimal("2.8210");
    BigDecimal firstSellingRate = new BigDecimal("2.8780");
	
	String secondCurrencyName = "euro";
	Integer secondConversionRate = 1;
	CurrencyCode secondCurrencyCode = CurrencyCode.EUR;
	BigDecimal secondBuyingRate = new BigDecimal("3.7976");
	BigDecimal secondSellingRate = new BigDecimal("3.8744");
	
	String correctXml = "<tabela_kursow typ=\"C\"><numer_tabeli>73/C/NBP/2007</numer_tabeli><data_notowania>2007-04-12</data_notowania><data_publikacji>2007-04-13</data_publikacji><pozycja><nazwa_waluty>dolar amerykañski</nazwa_waluty><przelicznik>1</przelicznik><kod_waluty>USD</kod_waluty><kurs_kupna>2,8210</kurs_kupna><kurs_sprzedazy>2,8780</kurs_sprzedazy></pozycja><pozycja><nazwa_waluty>euro</nazwa_waluty><przelicznik>1</przelicznik><kod_waluty>EUR</kod_waluty><kurs_kupna>3,7976</kurs_kupna><kurs_sprzedazy>3,8744</kurs_sprzedazy></pozycja></tabela_kursow>";

	String tableWithWrongType = "<tabela_kursow typ=\"A\"><numer_tabeli>73/C/NBP/2007</numer_tabeli><data_notowania>2007-04-12</data_notowania><data_publikacji>2007-04-13</data_publikacji><pozycja><nazwa_waluty>dolar amerykañski</nazwa_waluty><przelicznik>1</przelicznik><kod_waluty>USD</kod_waluty><kurs_kupna>2,8210</kurs_kupna><kurs_sprzedazy>2,8780</kurs_sprzedazy></pozycja><pozycja><nazwa_waluty>euro</nazwa_waluty><przelicznik>1</przelicznik><kod_waluty>EUR</kod_waluty><kurs_kupna>3,7976</kurs_kupna><kurs_sprzedazy>3,8744</kurs_sprzedazy></pozycja></tabela_kursow>";

	@Test
	public void testSuccessfullParsing() throws Exception{
		
		SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
		SAXParser saxParser = saxParserFactory.newSAXParser();
		NBPTableHandler handler = new BidAndOfferTableHandler();
		saxParser.parse(new InputSource(new StringReader(correctXml)), handler);
		NBPTable nbpTable = handler.getNBPTable();
		
		assertNotNull(nbpTable);
		assertTrue(nbpTable.getListDate().equals(listDate));
		assertTrue(nbpTable.getPublicationDate().equals(publicationDate));
		assertEquals(tableNumber, nbpTable.getTableNumber());
		assertEquals(tableType, nbpTable.getTableType());
		
		List<NBPTablePosition> tablePositions = nbpTable.getTablePostitons();
		assertEquals(2, tablePositions.size());
		//assertTrue(tablePositions.get(0) instanceof BidAndOfferTablePosition);
		//assertTrue(tablePositions.get(1) instanceof BidAndOfferTablePosition);
		
		//BidAndOfferTablePosition firstPosition = (BidAndOfferTablePosition) tablePositions.get(0);
		NBPTablePosition firstPosition = tablePositions.get(0);
		assertEquals(firstCurrencyName, firstPosition.getCurrencyName());
		assertTrue(firstConversionRate.equals(firstPosition.getConversionRate()));
		assertEquals(firstCurrencyCode, firstPosition.getCurrencyCode());
		assertTrue(firstBuyingRate.equals(firstPosition.getBuyingRate()));
		assertTrue(firstSellingRate.equals(firstPosition.getSellingRate()));
		
		//BidAndOfferTablePosition secondPosition = (BidAndOfferTablePosition) tablePositions.get(1);
		NBPTablePosition secondPosition = tablePositions.get(1);
		assertEquals(secondCurrencyName, secondPosition.getCurrencyName());
		assertTrue(secondConversionRate.equals(secondPosition.getConversionRate()));
		assertEquals(secondCurrencyCode, secondPosition.getCurrencyCode());
		assertTrue(secondBuyingRate.equals(secondPosition.getBuyingRate()));
		assertTrue(secondSellingRate.equals(secondPosition.getSellingRate()));
	}
	
	@Test(expected = IncorrectTableTypeException.class)
	public void testParsingTableWithWrongType() throws Exception{
		
		SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
		SAXParser saxParser = saxParserFactory.newSAXParser();
		BidAndOfferTableHandler handler = new BidAndOfferTableHandler();
		saxParser.parse(new InputSource(new StringReader(tableWithWrongType)), handler);
	}
	
}
