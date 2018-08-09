package io.renren.vas.entity;

import java.io.Serializable;


/**
 * 
 * 
 * @author pwk
 * @email sunlightcs@gmail.com
 * @date 2018-02-26 11:56:26
 */
public class ScTravelaaEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private String id;
	//
	private String carnum;
	//
	private String abnormal3month;
	//
	private String abnormal6month;
	//
	private String abnormal12month;
	//
	private String abnormaltotal;
	//
	private String travelabnormal;

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
	public void setAbnormal3month(String abnormal3month) {
		this.abnormal3month = abnormal3month;
	}
	/**
	 * 获取：
	 */
	public String getAbnormal3month() {
		return abnormal3month;
	}
	/**
	 * 设置：
	 */
	public void setAbnormal6month(String abnormal6month) {
		this.abnormal6month = abnormal6month;
	}
	/**
	 * 获取：
	 */
	public String getAbnormal6month() {
		return abnormal6month;
	}
	/**
	 * 设置：
	 */
	public void setAbnormal12month(String abnormal12month) {
		this.abnormal12month = abnormal12month;
	}
	/**
	 * 获取：
	 */
	public String getAbnormal12month() {
		return abnormal12month;
	}
	/**
	 * 设置：
	 */
	public void setAbnormaltotal(String abnormaltotal) {
		this.abnormaltotal = abnormaltotal;
	}
	/**
	 * 获取：
	 */
	public String getAbnormaltotal() {
		return abnormaltotal;
	}
	/**
	 * 设置：
	 */
	public void setTravelabnormal(String travelabnormal) {
		this.travelabnormal = travelabnormal;
	}
	/**
	 * 获取：
	 */
	public String getTravelabnormal() {
		return travelabnormal;
	}
}
