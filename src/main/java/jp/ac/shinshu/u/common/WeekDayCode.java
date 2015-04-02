/**
 *
 */
package jp.ac.shinshu.u.common;

/**
 * @author Osamu HASEGAWA
 *
 */
public enum WeekDayCode {

	WEEKDAY1("1", "月曜", 0),
	WEEKDAY2("2", "火曜", 1),
	WEEKDAY3("3", "水曜", 2),
	WEEKDAY4("4", "木曜", 3),
	WEEKDAY5("5", "金曜", 4),
	WEEKDAY6("6", "土曜", 5),
	WEEKDAY7("7", "日曜", 99),
	WEEKDAY8("8", "集中", 99),
	WEEKDAYZZ("ZZ", "時間割非表示", 100),
	;

	private final String code;
	private final String title;
	private final int weekDay;

	private WeekDayCode(final String code, final String title, final int weekDay) {
		this.code = code;
		this.title = title;
		this.weekDay = weekDay;
	}

	public String getCode() {
		return code;
	}

	public String getTitle() {
		return title;
	}
	
	public int getWeekDay() {
		return weekDay;
	}

}
