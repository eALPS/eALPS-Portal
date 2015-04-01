/**
 *
 */
package jp.ac.shinshu.u.ealps.portal.entity;

import java.io.Serializable;

/**
 * @author Osamu HASEGAWA
 *
 */
public class OpInfo implements Serializable {

	private static final long serialVersionUID = -1625117181651785498L;
	private int opinfoId;
	private int courseId;
	private String opLec;
	private String opWday;
	private String opHour;
	private int campus;

	public int getOpinfoId() {
		return opinfoId;
	}
	public void setOpinfoId(int opinfoId) {
		this.opinfoId = opinfoId;
	}
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public String getOpLec() {
		return opLec;
	}
	public void setOpLec(String opLec) {
		this.opLec = opLec;
	}
	public String getOpWday() {
		return opWday;
	}
	public void setOpWday(String opWday) {
		this.opWday = opWday;
	}
	public String getOpHour() {
		return opHour;
	}
	public void setOpHour(String opHour) {
		this.opHour = opHour;
	}
	public int getCampus() {
		return campus;
	}
	public void setCampus(int campus) {
		this.campus = campus;
	}

}
