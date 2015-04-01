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
public class AccountData implements Serializable {

	private static final long serialVersionUID = -1531425737153201451L;

	private int rowId;
	private String uid;
	private String id;
	private String uidNumber;
	private String password;
	private String affili;
	private String name;
	private String firstName;
	private String lastName;
	private String mail;
	private String subMail;
	private String facultyNumber;
	private int enable;
	private Timestamp updateDate;
	private int dataFrom;
	private String memo;

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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUidNumber() {
		return uidNumber;
	}
	public void setUidNumber(String uidNumber) {
		this.uidNumber = uidNumber;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAffili() {
		return affili;
	}
	public void setAffili(String affili) {
		this.affili = affili;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getSubMail() {
		return subMail;
	}
	public void setSubMail(String subMail) {
		this.subMail = subMail;
	}
	public String getFacultyNumber() {
		return facultyNumber;
	}
	public void setFacultyNumber(String facultyNumber) {
		this.facultyNumber = facultyNumber;
	}
	public int getEnable() {
		return enable;
	}
	public void setEnable(int enable) {
		this.enable = enable;
	}
	public Timestamp getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Timestamp updateDate) {
		this.updateDate = updateDate;
	}
	public int getDataFrom() {
		return dataFrom;
	}
	public void setDataFrom(int dataFrom) {
		this.dataFrom = dataFrom;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}

}
