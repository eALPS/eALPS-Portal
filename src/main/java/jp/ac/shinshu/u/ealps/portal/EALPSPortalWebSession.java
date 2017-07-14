/**
 *
 */
package jp.ac.shinshu.u.ealps.portal;

import com.google.inject.Inject;
import jp.ac.shinshu.u.ealps.portal.entity.AccountData;
import jp.ac.shinshu.u.ealps.portal.service.IAuthenticateService;
import org.apache.wicket.Session;
import org.apache.wicket.protocol.http.WebSession;
import org.apache.wicket.request.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Osamu HASEGAWA
 *
 */
public class EALPSPortalWebSession extends WebSession {

	private static final long serialVersionUID = 8717647158586014161L;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private AccountData accountData;
	private String userId;

	/**
	 * @param request
	 */
	public EALPSPortalWebSession(Request request) {
		super(request);
		this.accountData = new AccountData();
		this.userId = "";
	}

	public static EALPSPortalWebSession get(){
		return (EALPSPortalWebSession) Session.get();
	}

	public AccountData getAccountData() {
		return accountData;
	}

	public void setAccountData(AccountData accountData) {
		this.accountData = accountData;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
