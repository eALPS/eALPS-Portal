/**
 *
 */
package jp.ac.shinshu.u.common.definition;

/**
 * @author Osamu HASEGAWA
 *
 */
public enum HourCode {

	HOUR("", "不明", 99),
	HOUR1("1", "1時限", 0),
	HOUR11("11", "1時限～2時限前半", 0),
	HOUR12("12", "1時限後半〜2時限", 0),
	HOUR2("2", "2時限", 1),
	HOUR3("3", "3時限", 2),
	HOUR31("31", "3時限〜4時限前半", 2),
	HOUR32("32", "3時限後半〜4時限", 2),
	HOUR4("4", "4時限", 3),
	HOUR41("41", "4時限後半〜5時限", 3),
	HOUR42("42", "4時限〜5時限前半", 3),
	HOUR5("5", "5時限", 4),
	HOUR6("6", "6時限", 5),
	HOUR7("7", "7時限", 6),
	HOUR21("21", "2時限〜3時限前半", 1),
	HOUR51("51", "5時限〜5時限前半", 4),
	HOUR52("52", "5時限後半〜6時限", 4),
	HOURM1("M1", "1時限", 0),
	HOURM2("M2", "2時限", 1),
	HOURM3("M3", "3時限", 2),
	HOURM4("M4", "4時限", 3),
	HOURM5("M5", "5時限", 4),
	HOURM6("M6", "6時限", 5),
	HOUR93("93", "終日（9:00〜17:50）", 99),
	HOUR92("92", "午後（13:00〜17:50）", 99),
	HOUR91("91", "午前（9:00〜12:10）", 99),
	HOUR99("99", "不定期", 99),
	HOURZZ("ZZ", "時間割非表示", 100),
	;

	private final String code;
	private final String title;
	private final int hour;

	private HourCode(final String code, final String title, final int hour) {
		this.code = code;
		this.title = title;
		this.hour = hour;
	}

	public String getCode() {
		return code;
	}

	public String getTitle() {
		return title;
	}

	public int getHour() {
		return hour;
	}

}
