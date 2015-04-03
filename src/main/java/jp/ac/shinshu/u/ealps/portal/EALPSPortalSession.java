/**
 *
 */
package jp.ac.shinshu.u.ealps.portal;

import jp.ac.shinshu.u.ealps.portal.service.IAuthenticateService;

import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.request.Request;

import com.google.inject.Inject;

/**
 * @author Osamu HASEGAWA
 *
 */
public class EALPSPortalSession extends AuthenticatedWebSession {

	private static final long serialVersionUID = 1473562531420294942L;

	private Roles roles;

	@Inject
	private IAuthenticateService authenticateService;

	/**
	 * @param request
	 */
	public EALPSPortalSession(Request request) {
		super(request);
		// TODO 自動生成されたコンストラクター・スタブ
	}

	@Override
	public boolean authenticate(String userId, String password) {
		if(authenticateService.signIn(userId, password)) {
			this.roles = new Roles(authenticateService.setRoles(userId));
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Roles getRoles() {
		return roles;
	}

}
