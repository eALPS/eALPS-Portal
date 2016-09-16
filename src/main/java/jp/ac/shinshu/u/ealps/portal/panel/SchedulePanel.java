/**
 *
 */
package jp.ac.shinshu.u.ealps.portal.panel;

import java.util.List;

import jp.ac.shinshu.u.common.expansion.IndicatingAjaxDropDownChoice;
import jp.ac.shinshu.u.common.util.ChronoTrigger;
import jp.ac.shinshu.u.ealps.portal.service.IUtilityService;

import org.apache.commons.lang3.StringUtils;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.extensions.ajax.markup.html.AjaxLazyLoadPanel;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.util.ListModel;

import com.google.inject.Inject;

/**
 * @author Osamu HASEGAWA
 *
 */
public class SchedulePanel extends Panel {

	private static final long serialVersionUID = -1855016323405526904L;

	@Inject
	private IUtilityService UtilityService;

	private IModel<Integer> choicedYearModel;
	private IModel<String> scheduleHeadingLabelModel;

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

		choicedYearModel = new Model<Integer>(initYear);
		scheduleHeadingLabelModel = new Model<String>("年度 時間割");

		final AjaxLazyLoadPanel yearSchedulePanel;

		final Label scheduleHeadingLabel = new Label("scheduleHeadingLabel", new Model<String>("")) {
			private static final long serialVersionUID = -8328354210595730594L;

			@Override
			protected void onInitialize() {
				super.onInitialize();
				setDefaultModelObject(choicedYearModel.getObject() + scheduleHeadingLabelModel.getObject());
				setOutputMarkupId(true);
				setOutputMarkupPlaceholderTag(true);
			}

			@Override
			protected void onConfigure() {
				super.onConfigure();
				setDefaultModelObject(choicedYearModel.getObject() + scheduleHeadingLabelModel.getObject());
			}
		};
		add(scheduleHeadingLabel);

		yearSchedulePanel = new AjaxLazyLoadPanel("yearSchedule") {
			private static final long serialVersionUID = 1627715712927164849L;

			@Override
			public Component getLazyLoadComponent(String id) {
				return new YearSchedulePanel(id, choicedYearModel, Model.of(userId));
			}
			@Override
			protected void onInitialize() {
				super.onInitialize();
				setOutputMarkupId(true);
				setOutputMarkupPlaceholderTag(true);
			}
		};
		add(yearSchedulePanel);

		add(new WebMarkupContainer("viewOption") {
			private static final long serialVersionUID = -6449004618767005205L;

			@Override
			protected void onInitialize() {

				super.onInitialize();

				IModel<List<Integer>> dropDownYearListModel = new ListModel<Integer>(UtilityService.getYearList());
				Form<Void> selectYearForm = new Form<Void>("selectYearForm");
				add(selectYearForm);

				selectYearForm.add(new IndicatingAjaxDropDownChoice<Integer>("dropDownChoice", choicedYearModel, dropDownYearListModel) {
					private static final long serialVersionUID = 186950013127653794L;

					@Override
					protected void onInitialize() {
						super.onInitialize();
						add(new AjaxFormComponentUpdatingBehavior("onchange") {
							private static final long serialVersionUID = -9137740509717491849L;

							@Override
							protected void onUpdate(AjaxRequestTarget target) {
								scheduleHeadingLabelModel.setObject("年度 時間割");
								target.add(scheduleHeadingLabel);
								target.add(yearSchedulePanel);
							}

						});
					}
				});

				add(new AjaxLink<Void>("courseScheduleRadio") {
					private static final long serialVersionUID = -2821499865808963847L;

					@Override
					public void onClick(AjaxRequestTarget target) {
						scheduleHeadingLabelModel.setObject("年度 時間割");
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
						scheduleHeadingLabelModel.setObject("年度 履修コースリスト");
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
						scheduleHeadingLabelModel.setObject("年度 学部リスト");
						target.add(scheduleHeadingLabel);
//						target.appendJavaScript("$('.eALPSListRadio').click();");
					}

					@Override
					protected void onInitialize() {
						super.onInitialize();
					}

				});

				add(new AjaxLink<Void>("firstSemesterCheckButton") {
					private static final long serialVersionUID = 8964835272682940598L;

					@Override
					public void onClick(AjaxRequestTarget target) {
						target.appendJavaScript("$('#firstSemesterCheckButton').toggleClass('active');");
						target.appendJavaScript("$('#firstSemesterCheckButton').children().toggleClass('glyphicon-check');");
						target.appendJavaScript("$('.firstSemester').fadeToggle();");
					}

					@Override
					protected void onInitialize() {
						super.onInitialize();
						Label firstSemesterCheckButtonIcon = new Label("firstSemesterCheckButtonIcon","");
						firstSemesterCheckButtonIcon.add(new AttributeAppender("class", Model.of("glyphicon"), " "));
						this.add(firstSemesterCheckButtonIcon);

						this.add(new AttributeAppender("class", Model.of("btn btn-success firstSemesterCheck"), " "));
						if(StringUtils.equals(ChronoTrigger.getSemester(), "firstSemester")) {
							this.add(new AttributeAppender("class", Model.of("active"), " "));
							firstSemesterCheckButtonIcon.add(new AttributeAppender("class", Model.of("glyphicon-check"), " "));
						}
					}
				});

				add(new AjaxLink<Void>("secondSemesterCheckButton") {
					private static final long serialVersionUID = 669031140801566017L;

					@Override
					public void onClick(AjaxRequestTarget target) {
						target.appendJavaScript("$('#secondSemesterCheckButton').toggleClass('active');");
						target.appendJavaScript("$('#secondSemesterCheckButton').children().toggleClass('glyphicon-check');");
						target.appendJavaScript("$('.secondSemester').fadeToggle();");
					}

					@Override
					protected void onInitialize() {
						super.onInitialize();
						Label secondSemesterCheckButtonIcon = new Label("secondSemesterCheckButtonIcon","");
						secondSemesterCheckButtonIcon.add(new AttributeAppender("class", Model.of("glyphicon"), " "));
						this.add(secondSemesterCheckButtonIcon);

						this.add(new AttributeAppender("class", Model.of("btn btn-success secondSemesterCheck"), " "));
						if(StringUtils.equals(ChronoTrigger.getSemester(), "secondSemester")) {
							this.add(new AttributeAppender("class", Model.of("active"), " "));
							secondSemesterCheckButtonIcon.add(new AttributeAppender("class", Model.of("glyphicon-check"), " "));
						}
					}
				});

				add(new AjaxLink<Void>("throughSemesterCheckButton") {
					private static final long serialVersionUID = -1851547906748746440L;

					@Override
					public void onClick(AjaxRequestTarget target) {
						target.appendJavaScript("$('#throughSemesterCheckButton').toggleClass('active');");
						target.appendJavaScript("$('#throughSemesterCheckButton').children().toggleClass('glyphicon-check');");
						target.appendJavaScript("$('.throughSemester').fadeToggle();");
					}

					@Override
					protected void onInitialize() {
						super.onInitialize();
						Label throughSemesterCheckButtonIcon = new Label("throughSemesterCheckButtonIcon","");
						throughSemesterCheckButtonIcon.add(new AttributeAppender("class", Model.of("glyphicon glyphicon-check"), " "));
						this.add(throughSemesterCheckButtonIcon);
						this.add(new AttributeAppender("class", Model.of("btn btn-success throughSemesterCheck active"), " "));
					}
				});

				add(new AjaxLink<Void>("irregularSemesterCheckButton") {
					private static final long serialVersionUID = 3521967171284591732L;

					@Override
					public void onClick(AjaxRequestTarget target) {
						target.appendJavaScript("$('#irregularSemesterCheckButton').toggleClass('active');");
						target.appendJavaScript("$('#irregularSemesterCheckButton').children().toggleClass('glyphicon-check');");
						target.appendJavaScript("$('.irregularSemester').fadeToggle();");
					}

					@Override
					protected void onInitialize() {
						super.onInitialize();
						Label irregularSemesterCheckButtonIcon = new Label("irregularSemesterCheckButtonIcon","");
						irregularSemesterCheckButtonIcon.add(new AttributeAppender("class", Model.of("glyphicon glyphicon-check"), " "));
						this.add(irregularSemesterCheckButtonIcon);
						this.add(new AttributeAppender("class", Model.of("btn btn-success irregularSemesterCheck active"), " "));
					}
				});

				add(new AjaxLink<Void>("courseScheduleButton") {
					private static final long serialVersionUID = 904855932483517020L;

					@Override
					public void onClick(AjaxRequestTarget target) {
						target.appendJavaScript("$('.courseScheduleRadio').click();");
						scheduleHeadingLabelModel.setObject("年度 時間割");
						target.add(scheduleHeadingLabel);
					}

					@Override
					protected void onInitialize() {
						super.onInitialize();
						this.add(new Label("courseScheduleButtonLabel", "時間割"));
						this.add(new AttributeModifier("style", Model.of("display:none")));
					}

				});

				add(new AjaxLink<Void>("eALPSListButton") {
					private static final long serialVersionUID = -4117936053570493147L;

					@Override
					public void onClick(AjaxRequestTarget target) {
						target.appendJavaScript("$('.eALPSListRadio').click();");
						scheduleHeadingLabelModel.setObject("年度 学部リスト");
						target.add(scheduleHeadingLabel);
					}

					@Override
					protected void onInitialize() {
						super.onInitialize();
						this.add(new Label("eALPSListButtonLabel", "学部リスト"));
					}

				});
			}
		});

	}

}
