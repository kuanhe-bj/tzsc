package io.renren.vas.entity;

import java.io.Serializable;


/**
 * VIEW
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-03-28 15:52:43
 */
public class ScRcglEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private String cp;
	//车主姓名
	private String owner;
	//车辆品牌
	private String brand;
	//车辆颜色
	private String color;
	//
	private String xm;
	//
	private String sfz;
	//
	private String sjly;

	/**
	 * 设置：
	 */
	public void setCp(String cp) {
		this.cp = cp;
	}
	/**
	 * 获取：
	 */
	public String getCp() {
		return cp;
	}
	/**
	 * 设置：车主姓名
	 */
	public void setOwner(String owner) {
		this.owner = owner;
	}
	/**
	 * 获取：车主姓名
	 */
	public String getOwner() {
		return owner;
	}
	/**
	 * 设置：车辆品牌
	 */
	public void setBrand(String brand) {
		this.brand = brand;
	}
	/**
	 * 获取：车辆品牌
	 */
	public String getBrand() {
		return brand;
	}
	/**
	 * 设置：车辆颜色
	 */
	public void setColor(String color) {
		this.color = color;
	}
	/**
	 * 获取：车辆颜色
	 */
	public String getColor() {
		return color;
	}
	/**
	 * 设置：
	 */
	public void setXm(String xm) {
		this.xm = xm;
	}
	/**
	 * 获取：
	 */
	public String getXm() {
		return xm;
	}
	/**
	 * 设置：
	 */
	public void setSfz(String sfz) {
		this.sfz = sfz;
	}
	/**
	 * 获取：
	 */
	public String getSfz() {
		return sfz;
	}
	/**
	 * 设置：
	 */
	public void setSjly(String sjly) {
		this.sjly = sjly;
	}
	/**
	 * 获取：
	 */
	public String getSjly() {
		return sjly;
	}
}
