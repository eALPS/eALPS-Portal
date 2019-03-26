/**
 *
 */
package jp.ac.shinshu.u.ealps.portal.service;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * @author Osamu HASEGAWA
 *
 */
public class UtilityService implements IUtilityService {

	/**
	 * <p>デフォルトコンストラクタ</p>
	 */
	public UtilityService() {

	}

	/**
	 * 年度切り替え日以前か？
	 *
	 *
	 * @return 新年度に表示を切り替える以前であれば true (ex 2月1日とか)
	 */
	private boolean isBeforeNewSchoolYear(int month, int day) {
		// month は 0 から
		if ( (month < 2) || (month == 2 && day < 20) ) {
			return true;
		} else {
			return false;
		}

	};


	@Override
	public int getScheduleInitYear() {
		GregorianCalendar gcal = new GregorianCalendar();
		int year = gcal.get(GregorianCalendar.YEAR);
		int month = gcal.get(GregorianCalendar.MONTH);
		int day = gcal.get(GregorianCalendar.DAY_OF_MONTH);

		if ( isBeforeNewSchoolYear(month, day) ) {
			return year-1;
		} else {
			return year;
		}
	}


	@Override
	public List<Integer> getYearList() {
		List<Integer> yearList = new ArrayList<Integer>();
		GregorianCalendar gcal = new GregorianCalendar();
		int year = gcal.get(GregorianCalendar.YEAR);
		int month = gcal.get(GregorianCalendar.MONTH);
		int day = gcal.get(GregorianCalendar.DAY_OF_MONTH);

		// Yearリストの表示の長さ　デフォルトは 3
		int showYearLength = 3;
		//  年度切り替え前 は長さ 4
		if ( isBeforeNewSchoolYear(month, day) ) {
			showYearLength = 4;
		}

		for (int i = 0; i < showYearLength; i++) {
			yearList.add(year-i);
		}

		return yearList;
	}

}
