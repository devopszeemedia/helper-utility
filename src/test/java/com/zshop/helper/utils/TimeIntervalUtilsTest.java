package com.zshop.helper.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.Test;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

public class TimeIntervalUtilsTest {

	private static final String SECOND_1 = "1 second";
	private static final String SECOND_59 = "59 seconds";
	
	private static final String MINUTE_1 = "1 minute";
	private static final String MINUTES_10 = "10 minutes";
	private static final String MINUTES_59 = "59 minutes";
	
	private static final String HOUR_1 = "1 hour";
	private static final String HOURS_2 = "2 hours";
	private static final String HOUR_1_MIN_1 = "1 hour 1 minute";
	private static final String HOUR_1_MIN_11 = "1 hour 11 minutes";
	private static final String HOUR_2_MIN_5 = "2 hour 5 minutes";
	private static final String HOUR_2_MIN_11 = "2 hour 11 minutes";
	
	private static final String HOUR_2_MIN_15 = "2 hour 15 minutes";
	
	private static final String WEEK_1 = "1 week";
	private static final String WEEKS_2 = "2 weeks";
	private static final String WEEKS_4 = "4 weeks";
	
	private static final String MONTH_1 = "1 month";
	private static final String MONTHS_2 = "2 months";
	private static final String MONTHS_3 = "3 months";
	
	private static final String DAY_1 = "1 day";
	private static final String DAYS_5 = "5 days";
	
	private static final String YEAR_1 = "1 year";
	private static final String YEARS_5 = "5 years";
	
	@Test
	public void testOneSecondInterval() {

		LocalDateTime baseTime = getBaseTime();
		DateFilterDto testDate = DateFilterDto.builder().dateTime(baseTime.minusSeconds(1)).text("1 sec ago").build();

		String output = TimeIntervalUtils.getTimeInterval(DateUtils.getDateFromLocalDateTime(testDate.getDateTime()),
				DateUtils.getDateFromLocalDateTime(baseTime));
		assertNotNull(output);
		assertEquals(SECOND_1, output);

	}

	@Test
	public void test59SecondInterval() {

		LocalDateTime baseTime = getBaseTime();
		DateFilterDto testDate = DateFilterDto.builder().dateTime(baseTime.minusSeconds(59)).text("59 sec ago").build();

		String output = TimeIntervalUtils.getTimeInterval(DateUtils.getDateFromLocalDateTime(testDate.getDateTime()),
				DateUtils.getDateFromLocalDateTime(baseTime));
		assertNotNull(output);
		assertEquals(SECOND_59, output);

	}

	@Test
	public void test1MinuteInterval() {

		LocalDateTime baseTime = getBaseTime();
		DateFilterDto testDate = DateFilterDto.builder().dateTime(baseTime.minusSeconds(60)).text("60 sec ago").build();

		String output = TimeIntervalUtils.getTimeInterval(DateUtils.getDateFromLocalDateTime(testDate.getDateTime()),
				DateUtils.getDateFromLocalDateTime(baseTime));
		assertNotNull(output);
		assertEquals(MINUTE_1, output);

	}

	@Test
	public void testInterval100sec() {

		LocalDateTime baseTime = getBaseTime();
		DateFilterDto testDate = DateFilterDto.builder().dateTime(baseTime.minusSeconds(100)).text("100 sec ago")
				.build();

		String output = TimeIntervalUtils.getTimeInterval(DateUtils.getDateFromLocalDateTime(testDate.getDateTime()),
				DateUtils.getDateFromLocalDateTime(baseTime));
		assertNotNull(output);
		assertEquals(MINUTE_1, output);

	}

	@Test
	public void testLessThan1HourInterval() {

		LocalDateTime baseTime = getBaseTime();
		DateFilterDto testDate = DateFilterDto.builder().dateTime(baseTime.minusSeconds(3599)).text("3599 sec ago")
				.build();

		String output = TimeIntervalUtils.getTimeInterval(DateUtils.getDateFromLocalDateTime(testDate.getDateTime()),
				DateUtils.getDateFromLocalDateTime(baseTime));
		assertNotNull(output);
		assertEquals(MINUTES_59, output);

	}

	@Test
	public void test1hourInterval() {

		LocalDateTime baseTime = getBaseTime();
		DateFilterDto testDate = DateFilterDto.builder().dateTime(baseTime.minusSeconds(3600)).text("3600 sec ago")
				.build();

		String output = TimeIntervalUtils.getTimeInterval(DateUtils.getDateFromLocalDateTime(testDate.getDateTime()),
				DateUtils.getDateFromLocalDateTime(baseTime));
		assertNotNull(output);
		assertEquals(HOUR_1, output);

	}

	@Test
	public void testHoursAndMinutesTestInterval() {

		LocalDateTime baseTime = getBaseTime();
		DateFilterDto testDate = DateFilterDto.builder().dateTime(baseTime.minusSeconds(3660)).text("3660 sec ago")
				.build();

		String output = TimeIntervalUtils.getTimeInterval(DateUtils.getDateFromLocalDateTime(testDate.getDateTime()),
				DateUtils.getDateFromLocalDateTime(baseTime));
		assertNotNull(output);
		assertEquals(HOUR_1_MIN_1, output);

	}
	
	
	@Test
	public void test71MinutesTestInterval() {

		LocalDateTime baseTime = getBaseTime();
		DateFilterDto testDate = DateFilterDto.builder().dateTime(baseTime.minusMinutes(71)).text("71 min ago")
				.build();

		String output = TimeIntervalUtils.getTimeInterval(DateUtils.getDateFromLocalDateTime(testDate.getDateTime()),
				DateUtils.getDateFromLocalDateTime(baseTime));
		assertNotNull(output);
		assertEquals(HOUR_1_MIN_11, output);

	}
	
	
	@Test
	public void test61MinutesTestInterval() {

		LocalDateTime baseTime = getBaseTime();
		DateFilterDto testDate = DateFilterDto.builder().dateTime(baseTime.minusMinutes(61)).text("61 min ago")
				.build();

		String output = TimeIntervalUtils.getTimeInterval(DateUtils.getDateFromLocalDateTime(testDate.getDateTime()),
				DateUtils.getDateFromLocalDateTime(baseTime));
		assertNotNull(output);
		assertEquals(HOUR_1_MIN_1, output);

	}
	
	@Test
	public void test125MinutesTestInterval() {

		LocalDateTime baseTime = getBaseTime();
		DateFilterDto testDate = DateFilterDto.builder().dateTime(baseTime.minusMinutes(125)).text("125 min ago")
				.build();

		String output = TimeIntervalUtils.getTimeInterval(DateUtils.getDateFromLocalDateTime(testDate.getDateTime()),
				DateUtils.getDateFromLocalDateTime(baseTime));
		assertNotNull(output);
		assertEquals(HOUR_2_MIN_5, output);

	}
	
	@Test
	public void test131MinutesTestInterval() {

		LocalDateTime baseTime = getBaseTime();
		DateFilterDto testDate = DateFilterDto.builder().dateTime(baseTime.minusMinutes(131)).text("131 min ago")
				.build();

		String output = TimeIntervalUtils.getTimeInterval(DateUtils.getDateFromLocalDateTime(testDate.getDateTime()),
				DateUtils.getDateFromLocalDateTime(baseTime));
		assertNotNull(output);
		assertEquals(HOUR_2_MIN_11, output);

	}

	@Test
	public void test4WeeksInterval() {

		LocalDateTime baseTime = getBaseTime();
		DateFilterDto testDate = DateFilterDto.builder().dateTime(baseTime.minusSeconds(2592000))
				.text("2592000 sec ago").build();

		String output = TimeIntervalUtils.getTimeInterval(DateUtils.getDateFromLocalDateTime(testDate.getDateTime()),
				DateUtils.getDateFromLocalDateTime(baseTime));
		
		assertNotNull(output);
		assertEquals(WEEKS_4, output);

	}

	@Test
	public void test1MonthInterval() {

		LocalDateTime baseTime = getBaseTime();
		DateFilterDto testDate = DateFilterDto.builder().dateTime(baseTime.minusSeconds(2678400))
				.text("2678400 sec ago").build();

		String output = TimeIntervalUtils.getTimeInterval(DateUtils.getDateFromLocalDateTime(testDate.getDateTime()),
				DateUtils.getDateFromLocalDateTime(baseTime));
		
		assertNotNull(output);
		assertEquals(MONTH_1, output);

	}

	@Test
	public void test1MonthInJan() {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String dateStr = "2021-01-01 13:43:27";
		LocalDateTime baseTime = LocalDateTime.parse(dateStr, formatter);

		DateFilterDto testDate = DateFilterDto.builder().dateTime(baseTime.minusSeconds(2678399))
				.text("2678399 sec ago").build();

		String output = TimeIntervalUtils.getTimeInterval(DateUtils.getDateFromLocalDateTime(testDate.getDateTime()),
				DateUtils.getDateFromLocalDateTime(baseTime));
		
		assertNotNull(output);
		assertEquals(WEEKS_4, output);

	}

	@Test
	public void test1MonthInFeb() {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String dateStr = "2021-02-01 13:43:27";
		LocalDateTime baseTime = LocalDateTime.parse(dateStr, formatter);

		DateFilterDto testDate = DateFilterDto.builder().dateTime(baseTime.minusSeconds(2678399))
				.text("2678399 sec ago").build();

		String output = TimeIntervalUtils.getTimeInterval(DateUtils.getDateFromLocalDateTime(testDate.getDateTime()),
				DateUtils.getDateFromLocalDateTime(baseTime));
		
		assertNotNull(output);
		assertEquals(WEEKS_4, output);

	}
	
	@Test
	public void test1MonthInMar() {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String dateStr = "2021-03-01 13:43:27";
		LocalDateTime baseTime = LocalDateTime.parse(dateStr, formatter);

		DateFilterDto testDate = DateFilterDto.builder().dateTime(baseTime.minusSeconds(2678399))
				.text("2678399 sec ago").build();

		String output = TimeIntervalUtils.getTimeInterval(DateUtils.getDateFromLocalDateTime(testDate.getDateTime()),
				DateUtils.getDateFromLocalDateTime(baseTime));
		
		assertNotNull(output);
		assertEquals(MONTH_1, output);

	}
	
	@Test
	public void test10MinutesInterval() {

		LocalDateTime baseTime = getBaseTime();
		DateFilterDto testDate = DateFilterDto.builder().dateTime(baseTime.minusMinutes(10)).text("10 min ago").build();

		String output = TimeIntervalUtils.getTimeInterval(DateUtils.getDateFromLocalDateTime(testDate.getDateTime()),
				DateUtils.getDateFromLocalDateTime(baseTime));
		
		assertNotNull(output);
		assertEquals(MINUTES_10, output);

	}
	
	
	@Test
	public void test60MinutesInterval() {

		LocalDateTime baseTime = getBaseTime();
		DateFilterDto testDate = DateFilterDto.builder().dateTime(baseTime.minusMinutes(60)).text("60 min ago").build();

		String output = TimeIntervalUtils.getTimeInterval(DateUtils.getDateFromLocalDateTime(testDate.getDateTime()),
				DateUtils.getDateFromLocalDateTime(baseTime));
		
		assertNotNull(output);
		assertEquals(HOUR_1, output);

	}
	
	@Test
	public void test120MinutesInterval() {

		LocalDateTime baseTime = getBaseTime();
		DateFilterDto testDate = DateFilterDto.builder().dateTime(baseTime.minusMinutes(120)).text("120 min ago").build();

		String output = TimeIntervalUtils.getTimeInterval(DateUtils.getDateFromLocalDateTime(testDate.getDateTime()),
				DateUtils.getDateFromLocalDateTime(baseTime));
		
		assertNotNull(output);
		assertEquals(HOURS_2, output);

	}
	
	@Test
	public void test135MinutesInterval() {

		LocalDateTime baseTime = getBaseTime();
		DateFilterDto testDate = DateFilterDto.builder().dateTime(baseTime.minusMinutes(135)).text("135 min ago").build();

		String output = TimeIntervalUtils.getTimeInterval(DateUtils.getDateFromLocalDateTime(testDate.getDateTime()),
				DateUtils.getDateFromLocalDateTime(baseTime));
		
		assertNotNull(output);
		assertEquals(HOUR_2_MIN_15, output);

	}
	
	@Test
	public void test1DayInterval() {

		LocalDateTime baseTime = getBaseTime();
		DateFilterDto testDate = DateFilterDto.builder().dateTime(baseTime.minusDays(1)).text("1 day ago").build();

		String output = TimeIntervalUtils.getTimeInterval(DateUtils.getDateFromLocalDateTime(testDate.getDateTime()),
				DateUtils.getDateFromLocalDateTime(baseTime));
		
		assertNotNull(output);
		assertEquals(DAY_1, output);

	}
	
	@Test
	public void test5DaysInterval() {

		LocalDateTime baseTime = getBaseTime();
		DateFilterDto testDate = DateFilterDto.builder().dateTime(baseTime.minusDays(5)).text("5 days ago").build();

		String output = TimeIntervalUtils.getTimeInterval(DateUtils.getDateFromLocalDateTime(testDate.getDateTime()),
				DateUtils.getDateFromLocalDateTime(baseTime));
		
		assertNotNull(output);
		assertEquals(DAYS_5, output);

	}
	
	@Test
	public void test1WeekInterval() {

		LocalDateTime baseTime = getBaseTime();
		DateFilterDto testDate = DateFilterDto.builder().dateTime(baseTime.minusDays(7)).text("1 week ago").build();

		String output = TimeIntervalUtils.getTimeInterval(DateUtils.getDateFromLocalDateTime(testDate.getDateTime()),
				DateUtils.getDateFromLocalDateTime(baseTime));
		
		assertNotNull(output);
		assertEquals(WEEK_1, output);

	}
	
	@Test
	public void test2WeeksInterval() {

		LocalDateTime baseTime = getBaseTime();
		DateFilterDto testDate = DateFilterDto.builder().dateTime(baseTime.minusDays(15)).text("2 weeks ago").build();
		String output = TimeIntervalUtils.getTimeInterval(DateUtils.getDateFromLocalDateTime(testDate.getDateTime()),
				DateUtils.getDateFromLocalDateTime(baseTime));
		
		assertNotNull(output);
		assertEquals(WEEKS_2, output);

	}
	
	@Test
	public void test2MonthInterval() {

		LocalDateTime baseTime = getBaseTime();
		DateFilterDto testDate = DateFilterDto.builder().dateTime(baseTime.minusDays(63)).text("2 month ago").build();
		String output = TimeIntervalUtils.getTimeInterval(DateUtils.getDateFromLocalDateTime(testDate.getDateTime()),
				DateUtils.getDateFromLocalDateTime(baseTime));
		
		assertNotNull(output);
		assertEquals(MONTHS_2, output);

	}
	
	@Test
	public void test3MonthInterval() {

		LocalDateTime baseTime = getBaseTime();
		DateFilterDto testDate = DateFilterDto.builder().dateTime(baseTime.minusMonths(3)).text("3 month ago").build();
		String output = TimeIntervalUtils.getTimeInterval(DateUtils.getDateFromLocalDateTime(testDate.getDateTime()),
				DateUtils.getDateFromLocalDateTime(baseTime));
		
		assertNotNull(output);
		assertEquals(MONTHS_3, output);

	}
	
	@Test
	public void test1YearInterval() {

		LocalDateTime baseTime = getBaseTime();
		DateFilterDto testDate = DateFilterDto.builder().dateTime(baseTime.minusMonths(12)).text("1 year ago").build();
		String output = TimeIntervalUtils.getTimeInterval(DateUtils.getDateFromLocalDateTime(testDate.getDateTime()),
				DateUtils.getDateFromLocalDateTime(baseTime));
		
		assertNotNull(output);
		assertEquals(YEAR_1, output);

	}
	
	@Test
	public void testYearMonthInterval() {

		LocalDateTime baseTime = getBaseTime();
		DateFilterDto testDate = DateFilterDto.builder().dateTime(baseTime.minusMonths(16)).text("1 year 4 months ago").build();
		String output = TimeIntervalUtils.getTimeInterval(DateUtils.getDateFromLocalDateTime(testDate.getDateTime()),
				DateUtils.getDateFromLocalDateTime(baseTime));
		
		assertNotNull(output);
		assertEquals(YEAR_1, output);

	}
	
	@Test
	public void test5YearsInterval() {

		LocalDateTime baseTime = getBaseTime();
		DateFilterDto testDate = DateFilterDto.builder().dateTime(baseTime.minusMonths(60)).text("5 years ago").build();
		String output = TimeIntervalUtils.getTimeInterval(DateUtils.getDateFromLocalDateTime(testDate.getDateTime()),
				DateUtils.getDateFromLocalDateTime(baseTime));
		
		assertNotNull(output);
		assertEquals(YEARS_5, output);

	}
	
	@Test
	public void test70MonthInterval() {

		LocalDateTime baseTime = getBaseTime();
		DateFilterDto testDate = DateFilterDto.builder().dateTime(baseTime.minusMonths(70)).text("5 years 10 months ago").build();
		String output = TimeIntervalUtils.getTimeInterval(DateUtils.getDateFromLocalDateTime(testDate.getDateTime()),
				DateUtils.getDateFromLocalDateTime(baseTime));
		
		assertNotNull(output);
		assertEquals(YEARS_5, output);

	}
	
	private LocalDateTime getBaseTime() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String dateStr = "2021-01-29 13:43:27";
		LocalDateTime baseTime = LocalDateTime.parse(dateStr, formatter);
		return baseTime;
	}
	
	@Data
	@Builder
	@AllArgsConstructor
	static class DateFilterDto {

		LocalDateTime dateTime;
		String text;
	}
}
