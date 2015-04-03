/**
 *
 */
package jp.ac.shinshu.u.common;

import java.sql.Connection;

import jp.ac.shinshu.u.common.definition.SystemDefinition;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @version 1.0.0
 * @author OsamuHASEGAWA
 *
 */
public class TransactionManager {

	private static Logger logger = LoggerFactory.getLogger(TransactionManager.class);

//	コネクション ThreadLocal管理
	private static final ThreadLocal<ConnectionManager> eALPSPortalConnectionManagerThreadLocal = new ThreadLocal<ConnectionManager>();

//	トランザクション ThreadLocal管理
	private static final ThreadLocal<Boolean> isBeginThreadLocal = new ThreadLocal<Boolean>();

//	トランザクション Exception ThreadLocal
	private static final ThreadLocal<Boolean> handlingExceptionThreadLocal = new ThreadLocal<Boolean>();

	private TransactionManager (String datasource) {

	}

	/**
	 * コネクションを取得します
	 * @return DBコネクションインスタンス
	 *
	 */
	public static Connection getConnection(String dataSource) {
		if (isBeginThreadLocal.get() == null) {
			throw new RuntimeException("トランザクションが開始されていません。");
		}
		ConnectionManager cm = getConnectionManager(dataSource);
		return cm.getConnection();
	}

	/**
	 * dataSourceに応じたコネクションマネージャーを返す
	 * @param dataSource
	 * @return データソースに応じたコネクションマネージャー
	 */
	private static ConnectionManager getConnectionManager(String dataSource) {
		ConnectionManager cm = null;
		if(StringUtils.equals(dataSource, SystemDefinition.ADB2_DATASOURCE_JNDI_CONTEXT.toString())) {
			cm = eALPSPortalConnectionManagerThreadLocal.get();
			if (cm == null) {
				cm = new ConnectionManager(dataSource);
				eALPSPortalConnectionManagerThreadLocal.set(cm);
			}
		}
		return cm;
	}

	/**
	 * トランザクション開始時に呼び出す処理
	 *
	 */
	private static void begin() {
		isBeginThreadLocal.set(true);
		handlingExceptionThreadLocal.set(false);
	}

	/**
	 * Exception のハンドリング用
	 * @param handlingException
	 */
	public static void handlingException(boolean handlingException) {
		handlingExceptionThreadLocal.set(handlingException);
	}

	/**
	 * トランザクション終了時に呼び出す処理
	 * ThreadLocalで保持している情報を破棄します
	 *
	 */
	private static void destory() {
		ConnectionManager cm = null;

		cm = eALPSPortalConnectionManagerThreadLocal.get();
		if (cm != null) {
			cm.destroy();
			eALPSPortalConnectionManagerThreadLocal.remove();
			logger.debug("eALPSPortalConnectionManagerThreadLocal Remove.");
		}

		isBeginThreadLocal.remove();
		handlingExceptionThreadLocal.remove();

		logger.debug("Transaction destory.");
	}

	/**
	 * 指定された処理をトランザクションとして実行する
	 * @param closure
	 */
	public static void executeTransaction(TransactionClosure closure) {
		TransactionManager.begin();
		try {
			closure.execute();
			if(handlingExceptionThreadLocal.get()) {
				TransactionManager.rollback();
			} else {
				TransactionManager.commit();
			}
		} catch (Throwable e) {
			TransactionManager.rollback();
			throw new RuntimeException(e);
		} finally {
			try {
				TransactionManager.close();
			} catch (Throwable e) {
				logger.error("transaction close error.", e);
			}
			TransactionManager.destory();
		}
	}


	/**
	 * コミット
	 *
	 */
	public static void commit() {
		ConnectionManager cm = null;

		cm = eALPSPortalConnectionManagerThreadLocal.get();
		if(cm != null) {
			cm.commit();
		}

		logger.debug("transaction commit.");
	}

	/**
	 * ロールバック
	 *
	 */
	public static void rollback() {
		ConnectionManager cm = null;

		cm = eALPSPortalConnectionManagerThreadLocal.get();
		if (cm != null) {
			cm.rollback();
		}

		logger.debug("transaction rollback.");
	}

	/**
	 * クローズ
	 *
	 */
	private static void close() {
		ConnectionManager cm = null;
		cm = eALPSPortalConnectionManagerThreadLocal.get();
		if (cm != null) {
			cm.close();
		}

		logger.debug("transaction close.");
	}

}
