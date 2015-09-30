/**
 *
 */
package jp.ac.shinshu.u.ealps.portal.service;

import java.util.ArrayList;
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

	@Override
	public int getScheduleInitYear() {
//		GregorianCalendar gcal = new GregorianCalendar();
//		int year = gcal.get(GregorianCalendar.YEAR);
//		int month = gcal.get(GregorianCalendar.MONTH);
//		int day = gcal.get(GregorianCalendar.DAY_OF_MONTH);
//
//		if (month < 4) {
//			if (month == 3 && day > 19) {
//				return year;
//			} else {
//				return year-1;
//			}
//		} else {
//			return year;
//		}
		return 2015;
	}

	@Override
	public List<Integer> getYearList(int year) {
		List<Integer> yearList = new ArrayList<Integer>();
//		GregorianCalendar gcal = new GregorianCalendar();
//		int year = gcal.get(GregorianCalendar.YEAR);
//		int month = gcal.get(GregorianCalendar.MONTH);
//		int day = gcal.get(GregorianCalendar.DAY_OF_MONTH);
//
//		if (month < 4) {
//			if (month == 3 && day > 19) {
//				return year;
//			} else {
//				return year-1;
//			}
//		} else {
//			return year;
//		}

		for (int i = 0; i < 5; i++) {
			yearList.add(year-i);
		}

		return yearList;
	}

}
