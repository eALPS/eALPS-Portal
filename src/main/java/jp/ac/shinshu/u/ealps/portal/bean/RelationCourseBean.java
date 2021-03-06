/**
 *
 */
package jp.ac.shinshu.u.ealps.portal.bean;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jp.ac.shinshu.u.common.definition.HourCode;
import jp.ac.shinshu.u.common.definition.LecCode;
import jp.ac.shinshu.u.common.definition.WeekDayCode;
import jp.ac.shinshu.u.ealps.portal.entity.AccountData;
import jp.ac.shinshu.u.ealps.portal.entity.CourseData;
import jp.ac.shinshu.u.ealps.portal.entity.OpInfo;
import jp.ac.shinshu.u.ealps.portal.entity.Relation;

/**
 * @author Osamu HASEGAWA
 *
 */
public class RelationCourseBean implements Serializable {

	private static final long serialVersionUID = -4137049995727575865L;

	private Relation relation;
	private CourseData courseData;
	private List<AccountData> teacherList;
	private List<AccountData> subTeacherList;
	private List<OpInfo> opInfoList;
	private String lecClass;
	private boolean scheduleCourse;
	private boolean visible;

	public Relation getRelation() {
		return relation;
	}

	public void setRelation(Relation relation) {
		this.relation = relation;
	}

	public CourseData getCourseData() {
		return courseData;
	}

	public void setCourseData(CourseData courseData) {
		this.courseData = courseData;
	}

	public List<AccountData> getTeacherList() {
		return teacherList;
	}

	public void setTeacherList(List<AccountData> teacherList) {
		this.teacherList = teacherList;
	}

	public List<AccountData> getSubTeacherList() {
		return subTeacherList;
	}

	public void setSubTeacherList(List<AccountData> subTeacherList) {
		this.subTeacherList = subTeacherList;
	}

	public List<OpInfo> getOpInfoList() {
		return opInfoList;
	}

	public void setOpInfoList(List<OpInfo> opInfoList) {
		this.opInfoList = opInfoList;
	}

	public String getLecClass() {
		return lecClass;
	}

	public void setLecClass(String lecClass) {
		this.lecClass = lecClass;
	}

	public boolean isScheduleCourse() {
		return scheduleCourse;
	}

	public void setScheduleCourse(boolean scheduleCourse) {
		this.scheduleCourse = scheduleCourse;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public String getSubTeacherNameList() {
		StringBuilder subTeacherNameList = new StringBuilder();
		for (AccountData accountData : subTeacherList) {
			subTeacherNameList.append(accountData.getFirstName());
			subTeacherNameList.append(" ");
			subTeacherNameList.append(accountData.getLastName());
			subTeacherNameList.append(", ");
		}
		if (!subTeacherList.isEmpty()) {
			subTeacherNameList.delete(subTeacherNameList.lastIndexOf(","), subTeacherNameList.length());
		}
		return subTeacherNameList.toString();
	}

	public String getOpInfoValue() {
		StringBuilder opInfoValue = new StringBuilder();
		for (OpInfo opInfo : opInfoList) {
			opInfoValue.append(LecCode.valueOf("LEC" + opInfo.getOpLec()).getTitle());
			opInfoValue.append(" ");
			opInfoValue.append(WeekDayCode.valueOf("WEEKDAY" + opInfo.getOpWday()).getTitle());
			opInfoValue.append(" ");
			opInfoValue.append(HourCode.valueOf("HOUR" + opInfo.getOpHour()).getTitle());
			opInfoValue.append(", ");
		}
		if (!opInfoList.isEmpty()) {
			opInfoValue.delete(opInfoValue.lastIndexOf(","), opInfoValue.length());
		}
		return opInfoValue.toString();
	}

	public String getOpLecValue() {
		Set<String> opInfoValueHashSet = new HashSet<String>();
		StringBuilder opInfoValue = new StringBuilder();
		for (OpInfo opInfo : opInfoList) {
			if(!opInfoValueHashSet.contains(LecCode.valueOf("LEC" + opInfo.getOpLec()).getTitle())) {
				opInfoValueHashSet.add(LecCode.valueOf("LEC" + opInfo.getOpLec()).getTitle());
				opInfoValue.append(LecCode.valueOf("LEC" + opInfo.getOpLec()).getTitle());
				opInfoValue.append(", ");
			}
		}
		if (!opInfoList.isEmpty()) {
			opInfoValue.delete(opInfoValue.lastIndexOf(","), opInfoValue.length());
		}
		return opInfoValue.toString();
	}

	public String getCourseInformationHTMLCode() {
		StringBuilder htmlCode = new StringBuilder();

		htmlCode.append("<p><span class=\"glyphicon glyphicon-time\"></span>&nbsp;開講年度：");
		htmlCode.append(courseData.getOpYear() == 9999 ? "年度共通" : courseData.getOpYear());
		htmlCode.append("</p>");

		htmlCode.append("<p><span class=\"glyphicon glyphicon-time\"></span>&nbsp;開講時期：");
		htmlCode.append(getOpLecValue());
		htmlCode.append("</p>");

		htmlCode.append("<p><span class=\"glyphicon glyphicon-tag\"></span>&nbsp;題目コード：");
		htmlCode.append(courseData.getTitleCode());
		htmlCode.append("</p>");

		htmlCode.append("<p><span class=\"glyphicon glyphicon-tag\"></span>&nbsp;登録コード：");
		htmlCode.append(courseData.getRegCode());
		htmlCode.append("</p>");

		htmlCode.append("<p><span class=\"glyphicon glyphicon-user\"></span>&nbsp;主担当教員：");
		htmlCode.append(getTeacherList().isEmpty() ? "" : getTeacherList().get(0).getFirstName() + " " + getTeacherList().get(0).getLastName());
		htmlCode.append("</p>");

		htmlCode.append("<p title=\"");
		htmlCode.append(getSubTeacherList().size() == 1 ? "副担当教員" : "副担当教員：" + getSubTeacherNameList());
		htmlCode.append("\"><span class=\"glyphicon glyphicon-user\"></span>&nbsp;副担当教員：");
		htmlCode.append(getSubTeacherList().size() == 1 ? getSubTeacherList().get(0).getFirstName() + " " + getSubTeacherList().get(0).getLastName() : "...");
		htmlCode.append("</p>");

		return htmlCode.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((courseData.getUid() == null) ? 0 : courseData.getUid().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;

		RelationCourseBean other = (RelationCourseBean) obj;
		if (courseData.getUid() == null) {
			if (other.getCourseData().getUid() != null) {
				return false;
			}
		} else if (!(courseData.getUid().equals(other.getCourseData().getUid()) && lecClass.equals(other.getLecClass()))) {
			// uidと開講時期が等しかった場合同一とみなす
			return false;
		}
		return true;
	}

}
