package io.renren.vas.entity;

import java.io.Serializable;


/**
 * 
 * 
 * @author pwk
 * @email sunlightcs@gmail.com
 * @date 2018-02-11 13:39:58
 */
public class ScIllegalaaEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private String id;
	//
	private String carnum;
	//
	private String illegal3month;
	//
	private String illegal6month;
	//
	private String illegal12month;
	//
	private String illegaltotal;
	//
	private String illegalabnormal;

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
	public void setIllegal3month(String illegal3month) {
		this.illegal3month = illegal3month;
	}
	/**
	 * 获取：
	 */
	public String getIllegal3month() {
		return illegal3month;
	}
	/**
	 * 设置：
	 */
	public void setIllegal6month(String illegal6month) {
		this.illegal6month = illegal6month;
	}
	/**
	 * 获取：
	 */
	public String getIllegal6month() {
		return illegal6month;
	}
	/**
	 * 设置：
	 */
	public void setIllegal12month(String illegal12month) {
		this.illegal12month = illegal12month;
	}
	/**
	 * 获取：
	 */
	public String getIllegal12month() {
		return illegal12month;
	}
	/**
	 * 设置：
	 */
	public void setIllegaltotal(String illegaltotal) {
		this.illegaltotal = illegaltotal;
	}
	/**
	 * 获取：
	 */
	public String getIllegaltotal() {
		return illegaltotal;
	}
	/**
	 * 设置：
	 */
	public void setIllegalabnormal(String illegalabnormal) {
		this.illegalabnormal = illegalabnormal;
	}
	/**
	 * 获取：
	 */
	public String getIllegalabnormal() {
		return illegalabnormal;
	}
}
