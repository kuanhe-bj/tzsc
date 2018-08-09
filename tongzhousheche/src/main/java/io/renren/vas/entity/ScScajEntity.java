package io.renren.vas.entity;

import java.io.Serializable;
import java.util.Date;


/**
 * 
 * 
 * @author pwk
 * @email sunlightcs@gmail.com
 * @date 2018-02-09 15:19:34
 */
public class ScScajEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private String id;
	//
	private String ajbh;
	//
	private String ajmc;
	//
	private String pcsgxid;
	//
	private String jq;
	//
	private String faddid;
	//
	private String faddxz;
	//
	private Date fakssj;
	//
	private String cph;
	//
	private String jindu;
	//
	private String weidu;
	//
	private String jyaq;
	//
	
	private String gis;

	public String getJindu() {
		return jindu;
	}
	public void setJindu(String jindu) {
		this.jindu = jindu;
	}
	public String getWeidu() {
		return weidu;
	}
	public void setWeidu(String weidu) {
		this.weidu = weidu;
	}
	public String getJyaq() {
		return jyaq;
	}
	public void setJyaq(String jyaq) {
		this.jyaq = jyaq;
	}
	public String getGis() {
		return gis;
	}
	public void setGis(String gis) {
		this.gis = gis;
	}
	/**
	 * 设置：
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * 获取：
	 */
	public String getId() {
		return id;
	}

	/**
	 * 设置：
	 */
	public void setAjbh(String ajbh) {
		this.ajbh = ajbh;
	}
	/**
	 * 获取：
	 */
	public String getAjbh() {
		return ajbh;
	}
	/**
	 * 设置：
	 */
	public void setAjmc(String ajmc) {
		this.ajmc = ajmc;
	}
	/**
	 * 获取：
	 */
	public String getAjmc() {
		return ajmc;
	}
	/**
	 * 设置：
	 */
	public void setPcsgxid(String pcsgxid) {
		this.pcsgxid = pcsgxid;
	}
	/**
	 * 获取：
	 */
	public String getPcsgxid() {
		return pcsgxid;
	}
	/**
	 * 设置：
	 */
	public void setJq(String jq) {
		this.jq = jq;
	}
	/**
	 * 获取：
	 */
	public String getJq() {
		return jq;
	}
	/**
	 * 设置：
	 */
	public void setFaddid(String faddid) {
		this.faddid = faddid;
	}
	/**
	 * 获取：
	 */
	public String getFaddid() {
		return faddid;
	}
	/**
	 * 设置：
	 */
	public void setFaddxz(String faddxz) {
		this.faddxz = faddxz;
	}
	/**
	 * 获取：
	 */
	public String getFaddxz() {
		return faddxz;
	}
	/**
	 * 设置：
	 */
	public void setFakssj(Date fakssj) {
		this.fakssj = fakssj;
	}
	/**
	 * 获取：
	 */
	public Date getFakssj() {
		return fakssj;
	}
	/**
	 * 设置：
	 */
	public void setCph(String cph) {
		this.cph = cph;
	}
	/**
	 * 获取：
	 */
	public String getCph() {
		return cph;
	}
}
