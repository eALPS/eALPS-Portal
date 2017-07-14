package jp.ac.shinshu.u.ealps.portal.view;

import com.google.inject.Inject;
import jp.ac.shinshu.u.ealps.portal.EALPSPortalWebSession;
import jp.ac.shinshu.u.ealps.portal.panel.AttentionPanel;
import jp.ac.shinshu.u.ealps.portal.panel.CommonSitePanel;
import jp.ac.shinshu.u.ealps.portal.panel.InformationPanel;
import jp.ac.shinshu.u.ealps.portal.panel.SchedulePanel;
import jp.ac.shinshu.u.ealps.portal.service.IAuthenticateService;
import jp.ac.shinshu.u.ealps.portal.service.IUtilityService;
import org.apache.commons.lang3.StringUtils;
import org.apache.wicket.Component;
import org.apache.wicket.extensions.ajax.markup.html.AjaxLazyLoadPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class PersonalSchedulePage extends EALPSPortalWebPage {
	/**
	 *
	 */
	private static final long serialVersionUID = 1895047796065191872L;

	@Inject
	private IUtilityService UtilityService;
	@Inject
	private IAuthenticateService authenticateService;

	public PersonalSchedulePage(final PageParameters parameters) {

//		super(parameters);

		titleModel.setObject("時間割｜eALPSポータル");

		// POSTされたuserIdを取得してsessionに格納する
		// WebRequestCycle cycle = getWebRequestCycle();
		// String email = cycle.getRequest().getParameter("email");
		String userId = getRequest().getRequestParameters().getParameterValue("j_username").toString();
		if(StringUtils.isEmpty(userId)) {
			userId = "";
		}
		EALPSPortalWebSession.get().setUserId(userId);
		EALPSPortalWebSession.get().setAccountData(authenticateService.getAccountData(userId));

		final IModel<String> userIdModel = Model.of(userId);
		
		//add(new InformationPanel("informationPanel"));

		add(new AjaxLazyLoadPanel("informationPanel") {
			private static final long serialVersionUID = -4553102666424445198L;
			@Override
			public Component getLazyLoadComponent(String id) {
				return new InformationPanel(id);
			}
		});

		add(new SchedulePanel("schedulePanel", UtilityService.getScheduleInitYear(), userIdModel));

		add(new CommonSitePanel("commonSitePanel"));

		add(new AttentionPanel("attentionPanel"));

//		add(new AjaxLazyLoadPanel("schedulePanel") {
//			private static final long serialVersionUID = -9047759161966347185L;
//			@Override
//			public Component getLazyLoadComponent(String id) {
//				return new SchedulePanel(id, UtilityService.getScheduleInitYear(), userIdModel);
//			}
//		});

//		add(new Label("version", getApplication().getFrameworkSettings().getVersion()));

	}
}
