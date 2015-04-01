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
public class Relation implements Serializable {

	private static final long serialVersionUID = 5242180559214715290L;

	private int relId;
	private int listId;
	private String chain;
	private String uid;
	private int roleId;
	private int enable;
	private Timestamp updateAt;
	public int getRelId() {
		return relId;
	}
	public void setRelId(int relId) {
		this.relId = relId;
	}
	public int getListId() {
		return listId;
	}
	public void setListId(int listId) {
		this.listId = listId;
	}
	public String getChain() {
		return chain;
	}
	public void setChain(String chain) {
		this.chain = chain;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public int getEnable() {
		return enable;
	}
	public void setEnable(int enable) {
		this.enable = enable;
	}
	public Timestamp getUpdateAt() {
		return updateAt;
	}
	public void setUpdateAt(Timestamp updateAt) {
		this.updateAt = updateAt;
	}

}
