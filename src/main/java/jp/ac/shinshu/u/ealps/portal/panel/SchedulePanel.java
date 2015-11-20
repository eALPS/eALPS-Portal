/**
 *
 */
package jp.ac.shinshu.u.ealps.portal.panel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jp.ac.shinshu.u.ealps.portal.bean.RelationCourseBean;
import jp.ac.shinshu.u.ealps.portal.service.IRelationCourseService;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.extensions.ajax.markup.html.AjaxLazyLoadPanel;
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
	public SchedulePanel(String id, int initYear, IModel<String> userIdModel) {
		super(id);
		drow(initYear, userIdModel.getObject());
	}

	/**
	 * @param id
	 * @param model
	 */
	public SchedulePanel(String id, IModel<?> model, int initYear, IModel<String> userIdModel) {
		super(id, model);
		drow(initYear, userIdModel.getObject());
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

//				add(new AjaxLink<Void>("firstSemesterCheckButton") {
//					private static final long serialVersionUID = 7969319085789917881L;
//
//					@Override
//					public void onClick(AjaxRequestTarget target) {
//						//target.appendJavaScript("semesterHideCheck();");
//					}
//
//					@Override
//					protected void onInitialize() {
//						this.add(new Label("firstSemesterCheckButtonLabel", " 前期 "));
//						super.onInitialize();
//					}
//
//				});

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

		// 履修情報から取得するコースリスト
		final IModel<List<RelationCourseBean>> relationCourseBeanListModel = new LoadableDetachableModel<List<RelationCourseBean>>() {
			private static final long serialVersionUID = 8993056010257360948L;
			@Override
			protected List<RelationCourseBean> load() {
				return relationCourseService.getRelationCourseBeanList(userId);
			}
		};

		// 履修情報コースリストを基に時間割用のModelを生成
		final IModel<List<List<Set<RelationCourseBean>>>> courseScheduleListModel = new LoadableDetachableModel<List<List<Set<RelationCourseBean>>>>() {
			private static final long serialVersionUID = -2403192669728340428L;
			@Override
			protected List<List<Set<RelationCourseBean>>> load() {
				return relationCourseService.getCourseScheduleList(relationCourseBeanListModel.getObject());
			}
		};

		add(new AjaxLazyLoadPanel("courseSchedule") {
			private static final long serialVersionUID = 1627715712927164849L;

			@Override
			public Component getLazyLoadComponent(String id) {
				WebMarkupContainer courseScheduleContainer = new WebMarkupContainer(id) {
					private static final long serialVersionUID = 8940695213684029881L;

					@Override
					protected void onInitialize() {
						super.onInitialize();

						PropertyListView<List<Set<RelationCourseBean>>> courseSchedulePeriodListView = new PropertyListView<List<Set<RelationCourseBean>>>("courseSchedulePeriodListView", courseScheduleListModel) {
							private static final long serialVersionUID = 6677315988816669972L;

							@Override
							protected void populateItem(ListItem<List<Set<RelationCourseBean>>> listItem) {
								listItem.add(new Label("period", listItem.getIndex()+1+"時限"));

								PropertyListView<Set<RelationCourseBean>> courseScheduleWeekDayListView = new PropertyListView<Set<RelationCourseBean>>("courseScheduleWeekDayListView", listItem.getModel()) {
									private static final long serialVersionUID = -8275367648185376988L;

									@Override
									protected void populateItem(ListItem<Set<RelationCourseBean>> listItem) {

										List<RelationCourseBean> listItemToList = new ArrayList<RelationCourseBean>(listItem.getModelObject());

										PropertyListView<RelationCourseBean> courseScheduleListView = new PropertyListView<RelationCourseBean>("courseScheduleListView", listItemToList) {
											private static final long serialVersionUID = 8942762394061370989L;

											@Override
											protected void populateItem(ListItem<RelationCourseBean> listItem) {
												listItem.add(new ExternalLink("courseData.url", listItem.getModelObject().getCourseData().getUrl(), listItem.getModelObject().getCourseData().getTitleName()));
//												listItem.add(new Label("courseData.titleCode"));
												listItem.add(new Label("teacherName", listItem.getModelObject().getTeacherList().isEmpty() ? "" : listItem.getModelObject().getTeacherList().get(0).getFirstName() + " " + listItem.getModelObject().getTeacherList().get(0).getLastName()));
												listItem.add(new Label("subTeacherName", listItem.getModelObject().getSubTeacherList().size() == 1 ? listItem.getModelObject().getSubTeacherList().get(0).getFirstName() + " " + listItem.getModelObject().getSubTeacherList().get(0).getLastName() : "...").add(new AttributeAppender("title", Model.of(listItem.getModelObject().getSubTeacherList().size() == 1 ? "副担当教員" : listItem.getModelObject().getSubTeacherNameList()))));
												listItem.add(new AttributeAppender("class", Model.of(listItem.getModelObject().getLecClass())," "));
											}
										};
										listItem.add(courseScheduleListView);

									}
								};
								listItem.add(courseScheduleWeekDayListView);
							}
							@Override
							protected void onInitialize() {
//								setVisible(false);
								setOutputMarkupId(true);
								setOutputMarkupPlaceholderTag(true);
								setReuseItems(true);
								super.onInitialize();
							}
						};
						this.add(courseSchedulePeriodListView);
					}
				};
				return courseScheduleContainer;
			}
		});

//		add(new WebMarkupContainer("courseSchedule") {
//			private static final long serialVersionUID = -4414769783048460641L;
//
//			@Override
//			protected void onInitialize() {
//				super.onInitialize();
//
//				PropertyListView<List<Set<RelationCourseBean>>> courseSchedulePeriodListView = new PropertyListView<List<Set<RelationCourseBean>>>("courseSchedulePeriodListView", courseScheduleListModel) {
//					private static final long serialVersionUID = 2927156290884480612L;
//					@Override
//					protected void populateItem(ListItem<List<Set<RelationCourseBean>>> listItem) {
//						listItem.add(new Label("period", listItem.getIndex()+1+"時限"));
//
//						PropertyListView<Set<RelationCourseBean>> courseScheduleWeekDayListView = new PropertyListView<Set<RelationCourseBean>>("courseScheduleWeekDayListView", listItem.getModel()) {
//							private static final long serialVersionUID = -6532971349026986813L;
//							@Override
//							protected void populateItem(ListItem<Set<RelationCourseBean>> listItem) {
//
//								List<RelationCourseBean> listItemToList = new ArrayList<RelationCourseBean>(listItem.getModelObject());
//
//								PropertyListView<RelationCourseBean> courseScheduleListView = new PropertyListView<RelationCourseBean>("courseScheduleListView", listItemToList) {
//									private static final long serialVersionUID = -8275367648185376988L;
//									@Override
//									protected void populateItem(ListItem<RelationCourseBean> listItem) {
//										listItem.add(new ExternalLink("courseData.url", listItem.getModelObject().getCourseData().getUrl(), listItem.getModelObject().getCourseData().getTitleName()));
////										listItem.add(new Label("courseData.titleCode"));
//										listItem.add(new Label("teacherName", listItem.getModelObject().getTeacherList().isEmpty() ? "" : listItem.getModelObject().getTeacherList().get(0).getFirstName() + " " + listItem.getModelObject().getTeacherList().get(0).getLastName()));
//										listItem.add(new Label("subTeacherName", listItem.getModelObject().getSubTeacherList().size() == 1 ? listItem.getModelObject().getSubTeacherList().get(0).getFirstName() + " " + listItem.getModelObject().getSubTeacherList().get(0).getLastName() : "...").add(new AttributeAppender("title", Model.of(listItem.getModelObject().getSubTeacherList().size() == 1 ? "副担当教員" : listItem.getModelObject().getSubTeacherNameList()))));
//										listItem.add(new AttributeAppender("class", Model.of(listItem.getModelObject().getLecClass())," "));
//									}
//								};
//								listItem.add(courseScheduleListView);
//
//							}
//						};
//						listItem.add(courseScheduleWeekDayListView);
//					}
//					@Override
//					protected void onInitialize() {
////						setVisible(false);
//						setOutputMarkupId(true);
//						setOutputMarkupPlaceholderTag(true);
//						setReuseItems(true);
//						super.onInitialize();
//					}
//				};
//				this.add(courseSchedulePeriodListView);
//			}
//		});

		final Set<String> checkCourseRepitition = new HashSet<String>();

		add(new WebMarkupContainer("nonScheduleCourseList") {
			private static final long serialVersionUID = 6244941068825497509L;

			@Override
			protected void onInitialize() {
				super.onInitialize();

				PropertyListView<RelationCourseBean> nonScheduleCourseListView = new PropertyListView<RelationCourseBean>("nonScheduleCourseListView", relationCourseBeanListModel) {
					private static final long serialVersionUID = -4452224939836409982L;
					@Override
					protected void populateItem(final ListItem<RelationCourseBean> listItem) {
						listItem.add(new ExternalLink("courseData.url", listItem.getModelObject().getCourseData().getUrl(), listItem.getModelObject().getCourseData().getTitleName()));
						listItem.add(new Label("courseData.titleCode"));
						listItem.add(new Label("teacherName", listItem.getModelObject().getTeacherList().isEmpty() ? "" : listItem.getModelObject().getTeacherList().get(0).getFirstName() + " " + listItem.getModelObject().getTeacherList().get(0).getLastName()));
						listItem.add(new Label("subTeacherName", listItem.getModelObject().getSubTeacherList().size() == 1 ? listItem.getModelObject().getSubTeacherList().get(0).getFirstName() + " " + listItem.getModelObject().getSubTeacherList().get(0).getLastName() : "...").add(new AttributeAppender("title", Model.of(listItem.getModelObject().getSubTeacherList().size() == 1 ? "副担当教員" : listItem.getModelObject().getSubTeacherNameList()))));
						listItem.add(new Label("courseData.opYear"));
						listItem.add(new Label("opInfoValue"));
						listItem.add(new AttributeAppender("class", Model.of(listItem.getModelObject().getLecClass())," "));
						listItem.setVisible(!listItem.getModelObject().isScheduleCourse() && checkCourseRepitition.add(listItem.getModelObject().getCourseData().getUid()));
					}
				};
				this.add(nonScheduleCourseListView);
			}
		});

		add(new WebMarkupContainer("courseList") {
			private static final long serialVersionUID = -2280591673089064874L;
			@Override
			protected void onInitialize() {
				this.add(new AttributeModifier("style", Model.of("display:none")));
				super.onInitialize();

				PropertyListView<RelationCourseBean> courseListView = new PropertyListView<RelationCourseBean>("courseListView", relationCourseBeanListModel) {
					private static final long serialVersionUID = -4452224939836409982L;

					@Override
					protected void populateItem(final ListItem<RelationCourseBean> listItem) {
						listItem.add(new ExternalLink("courseData.url", listItem.getModelObject().getCourseData().getUrl(), listItem.getModelObject().getCourseData().getTitleName()));
						listItem.add(new Label("courseData.titleCode"));
						listItem.add(new Label("teacherName", listItem.getModelObject().getTeacherList().isEmpty() ? "" : listItem.getModelObject().getTeacherList().get(0).getFirstName() + " " + listItem.getModelObject().getTeacherList().get(0).getLastName()));
						listItem.add(new Label("subTeacherName", listItem.getModelObject().getSubTeacherList().size() == 1 ? listItem.getModelObject().getSubTeacherList().get(0).getFirstName() + " " + listItem.getModelObject().getSubTeacherList().get(0).getLastName() : "...").add(new AttributeAppender("title", Model.of(listItem.getModelObject().getSubTeacherList().size() == 1 ? "副担当教員" : listItem.getModelObject().getSubTeacherNameList()))));
						listItem.add(new Label("courseData.opYear"));
						listItem.add(new Label("opInfoValue"));
						listItem.add(new AttributeAppender("class", Model.of(listItem.getModelObject().getLecClass())," "));
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
