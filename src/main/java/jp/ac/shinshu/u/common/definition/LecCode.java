/**
 *
 */
package jp.ac.shinshu.u.common.definition;

/**
 * @author Osamu HASEGAWA
 *
 */
public enum LecCode {

	LEC("", "不明", 99),
	LEC00("00", "通年", 0),
	LEC01("01", "通年（集中）", 0),
	LEC10("10", "前期", 1),
	LEC11("11", "前期（前半）", 1),
	LEC12("12", "前期（後半）", 1),
	LEC15("15", "前期（集中）", 1),
	LEC16("16", "前期（隔週A）", 1),
	LEC17("17", "前期（隔週B）", 1),
	LEC19("19", "前期（臨時）", 1),
	LEC20("20", "後期", 2),
	LEC21("21", "後期（前半）", 2),
	LEC22("22", "後期（後半）", 2),
	LEC25("25", "後期（集中）", 2),
	LEC26("26", "後期（隔週A）", 2),
	LEC27("27", "後期（隔週B）", 2),
	LEC29("29", "後期（臨時）", 2),
	LEC24("24", "集中（春期）", 3),
	LEC13("13", "集中（夏期）", 3),
	LEC23("23", "集中（冬期）", 3),
	LECJ1("J1", "1学期", 4),
	LECJ2("J2", "2学期", 5),
	LECJ3("J3", "3学期", 6),
	LEC99("99", "不定期", 99),
	LECZZ("ZZ", "時間割非表示", 100),
	;

	private final String code;
	private final String title;
	private final int lec;

	private LecCode(final String code, final String title, final int lec) {
		this.code = code;
		this.title = title;
		this.lec = lec;
	}

	public String getCode() {
		return code;
	}

	public String getTitle() {
		return title;
	}

	public int getLec() {
		return lec;
	}

	public String getLecClass() {
		if(lec == 0) {
			return "throughSemester";
		} else if (lec == 1) {
			return "firstSemester";
		} else if (lec == 2) {
			return "secondSemester";
		} else {
			return "irregularSemester";
		}
	}

}
