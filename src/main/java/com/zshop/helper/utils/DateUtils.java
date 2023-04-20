package com.zshop.helper.utils;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.MINUTES;

import java.lang.reflect.Array;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;

import org.springframework.util.StringUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DateUtils {

	private static final int CALENDAR_YEAR_LIST_SIZE = 21;

	private DateUtils() {

	}

	public static final String DATE_FORMAT_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

	/**
	 * yyyy-MM-dd HH:mm e.g. : 2020-10-25 13:10
	 */
	public static final String DATE_FORMAT_YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";

	private static final String HH_MM = "HH:mm";
	/**
	 * yyyy-MM-dd e.g. : 2020-10-25
	 */
	public static final String DATE_FORMAT_YYYY_MM_DD = "yyyy-MM-dd";

	public static final String UTC_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ssXXX";

	public static final String UTC_DEFAULT_TIME_ZONE = "UTC";

	public static final String DATE_FORMAT_YYYY_MM_DD_HH_MM_SS_S = "yyyy-MM-dd HH:mm:ss.S";

	public static final String DATE_FORMAT_YYYY_DD_MM_YY_HH_MM_SS = "dd-MMM-yy HH:mm:ss";

	private static final DateTimeFormatter DATE_FORMAT_YYYY_MM_DD_HH_MM_SS_FORMATTER = DateTimeFormatter
			.ofPattern(DATE_FORMAT_YYYY_MM_DD_HH_MM_SS);

	public static String getCurrentDateStr() {
		return LocalDateTime.now().format(DATE_FORMAT_YYYY_MM_DD_HH_MM_SS_FORMATTER);
	}

	public static Date getCurrentDate() {
		return Calendar.getInstance().getTime();
	}

	public static String dateToString(Date date) {
		return new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD_HH_MM_SS).format(date);
	}

	public static String dateToString(LocalTime localTime) {
		return localTime.format(DateTimeFormatter.ofPattern(HH_MM));
	}

	public static LocalDateTime getCurrentLocalDateTime() {
		return LocalDateTime.now();
	}

	/**
	 * used to get Date from time stamp string
	 * 
	 * @param timeStampDateStr - string of time stamp
	 * @return
	 */
	public static Date getDateFromTSTMPString(String timeStampDateStr) {
		try {
			// removing the last dot '.' from the string
			timeStampDateStr = timeStampDateStr.substring(0, timeStampDateStr.indexOf('.'));
			return getDateFromString(timeStampDateStr, DATE_FORMAT_YYYY_MM_DD_HH_MM_SS);
		} catch (Exception ex) {
			log.error("Error occured in DateUtils.getDateFromTSTMPString {} ", ex);
		}
		return null;

	}

	public static Date getDateFromString(String dateTime, String currentFormat) {
		try {
			LocalDateTime ldt = formatDate(dateTime, currentFormat);
			if (ldt != null) {
				return Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
			}
		} catch (Exception e) {
			log.error("Error occured in DateUtils.getDateFromString {} ", e);
		}
		return null;
	}

	public static Date getDateFromLocalDateTime(LocalDateTime ldt) {
		try {
			if (ldt != null) {
				return Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
			}
		} catch (Exception e) {
			log.error("Error occured in DateUtils.getDateFromLocalDateTime {} ", e);
		}
		return null;
	}

	public static LocalDateTime formatDate(String dateTime, String currentFormat) {
		if (!StringUtils.isEmpty(currentFormat)) {
			return LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern(currentFormat));
		}
		return null;
	}

	public static boolean isFutureDate(String requestLdtStr) {
		boolean isFutureDate = true;
		if (!StringUtils.isEmpty(requestLdtStr)) {
			LocalDateTime requestLdt = formatDate(requestLdtStr, DATE_FORMAT_YYYY_MM_DD_HH_MM_SS);
			if (requestLdt != null && requestLdt.isBefore(getCurrentLocalDateTime()))
				isFutureDate = false;

		}
		return isFutureDate;
	}

	/**
	 * Utility method to generate time-period snapshot for given duration
	 * 
	 * @param duration
	 * @return
	 */
	public static Map<Long, Set<PeriodSnapshot>> generateTimeSnapshotForDuration(Set<Long> durationSet,
			String startTimeStr, String endTimeStr) {

		Map<Long, Set<PeriodSnapshot>> durationPeriodSnapshotMap = new HashMap<>();

		durationSet.forEach(duration -> {

			LocalTime startTime = null;
			LocalTime endTime = null;

			if (StringUtils.isEmpty(startTimeStr)) {
				startTime = LocalTime.of(0, 0, 0);
			} else {
				startTime = LocalTime.parse(startTimeStr, DateTimeFormatter.ofPattern("HH:mm:ss"));
			}

			if (StringUtils.isEmpty(endTimeStr)) {
				endTime = LocalTime.of(23, 59, 59);
			} else {
				endTime = LocalTime.parse(endTimeStr, DateTimeFormatter.ofPattern("HH:mm:ss"));
			}

			Integer loops = (int) (Duration.between(startTime, endTime).toMinutes() / duration);

			Set<PeriodSnapshot> periods = new LinkedHashSet<>();

			List<LocalTime> times = new ArrayList<>(loops);

			for (int i = 1; i <= loops; i++) {

				PeriodSnapshot period = new PeriodSnapshot();
				/**
				 * Add 1 minute span between each slot
				 * 
				 */
				period.setStartTime(startTime);
				period.setEndTime(startTime.plusMinutes(duration));
				periods.add(period);

				times.add(startTime);
				startTime = startTime.plusMinutes(duration);
			}
			durationPeriodSnapshotMap.put(duration, periods);
		});

		return durationPeriodSnapshotMap;
	}

	/**
	 * @param startDateStr - the text to parse such as "2007-12-03", not null
	 * @param endDateStr   - the text to parse such as "2007-12-03", not null
	 * 
	 * @return {@link List<LocalDate>} of {@link LocalDate} for provided range
	 */
	public static List<LocalDate> getDatesRange(String startDateStr, String endDateStr) {

		LocalDate incrementingDate = LocalDate.parse(startDateStr);
		LocalDate endDate = LocalDate.parse(endDateStr);

		List<LocalDate> allDates = new ArrayList<>();

		if (incrementingDate.isAfter(endDate)) {
			throw new IllegalStateException("start date must be before or equal to end date");
		}

		while (!incrementingDate.isAfter(endDate)) {
			allDates.add(incrementingDate);
			incrementingDate = incrementingDate.plusDays(1);
		}

		return allDates;
	}

	/**
	 * @param startDate - {@link Date}
	 * @param endDate   - {@link Date}
	 * 
	 * @return {@link List<LocalDate>} of {@link LocalDate} for provided range
	 */
	public static List<LocalDate> getDatesRange(Date startDate, Date endDate) {

		LocalDate incrementingDateLocale = startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate endDateLocale = endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

		List<LocalDate> allDates = new ArrayList<>();

		if (incrementingDateLocale.isAfter(endDateLocale)) {
			throw new IllegalStateException("start date must be before or equal to end date");
		}

		while (!incrementingDateLocale.isAfter(endDateLocale)) {
			allDates.add(incrementingDateLocale);
			incrementingDateLocale = incrementingDateLocale.plusDays(1);
		}

		return allDates;
	}

	/**
	 * used to get {@link String} from {@link Date}
	 * 
	 * @param date
	 * @param dateFormat
	 * 
	 * @return - date as {@link String}
	 */
	public static String dateToString(Date date, String dateFormat) {
		return new SimpleDateFormat(dateFormat).format(date);
	}

	public static Date dateToCustomDateFormat(Date date, String dateFormat) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		String dateToString = dateToString(date, dateFormat);
		return sdf.parse(dateToString);
	}

	/**
	 * @param dateStr - only date with format yyyy-MM-dd e.g. : 2020-10-25
	 * @return - instance of {@link Date} for passed string date with time as
	 *         00:00:00
	 */
	public static Date getDateWithoutTimeFromString(String dateStr) {
		try {
			dateStr = dateStr.split(" ")[0];
			dateStr += " 00:00:00";
			LocalDateTime ldt = formatDate(dateStr, DATE_FORMAT_YYYY_MM_DD_HH_MM_SS);
			if (ldt != null) {
				return Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
			}
		} catch (Exception e) {
			log.error("Error occured in DateUtils.getDateFromString {} ", e);
		}
		return null;
	}

	/**
	 * used to set the time in passed {@link Date} instance
	 * 
	 * @param date
	 * @param hourOfDay
	 * @param minute
	 * @param second
	 * @param ms
	 * 
	 * @return - Instance of {@link Date} with setting time as passed parameters
	 */
	public static Date setTime(final Date date, final int hourOfDay, final int minute, final int second, final int ms) {
		final GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(date);
		gc.set(Calendar.HOUR_OF_DAY, hourOfDay);
		gc.set(Calendar.MINUTE, minute);
		gc.set(Calendar.SECOND, second);
		gc.set(Calendar.MILLISECOND, ms);
		return gc.getTime();
	}

	/**
	 * used to get {@link Timestamp} from {@link String}
	 * 
	 * @param date
	 * @param dateFormat
	 * 
	 * @return - date as {@link Timestamp}
	 */
	public static Timestamp getTimestampFromString(String date, String currentFormat) {
		Date dateInstance = getDateFromString(date, currentFormat);
		if (dateInstance != null) {
			return new Timestamp(dateInstance.getTime());
		}
		return null;

	}

	/**
	 * @param startDate
	 * @param endDate
	 * @return boolean
	 */
	public static boolean isStartDateBeforeEndDate(Date startDate, Date endDate) {
		try {
			LocalDate startDateLocale = startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			LocalDate endDateLocale = endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			return !startDateLocale.isAfter(endDateLocale);
		} catch (Exception e) {
			log.error("Error occured in DateUtils.isStartDateBeforeEndDate {} ", e);
		}
		return false;
	}

	/**
	 * used to get time difference with current time for passed start time
	 * 
	 * @param startDateInstance
	 * @param chronoUnit        => e.g. ChronoUnit.MINUTES
	 * @return difference with current date time & passed date time
	 */
	public static long getTimeDifference(Date startDateInstance, ChronoUnit chronoUnit) {
		LocalDateTime startLocalDateTime = startDateInstance.toInstant().atZone(ZoneId.systemDefault())
				.toLocalDateTime();
		LocalDateTime currentDateTime = LocalDateTime.now();
		return chronoUnit.between(currentDateTime, startLocalDateTime);
	}

	public static long getTimeDifference(Date startDateInstance, Date endDateInstance, ChronoUnit chronoUnit) {
		LocalDateTime startLocalDateTime = startDateInstance.toInstant().atZone(ZoneId.systemDefault())
				.toLocalDateTime();
		LocalDateTime endDateTime = endDateInstance.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
		return chronoUnit.between(endDateTime, startLocalDateTime);
	}

	public static LocalDate convertToLocalDateViaMilisecond(Date dateToConvert) {
		ZoneId systemDefault = ZoneId.systemDefault();
		return Instant.ofEpochMilli(dateToConvert.getTime()).atZone(systemDefault).toLocalDate();

	}

	/*
	 * Utility to check if two time ranges are less than given minutes
	 */
	public static boolean isValidUpcomingShow(long startTime, long endTime, long minutes) {

		long showDuration = endTime - startTime;
		long timeGap = endTime - new Date().getTime();
		return timeGap >= 0 && timeGap <= ((MILLISECONDS.convert(minutes, MINUTES)) + showDuration);
	}

	public static String formatToUtc(final Date dateToFormat) {
		SimpleDateFormat df = new SimpleDateFormat(UTC_DATE_FORMAT);
		df.setTimeZone(TimeZone.getTimeZone(UTC_DEFAULT_TIME_ZONE));
		return df.format(dateToFormat);
	}

	public static LocalDate dateStrToLocalDate(final String dateToFormat, DateTimeFormatter dateFormat) {
		return LocalDate.parse(dateToFormat, dateFormat);
	}

	public static Boolean isDateAfterCurrentDate(String date, String dateFormat) {
		LocalDateTime checkDate = DateUtils.formatDate(date, dateFormat);
		LocalDateTime today = LocalDateTime.now();
		return checkDate != null && checkDate.isAfter(today);
	}

	public static LocalTime localTimeFromDate(Date dateTime) {

		return LocalDateTime.ofInstant(dateTime.toInstant(), ZoneId.systemDefault()).toLocalTime();
	}

	public static LocalDateTime localDateTimeFromDate(Date dateTime) {

		return LocalDateTime.ofInstant(dateTime.toInstant(), ZoneId.systemDefault());
	}
	
	public static List<String> getCalendarYearsList() {

		int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		List<String> fillingYearList = new ArrayList<>();

		int[] fillingYearsArr = new int[CALENDAR_YEAR_LIST_SIZE];

		for (int i = 0; i <= CALENDAR_YEAR_LIST_SIZE-1; i++) {
			fillingYearsArr[i] = currentYear;
			currentYear = currentYear - 1;
		}

		for (int i = 0; i < CALENDAR_YEAR_LIST_SIZE-1; i++) {
			int y = (int) Array.get(fillingYearsArr, i);
			int x = (int) Array.get(fillingYearsArr, i + 1);
			String fromYear = String.valueOf(x);
			String toYear = String.valueOf(y);
			String fillingYearListElem = fromYear + "-" + toYear.substring(2, toYear.length());
			fillingYearList.add(fillingYearListElem);
		}
		return fillingYearList;

	}

}