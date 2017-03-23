/**
 *
 */
package jp.ac.shinshu.u.ealps.portal.view.login;

import com.google.inject.Inject;
import jp.ac.shinshu.u.ealps.portal.entity.SiteInfo;
import jp.ac.shinshu.u.ealps.portal.panel.AttentionPanel;
import jp.ac.shinshu.u.ealps.portal.service.IEALPSListService;
import jp.ac.shinshu.u.ealps.portal.service.IUtilityService;
import jp.ac.shinshu.u.ealps.portal.view.EALPSPortalWebPage;
import jp.ac.shinshu.u.ealps.portal.view.EChesPortalWebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 * @author Osamu HASEGAWA
 *
 */
public class EChes extends EChesPortalWebPage {

	private static final long serialVersionUID = -7259088060870661052L;

	@Inject
	private IEALPSListService eALPSListService;
	@Inject
	private IUtilityService utilityService;

	private IModel<Integer> yearModel;

	public EChes(PageParameters parameters) {
		super(parameters);
		if (parameters != null) {
			yearModel = Model.of(parameters.get("year").toInt(utilityService.getScheduleInitYear()));
		} else {
			yearModel = Model.of(utilityService.getScheduleInitYear());
		}
		draw();
	}

	public void draw() {

		final SiteInfo siteInfo = eALPSListService.getEChesSiteInfo(yearModel.getObject());

		titleModel.setObject("ログインページ｜eChesポータル");

		add(new Label("caption", siteInfo.getCaption()));
		add(new Label("year", yearModel));
		add(new Form<Void>("postForm") {
			@Override
			protected String getActionUrl() {
				return siteInfo.getDomainName() + "/login/index.php";
			}
		});

		add(new Form<Void>("postGuestForm") {
			@Override
			protected String getActionUrl() {
				return siteInfo.getDomainName() + "/login/index.php";
			}
		});

//		add(new AttentionPanel("attentionPanel"));

	}

	@Override
	protected void onInitialize() {
		super.onInitialize();
		setStatelessHint(true);
	}
}
