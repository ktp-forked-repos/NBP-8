package pl.parser.nbp;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.joda.time.DateTime;
import org.xml.sax.SAXException;

public class NBPTablesCreator {

	NBPTableHandler npbTablesHandler;
	NBPTableNamesObtainer tableNamesObtainer = new NBPTableNamesObtainer();
	
	
	List<NBPTable> getTables(DateTime dateFrom, DateTime dateTo, TableType tableType) throws DateValidationException {
		List<String> tableNames = tableNamesObtainer.getTableNames(dateFrom, dateTo, tableType);
		npbTablesHandler = tableType.getTableHandler();
		List<NBPTable> nbpTables = new ArrayList<NBPTable>();
		for(String tableName : tableNames){
			try {
				SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
				saxParserFactory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
				SAXParser saxParser = saxParserFactory.newSAXParser(); 
				URL url = new URL(NBPTableNamesObtainer.NBP_RESOURCE_PREFIX + tableName + ".xml");
				saxParser.parse(url.openStream(), npbTablesHandler);
				nbpTables.add(npbTablesHandler.getNBPTable());
			} catch (MalformedURLException e) {
				System.out.println("Incorrect table URL! Returning empty collection");
				return new ArrayList<NBPTable>();
			} catch (IOException e) {
				System.out.println("NBP table was not found in given URL! Returning empty collection");
				return new ArrayList<NBPTable>();
			} catch (ParserConfigurationException e) {
				System.out.println("Exception during xml parsing. Returning empty collection");
				return new ArrayList<NBPTable>();
			} catch (SAXException e) {
				System.out.println("Exception during creating SAX parser instance. Returning empty collection");
				return new ArrayList<NBPTable>();
			}
		}
		return nbpTables;
	}
	

	public NBPTableNamesObtainer getTableNamesObtainer() {
		return tableNamesObtainer;
	}



	public void setTableNamesObtainer(NBPTableNamesObtainer tableNamesObtainer) {
		this.tableNamesObtainer = tableNamesObtainer;
	}
}
