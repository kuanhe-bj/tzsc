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
public class ScViwEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private String id;
	//
	private String carnum;
    @JsonSerialize(using=DateJsonSerializer.class)  
    @JsonDeserialize(using=DateJsonDeserializer.class)
	private Date triggertime;
	//
	private String triggerreason;
	//
	private String readstate;
	//
	private String pushstate;

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
	public void setTriggerreason(String triggerreason) {
		this.triggerreason = triggerreason;
	}
	/**
	 * 获取：
	 */
	public String getTriggerreason() {
		return triggerreason;
	}
	/**
	 * 设置：
	 */
	public void setReadstate(String readstate) {
		this.readstate = readstate;
	}
	/**
	 * 获取：
	 */
	public String getReadstate() {
		return readstate;
	}
	/**
	 * 设置：
	 */
	public void setPushstate(String pushstate) {
		this.pushstate = pushstate;
	}
	/**
	 * 获取：
	 */
	public String getPushstate() {
		return pushstate;
	}
}
