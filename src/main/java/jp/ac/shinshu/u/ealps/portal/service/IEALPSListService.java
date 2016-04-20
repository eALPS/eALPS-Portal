/**
 *
 */
package jp.ac.shinshu.u.ealps.portal.service;

import java.util.List;

import jp.ac.shinshu.u.ealps.portal.entity.SiteInfo;

import com.google.inject.ImplementedBy;

/**
 * @author Osamu HASEGAWA
 *
 */
@ImplementedBy(EALPSListService.class)
public interface IEALPSListService {

	/**
	 * <p>該当年度の学部サイトリスト取得</p>
	 * @param year
	 * @return List<EALPSListBean>
	 */
	public List<SiteInfo> getEALPSList(int year);

}
