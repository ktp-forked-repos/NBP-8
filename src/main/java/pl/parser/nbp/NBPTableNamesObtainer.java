package pl.parser.nbp;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class NBPTableNamesObtainer {

	public static final String NBP_RESOURCE_PREFIX = "http://www.nbp.pl/kursy/xml/";
	private List<String> nbpTables = new ArrayList();;
	private DateValidator dateValidator = new DateValidator();
	private DateUtils dateUtils = new DateUtils();

	public List<String> getTableNames(DateTime dateFrom, DateTime dateTo,
			TableType tableType) throws DateValidationException {
		if (tableType != null) {

			dateValidator.validateDates(dateFrom, dateTo);
			int year = dateFrom.getYear();
			int tableCounter = dateUtils.getYearDifference(dateFrom, dateTo);
			String directoryURL = "";
			nbpTables = new ArrayList<String>();

			do {
				try {
					directoryURL = String.format("%s%s%s%s",
							NBP_RESOURCE_PREFIX, "dir",
							year == new DateTime().getYear() ? "" : year,
							".txt");
					URL url = new URL(directoryURL);
					Scanner scanner = new Scanner(url.openStream());
					while (scanner.hasNextLine()) {
						String table = scanner.nextLine();
						DateTime tablePublicationDate = dateUtils.extractDateFromTableName(table);
						if (table.contains(tableType.getSymbol())
								&& dateUtils.isDateInBetweenDates(dateFrom, dateTo, tablePublicationDate)) {
							nbpTables.add(table.substring(table.indexOf(tableType.getSymbol()))); // to
																				// remove
																				// BOM
						}
					}
					year++;
					tableCounter--;
					// return nbpTables;
				} catch (MalformedURLException e) {
					throw new RuntimeException(String.format(
							"URL '%s' is not in right format", directoryURL));
				} catch (IOException e) {
					System.out.print(String.format(
							"Cannot find nbp table names for year %s. ", year));
					if (year != dateTo.getYear()) {
						System.out.println("Trying to find tables for next year, until given end date");
					}
					year++;
					tableCounter--;
					continue;

				}
			} while (tableCounter >= 0);
		}
		return nbpTables;
	}
}
