/**
 *
 */
package jp.ac.shinshu.u.ealps.portal.view.logout;

import org.apache.wicket.request.mapper.parameter.PageParameters;

import jp.ac.shinshu.u.ealps.portal.panel.AttentionPanel;
import jp.ac.shinshu.u.ealps.portal.view.EALPSPortalWebPage;

/**
 * @author Osamu HASEGAWA
 *
 */
public class GlobalLogout extends EALPSPortalWebPage {

	private static final long serialVersionUID = -1147041657618681793L;

	public GlobalLogout(final PageParameters parameters) {

		titleModel.setObject("ログアウトページ｜eALPSポータル");

		add(new AttentionPanel("attentionPanel"));

	}
}
