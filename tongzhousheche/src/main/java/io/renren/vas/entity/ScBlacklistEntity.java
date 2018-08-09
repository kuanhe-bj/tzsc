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
 * @date 2018-02-11 13:39:58
 */
public class ScBlacklistEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private int id;
	//
	private String carnum;
    @JsonSerialize(using=DateJsonSerializer.class)  
    @JsonDeserialize(using=DateJsonDeserializer.class)
	private Date createtime;
	//
	private String createuser;
	//
	private String createreason;
	//
	private String audituser;
    @JsonSerialize(using=DateJsonSerializer.class)  
    @JsonDeserialize(using=DateJsonDeserializer.class)
	private Date audittime;
	//
	private int auditresults;
	//
	private String auditopinion;
	
	private Date endtime;
	
	private int status;
	
	private int bkStatus;
//	布控id
	private int bkid;

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
	public void setCarnum(String carnum) {
		this.carnum = carnum;
	}
	/**
	 * 获取：
	 */
	public String getCarnum() {
		return carnum;
	}
	/**
	 * 设置：
	 */
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	/**
	 * 获取：
	 */
	public Date getCreatetime() {
		return createtime;
	}
	/**
	 * 设置：
	 */
	public void setCreateuser(String createuser) {
		this.createuser = createuser;
	}
	/**
	 * 获取：
	 */
	public String getCreateuser() {
		return createuser;
	}
	/**
	 * 设置：
	 */
	public void setCreatereason(String createreason) {
		this.createreason = createreason;
	}
	/**
	 * 获取：
	 */
	public String getCreatereason() {
		return createreason;
	}
	/**
	 * 设置：
	 */
	public void setAudituser(String audituser) {
		this.audituser = audituser;
	}
	/**
	 * 获取：
	 */
	public String getAudituser() {
		return audituser;
	}
	/**
	 * 设置：
	 */
	public void setAudittime(Date audittime) {
		this.audittime = audittime;
	}
	/**
	 * 获取：
	 */
	public Date getAudittime() {
		return audittime;
	}
	/**
	 * 设置：
	 */
	public void setAuditresults(int auditresults) {
		this.auditresults = auditresults;
	}
	/**
	 * 获取：
	 */
	public int getAuditresults() {
		return auditresults;
	}
	/**
	 * 设置：
	 */
	public void setAuditopinion(String auditopinion) {
		this.auditopinion = auditopinion;
	}
	/**
	 * 获取：
	 */
	public String getAuditopinion() {
		return auditopinion;
	}
	public Date getEndtime() {
		return endtime;
	}
	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getBkStatus() {
		return bkStatus;
	}
	public void setBkStatus(int bkStatus) {
		this.bkStatus = bkStatus;
	}
	public int getBkid() {
		return bkid;
	}
	public void setBkid(int bkid) {
		this.bkid = bkid;
	}
	
	
}
