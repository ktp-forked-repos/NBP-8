package pl.parser.nbp;

import java.util.Calendar;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class DateUtils {

	public DateTime convertStringToDate(String input, String pattern) {
		if (input != null && pattern != null) {
			DateTimeFormatter formatter = DateTimeFormat.forPattern(pattern);
			return new DateTime(formatter.parseDateTime(input));
		}
		return null;
	}

	public Integer getYearDifference(DateTime dateFrom, DateTime dateTo) {
		if (dateFrom != null && dateTo != null) {
			Period period = new Period(dateFrom, dateTo);
			return period.getYears();
		}
		return null;
	}

	public DateTime extractDateFromTableName(String tableName) {
		if (tableName != null) {
			DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyyMMdd");
			String stringDate = "20"
					+ tableName.substring(tableName.indexOf("z") + 1);
			return new DateTime(formatter.parseDateTime(stringDate));
		}
		return null;
	}

	public boolean isDateInBetweenDates(DateTime dateFrom, DateTime dateTo,
			DateTime dateBetween) {
		if (dateFrom != null && dateTo != null && dateBetween != null) {
			return (dateFrom.isEqual(dateBetween) || dateFrom
					.isBefore(dateBetween)
					&& (dateTo.isEqual(dateBetween) || dateTo
							.isAfter(dateBetween)));
		}
		return false;
	}

}
