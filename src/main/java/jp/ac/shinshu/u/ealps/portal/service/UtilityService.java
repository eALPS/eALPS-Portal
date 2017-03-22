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

	@Override
	public int getScheduleInitYear() {
		GregorianCalendar gcal = new GregorianCalendar();
		int year = gcal.get(GregorianCalendar.YEAR);
		int month = gcal.get(GregorianCalendar.MONTH);
		int day = gcal.get(GregorianCalendar.DAY_OF_MONTH);

		if (month <= 2) {
			if (month == 2 && day >= 22) {
				return year;
			} else {
				return year-1;
			}
		} else {
			return year;
		}
	}

	@Override
	public List<Integer> getYearList() {
		List<Integer> yearList = new ArrayList<Integer>();
		GregorianCalendar gcal = new GregorianCalendar();
		int year = gcal.get(GregorianCalendar.YEAR);

		for (int i = 0; i < 5; i++) {
			yearList.add(year-i);
			if(year-i <= 2015 ) {
				break;
			}
		}

		return yearList;
	}

}
