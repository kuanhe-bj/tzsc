package io.renren.vas.entity;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import io.renren.common.utils.DateJsonDeserializer;
import io.renren.common.utils.DateJsonSerializer;


/**
 * 
 * 
 * @author pwk
 * @email sunlightcs@gmail.com
 * @date 2018-02-09 15:19:34
 */
public class ScBdqclEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private int id;
	//
	private String cph;
	//
	private String czxm;
	//
	private String czdzid;
	//
	private String dwmc;
	//
	private String dwdz;
	//
	private String czxxzd;
	//
	private String szxm;
	//
	private String szdzid;
	//
	private String szxz;
	
    @JsonSerialize(using=DateJsonSerializer.class)  
    @JsonDeserialize(using=DateJsonDeserializer.class)
	private Date operatetime;
	//
	private String ajid;

	/**
	 * 设置：
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public int getId() {
		return id;
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
	/**
	 * 设置：
	 */
	public void setCzxm(String czxm) {
		this.czxm = czxm;
	}
	/**
	 * 获取：
	 */
	public String getCzxm() {
		return czxm;
	}
	/**
	 * 设置：
	 */
	public void setCzdzid(String czdzid) {
		this.czdzid = czdzid;
	}
	/**
	 * 获取：
	 */
	public String getCzdzid() {
		return czdzid;
	}
	/**
	 * 设置：
	 */
	public void setDwmc(String dwmc) {
		this.dwmc = dwmc;
	}
	/**
	 * 获取：
	 */
	public String getDwmc() {
		return dwmc;
	}
	/**
	 * 设置：
	 */
	public void setDwdz(String dwdz) {
		this.dwdz = dwdz;
	}
	/**
	 * 获取：
	 */
	public String getDwdz() {
		return dwdz;
	}
	/**
	 * 设置：
	 */
	public void setCzxxzd(String czxxzd) {
		this.czxxzd = czxxzd;
	}
	/**
	 * 获取：
	 */
	public String getCzxxzd() {
		return czxxzd;
	}
	/**
	 * 设置：
	 */
	public void setSzxm(String szxm) {
		this.szxm = szxm;
	}
	/**
	 * 获取：
	 */
	public String getSzxm() {
		return szxm;
	}
	/**
	 * 设置：
	 */
	public void setSzdzid(String szdzid) {
		this.szdzid = szdzid;
	}
	/**
	 * 获取：
	 */
	public String getSzdzid() {
		return szdzid;
	}
	/**
	 * 设置：
	 */
	public void setSzxz(String szxz) {
		this.szxz = szxz;
	}
	/**
	 * 获取：
	 */
	public String getSzxz() {
		return szxz;
	}
	/**
	 * 设置：
	 */
	public void setOperatetime(Date operatetime) {
		this.operatetime = operatetime;
	}
	/**
	 * 获取：
	 */
	public Date getOperatetime() {
		return operatetime;
	}
	/**
	 * 设置：
	 */
	public void setAjid(String ajid) {
		this.ajid = ajid;
	}
	/**
	 * 获取：
	 */
	public String getAjid() {
		return ajid;
	}
}
