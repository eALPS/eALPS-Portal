/**
 *
 */
package jp.ac.shinshu.u.ealps.portal.service;

import java.util.List;

import com.google.inject.ImplementedBy;


/**
 * @author Osamu HASEGAWA
 *
 */
@ImplementedBy(UtilityService.class)
public interface IUtilityService {

	/**
	 * <p>初期表示用の年度を取得する．</p>
	 * <p>運用上の問題で3月22日以降は次年度とする．</p>
	 *
	 * @return	成功：int initYear
	 * 			失敗：0
	 */
	public int getScheduleInitYear();

	/**
	 * <p>現在の年から5年分の年リストを取得する．</p>
	 * <p>ただし，最小の年は2015とする</p>
	 *
	 * @return	成功：List<Integer> yearList
	 * 			失敗：空のリスト
	 */
	public List<Integer> getYearList();

}
