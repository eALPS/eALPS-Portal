/**
 *
 */
package jp.ac.shinshu.u.ealps.portal.service;

import java.util.List;

import jp.ac.shinshu.u.ealps.portal.dao.adb2.SiteInfoDAO;
import jp.ac.shinshu.u.ealps.portal.entity.SiteInfo;

/**
 * @author Osamu HASEGAWA
 *
 */
public class EALPSListService implements IEALPSListService {

	@Override
	public List<SiteInfo> getEALPSSiteInfoList(int year) {
		SiteInfoDAO siteInfoDAO = new SiteInfoDAO();
		List<SiteInfo> siteInfoList = siteInfoDAO.selectSiteInfoList(year);
		return siteInfoList;
	}

	@Override
	public SiteInfo getEALPSSiteInfo(String siteUid) {
		SiteInfoDAO siteInfoDAO = new SiteInfoDAO();
		return siteInfoDAO.selectSiteInfo(siteUid);
	}

	@Override
	public List<SiteInfo> getEALPSCommonSiteInfoList() {
		SiteInfoDAO siteInfoDAO = new SiteInfoDAO();
		List<SiteInfo> siteInfoList = siteInfoDAO.selectCommonSiteInfoList();
		return siteInfoList;
	}


}
