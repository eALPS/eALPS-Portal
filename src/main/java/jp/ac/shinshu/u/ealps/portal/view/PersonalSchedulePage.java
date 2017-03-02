package jp.ac.shinshu.u.ealps.portal.view;

import jp.ac.shinshu.u.ealps.portal.panel.AttentionPanel;
import jp.ac.shinshu.u.ealps.portal.panel.CommonSitePanel;
import jp.ac.shinshu.u.ealps.portal.panel.InformationPanel;
import jp.ac.shinshu.u.ealps.portal.panel.SchedulePanel;
import jp.ac.shinshu.u.ealps.portal.service.IUtilityService;

import org.apache.commons.lang3.StringUtils;
import org.apache.wicket.Component;
import org.apache.wicket.extensions.ajax.markup.html.AjaxLazyLoadPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
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
		final IModel<String> userIdModel = new Model<String>();
		userIdModel.setObject(getRequest().getRequestParameters().getParameterValue("j_username").toString());

		if(StringUtils.isEmpty(userIdModel.getObject())) {
			userIdModel.setObject("");
		}

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
