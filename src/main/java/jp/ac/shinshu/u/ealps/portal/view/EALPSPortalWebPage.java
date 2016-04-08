/**
 *
 */
package jp.ac.shinshu.u.ealps.portal.view;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.html.IHeaderContributor;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.Model;

/**
 * @author osamu
 *
 */
public class EALPSPortalWebPage extends WebPage implements IHeaderContributor {
	/**
	 *
	 */
	private static final long serialVersionUID = -6229591982136332793L;

	protected Model<String> titleModel = new Model<String>();

//	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public EALPSPortalWebPage() {

		titleModel.setObject("eALPSポータル");
		this.add(new Label("title", titleModel));

		Calendar calendar = new GregorianCalendar();
		this.add(new Label("currentYear", new Model<Integer>(calendar.get(Calendar.YEAR))));

	}

	@Override
	public void renderHead(IHeaderResponse response) {

//		logger.debug(SystemDefinition.FQDN);

		response.render(CssHeaderItem.forUrl("bootstrap/css/bootstrap.min.css"));
//		response.render(CssHeaderItem.forUrl("bootstrap/css/bootstrap-theme.min.css"));

		response.render(CssHeaderItem.forUrl("css/dataTables.bootstrap.css"));
		response.render(CssHeaderItem.forUrl("css/common.css"));

		response.render(JavaScriptHeaderItem.forUrl("https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"));
		response.render(JavaScriptHeaderItem.forUrl("bootstrap/js/bootstrap.min.js"));
		response.render(JavaScriptHeaderItem.forUrl("js/jquery.dataTables.min.js"));
		response.render(JavaScriptHeaderItem.forUrl("js/dataTables.bootstrap.js"));
	}

}
