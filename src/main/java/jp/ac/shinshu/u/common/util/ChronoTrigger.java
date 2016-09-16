package jp.ac.shinshu.u.common.util;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by osamu on 2016/09/15.
 */
public class ChronoTrigger {

	/**
	 * 現在日時から学期を取得する
	 * @return 学期（前期 or 後期）
	 */
	public static String getSemester() {
		String semester = "";
		Calendar startFirstSemester = new GregorianCalendar(0,2,20);
		Calendar startSecondSemester = new GregorianCalendar(0,8,20);
		Calendar cal = (GregorianCalendar)Calendar.getInstance();
		cal.set(Calendar.YEAR,0);

		if(cal.before(startFirstSemester)) {
			semester = "secondSemester";
		} else if (cal.before(startSecondSemester)) {
			semester = "firstSemester";
		} else {
			semester = "secondSemester";
		}
		return semester;
	}
}
