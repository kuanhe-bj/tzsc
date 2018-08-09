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
 * @date 2018-02-11 13:53:44
 */
public class ScAlarminfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private int id;
	//
	private String carnum;
    @JsonSerialize(using=DateJsonSerializer.class)  
    @JsonDeserialize(using=DateJsonDeserializer.class)
	private Date triggertime;
	//
	private String blackid;
	//
	private String datasource;
	//
	private String datasourceid;
	//
	private int readstate;
	//
	private int pushstate;
	
	private String receiver;
	
	private String message;
	
	private int num;
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
	public void setTriggertime(Date triggertime) {
		this.triggertime = triggertime;
	}
	/**
	 * 获取：
	 */
	public Date getTriggertime() {
		return triggertime;
	}
	/**
	 * 设置：
	 */
	public void setBlackid(String blackid) {
		this.blackid = blackid;
	}
	/**
	 * 获取：
	 */
	public String getBlackid() {
		return blackid;
	}
	/**
	 * 设置：
	 */
	public void setDatasource(String datasource) {
		this.datasource = datasource;
	}
	/**
	 * 获取：
	 */
	public String getDatasource() {
		return datasource;
	}
	/**
	 * 设置：
	 */
	public void setDatasourceid(String datasourceid) {
		this.datasourceid = datasourceid;
	}
	/**
	 * 获取：
	 */
	public String getDatasourceid() {
		return datasourceid;
	}
	/**
	 * 设置：
	 */
	public void setReadstate(int readstate) {
		this.readstate = readstate;
	}
	/**
	 * 获取：
	 */
	public int getReadstate() {
		return readstate;
	}
	/**
	 * 设置：
	 */
	public void setPushstate(int pushstate) {
		this.pushstate = pushstate;
	}
	/**
	 * 获取：
	 */
	public int getPushstate() {
		return pushstate;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	
	
}
