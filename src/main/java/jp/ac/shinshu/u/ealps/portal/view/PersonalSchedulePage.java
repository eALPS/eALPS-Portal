package jp.ac.shinshu.u.ealps.portal.view;

import jp.ac.shinshu.u.ealps.portal.panel.InformationPanel;
import jp.ac.shinshu.u.ealps.portal.panel.SchedulePanel;
import jp.ac.shinshu.u.ealps.portal.service.IUtilityService;

import org.apache.commons.lang3.StringUtils;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.google.inject.Inject;

public class PersonalSchedulePage extends EALPSPortalWebPage {
	/**
	 *
	 */
	private static final long serialVersionUID = 1895047796065191872L;

	@Inject
	private IUtilityService UtilityService;

	public PersonalSchedulePage(final PageParameters parameters) {

//		super(parameters);

		titleModel.setObject("時間割｜eALPSポータル");

		// POSTされたuserIdを取得する
		// WebRequestCycle cycle = getWebRequestCycle();
		// String email = cycle.getRequest().getParameter("email");
		String userId = getRequest().getRequestParameters().getParameterValue("j_username").toString();

		if(StringUtils.isEmpty(userId)) {
			userId = "";
		}

		add(new InformationPanel("informationPanel"));

		add(new SchedulePanel("schedulePanel", UtilityService.getScheduleInitYear(), userId));

//		add(new Label("version", getApplication().getFrameworkSettings().getVersion()));

	}
}
