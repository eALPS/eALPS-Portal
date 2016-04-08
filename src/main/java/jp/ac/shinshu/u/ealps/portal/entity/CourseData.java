/**
 *
 */
package jp.ac.shinshu.u.ealps.portal.entity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author Osamu HASEGAWA
 *
 */
public class CourseData implements Serializable {

	private static final long serialVersionUID = -7980711847205943757L;

	private int rowId;
	private String uid;
	private String titleCode;
	private String titleName;
	private String regCode;
	private String depCode;
	private String siteUid;
	private String opFlag;
	private int opYear;
	private String opLec;
	private int toLMS;
	private int listId;
	private int delFlag;
	private int mdlId;
	private int mdlCatId;
	private String memo;
	private Timestamp originalDate;
	private Timestamp updateDate;
	private int catId;
	private String url;
	private String optUrl;
	private int dataFrom;
	private String fileUrl;

	public int getRowId() {
		return rowId;
	}
	public void setRowId(int rowId) {
		this.rowId = rowId;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getTitleCode() {
		return titleCode;
	}
	public void setTitleCode(String titleCode) {
		this.titleCode = titleCode;
	}
	public String getTitleName() {
		return titleName;
	}
	public void setTitleName(String titleName) {
		this.titleName = titleName;
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
	public String getSiteUid() {
		return siteUid;
	}
	public void setSiteUid(String siteUid) {
		this.siteUid = siteUid;
	}
	public String getOpFlag() {
		return opFlag;
	}
	public void setOpFlag(String opFlag) {
		this.opFlag = opFlag;
	}
	public int getOpYear() {
		return opYear;
	}
	public void setOpYear(int opYear) {
		this.opYear = opYear;
	}
	public String getOpLec() {
		return opLec;
	}
	public void setOpLec(String opLec) {
		this.opLec = opLec;
	}
	public int getToLMS() {
		return toLMS;
	}
	public void setToLMS(int toLMS) {
		this.toLMS = toLMS;
	}
	public int getListId() {
		return listId;
	}
	public void setListId(int listId) {
		this.listId = listId;
	}
	public int getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(int delFlag) {
		this.delFlag = delFlag;
	}
	public int getMdlId() {
		return mdlId;
	}
	public void setMdlId(int mdlId) {
		this.mdlId = mdlId;
	}
	public int getMdlCatId() {
		return mdlCatId;
	}
	public void setMdlCatId(int mdlCatId) {
		this.mdlCatId = mdlCatId;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public Timestamp getOriginalDate() {
		return originalDate;
	}
	public void setOriginalDate(Timestamp originalDate) {
		this.originalDate = originalDate;
	}
	public Timestamp getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Timestamp updateDate) {
		this.updateDate = updateDate;
	}
	public int getCatId() {
		return catId;
	}
	public void setCatId(int catId) {
		this.catId = catId;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getOptUrl() {
		return optUrl;
	}
	public void setOptUrl(String optUrl) {
		this.optUrl = optUrl;
	}
	public int getDataFrom() {
		return dataFrom;
	}
	public void setDataFrom(int dataFrom) {
		this.dataFrom = dataFrom;
	}
	public String getFileUrl() {
		return fileUrl;
	}
	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

}
