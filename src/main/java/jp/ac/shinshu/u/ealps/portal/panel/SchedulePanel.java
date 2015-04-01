/**
 * 
 */
package jp.ac.shinshu.u.ealps.portal.panel;

import java.util.Arrays;
import java.util.List;

import jp.ac.shinshu.u.ealps.portal.bean.RelationCourseBean;
import jp.ac.shinshu.u.ealps.portal.service.IRelationCourseService;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PropertyListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.util.ListModel;

import com.google.inject.Inject;

/**
 * @author Osamu HASEGAWA
 *
 */
public class SchedulePanel extends Panel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1855016323405526904L;
	
	@Inject
	private IRelationCourseService relationCourseService;

	/**
	 * @param id
	 */
	public SchedulePanel(String id, int initYear, String userId) {
		super(id);
		drow(initYear, userId);
	}

	/**
	 * @param id
	 * @param model
	 */
	public SchedulePanel(String id, IModel<?> model, int initYear, String userId) {
		super(id, model);
		drow(initYear, userId);
	}
	
	private void drow(int initYear, final String userId) {
		
//		final IModel<Integer> hogehoge = new Model<Integer>(0) {
//			
//		};
		final IModel<Integer> choicedYearModel = new Model<Integer>(initYear);
		final IModel<String> scheduleHeadingLabelModel = new Model<String>(choicedYearModel.getObject() + "年度 時間割");;
		final Label scheduleHeadingLabel = new Label("scheduleHeadingLabel", scheduleHeadingLabelModel) {
			private static final long serialVersionUID = -8328354210595730594L;
			
			@Override
			protected void onInitialize() {
				super.onInitialize();
				//setDefaultModelObject(choicedYearModel.getObject() + "年度 時間割");
				setOutputMarkupId(true);
			}
			
			@Override
			protected void onConfigure() {
				super.onConfigure();
				setDefaultModelObject(choicedYearModel.getObject() + "年度 時間割");
			}
		};
		add(scheduleHeadingLabel);
		
		
		
		add(new WebMarkupContainer("viewOption") {
			private static final long serialVersionUID = -6449004618767005205L;

			@Override
			protected void onInitialize() {
				
				IModel<List<Integer>> dropDownYearListModel = new ListModel<Integer>(Arrays.asList(2015));
				Form<Void> selectYearForm = new Form<Void>("selectYearForm");
				add(selectYearForm);
				
				selectYearForm.add(new DropDownChoice<Integer>("dropDownChoice", choicedYearModel,dropDownYearListModel) {
					private static final long serialVersionUID = 186950013127653794L;

					@Override
					protected void onInitialize() {
						super.onInitialize();
						add(new AjaxFormComponentUpdatingBehavior("onchange") {
							private static final long serialVersionUID = -9137740509717491849L;

							@Override
							protected void onUpdate(AjaxRequestTarget target) {
								target.add(scheduleHeadingLabel);
							}
							
						});
					}
				});
				
				
				add(new AjaxLink<Void>("courseScheduleRadio") {
					private static final long serialVersionUID = -2821499865808963847L;

					@Override
					public void onClick(AjaxRequestTarget target) {
						scheduleHeadingLabelModel.setObject(choicedYearModel.getObject() + "年度 時間割");
						target.add(scheduleHeadingLabel);
					}

					@Override
					protected void onInitialize() {
						super.onInitialize();
					}

				});
				
				add(new AjaxLink<Void>("courseListRadio") {
					private static final long serialVersionUID = -7872585462069409304L;

					@Override
					public void onClick(AjaxRequestTarget target) {
						scheduleHeadingLabelModel.setObject(choicedYearModel.getObject() + "年度 履修コースリスト");
						target.add(scheduleHeadingLabel);
					}

					@Override
					protected void onInitialize() {
						super.onInitialize();
					}

				});
				
				add(new AjaxLink<Void>("eALPSListRadio") {
					private static final long serialVersionUID = 4974661936044397031L;

					@Override
					public void onClick(AjaxRequestTarget target) {
						scheduleHeadingLabelModel.setObject(choicedYearModel.getObject() + "年度 学部リスト");
						target.add(scheduleHeadingLabel);
//						target.appendJavaScript("$('.eALPSListRadio').click();");
					}

					@Override
					protected void onInitialize() {
						super.onInitialize();
					}

				});
				
				add(new AjaxLink<Void>("courseScheduleButton") {
					private static final long serialVersionUID = 904855932483517020L;

					@Override
					public void onClick(AjaxRequestTarget target) {
						target.appendJavaScript("$('.courseScheduleRadio').click();");
					}

					@Override
					protected void onInitialize() {
						this.add(new Label("courseScheduleButtonLabel", "時間割"));
						this.add(new AttributeModifier("style", Model.of("display:none")));
						super.onInitialize();
					}

				});
				
				add(new AjaxLink<Void>("eALPSListButton") {
					private static final long serialVersionUID = -4117936053570493147L;

					@Override
					public void onClick(AjaxRequestTarget target) {
						target.appendJavaScript("$('.eALPSListRadio').click();");
					}

					@Override
					protected void onInitialize() {
						this.add(new Label("eALPSListButtonLabel", "学部リスト"));
						super.onInitialize();
					}

				});
				
				super.onInitialize();
			}
		});
		
		add(new WebMarkupContainer("courseSchedule") {
			private static final long serialVersionUID = -4414769783048460641L;

			@Override
			protected void onInitialize() {
				super.onInitialize();
			}
		});
		
		add(new WebMarkupContainer("nonScheduleCourseList") {
			private static final long serialVersionUID = 6244941068825497509L;

			@Override
			protected void onInitialize() {
				super.onInitialize();
			}
		});
		
		final IModel<List<RelationCourseBean>> relationCourseBeanListModel = new LoadableDetachableModel<List<RelationCourseBean>>() {
			private static final long serialVersionUID = 8993056010257360948L;
			@Override
			protected List<RelationCourseBean> load() {
				return relationCourseService.getRelationCourseBeanList(userId);
			}
		};
		add(new WebMarkupContainer("courseList") {
			private static final long serialVersionUID = -2280591673089064874L;
			@Override
			protected void onInitialize() {
				this.add(new AttributeModifier("style", Model.of("display:none")));
				super.onInitialize();
			
				PropertyListView<RelationCourseBean> courseListView = new PropertyListView<RelationCourseBean>("courseListView", relationCourseBeanListModel) {
					private static final long serialVersionUID = -4452224939836409982L;
					@Override
					protected void populateItem(final ListItem<RelationCourseBean> listitem) {
						listitem.add(new ExternalLink("courseURL", listitem.getModelObject().getCourseURL(), listitem.getModelObject().getCourseTitleName()));
						listitem.add(new Label("teacherName"));
					}
				};
				this.add(courseListView);
			}
		});
		
		add(new WebMarkupContainer("eALPSList") {
			private static final long serialVersionUID = -4414769783048460641L;

			@Override
			protected void onInitialize() {
				this.add(new AttributeModifier("style", Model.of("display:none")));
				super.onInitialize();
			}
		});
		
	}

}
