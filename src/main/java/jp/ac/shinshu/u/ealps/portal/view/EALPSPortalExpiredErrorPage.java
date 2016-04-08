/**
 *
 */
package jp.ac.shinshu.u.ealps.portal.view;

import org.apache.wicket.markup.html.link.ExternalLink;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Osamu HASEGAWA
 *
 */
public class EALPSPortalExpiredErrorPage extends EALPSPortalWebPage {

	private static final long serialVersionUID = 607842004674176532L;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

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
		logger.error("Error Log ->", e);
	}

}