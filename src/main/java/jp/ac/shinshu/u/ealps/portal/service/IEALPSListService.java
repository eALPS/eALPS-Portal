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
	 * @return List<SiteInfo>
	 */
	public List<SiteInfo> getEALPSSiteInfoList(int year);

	/**
	 * <p>siteUidからSiteInfoのエンティティを取得</p>
	 * @param siteUid
	 * @return SiteInfo
	 */
	public SiteInfo getEALPSSiteInfo(String siteUid);

	/**
	 * <p>年度共通サイトリスト取得</p>
	 * @return List<SiteInfo>
	 */
	public List<SiteInfo> getEALPSCommonSiteInfoList();

	/**
	 * <p>引数で指定した年度のechesのSiteInfoを取得</p>
	 * @param year
	 * @return SiteInfo
	 */
	public SiteInfo getEChesSiteInfo(int year);
}
