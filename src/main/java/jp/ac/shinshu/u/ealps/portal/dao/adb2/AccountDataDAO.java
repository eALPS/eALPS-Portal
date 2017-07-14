package jp.ac.shinshu.u.ealps.portal.dao.adb2;

import jp.ac.shinshu.u.common.JDBCUtility;
import jp.ac.shinshu.u.ealps.portal.entity.AccountData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * <p>アカウントデータテーブル用のDAO</p>
 *
 * @version 1.0.0
 * @author Osamu HASEGAWA
 */
public class AccountDataDAO extends ADB2AbstractDAO {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * <p>デフォルトコンストラクタ．</p>
	 */
	public AccountDataDAO() {
		super();
	}

	public AccountData selectAccountData(String userId) {

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT a.rowId, a.uid, a.id, a.uidNumber, a.password, a.affili, a.name, a.firstName, a.lastName, a.mail, a.subMail, a.facultyNumber, a.enable, a.updateDate, a.dataFrom, a.memo ");
		sql.append("FROM accountData a ");
		sql.append("WHERE a.uid = ? ");

		ResultSet resultSet = find(sql.toString(), userId);

		AccountData accountData = null;

		try {
			accountData = new AccountData();
			if (resultSet.next()) {
				accountData.setRowId(resultSet.getInt("rowId"));
				accountData.setUid(resultSet.getString("uid"));
				accountData.setId(resultSet.getString("id"));
				accountData.setUidNumber(resultSet.getString("uidNumber"));
				accountData.setPassword(resultSet.getString("password"));
				accountData.setAffili(resultSet.getString("affili"));
				accountData.setName(resultSet.getString("name"));
				accountData.setFirstName(resultSet.getString("firstName"));
				accountData.setLastName(resultSet.getString("lastName"));
				accountData.setMail(resultSet.getString("mail"));
				accountData.setSubMail(resultSet.getString("subMail"));
				accountData.setFacultyNumber(resultSet.getString("facultyNumber"));
				accountData.setEnable(resultSet.getInt("enable"));
				accountData.setUpdateDate(resultSet.getTimestamp("updateDate"));
				accountData.setDataFrom(resultSet.getInt("dataFrom"));
				accountData.setMemo(resultSet.getString("memo"));
			}
		} catch (SQLException e) {
			logger.error("SQLException", e);
			throw new RuntimeException(e);
		} finally {
			JDBCUtility.close(statement, resultSet);
		}

		return accountData;
	}

}
