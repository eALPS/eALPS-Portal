/**
 *
 */
package jp.ac.shinshu.u.ealps.portal.view.login;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import jp.ac.shinshu.u.ealps.portal.panel.InformationPanel;
import jp.ac.shinshu.u.ealps.portal.panel.SchedulePanel;
import jp.ac.shinshu.u.ealps.portal.service.IUtilityService;
import jp.ac.shinshu.u.ealps.portal.view.EALPSPortalWebPage;

import org.apache.commons.lang3.StringUtils;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.Model;
import org.apache.wicket.protocol.http.servlet.ServletWebRequest;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.google.inject.Inject;

/**
 * @author Osamu HASEGAWA
 *
 */
public class Citizen extends EALPSPortalWebPage {

	private static final long serialVersionUID = 5205056644436015115L;

	@Inject
	private IUtilityService UtilityService;

	public Citizen(final PageParameters parameters) {


		titleModel.setObject("市民開放講座 時間割｜eALPSポータル");

		HttpServletRequest httpServletRequest = ((ServletWebRequest) RequestCycle.get().getRequest()).getContainerRequest();

		// ヘッダー確認用
		@SuppressWarnings("unchecked")
		Enumeration<String> headerNames = httpServletRequest.getHeaderNames();
		StringBuilder headerName = new StringBuilder("");
		if(headerNames != null) {
			while( headerNames.hasMoreElements()) {
				headerName.append(headerNames.nextElement());
				headerName.append(", ");
			}
		}

		String uid = httpServletRequest.getHeader("uid") == null ? "" : httpServletRequest.getHeader("uid").toString();
		String j_username = httpServletRequest.getHeader("j_username") == null ? "" : httpServletRequest.getHeader("j_username").toString();
		String userId = httpServletRequest.getHeader("userId") == null ? "" : httpServletRequest.getHeader("userId").toString();

//		String uid = httpServletRequest.getAttribute("uid") == null ? "" : httpServletRequest.getAttribute("uid").toString();
//		String j_username = httpServletRequest.getAttribute("j_username") == null ? "" : httpServletRequest.getAttribute("j_username").toString();
//		String userId = httpServletRequest.getAttribute("userId") == null ? "" : httpServletRequest.getAttribute("userId").toString();

//		String uid = getRequest().getRequestParameters().getParameterValue("uid").toString();
//		String j_username = getRequest().getRequestParameters().getParameterValue("j_username").toString();
//		String userId = getRequest().getRequestParameters().getParameterValue("userId").toString();

		// ヘッダー確認用
		this.add(new Label("headerNames", Model.of(headerNames.toString())));

		this.add(new Label("j_username", Model.of(j_username)));
		this.add(new Label("userId", Model.of(userId)));
		this.add(new Label("uid", Model.of(uid)));

		if(StringUtils.isEmpty(uid)) {
			uid = "";
		}

		add(new InformationPanel("informationPanel"));

		add(new SchedulePanel("schedulePanel", UtilityService.getScheduleInitYear(), uid));
	}

}
