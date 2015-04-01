/**
 * 
 */
package jp.ac.shinshu.u.ealps.portal.bean;

import java.io.Serializable;


/**
 * @author Osamu HASEGAWA
 *
 */
public class RelationCourseBean implements Serializable {

	private static final long serialVersionUID = -4137049995727575865L;
	private String courseUID;
	private String courseTitleCode;
	private String courseTitleName;
	private String regCode;
	private String depCode;
	private String courseOpLec;
	private String courseURL;
//	private List<?> teacherAccountData;
//	private List<?> subTeacherAccountData;
	private String teacherName;

	/**
	 * 
	 */
	public RelationCourseBean() {
		
	}

	public String getCourseUID() {
		return courseUID;
	}

	public void setCourseUID(String courseUID) {
		this.courseUID = courseUID;
	}

	public String getCourseTitleCode() {
		return courseTitleCode;
	}

	public void setCourseTitleCode(String courseTitleCode) {
		this.courseTitleCode = courseTitleCode;
	}

	public String getCourseTitleName() {
		return courseTitleName;
	}

	public void setCourseTitleName(String courseTitleName) {
		this.courseTitleName = courseTitleName;
	}

	public String getRegCode() {
		return regCode;
	}

	public void setRegCode(String regCode) {
		this.regCode = regCode;
	}

	public String getDepCode() {
		return depCode;
	}

	public void setDepCode(String depCode) {
		this.depCode = depCode;
	}

	public String getCourseOpLec() {
		return courseOpLec;
	}

	public void setCourseOpLec(String courseOpLec) {
		this.courseOpLec = courseOpLec;
	}

	public String getCourseURL() {
		return courseURL;
	}

	public void setCourseURL(String courseURL) {
		this.courseURL = courseURL;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	
	

}
