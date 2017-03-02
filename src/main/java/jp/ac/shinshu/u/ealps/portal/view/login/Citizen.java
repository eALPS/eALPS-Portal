/**
 *
 */
package jp.ac.shinshu.u.ealps.portal.view.login;

import jp.ac.shinshu.u.ealps.portal.view.EALPSPortalWebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 * @author Osamu HASEGAWA
 *
 */
public class Citizen extends EALPSPortalWebPage {

	private static final long serialVersionUID = -4250861683283721227L;

	public Citizen(final PageParameters parameters) {

		titleModel.setObject("市民開放講座 ブックマーク用ページ｜eALPSポータル");

		add(new BookmarkablePageLink<Void>("citizenSchedule", CitizenSchedule.class) {
			@Override
			protected void onInitialize() {
				super.onInitialize();
				this.add(new Label("citizenScheduleLabel", "ログインはこちら"));
			}
		});

//		add(new AttentionPanel("attentionPanel"));

	}
}
