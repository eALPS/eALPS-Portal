/**
 *
 */
package jp.ac.shinshu.u.common;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @version 1.0.0
 * @author OsamuHASEGAWA
 */
public class JDBCUtility {

private static final Logger LOG = LoggerFactory.getLogger(JDBCUtility.class);

	/**
	 * @param statement
	 * @throws SQLException
	 */
	public static void closeStatement(Statement statement) throws SQLException {
		if (statement != null) {
			statement.close();
			statement = null;
		}

		LOG.debug("Close Statement");
	}

	/**
	 * @param resultSet
	 * @throws SQLException
	 */
	public static void closeResultSet(ResultSet resultSet) throws SQLException {
		if (resultSet != null) {
			resultSet.close();
			resultSet = null;
		}

		LOG.debug("Close ResultSet");
	}

	/**
	 * @param statement
	 */
	public static void close(Statement statement) {
		try {
			closeStatement(statement);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * @param resultSet
	 */
	public static void close(ResultSet resultSet) {
		try {
			closeResultSet(resultSet);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * @param statement
	 * @param resultSet
	 */
	public static void close(Statement statement, ResultSet resultSet) {
		try {
			closeResultSet(resultSet);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		try {
			closeStatement(statement);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
