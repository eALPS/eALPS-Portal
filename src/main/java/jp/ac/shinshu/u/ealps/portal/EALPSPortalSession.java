/**
 *
 */
package jp.ac.shinshu.u.ealps.portal;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

import jp.ac.shinshu.u.ealps.portal.entity.AccountData;
import jp.ac.shinshu.u.ealps.portal.service.IAuthenticateService;

import org.apache.wicket.Session;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.request.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;

/**
 * @author Osamu HASEGAWA
 *
 */
public class EALPSPortalSession extends AuthenticatedWebSession implements HttpSessionBindingListener {

	private static final long serialVersionUID = 1473562531420294942L;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private AccountData accountData;
	private String userId;
	private Roles roles;

	@Inject
	private IAuthenticateService authenticateService;

	/**
	 * @param request
	 */
	public EALPSPortalSession(Request request) {
		super(request);
		userId = "";
	}

	@Override
	public boolean authenticate(String userId, String password) {
		this.userId = userId;
		if(authenticateService.signIn(userId, password)) {
			this.roles = new Roles(authenticateService.setRoles(userId));
			logger.debug(userId + "がログインに成功");
			return true;
		} else {
			logger.debug(userId + "がログインに失敗");
			return false;
		}
	}

	@Override
	public Roles getRoles() {
		return roles;
	}

	@Override
	public void valueBound(HttpSessionBindingEvent arg0) {
		logger.debug("ValueBoundEvent SessionId : " + ((EALPSPortalSession) arg0.getValue()).getId());
	}

	@Override
	public void valueUnbound(HttpSessionBindingEvent arg0) {
		logger.debug("ValueUnBoundEvent SessionId : " + ((EALPSPortalSession) arg0.getValue()).getId());
	}

	public static EALPSPortalSession get(){
		return (EALPSPortalSession) Session.get();
	}

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
		this.accountData = authenticateService.getAccountData(userId);
		logger.debug("AccountData:" + accountData.toString());
	}

}
