/**
 *
 */
package jp.ac.shinshu.u.ealps.portal.panel;

import com.google.inject.Inject;
import jp.ac.shinshu.u.ealps.portal.entity.SiteInfo;
import jp.ac.shinshu.u.ealps.portal.service.IEALPSListService;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PropertyListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;

import java.util.List;

/**
 * @author Osamu HASEGAWA
 *
 */
public class CommonSitePanel extends Panel {

	private static final long serialVersionUID = -8795297921223515461L;

	@Inject
	private IEALPSListService ealpsListService;

	private IModel<List<SiteInfo>> commonSiteInfoListModel;

	/**
	 * @param id
	 */
	public CommonSitePanel(String id) {
		super(id);
		draw();
	}

	private void draw() {

		//該当年度の学部リストを取得
		commonSiteInfoListModel = new LoadableDetachableModel<List<SiteInfo>>() {
			private static final long serialVersionUID = 8273120838732693602L;

			@Override
			protected List<SiteInfo> load() {
				return ealpsListService.getEALPSCommonSiteInfoList();
			}
		};

		add(new WebMarkupContainer("eALPSCommonSiteListContainer"){
			private static final long serialVersionUID = -791384581281675495L;

			@Override
			protected void onInitialize() {
				super.onInitialize();

				PropertyListView<SiteInfo> eALPSCommonSiteListView = new PropertyListView<SiteInfo>("eALPSCommonSiteListView", commonSiteInfoListModel) {
					private static final long serialVersionUID = -6187276909441962109L;

					@Override
					protected void populateItem(ListItem<SiteInfo> listItem) {
						listItem.add(new ExternalLink("site", listItem.getModelObject().getDomainName(), listItem.getModelObject().getCaption()));
					}
					@Override
					protected void onInitialize() {
						super.onInitialize();
//						setVisible(false);
						setOutputMarkupId(true);
						setOutputMarkupPlaceholderTag(true);
//						setReuseItems(true);
					}

				};
				this.add(eALPSCommonSiteListView);
			}
		});

	}

}
