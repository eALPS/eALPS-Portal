/**
 *
 */
package jp.ac.shinshu.u.common;

/**
 * @author Osamu HASEGAWA
 *
 */
public enum LecCode {

	LEC00("00", "通年"),
	LEC01("01", "通年（集中）"),
	LEC10("10", "前期"),
	LEC11("11", "前期（前半）"),
	LEC12("12", "前期（後半）"),
	LEC13("13", "集中（夏期）"),
	LEC15("15", "前期（集中）"),
	LEC20("20", "後期"),
	LEC21("21", "後期（前半）"),
	LEC22("22", "後期（後半）"),
	LEC23("23", "集中（冬期）"),
	LEC24("24", "集中（春期）"),
	LEC25("25", "後期（集中）"),
	LEC99("99", "不定期"),
	LEC16("16", "前期（隔週A）"),
	LEC17("17", "前期（隔週B）"),
	LEC26("26", "後期（隔週A）"),
	LEC27("27", "後期（隔週B）"),
	LEC19("19", "前期（臨時）"),
	LEC29("29", "後期（臨時）"),
	LECJ1("J1", "1学期"),
	LECJ2("J2", "2学期"),
	LECJ3("J3", "3学期"),
	LECZZ("ZZ", "時間割非表示"),
	;

	private final String code;
	private final String title;

	private LecCode(final String code, final String title) {
		this.code = code;
		this.title = title;
	}

	public String getCode() {
		return code;
	}

	public String getTitle() {
		return title;
	}

}
