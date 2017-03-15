/**
 *
 */
package jp.ac.shinshu.u.ealps.portal.view.login;

import com.google.inject.Inject;
import jp.ac.shinshu.u.ealps.portal.entity.SiteInfo;
import jp.ac.shinshu.u.ealps.portal.panel.AttentionPanel;
import jp.ac.shinshu.u.ealps.portal.service.IEALPSListService;
import jp.ac.shinshu.u.ealps.portal.view.EALPSPortalWebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 * @author Osamu HASEGAWA
 *
 */
public class Shibboleth extends EALPSPortalWebPage {

	private static final long serialVersionUID = -4250861683283721227L;

	@Inject
	private IEALPSListService eALPSListService;

	private IModel<String> siteUidModel;

	public Shibboleth(PageParameters parameters) {
		super(parameters);

		if (parameters != null) {
			siteUidModel = Model.of(parameters.get("siteUid").toString("Unspecified"));
		}

		final SiteInfo siteInfo = eALPSListService.getEALPSSiteInfo(siteUidModel.getObject());

		titleModel.setObject(siteInfo.getCaption() + "｜eALPSポータル");

		add(new Label("caption", siteInfo.getCaption() + "サイトにログインする"));

		add(new ExternalLink("moodleLink", Model.of(siteInfo.getDomainName()), Model.of("ログインはこちら")));

		add(new AttentionPanel("attentionPanel"));

	}

	@Override
	protected void onInitialize() {
		super.onInitialize();
		setStatelessHint(true);
	}
}
