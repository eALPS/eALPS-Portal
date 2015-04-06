package jp.ac.shinshu.u.ealps.portal;

import jp.ac.shinshu.u.common.definition.SystemDefinition;
import jp.ac.shinshu.u.ealps.portal.view.EALPSPortalExpiredErrorPage;
import jp.ac.shinshu.u.ealps.portal.view.PersonalSchedulePage;

import org.apache.wicket.authroles.authentication.AbstractAuthenticatedWebSession;
import org.apache.wicket.authroles.authentication.AuthenticatedWebApplication;
import org.apache.wicket.core.request.handler.PageProvider;
import org.apache.wicket.core.request.handler.RenderPageRequestHandler;
import org.apache.wicket.guice.GuiceComponentInjector;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.PageExpiredException;
import org.apache.wicket.request.IRequestHandler;
import org.apache.wicket.request.cycle.AbstractRequestCycleListener;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.settings.IExceptionSettings;

/**
 * Application object for your web application.
 * If you want to run this application without deploying, run the Start class.
 *
 * @see jp.ac.shinshu.u.ealps.portal.Start#main(String[])
 */
public class EALPSPortalApplication extends AuthenticatedWebApplication
{
	/**
	 * @see org.apache.wicket.Application#getHomePage()
	 */
	@Override
	public Class<? extends WebPage> getHomePage()
	{
		return PersonalSchedulePage.class;
	}

	/**
	 * @see org.apache.wicket.Application#init()
	 */
	@Override
	public void init()
	{
		super.init();

		// サーバ・クライアント間のリクエスト・レスポンス時の文字エンコード
		getRequestCycleSettings().setResponseRequestEncoding(SystemDefinition.APPLICATION_ENCODING);

		// Wicketに取り込まれるHTMLファイルのエンコード
		getMarkupSettings().setDefaultMarkupEncoding(SystemDefinition.APPLICATION_ENCODING);

		// RuntimeExceptionが発生した場合はInternalErrorPageを表示
		getExceptionSettings().setUnexpectedExceptionDisplay(IExceptionSettings.SHOW_INTERNAL_ERROR_PAGE);

		// 独自InternalErrorPageクラス
		// getApplicationSettings().setInternalErrorPage(EALPSPortalExpiredErrorPage.class);

		// 独自ExpiredErrorPageクラス
		// getApplicationSettings().setPageExpiredErrorPage(EALPSPortalExpiredErrorPage.class);

		// Wicket-guice を利用する
		getComponentInstantiationListeners().add(new GuiceComponentInjector(this));

		// Exceptionが発生した際のリスナーを追加
		getRequestCycleListeners().add(new AbstractRequestCycleListener() {
			@Override
			public IRequestHandler onException(RequestCycle paramRequestCycle, Exception e) {
				if(e instanceof PageExpiredException) {
					return new RenderPageRequestHandler(new PageProvider(new EALPSPortalExpiredErrorPage(e)));
//					return new EALPSPortalExpiredErrorPage();
				}
				return new RenderPageRequestHandler(new PageProvider(new EALPSPortalExpiredErrorPage(e)));
			}
		});

		// URLマッピング
//		mountPages();
	}

	@Override
	protected Class<? extends WebPage> getSignInPageClass() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	protected Class<? extends AbstractAuthenticatedWebSession> getWebSessionClass() {
		return EALPSPortalSession.class;
	}
}
