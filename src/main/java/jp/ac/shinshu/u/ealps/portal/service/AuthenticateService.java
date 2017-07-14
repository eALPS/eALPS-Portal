/**
 *
 */
package jp.ac.shinshu.u.ealps.portal.service;

import jp.ac.shinshu.u.ealps.portal.dao.adb2.AccountDataDAO;
import jp.ac.shinshu.u.ealps.portal.entity.AccountData;
import org.apache.commons.lang3.StringUtils;

/**
 * @author Osamu HASEGAWA
 *
 */
public class AuthenticateService implements IAuthenticateService {

	@Override
	public boolean signIn(String userId, String password) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

	@Override
	public String[] setRoles(String userId) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public AccountData getAccountData(String userId) {
		AccountDataDAO accountDataDAO = new AccountDataDAO();
		return accountDataDAO.selectAccountData(userId);
	}
}
