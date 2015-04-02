/**
 *
 */
package jp.ac.shinshu.u.ealps.portal.dao.adb2;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.ac.shinshu.u.common.JDBCUtility;
import jp.ac.shinshu.u.ealps.portal.bean.PortalInformationBean;
import jp.ac.shinshu.u.ealps.portal.entity.PortalInformation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>お知らせテーブル用のDAO</p>
 *
 * @version 1.0.0
 * @author Osamu HASEGAWA
 *
 */
public class PortalInformationDAO extends ADB2AbstractDAO {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * <p>デフォルトコンストラクタ．</p>
	 */
	public PortalInformationDAO() {
		super();
	}

	public List<PortalInformation> selectPortalInformationList() {
		List<PortalInformation> informationBeanList = new ArrayList<PortalInformation>();

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT a.id, a.title, a.body, a.importanceTypeID, a.addressTypeID, a.open, a.close, a.insert, a.update, a.delete, a.availability ");
		sql.append("FROM portal_information a ");
		sql.append("WHERE a.availability ");
//		sql.append("AND CURRENT_TIMESTAMP BETWEEN a.open AND a.close ");
//		sql.append("ORDER BY sorttime DESC ");
		ResultSet resultSet = find(sql.toString());

		try {
			PortalInformation portalInformation = null;
			while(resultSet.next()) {
				portalInformation = new PortalInformation();
				portalInformation.setId(resultSet.getInt("id"));
				portalInformation.setTitle(resultSet.getString("title"));
				portalInformation.setBody(resultSet.getString("body"));
				portalInformation.setImportanceTypeID(resultSet.getInt("importanceTypeID"));
				portalInformation.setAddressTypeID(resultSet.getInt("addressTypeID"));
				portalInformation.setOpen(resultSet.getTimestamp("open"));
				portalInformation.setClose(resultSet.getTimestamp("close"));
				portalInformation.setInsert(resultSet.getTimestamp("insert"));
				portalInformation.setUpdate(resultSet.getTimestamp("update"));
				portalInformation.setDelete(resultSet.getTimestamp("delete"));
				portalInformation.setAvailability(resultSet.getBoolean("availability"));

				informationBeanList.add(portalInformation);
			}
		} catch (SQLException e) {
			logger.error("SQLException", e);
			throw new RuntimeException(e);
		} finally {
			JDBCUtility.close(statement, resultSet);
		}

		return informationBeanList;
	}
	
	public List<PortalInformationBean> selectPortalInformationBeanList() {
		List<PortalInformationBean> portalInformationBeanList = new ArrayList<PortalInformationBean>();

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT a.id, a.title, a.body, a.importanceTypeID, b.text, b.css, a.addressTypeID, c.text, c.css, a.open, a.close, a.insert, a.update, a.delete, a.availability ");
		sql.append("FROM portal_information a, portal_importanceType b, portal_addressType c ");
		sql.append("WHERE a.availability ");
		sql.append("AND a.importanceTypeID = b.id ");
		sql.append("AND a.addressTypeID = c.id ");
//		sql.append("AND CURRENT_TIMESTAMP BETWEEN a.open AND a.close ");
//		sql.append("ORDER BY sorttime DESC ");
		ResultSet resultSet = find(sql.toString());

		try {
			PortalInformationBean portalInformationBean = null;
			while(resultSet.next()) {
				portalInformationBean = new PortalInformationBean();
				portalInformationBean.setId(resultSet.getInt("a.id"));
				portalInformationBean.setTitle(resultSet.getString("a.title"));
				portalInformationBean.setBody(resultSet.getString("a.body"));
				portalInformationBean.setImportanceTypeID(resultSet.getInt("a.importanceTypeID"));
				portalInformationBean.setImportanceText(resultSet.getString("b.text"));
				portalInformationBean.setImportanceCSS(resultSet.getString("b.css"));
				portalInformationBean.setAddressTypeID(resultSet.getInt("a.addressTypeID"));
				portalInformationBean.setAddressText(resultSet.getString("c.text"));
				portalInformationBean.setAddressCSS(resultSet.getString("c.css"));
				portalInformationBean.setOpen(resultSet.getTimestamp("a.open"));
				portalInformationBean.setClose(resultSet.getTimestamp("a.close"));
				portalInformationBean.setInsert(resultSet.getTimestamp("a.insert"));
				portalInformationBean.setUpdate(resultSet.getTimestamp("a.update"));
				portalInformationBean.setDelete(resultSet.getTimestamp("a.delete"));
				portalInformationBean.setAvailability(resultSet.getBoolean("a.availability"));

				portalInformationBeanList.add(portalInformationBean);
			}
		} catch (SQLException e) {
			logger.error("SQLException", e);
			throw new RuntimeException(e);
		} finally {
			JDBCUtility.close(statement, resultSet);
		}

		return portalInformationBeanList;
	}

}
