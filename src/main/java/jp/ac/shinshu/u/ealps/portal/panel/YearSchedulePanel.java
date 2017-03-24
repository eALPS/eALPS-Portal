/**
 *
 */
package jp.ac.shinshu.u.ealps.portal.panel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jp.ac.shinshu.u.ealps.portal.bean.RelationCourseBean;
import jp.ac.shinshu.u.ealps.portal.entity.SiteInfo;
import jp.ac.shinshu.u.ealps.portal.service.IEALPSListService;
import jp.ac.shinshu.u.ealps.portal.service.IRelationCourseService;

import org.apache.commons.lang3.StringUtils;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.ExternalLink;
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
public class YearSchedulePanel extends Panel {

	/**
	 *
	 */
	private static final long serialVersionUID = 4238633461728986761L;

	@Inject
	private IRelationCourseService relationCourseService;
	@Inject
	private IEALPSListService ealpsListService;

	private IModel<List<RelationCourseBean>> relationCourseBeanListModel;
	private IModel<Integer> yearLoadableDetachableModel;
	private IModel<List<List<Set<RelationCourseBean>>>> courseScheduleListModel;
	private IModel<List<SiteInfo>> siteInfoListModel;

	private Set<String> checkCourseRepitition;

	/**
	 * @param id
	 */
	public YearSchedulePanel(String id) {
		super(id);
	}

	/**
	 * @param id
	 * @param yearModel
	 * @param userIdModel
	 */
	public YearSchedulePanel(String id, IModel<Integer> yearModel, IModel<String> userIdModel) {
		super(id);
		drow(yearModel, userIdModel);
	}

	private void drow(final IModel<Integer> yearModel, final IModel<String> userIdModel){

		// ajax更新のためにLoadableDetachableModelに格納
		yearLoadableDetachableModel = new LoadableDetachableModel<Integer>() {
			private static final long serialVersionUID = 8950779461219901164L;
			@Override
			protected Integer load() {
				return yearModel.getObject();
			}
		};

		// 履修情報から取得するコースリスト
		relationCourseBeanListModel = new LoadableDetachableModel<List<RelationCourseBean>>() {
			private static final long serialVersionUID = 8993056010257360948L;
			@Override
			protected List<RelationCourseBean> load() {
				return relationCourseService.getRelationCourseBeanList(userIdModel.getObject(), yearModel.getObject());
			}
		};

		// 履修情報コースリストを基に時間割用のModelを生成
		courseScheduleListModel = new LoadableDetachableModel<List<List<Set<RelationCourseBean>>>>() {
			private static final long serialVersionUID = -2403192669728340428L;
			@Override
			protected List<List<Set<RelationCourseBean>>> load() {
				return relationCourseService.getCourseScheduleList(relationCourseBeanListModel.getObject());
			}
		};

		//該当年度の学部リストを取得
		siteInfoListModel = new LoadableDetachableModel<List<SiteInfo>>() {
			private static final long serialVersionUID = -729636742110845049L;
			@Override
			protected List<SiteInfo> load() {
				return ealpsListService.getEALPSSiteInfoList(yearModel.getObject());
			}
		};

		add(new WebMarkupContainer("courseSchedule") {
			private static final long serialVersionUID = -4414769783048460641L;

			@Override
			protected void onInitialize() {
				super.onInitialize();

				PropertyListView<List<Set<RelationCourseBean>>> courseSchedulePeriodListView = new PropertyListView<List<Set<RelationCourseBean>>>("courseSchedulePeriodListView", courseScheduleListModel) {
					private static final long serialVersionUID = 2927156290884480612L;
					@Override
					protected void populateItem(ListItem<List<Set<RelationCourseBean>>> listItem) {
						listItem.add(new Label("period", listItem.getIndex()+1+"時限"));

						PropertyListView<Set<RelationCourseBean>> courseScheduleWeekDayListView = new PropertyListView<Set<RelationCourseBean>>("courseScheduleWeekDayListView", listItem.getModel()) {
							private static final long serialVersionUID = -6532971349026986813L;
							@Override
							protected void populateItem(ListItem<Set<RelationCourseBean>> listItem) {

								List<RelationCourseBean> listItemToList = new ArrayList<RelationCourseBean>(listItem.getModelObject());

								PropertyListView<RelationCourseBean> courseScheduleListView = new PropertyListView<RelationCourseBean>("courseScheduleListView", listItemToList) {
									private static final long serialVersionUID = -8275367648185376988L;
									@Override
									protected void populateItem(ListItem<RelationCourseBean> listItem) {
										listItem.add(new AttributeAppender("class", Model.of(listItem.getModelObject().getLecClass())," "));
										listItem.add(new ExternalLink("courseData.url", StringUtils.isBlank(listItem.getModelObject().getCourseData().getOptUrl()) ? listItem.getModelObject().getCourseData().getUrl() : listItem.getModelObject().getCourseData().getOptUrl(), listItem.getModelObject().getCourseData().getTitleName()));
	//									listItem.add(new Label("courseData.titleCode"));
//										listItem.add(new Label("teacherName", listItem.getModelObject().getTeacherList().isEmpty() ? "" : listItem.getModelObject().getTeacherList().get(0).getFirstName() + " " + listItem.getModelObject().getTeacherList().get(0).getLastName()));
//										listItem.add(new Label("subTeacherName", listItem.getModelObject().getSubTeacherList().size() == 1 ? listItem.getModelObject().getSubTeacherList().get(0).getFirstName() + " " + listItem.getModelObject().getSubTeacherList().get(0).getLastName() : "...").add(new AttributeAppender("title", Model.of(listItem.getModelObject().getSubTeacherList().size() == 1 ? "副担当教員" : "副担当教員：" + listItem.getModelObject().getSubTeacherNameList()))));

										ExternalLink fileURL = new ExternalLink("courseData.fileUrl", listItem.getModelObject().getCourseData().getFileUrl(), StringUtils.isBlank(listItem.getModelObject().getCourseData().getFileLinkName()) ? "関連外部リンク" : listItem.getModelObject().getCourseData().getFileLinkName());
										if(StringUtils.isBlank(listItem.getModelObject().getCourseData().getFileUrl())) {
											fileURL.setVisible(false);
										}
										listItem.add(fileURL);
										listItem.add(new Label("courseInformation","コース情報").add(new AttributeAppender("data-content",listItem.getModelObject().getCourseInformationHTMLCode())));
									}
								};
								listItem.add(courseScheduleListView);

							}
						};
						listItem.add(courseScheduleWeekDayListView);
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
				this.add(courseSchedulePeriodListView);
			}
		});

		add(new WebMarkupContainer("nonScheduleCourseList") {
			private static final long serialVersionUID = 6244941068825497509L;

			@Override
			protected void onInitialize() {
				super.onInitialize();
				// 重複確認用 Set
				checkCourseRepitition = new HashSet<String>();

				PropertyListView<RelationCourseBean> nonScheduleCourseListView = new PropertyListView<RelationCourseBean>("nonScheduleCourseListView", relationCourseBeanListModel) {
					private static final long serialVersionUID = -4452224939836409982L;
					@Override
					protected void populateItem(final ListItem<RelationCourseBean> listItem) {
						listItem.add(new AttributeAppender("class", Model.of(listItem.getModelObject().getLecClass())," "));
						listItem.add(new ExternalLink("courseData.url", StringUtils.isBlank(listItem.getModelObject().getCourseData().getOptUrl()) ? listItem.getModelObject().getCourseData().getUrl() : listItem.getModelObject().getCourseData().getOptUrl(), listItem.getModelObject().getCourseData().getTitleName()));
//						listItem.add(new Label("courseData.titleCode"));
//						listItem.add(new Label("teacherName", listItem.getModelObject().getTeacherList().isEmpty() ? "" : listItem.getModelObject().getTeacherList().get(0).getFirstName() + " " + listItem.getModelObject().getTeacherList().get(0).getLastName()));
//						listItem.add(new Label("subTeacherName", listItem.getModelObject().getSubTeacherList().size() == 1 ? listItem.getModelObject().getSubTeacherList().get(0).getFirstName() + " " + listItem.getModelObject().getSubTeacherList().get(0).getLastName() : "...").add(new AttributeAppender("title", Model.of(listItem.getModelObject().getSubTeacherList().size() == 1 ? "副担当教員" : "副担当教員：" + listItem.getModelObject().getSubTeacherNameList()))));
//						listItem.add(new Label("courseData.opYear", listItem.getModelObject().getCourseData().getOpYear() == 9999 ? "年度共通" : listItem.getModelObject().getCourseData().getOpYear()));
//						listItem.add(new Label("opInfoValue"));
						ExternalLink fileURL = new ExternalLink("courseData.fileUrl", listItem.getModelObject().getCourseData().getFileUrl(), StringUtils.isBlank(listItem.getModelObject().getCourseData().getFileLinkName()) ? "関連外部リンク" : listItem.getModelObject().getCourseData().getFileLinkName());
						if(StringUtils.isBlank(listItem.getModelObject().getCourseData().getFileUrl())) {
							fileURL.setVisible(false);
						}
						listItem.add(fileURL);
						listItem.add(new Label("courseInformation","コース情報").add(new AttributeAppender("data-content",listItem.getModelObject().getCourseInformationHTMLCode())));
						listItem.setVisible(!listItem.getModelObject().isScheduleCourse() && checkCourseRepitition.add(listItem.getModelObject().getCourseData().getUid()));
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
				this.add(nonScheduleCourseListView);
			}

			@Override
			protected void onConfigure() {
				super.onConfigure();
				// ajax更新の際に重複確認用のSetを初期化
				checkCourseRepitition = new HashSet<String>();
			}
		});

		add(new WebMarkupContainer("courseList") {
			private static final long serialVersionUID = -2280591673089064874L;
			@Override
			protected void onInitialize() {
				super.onInitialize();
				this.add(new AttributeModifier("style", Model.of("display:none")));

				final PropertyListView<RelationCourseBean> courseListView = new PropertyListView<RelationCourseBean>("courseListView", relationCourseBeanListModel) {
					private static final long serialVersionUID = 8465981643504442130L;
					@Override
					protected void populateItem(final ListItem<RelationCourseBean> listItem) {
						listItem.add(new AttributeAppender("class", Model.of(listItem.getModelObject().getLecClass())," "));
						listItem.add(new ExternalLink("courseData.url", StringUtils.isBlank(listItem.getModelObject().getCourseData().getOptUrl()) ? listItem.getModelObject().getCourseData().getUrl() : listItem.getModelObject().getCourseData().getOptUrl(), listItem.getModelObject().getCourseData().getTitleName()));
//						listItem.add(new Label("courseData.titleCode"));
//						listItem.add(new Label("teacherName", listItem.getModelObject().getTeacherList().isEmpty() ? "" : listItem.getModelObject().getTeacherList().get(0).getFirstName() + " " + listItem.getModelObject().getTeacherList().get(0).getLastName()));
//						listItem.add(new Label("subTeacherName", listItem.getModelObject().getSubTeacherList().size() == 1 ? listItem.getModelObject().getSubTeacherList().get(0).getFirstName() + " " + listItem.getModelObject().getSubTeacherList().get(0).getLastName() : "...").add(new AttributeAppender("title", Model.of(listItem.getModelObject().getSubTeacherList().size() == 1 ? "副担当教員" : "副担当教員：" + listItem.getModelObject().getSubTeacherNameList()))));
//						listItem.add(new Label("courseData.opYear", listItem.getModelObject().getCourseData().getOpYear() == 9999 ? "年度共通" : listItem.getModelObject().getCourseData().getOpYear()));
//						listItem.add(new Label("opInfoValue"));
						ExternalLink fileURL = new ExternalLink("courseData.fileUrl", listItem.getModelObject().getCourseData().getFileUrl(), StringUtils.isBlank(listItem.getModelObject().getCourseData().getFileLinkName()) ? "関連外部リンク" : listItem.getModelObject().getCourseData().getFileLinkName());
						if(StringUtils.isBlank(listItem.getModelObject().getCourseData().getFileUrl())) {
							fileURL.setVisible(false);
						}
						listItem.add(fileURL);
						listItem.add(new Label("courseInformation","コース情報").add(new AttributeAppender("data-content",listItem.getModelObject().getCourseInformationHTMLCode())));
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
				this.add(courseListView);
			}
		});

		add(new WebMarkupContainer("eALPSList", yearLoadableDetachableModel){
			private static final long serialVersionUID = -4414769783048460641L;

			@Override
			protected void onInitialize() {
				super.onInitialize();
				add(new AttributeModifier("style", Model.of("display:none")));

				PropertyListView<SiteInfo> eALPSListView = new PropertyListView<SiteInfo>("eALPSListView", siteInfoListModel) {
					private static final long serialVersionUID = -7900282300765683317L;

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
				this.add(eALPSListView);
			}
		});
	}

}
