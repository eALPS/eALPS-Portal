/**
 *
 */
package jp.ac.shinshu.u.ealps.portal.bean;

import java.io.Serializable;
import java.util.List;

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

//	private String courseUID;
//	private String courseTitleCode;
//	private String courseTitleName;
//	private String regCode;
//	private String depCode;
//	private String courseOpLec;
//	private String courseURL;
////	private List<?> teacherAccountData;
////	private List<?> subTeacherAccountData;
//	private String teacherName;
//
//	/**
//	 *
//	 */
//	public RelationCourseBean() {
//
//	}
//
//	public String getCourseUID() {
//		return courseUID;
//	}
//
//	public void setCourseUID(String courseUID) {
//		this.courseUID = courseUID;
//	}
//
//	public String getCourseTitleCode() {
//		return courseTitleCode;
//	}
//
//	public void setCourseTitleCode(String courseTitleCode) {
//		this.courseTitleCode = courseTitleCode;
//	}
//
//	public String getCourseTitleName() {
//		return courseTitleName;
//	}
//
//	public void setCourseTitleName(String courseTitleName) {
//		this.courseTitleName = courseTitleName;
//	}
//
//	public String getRegCode() {
//		return regCode;
//	}
//
//	public void setRegCode(String regCode) {
//		this.regCode = regCode;
//	}
//
//	public String getDepCode() {
//		return depCode;
//	}
//
//	public void setDepCode(String depCode) {
//		this.depCode = depCode;
//	}
//
//	public String getCourseOpLec() {
//		return courseOpLec;
//	}
//
//	public void setCourseOpLec(String courseOpLec) {
//		this.courseOpLec = courseOpLec;
//	}
//
//	public String getCourseURL() {
//		return courseURL;
//	}
//
//	public void setCourseURL(String courseURL) {
//		this.courseURL = courseURL;
//	}
//
//	public String getTeacherName() {
//		return teacherName;
//	}
//
//	public void setTeacherName(String teacherName) {
//		this.teacherName = teacherName;
//	}

}
