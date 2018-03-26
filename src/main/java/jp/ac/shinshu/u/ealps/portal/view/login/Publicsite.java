/**
 *
 */
package jp.ac.shinshu.u.ealps.portal.view.login;

import com.google.inject.Inject;
import jp.ac.shinshu.u.ealps.portal.entity.SiteInfo;
import jp.ac.shinshu.u.ealps.portal.panel.AttentionPanel;
import jp.ac.shinshu.u.ealps.portal.panel.InformationPanel;
import jp.ac.shinshu.u.ealps.portal.service.IEALPSListService;
import jp.ac.shinshu.u.ealps.portal.view.EALPSPortalWebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;


/**
 * @author Osamu HASEGAWA
 *
 */
public class Publicsite extends EALPSPortalWebPage {

	private static final long serialVersionUID = -4250861683283721227L;

	@Inject
	private IEALPSListService eALPSListService;

	private IModel<String> siteUidModel;

	public Publicsite(final PageParameters parameters) {
		super(parameters);

		siteUidModel = Model.of("public");
		final SiteInfo siteInfo = eALPSListService.getEALPSSiteInfo(siteUidModel.getObject());

		titleModel.setObject("信州大学一般公開サイト ブックマーク用ページ｜eALPSポータル");

		add(new Form<Void>("postForm") {
			@Override
			protected String getActionUrl() { return siteInfo.getDomainName() + "/login/index.php"; }
		});

		add(new Form<Void>("postGuestForm") {
			@Override
			protected String getActionUrl() { return siteInfo.getDomainName() + "/login/index.php"; }
		});

		// add(new InformationPanel("informationPanel"));
		// add(new AttentionPanel("attentionPanel"));

	}
}
