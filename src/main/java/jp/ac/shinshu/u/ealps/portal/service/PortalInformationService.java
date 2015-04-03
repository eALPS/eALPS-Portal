/**
 *
 */
package jp.ac.shinshu.u.ealps.portal.service;

import java.util.List;

import jp.ac.shinshu.u.ealps.portal.bean.PortalInformationBean;
import jp.ac.shinshu.u.ealps.portal.dao.adb2.PortalInformationDAO;
import jp.ac.shinshu.u.ealps.portal.entity.PortalInformation;

/**
 * <p>お知らせ関連実装クラス</p>
 * @version 1.0.0
 * @author Osamu HASEGAWA
 *
 */
public class PortalInformationService implements IPortalInformationService {

	@Override
	public List<PortalInformation> getPortalInformationList() {
		List<PortalInformation> portalInformationList;
		PortalInformationDAO portalInformationDAO = new PortalInformationDAO();
		portalInformationList = portalInformationDAO.selectPortalInformationList();
		return portalInformationList;
	}

	@Override
	public List<PortalInformationBean> getPortalInformationBeanList() {
		List<PortalInformationBean> portalInformationList;
		PortalInformationDAO portalInformationDAO = new PortalInformationDAO();
		portalInformationList = portalInformationDAO.selectPortalInformationBeanList();
		return portalInformationList;
	}

}
