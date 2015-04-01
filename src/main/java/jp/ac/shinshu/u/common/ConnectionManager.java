package jp.ac.shinshu.u.common;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @since 1.0.0
 * @author OsamuHASEGAWA
 *
 */
public class ConnectionManager {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	private DataSource dataSource;
	private Connection connection;

	public ConnectionManager(String dataSource) {

		if (StringUtils.isEmpty(dataSource)) {
			throw new RuntimeException("datasource is empty. [" + dataSource + "]");
		}
		try {
			Context ctx = new InitialContext();
			this.dataSource = (DataSource)ctx.lookup(dataSource);
		} catch (NamingException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * コネクションを取得する
	 * @return Connection
	 */
	public Connection getConnection() {
		Connection con = connection;
		if (con == null) {
			try {
				con = dataSource.getConnection();
//				con = new EALPSPortalConnection(con);
				con.setAutoCommit(false);
				logger.debug("コネクションを取得しました　：　" + con);
			} catch (SQLException e) {
				logger.error(dataSource.toString());
				logger.error("コネクションの取得に失敗しました.", e);
				throw new RuntimeException(e);
			}
			connection = con;
		}
		return con;
	}

	/**
	 * コミット
	 *
	 */
	public void commit() {
		try {
			if (connection != null) {
				connection.commit();
				logger.info("connection commit. connection : " + connection + "  Datasource : " + dataSource.toString());
			}
		} catch (SQLException e) {
			logger.error("connection commit error. connection : " + connection + "  Datasource : " + dataSource.toString(), e);
		}
	}

	/**
	 * ロールバック
	 *
	 */
	public void rollback() {
		try {
			if (connection != null) {
				connection.rollback();
				logger.info("connection rollback. connection : " + connection + "  Datasource : " + dataSource.toString());
			}
		} catch (SQLException e) {
			logger.error("connection rollback error. connection=" + connection + "  Datasource : " + dataSource.toString(), e);
		}
	}

	/**
	 * クローズ
	 *
	 */
	public void close() {
		try {
			if (connection != null) {
				connection.close();
				logger.info("connection close. connection : " + connection + "  Datasource : " + dataSource.toString());
			}
		} catch (SQLException e) {
			logger.error("connection close error. connection : " + connection + "  Datasource : " + dataSource.toString(), e);
		}
	}

	/**
	 * コネクションの破棄
	 *
	 */
	public void destroy() {
		if (connection != null) {
			connection = null;
		}
	}

}
