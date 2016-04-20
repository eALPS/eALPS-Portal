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
	public List<SiteInfo> getEALPSList(int year) {
		SiteInfoDAO siteInfoDAO = new SiteInfoDAO();
		List<SiteInfo> eALPSListBean = siteInfoDAO.selectSiteInfoList(year);
		return eALPSListBean;
	}

}
