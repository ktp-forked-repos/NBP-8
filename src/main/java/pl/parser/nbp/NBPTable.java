package pl.parser.nbp;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

public class NBPTable {

	protected TableType tableType;
	protected String tableNumber;
	protected DateTime listDate;
	protected DateTime publicationDate;
	
	protected List<NBPTablePosition> tablePostitons = new ArrayList<NBPTablePosition>();
	
	public TableType getTableType() {
		return tableType;
	}
	
	public void setTableType(TableType tableType) {
		this.tableType = tableType;
	}
	public String getTableNumber() {
		return tableNumber;
	}
	public void setTableNumber(String tableNumber) {
		this.tableNumber = tableNumber;
	}
	public DateTime getListDate() {
		return listDate;
	}
	public void setListDate(DateTime listDate) {
		this.listDate = listDate;
	}
	public DateTime getPublicationDate() {
		return publicationDate;
	}
	public void setPublicationDate(DateTime publicationDate) {
		this.publicationDate = publicationDate;
	}

	public List<NBPTablePosition> getTablePostitons() {
		return tablePostitons;
	}

	public void setTablePostitons(List<NBPTablePosition> tablePostitons) {
		this.tablePostitons = tablePostitons;
	}
	
	
	
}
