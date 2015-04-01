/**
 * 
 */
package jp.ac.shinshu.u.ealps.portal.service;

import java.util.List;

import jp.ac.shinshu.u.ealps.portal.bean.PortalInformationBean;
import jp.ac.shinshu.u.ealps.portal.entity.PortalInformation;

import com.google.inject.ImplementedBy;

/**
 * @author Osamu HASEGAWA
 *
 */
@ImplementedBy(PortalInformationService.class)
public interface IPortalInformationService {
	
	/**
	 * <p>お知らせに表示する情報 {@link PortalInformation} を取得する．</p>
	 * 
	 * @return 成功 {@link PortalInformation} の {@link List}.
	 * 		   失敗 null.
	 */
	public List<PortalInformation> getPortalInformationList();
	
	/**
	 * <p>お知らせに表示する情報 {@link PortalInformationBean} を取得する．</p>
	 * 
	 * @return 成功 {@link PortalInformationBean} の {@link List}.
	 * 		   失敗 null.
	 */
	public List<PortalInformationBean> getPortalInformationBeanList();

}
