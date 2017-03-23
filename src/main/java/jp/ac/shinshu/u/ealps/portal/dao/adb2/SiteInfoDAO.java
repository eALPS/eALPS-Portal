/**
 *
 */
package jp.ac.shinshu.u.ealps.portal.dao.adb2;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.ac.shinshu.u.common.JDBCUtility;
import jp.ac.shinshu.u.ealps.portal.entity.SiteInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>お知らせテーブル用のDAO</p>
 *
 * @version 1.0.0
 * @author Osamu HASEGAWA
 *
 */
public class SiteInfoDAO extends ADB2AbstractDAO {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * <p>デフォルトコンストラクタ．</p>
	 */
	public SiteInfoDAO() {
		super();
	}

	public List<SiteInfo> selectSiteInfoList(int year) {
		List<SiteInfo> siteInfoList = new ArrayList<SiteInfo>();

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT a.siteInfoId, a.siteUid, a.opYear, a.dispOrder, a.visible, a.caption, a.domainName, a.token, a.commonSite ");
		sql.append("FROM siteInfo a ");
		sql.append("WHERE a.opYear = ? ");
		sql.append("AND a.dispOrder IS NOT NULL ");
		sql.append("AND a.commonSite != 1 ");
		sql.append("ORDER BY a.dispOrder");
//		sql.append("AND CURRENT_TIMESTAMP BETWEEN a.open AND a.close ");

		ResultSet resultSet = find(sql.toString(), year);

		try {
			SiteInfo siteInfo = null;
			while(resultSet.next()) {
				siteInfo = new SiteInfo();
				siteInfo.setSiteInfoId(resultSet.getInt("siteInfoId"));
				siteInfo.setSiteUId(resultSet.getString("siteUid"));
				siteInfo.setOpYear(resultSet.getInt("opYear"));
				siteInfo.setDispOrder(resultSet.getInt("dispOrder"));
				siteInfo.setVisible(resultSet.getInt("visible"));
				siteInfo.setCaption(resultSet.getString("caption"));
				siteInfo.setDomainName(resultSet.getString("domainName"));
				siteInfo.setToken(resultSet.getString("token"));
				siteInfo.setCommonSite(resultSet.getInt("commonSite"));

				siteInfoList.add(siteInfo);
			}
		} catch (SQLException e) {
			logger.error("SQLException", e);
			throw new RuntimeException(e);
		} finally {
			JDBCUtility.close(statement, resultSet);
		}

		return siteInfoList;
	}

	public SiteInfo selectSiteInfo(String siteUid) {
		SiteInfo siteInfo = null;

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT a.siteInfoId, a.siteUid, a.opYear, a.dispOrder, a.visible, a.caption, a.domainName, a.token, a.commonSite ");
		sql.append("FROM siteInfo a ");
		sql.append("WHERE a.siteUid = ? ");

		ResultSet resultSet = find(sql.toString(), siteUid);

		try {
			if (resultSet.next()) {
				siteInfo = new SiteInfo();
				siteInfo.setSiteInfoId(resultSet.getInt("siteInfoId"));
				siteInfo.setSiteUId(resultSet.getString("siteUid"));
				siteInfo.setOpYear(resultSet.getInt("opYear"));
				siteInfo.setDispOrder(resultSet.getInt("dispOrder"));
				siteInfo.setVisible(resultSet.getInt("visible"));
				siteInfo.setCaption(resultSet.getString("caption"));
				siteInfo.setDomainName(resultSet.getString("domainName"));
				siteInfo.setToken(resultSet.getString("token"));
				siteInfo.setCommonSite(resultSet.getInt("commonSite"));
			}
		} catch (SQLException e) {
			logger.error("SQLException", e);
			throw new RuntimeException(e);
		} finally {
			JDBCUtility.close(statement, resultSet);
		}

		return siteInfo;
	}

	public List<SiteInfo> selectCommonSiteInfoList() {
		List<SiteInfo> siteInfoList = new ArrayList<SiteInfo>();

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT a.siteInfoId, a.siteUid, a.opYear, a.dispOrder, a.visible, a.caption, a.domainName, a.token, a.commonSite ");
		sql.append("FROM siteInfo a ");
		sql.append("WHERE a.commonSite = 1 ");
		sql.append("AND a.dispOrder IS NOT NULL ");
		sql.append("ORDER BY a.dispOrder");

		ResultSet resultSet = find(sql.toString());

		try {
			SiteInfo siteInfo = null;
			while(resultSet.next()) {
				siteInfo = new SiteInfo();
				siteInfo.setSiteInfoId(resultSet.getInt("siteInfoId"));
				siteInfo.setSiteUId(resultSet.getString("siteUid"));
				siteInfo.setOpYear(resultSet.getInt("opYear"));
				siteInfo.setDispOrder(resultSet.getInt("dispOrder"));
				siteInfo.setVisible(resultSet.getInt("visible"));
				siteInfo.setCaption(resultSet.getString("caption"));
				siteInfo.setDomainName(resultSet.getString("domainName"));
				siteInfo.setToken(resultSet.getString("token"));
				siteInfo.setCommonSite(resultSet.getInt("commonSite"));

				siteInfoList.add(siteInfo);
			}
		} catch (SQLException e) {
			logger.error("SQLException", e);
			throw new RuntimeException(e);
		} finally {
			JDBCUtility.close(statement, resultSet);
		}

		return siteInfoList;
	}

	public SiteInfo selectEChesSiteInfo(int year) {
		SiteInfo siteInfo = null;

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT a.siteInfoId, a.siteUid, a.opYear, a.dispOrder, a.visible, a.caption, a.domainName, a.token, a.commonSite ");
		sql.append("FROM siteInfo a ");
		sql.append("WHERE a.siteUid like '%eChes' ");
		sql.append("AND a.opYear = ? ");

		ResultSet resultSet = find(sql.toString(), year);

		try {
			if (resultSet.next()) {
				siteInfo = new SiteInfo();
				siteInfo.setSiteInfoId(resultSet.getInt("siteInfoId"));
				siteInfo.setSiteUId(resultSet.getString("siteUid"));
				siteInfo.setOpYear(resultSet.getInt("opYear"));
				siteInfo.setDispOrder(resultSet.getInt("dispOrder"));
				siteInfo.setVisible(resultSet.getInt("visible"));
				siteInfo.setCaption(resultSet.getString("caption"));
				siteInfo.setDomainName(resultSet.getString("domainName"));
				siteInfo.setToken(resultSet.getString("token"));
				siteInfo.setCommonSite(resultSet.getInt("commonSite"));
			}
		} catch (SQLException e) {
			logger.error("SQLException", e);
			throw new RuntimeException(e);
		} finally {
			JDBCUtility.close(statement, resultSet);
		}

		return siteInfo;
	}
}
