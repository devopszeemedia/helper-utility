package com.zshop.helper.utils;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * Utility to drive timestamp gaps for different activities like show-comments,
 * vlog comments
 * 
 * @author shirshsinha
 *
 */
public enum TimeIntervalUtils {

	SECONDS {
		@Override
		public DateFilterDto getDateFilterDto() {
			return new DateFilterDto(baseTime, baseTime.minusSeconds(60));
		}

		@Override
		public String calculateInterval(LocalDateTime dateToCompare) {

			return ChronoUnit.SECONDS.between(dateToCompare, baseTime) + "$second";
		}
	},
	MINUTES {
		@Override
		public DateFilterDto getDateFilterDto() {
			return new DateFilterDto(baseTime.minusMinutes(1), baseTime.minusMinutes(60));
		}

		@Override
		public String calculateInterval(LocalDateTime dateToCompare) {

			return ChronoUnit.MINUTES.between(dateToCompare, baseTime) + "$minute";
		}
	},

	HOURS {
		@Override
		public DateFilterDto getDateFilterDto() {
			return new DateFilterDto(baseTime.minusHours(1), baseTime.minusHours(24));
		}

		@Override
		public String calculateInterval(LocalDateTime dateToCompare) {

			long minutes = ChronoUnit.MINUTES.between(dateToCompare, baseTime);
			return (minutes / 60) + "$hour" + appendSToInterval(getFractionalPart((minutes % 60), "minute"));
		}
	},

	DAYS {
		@Override
		public DateFilterDto getDateFilterDto() {
			return new DateFilterDto(baseTime.minusDays(1), baseTime.minusDays(7));
		}

		@Override
		public String calculateInterval(LocalDateTime dateToCompare) {

			return ChronoUnit.DAYS.between(dateToCompare, baseTime) + "$day";
		}
	},

	WEEK {

		@Override
		public DateFilterDto getDateFilterDto() {
			return new DateFilterDto(baseTime.minusDays(7),
					baseTime.minusDays(baseTime.minusMonths(1).toLocalDate().lengthOfMonth()));
		}

		@Override
		public String calculateInterval(LocalDateTime dateToCompare) {

			long weeks = ChronoUnit.WEEKS.between(dateToCompare, baseTime);
			return weeks + "$week";
		}

	},

	MONTHS {

		@Override
		public DateFilterDto getDateFilterDto() {
			return new DateFilterDto(baseTime.minusDays(baseTime.toLocalDate().lengthOfMonth()).withSecond(0),
					baseTime.minusDays(baseTime.minusYears(1).toLocalDate().lengthOfYear()).withSecond(0));
		}

		@Override
		public String calculateInterval(LocalDateTime dateToCompare) {

			return ChronoUnit.MONTHS.between(dateToCompare, baseTime) + "$month";
		}
	},
	YEARS {

		@Override
		public DateFilterDto getDateFilterDto() {
			return new DateFilterDto(null, null);
		}

		@Override
		public String calculateInterval(LocalDateTime dateToCompare) {

			long years = ChronoUnit.YEARS.between(dateToCompare, baseTime);
			return years + "$year";
		}
	};

	private static LocalDateTime baseTime;

	public abstract DateFilterDto getDateFilterDto();

	public abstract String calculateInterval(LocalDateTime dateToCompare);

	private static String getInterval(LocalDateTime actionDateTime) {

		for (TimeIntervalUtils currentVal : TimeIntervalUtils.values()) {

			DateFilterDto dateFilterDto = currentVal.getDateFilterDto();

			if (currentVal == TimeIntervalUtils.MONTHS) {
				actionDateTime = actionDateTime.withSecond(0);
			}
			if (dateFilterDto.getEndDateRange() != null && dateFilterDto.getStartDateRange() != null
					&& (actionDateTime.isBefore(dateFilterDto.startDateRange)
							|| actionDateTime.isEqual(dateFilterDto.startDateRange))
					&& (actionDateTime.isAfter(dateFilterDto.endDateRange))) {
				return currentVal.calculateInterval(actionDateTime);
			}
		}
		return TimeIntervalUtils.YEARS.calculateInterval(actionDateTime);
	}

	/**
	 * This method needs to be called via client
	 * 
	 * @param actionDateTime  : denotes time to be compared
	 * @param requestDateTime : handle to configure base time
	 * @return
	 */
	public static String getTimeInterval(Date actionDateTime, Date requestDateTime) {

		baseTime = DateUtils.localDateTimeFromDate(requestDateTime);
		String interval = getInterval(DateUtils.localDateTimeFromDate(actionDateTime));

		return appendSToInterval(interval);
	}

	private static String appendSToInterval(String interval) {
		String[] dateArr = interval.split("\\$");

		if (dateArr.length > 1) {

			StringBuilder output = new StringBuilder(dateArr[0]).append(" ").append(dateArr[1]);
			if (Long.parseLong(dateArr[0].trim()) > 1) {
				output.append("s");
			}

			return output.toString().endsWith("ss") ? output.toString().trim().replace("ss", "s") : output.toString();
		}
		return interval;
	}

	@Data
	@Builder
	@AllArgsConstructor
	static class DateFilterDto {

		LocalDateTime startDateRange;
		LocalDateTime endDateRange;

	}

	public String getFractionalPart(long fractionalPart, String strForm) {

		return fractionalPart == 0l ? "" : (" " + fractionalPart + "$" + strForm);
	}

}
