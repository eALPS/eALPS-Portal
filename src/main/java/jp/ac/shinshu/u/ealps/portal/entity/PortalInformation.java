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
public class PortalInformation implements Serializable {

	private static final long serialVersionUID = 3389321900267544266L;
	
	private int id;
	private String title;
	private String body;
	private int importanceTypeID;
	private int addressTypeID;
	private Timestamp open;
	private Timestamp close;
	private Timestamp insert;
	private Timestamp update;
	private Timestamp delete;
	private boolean availability;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public int getImportanceTypeID() {
		return importanceTypeID;
	}
	public void setImportanceTypeID(int importanceTypeID) {
		this.importanceTypeID = importanceTypeID;
	}
	public int getAddressTypeID() {
		return addressTypeID;
	}
	public void setAddressTypeID(int addressTypeID) {
		this.addressTypeID = addressTypeID;
	}
	public Timestamp getOpen() {
		return open;
	}
	public void setOpen(Timestamp open) {
		this.open = open;
	}
	public Timestamp getClose() {
		return close;
	}
	public void setClose(Timestamp close) {
		this.close = close;
	}
	public Timestamp getInsert() {
		return insert;
	}
	public void setInsert(Timestamp insert) {
		this.insert = insert;
	}
	public Timestamp getUpdate() {
		return update;
	}
	public void setUpdate(Timestamp update) {
		this.update = update;
	}
	public Timestamp getDelete() {
		return delete;
	}
	public void setDelete(Timestamp delete) {
		this.delete = delete;
	}
	public boolean isAvailability() {
		return availability;
	}
	public void setAvailability(boolean availability) {
		this.availability = availability;
	}

}
