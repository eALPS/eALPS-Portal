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
	 * 
	 * <p>初期表示用の年度を取得する．</p>
	 * <p>運用上の問題で3月20日以降は次年度とする．</p>
	 * 
	 * @return	成功：int initYear
	 * 			失敗：0
	 */
	public int getScheduleInitYear();
	
	
	public List<Integer> getYearList(int year);

}
