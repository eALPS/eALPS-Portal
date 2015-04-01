/**
 *
 */
package jp.ac.shinshu.u.ealps.portal.dao.adb2;

import jp.ac.shinshu.u.common.SystemDefinition;
import jp.ac.shinshu.u.common.dao.AbstractDAO;

/**
 * <p>ADB2にアクセスするDAOの基底クラスです</p>
 *
 * @version 1.0.0
 * @author Osamu HASEGAWA
 *
 */
public abstract class ADB2AbstractDAO extends AbstractDAO {

	private static final String datasource = SystemDefinition.ADB2_DATASOURCE_JNDI_CONTEXT.toString();

	public ADB2AbstractDAO() {
		super(datasource);
	}

}
