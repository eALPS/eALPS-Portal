/**
 *
 */
package jp.ac.shinshu.u.ealps.portal.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jp.ac.shinshu.u.common.definition.HourCode;
import jp.ac.shinshu.u.common.definition.LecCode;
import jp.ac.shinshu.u.common.definition.WeekDayCode;
import jp.ac.shinshu.u.ealps.portal.bean.RelationCourseBean;
import jp.ac.shinshu.u.ealps.portal.dao.adb2.RelationDAO;
import jp.ac.shinshu.u.ealps.portal.entity.OpInfo;

import org.apache.commons.lang3.SerializationUtils;

/**
 * @author Osamu HASEGAWA
 *
 */
public class RelationCourseService implements IRelationCourseService {

	/**
	 * <p>デフォルトコンストラクタ</p>
	 */
	public RelationCourseService() {

	}

	@Override
	public List<RelationCourseBean> getRelationCourseBeanList(String uid) {
		List<RelationCourseBean> relationCourseBeanList;
		RelationDAO relationCourseDAO = new RelationDAO();
		relationCourseBeanList = relationCourseDAO.selectRelationCourseBeanList(uid);
		return relationCourseBeanList;
	}

	@Override
	public List<List<Set<RelationCourseBean>>> getCourseScheduleList(List<RelationCourseBean> relationCourseBeanList) {

		List<List<Set<RelationCourseBean>>> courseSchedulePeriodList = null;
		List<Set<RelationCourseBean>> courseScheduleWeekDayList = null;
		Set<RelationCourseBean> courseScheduleSet = null;

		courseSchedulePeriodList = new ArrayList<List<Set<RelationCourseBean>>>();
		for (int i = 0; i < 7; i++) {
			courseScheduleWeekDayList = new ArrayList<Set<RelationCourseBean>>();
			for (int j = 0; j < 6; j++) {
				courseScheduleSet = new HashSet<RelationCourseBean>();
				courseScheduleWeekDayList.add(courseScheduleSet);
			}
			courseSchedulePeriodList.add(courseScheduleWeekDayList);
		}

		int hour = 0;
		int weekDay = 0;
		for(RelationCourseBean relationCourseBean : relationCourseBeanList) {
			for(OpInfo opInfo : relationCourseBean.getOpInfoList()) {
				RelationCourseBean deepCopyrelationCourseBean = SerializationUtils.clone(relationCourseBean);
				hour = HourCode.valueOf("HOUR" + opInfo.getOpHour()).getHour();
				weekDay = WeekDayCode.valueOf("WEEKDAY" + opInfo.getOpWday()).getWeekDay();
				deepCopyrelationCourseBean.setLecClass(LecCode.valueOf("LEC" + opInfo.getOpLec()).getLecClass());
				if(hour == 100 || weekDay == 100) {
					deepCopyrelationCourseBean.setVisible(false);
					deepCopyrelationCourseBean.setScheduleCourse(false);
				} else if (hour == 99 || weekDay == 99) {
					deepCopyrelationCourseBean.setVisible(true);
					deepCopyrelationCourseBean.setScheduleCourse(false);
				} else {
					deepCopyrelationCourseBean.setVisible(true);
					deepCopyrelationCourseBean.setScheduleCourse(true);
					courseSchedulePeriodList.get(hour).get(weekDay).add(deepCopyrelationCourseBean);
				}
			}
		}
		return courseSchedulePeriodList;
	}

}
