/**
 * 
 */
package jp.ac.shinshu.u.ealps.portal.panel;

import java.util.List;

import jp.ac.shinshu.u.ealps.portal.bean.PortalInformationBean;
import jp.ac.shinshu.u.ealps.portal.service.IPortalInformationService;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.datetime.PatternDateConverter;
import org.apache.wicket.datetime.markup.html.basic.DateLabel;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.basic.MultiLineLabel;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PropertyListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;

import com.google.inject.Inject;

/**
 * @author Osamu HASEGAWA
 *
 */
public class InformationPanel extends Panel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4818456781000834896L;
	
	@Inject
	private IPortalInformationService informationService;

	/**
	 * @param id
	 */
	public InformationPanel(String id) {
		super(id);
		
		IModel<List<PortalInformationBean>> portalInformationListModel = new LoadableDetachableModel<List<PortalInformationBean>>() {

			private static final long serialVersionUID = -699142376326336376L;

			@Override
			protected List<PortalInformationBean> load() {
				return informationService.getPortalInformationBeanList();
			}
		};
		drow(portalInformationListModel);
	}

	/**
	 * @param id
	 * @param model
	 */
	public InformationPanel(String id, IModel<? extends List<? extends PortalInformationBean>> model) {
		super(id, model);
		drow(model);
	}
	
	
	private void drow(IModel<? extends List<? extends PortalInformationBean>> model) {
		
		
		PropertyListView<PortalInformationBean> informationListView = new PropertyListView<PortalInformationBean>("PortalInformationBeanListView", model) {

			private static final long serialVersionUID = 7549092380662391084L;

			@Override
			protected void populateItem(final ListItem<PortalInformationBean> listitem) {
				listitem.add(new Label("importanceText").add(new AttributeAppender("class", Model.of(listitem.getModelObject().getImportanceCSS())," ")));
				listitem.add(new Label("addressText").add(new AttributeAppender("class", Model.of(listitem.getModelObject().getAddressCSS()), " ")));
//				listitem.add(new DateLabel("insert", new PatternDateConverter(getString("dateTime"), false)));
				listitem.add(new DateLabel("open", new PatternDateConverter(getString("date"), false)));
//				listitem.add(new Label("importance.importanceName") {
//					private static final long serialVersionUID = -3713987804561363067L;
//					@Override
//					protected void onInitialize() {
//						this.add(new AttributeAppender("class", Model.of("label-" + listitem.getModelObject().getImportance().getImportance()), " "));
//						super.onInitialize();
//					}
//				});
				listitem.add(new AjaxLink<Void>("titleLink") {

					private static final long serialVersionUID = 5612363001325692204L;

					@Override
					public void onClick(AjaxRequestTarget arg0) {
						arg0.appendJavaScript("$('#" + getMarkupId() + " > div').slideToggle('fast');");
					}

					@Override
					protected void onInitialize() {
						this.add(new Label("title"));
						this.add(new WebMarkupContainer("bodyContainer") {
							private static final long serialVersionUID = 2559565264508620678L;

							@Override
							protected void onInitialize() {
								this.add(new AttributeModifier("style", Model.of("display:none")));
								this.add(new MultiLineLabel("body").setEscapeModelStrings(false));
								super.onInitialize();
							}
						});
						super.onInitialize();
					}

				});
//				listitem.add(new IndicatingAjaxLink<Void>("title") {
//					private static final long serialVersionUID = -3929346027899930701L;
//
//					@Override
//					public void onClick(AjaxRequestTarget arg0) {
//						arg0.appendJavascript("$('#" + getMarkupId() + "').parent().next().slideToggle('fast');");
//					}
//
//					@Override
//					protected void onInitialize() {
//						this.add(new Label("title"));
//						super.onInitialize();
//					}
//
//				});
//				listitem.add(new WebMarkupContainer("bodyContainer") {
//					private static final long serialVersionUID = 2559565264508620678L;
//
//					@Override
//					protected void onInitialize() {
//						this.add(new AttributeModifier("style", true, Model.of("display:none")));
//						this.add(new MultiLineLabel("body"));
//						super.onInitialize();
//					}
//				});
			}

		};
		this.add(informationListView);
	}

}
