/**
 *
 */
package jp.ac.shinshu.u.ealps.portal.view;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 * @author osamu
 *
 */
public class Index extends WebPage {

	/**
	 *
	 */
	private static final long serialVersionUID = -2015902604825387535L;

	public Index(final PageParameters parameters) {
		super(parameters);

//		String j_username = getRequest().getRequestParameters().getParameterValue("j_username").toString();
//		String userId = getRequest().getRequestParameters().getParameterValue("userId").toString();
//		String uid = getRequest().getRequestParameters().getParameterValue("uid").toString();

		add(new Label("version", getApplication().getFrameworkSettings().getVersion()));

	}

}
