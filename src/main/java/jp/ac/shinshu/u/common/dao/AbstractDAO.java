/**
 *
 */
package jp.ac.shinshu.u.common.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import jp.ac.shinshu.u.common.ConnectionManager;
import jp.ac.shinshu.u.common.TransactionManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>DAOの基底抽象クラスです．</p>
 *
 * @version 1.0.0
 * @author OsamuHASEGAWA
 *
 */
public abstract class AbstractDAO {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private String dataSource;
	private Connection connection = null;
	protected PreparedStatement statement = null;
	//protected ResultSet resultSet = null;

	/**
	 * コンストラクタ
	 * 引数のdataSourceをローカルのdataSourceに保持します．
	 * @param datasource
	 */
	public AbstractDAO(String dataSource) {
		this.dataSource = dataSource;
	}

	/**
	 * 引数のObjectの配列中のデータをstatementにセットします．
	 * @param params
	 * @throws SQLException
	 */
	private void fillStatement(Object... params) throws SQLException {
		if (params == null) {
			return;
		}
		for (int i = 0; i < params.length; i++) {
			if (params[i] != null) {
				statement.setObject(i + 1, params[i]);
			} else {
				statement.setNull(i + 1, Types.OTHER);
			}
		}
		return;
	}

	/**
	 * 引数のsqlを実行し，結果セットを返します．
	 * @param sql
	 * @return 結果セット
	 * @see AbstractDAO#findThrowSQLException(String, Object...)
	 */
	protected ResultSet find(String sql) {
		try {
			return findThrowSQLException(sql, (Object[])null);
		} catch (SQLException e) {
			logger.error("SQLException : ", e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * 引数のsqlに引数のデータをセットして実行し，結果セットを返します．
	 * @param sql
	 * @param params （null許可）
	 * @return 結果セット
	 * @see AbstractDAO#findThrowSQLException(String, Object...)
	 */
	protected ResultSet find(String sql, Object... params) {
		try {
			return findThrowSQLException(sql, params);
		} catch (SQLException e) {
			logger.error("SQLException : ", e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * 引数のsqlに引数のObject配列中のデータをセットしてデータベース照会文を実行し，結果セットを返します．
	 * @param sql 実行する照会文
	 * @param params 照会文にセットするパラメータ（null許可）
	 * @return 結果セット
	 * @throws SQLException
	 */
	private ResultSet findThrowSQLException(String sql, Object... params) throws SQLException{
		connection = TransactionManager.getConnection(dataSource);
//		statement = connection.prepareStatement(sql);
		statement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		fillStatement(params);
		ResultSet resultSet = statement.executeQuery();

		if (logger.isDebugEnabled()) {
			int row = 0;
			if (resultSet instanceof List<?>) {
				row = ((List<?>) resultSet).size();
			} else {
				row = resultSet == null ? 0 : 1;
			}
			logger.debug("検索結果=[" + row + "]件");
		}
		return resultSet;
	}

	/**
	 * 引数のsqlのデータベース更新文を実行し，更新件数を返します．
	 * @param sql 実行する更新文
	 * @return 更新件数
	 * @see AbstractDAO#updateThrowSQLException(String, Object...)
	 */
	public int update(String sql) {
		try {
			return updateThrowSQLException(true, sql, (Object[])null);
		} catch (SQLException e) {
			logger.error("SQLExceprion : ", e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * 引数のsqlに引数のObject配列中のデータをセットしてデータベース更新文を実行し，更新件数を返します．
	 * @param sql 実行する更新文
	 * @param params 更新文にセットするパラメータ（null許可）
	 * @return 更新件数
	 * @see AbstractDAO#updateThrowSQLException(String, Object...)
	 */
	public int update(String sql, Object... params) {
		try {
			return updateThrowSQLException(true, sql, params);
		} catch (SQLException e) {
			logger.error("SQLExceprion : ", e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * 無断使用厳禁
	 * コネクション周りはTransactionFilterで制御しているので，基本的にこのメソッドは利用しない．
	 * 引数のsqlに引数のObject配列中のデータをセットしてデータベース更新文を実行し，更新件数を返します．
	 * @param transaction トランザクション管理
	 * @param sql 実行する更新文
	 * @param params 更新文にセットするパラメータ（null許可）
	 * @return 更新件数
	 * @see AbstractDAO#updateThrowSQLException(String, Object...)
	 */
	public int update(boolean transaction, String sql, Object... params) {
		try {
			return updateThrowSQLException(transaction, sql, params);
		} catch (SQLException e) {
			logger.error("SQLExceprion : ", e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * 引数のsqlに引数のObject配列中のデータをセットしてデータベース更新文を実行し，更新件数を返します．
	 * @param sql 実行する更新文
	 * @param params 更新文にセットするパラメータ（null許可）
	 * @return 更新件数
	 * @throws SQLException
	 */
	public int updateThrowSQLException(boolean transaction, String sql, Object... params) throws SQLException {

		ConnectionManager cm = null;
		int result = 0;

		if(transaction) {
			connection = TransactionManager.getConnection(dataSource);
			statement = connection.prepareStatement(sql);
			fillStatement(params);
			result = statement.executeUpdate();
			logger.debug("更新件数=[" + result + "]件");
		} else {
			cm = new ConnectionManager(dataSource);
			connection = cm.getConnection();
			try {
				statement = connection.prepareStatement(sql);
				fillStatement(params);
				result = statement.executeUpdate();
				logger.debug("更新件数=[" + result + "]件");
				cm.commit();
			} catch (Throwable e) {
				cm.rollback();
				logger.error("transaction rollback cause.", e);
				throw new RuntimeException(e);
			} finally {
				try {
					cm.close();
				} catch (Throwable e) {
					logger.error("transaction close error.", e);
				}
				connection = null;
			}
		}

		return result;
	}
}
