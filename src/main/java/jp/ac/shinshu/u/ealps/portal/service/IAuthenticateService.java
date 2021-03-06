/**
 *
 */
package jp.ac.shinshu.u.ealps.portal.service;

import com.google.inject.ImplementedBy;
import jp.ac.shinshu.u.ealps.portal.entity.AccountData;

/**
 * @author Osamu HASEGAWA
 *
 */
@ImplementedBy(AuthenticateService.class)
public interface IAuthenticateService {

	/**
	 * <p>サインイン処理</p>
	 * @param userId
	 * @param password
	 * @return true：許可<br />false：不許可
	 */
	public boolean signIn(String userId, String password);

	/**
	 * <p>ページ閲覧権限をセットする</p>
	 * @param userId
	 * @return セットするロールの配列
	 */
	public String[] setRoles(String userId);

	/**
	 * <p>ユーザIDを基にDBからアカウントデータを取得する</p>
	 * @param userId
	 * @return アカウントデータ
	 */
	public AccountData getAccountData(String userId);

}
