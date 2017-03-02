/**
 *
 */
package jp.ac.shinshu.u.ealps.portal.entity;

import java.io.Serializable;

/**
 * @author Osamu HASEGAWA
 *
 */
public class SiteInfo implements Serializable {

	private static final long serialVersionUID = -6638541549278760081L;
	private int siteInfoId;
	private String siteUId;
	private int opYear;
	private int dispOrder;
	private int visible;
	private String caption;
	private String domainName;
	private String token;
	private int commonSite;

	public int getSiteInfoId() {
		return siteInfoId;
	}
	public void setSiteInfoId(int siteInfoId) {
		this.siteInfoId = siteInfoId;
	}
	public String getSiteUId() {
		return siteUId;
	}
	public void setSiteUId(String siteUId) {
		this.siteUId = siteUId;
	}
	public int getOpYear() {
		return opYear;
	}
	public void setOpYear(int opYear) {
		this.opYear = opYear;
	}
	public int getDispOrder() {
		return dispOrder;
	}
	public void setDispOrder(int dispOrder) {
		this.dispOrder = dispOrder;
	}
	public int getVisible() {
		return visible;
	}
	public void setVisible(int visible) {
		this.visible = visible;
	}
	public String getCaption() {
		return caption;
	}
	public void setCaption(String caption) {
		this.caption = caption;
	}
	public String getDomainName() {
		return domainName;
	}
	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public int getCommonSite() {
		return commonSite;
	}
	public void setCommonSite(int commonSite) {
		this.commonSite = commonSite;
	}

}
