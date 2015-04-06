/**
 *
 */
package jp.ac.shinshu.u.ealps.portal.view;

import org.apache.wicket.markup.html.link.ExternalLink;

/**
 * @author Osamu HASEGAWA
 *
 */
public class EALPSPortalExpiredErrorPage extends EALPSPortalWebPage {

	private static final long serialVersionUID = 607842004674176532L;

	/**
	 * <p>デフォルトコンストラクタ</p>
	 */
	public EALPSPortalExpiredErrorPage(Exception e) {
		super();
		drow(e);
	}

	/**
	 * <p>共通処理</p>
	 */
	private void drow(Exception e) {
		titleModel.setObject("ページの期限切れ｜eALPSポータル");
		this.add(new ExternalLink("acsuLink", "https://acsu.shinshu-u.ac.jp/"));
	}

}
