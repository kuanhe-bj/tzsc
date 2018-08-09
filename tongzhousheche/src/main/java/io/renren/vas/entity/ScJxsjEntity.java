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
public class ScJxsjEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private String vehiOwner;
	//
	private Date enterTime;
	//
	private String licensePlate;
	//
	private String enterCardNo;
	//
	private String enterName;
	//
	private String dataid;

	/**
	 * 设置：
	 */
	public void setVehiOwner(String vehiOwner) {
		this.vehiOwner = vehiOwner;
	}
	/**
	 * 获取：
	 */
	public String getVehiOwner() {
		return vehiOwner;
	}
	/**
	 * 设置：
	 */
	public void setEnterTime(Date enterTime) {
		this.enterTime = enterTime;
	}
	/**
	 * 获取：
	 */
	public Date getEnterTime() {
		return enterTime;
	}
	/**
	 * 设置：
	 */
	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}
	/**
	 * 获取：
	 */
	public String getLicensePlate() {
		return licensePlate;
	}
	/**
	 * 设置：
	 */
	public void setEnterCardNo(String enterCardNo) {
		this.enterCardNo = enterCardNo;
	}
	/**
	 * 获取：
	 */
	public String getEnterCardNo() {
		return enterCardNo;
	}
	/**
	 * 设置：
	 */
	public void setEnterName(String enterName) {
		this.enterName = enterName;
	}
	/**
	 * 获取：
	 */
	public String getEnterName() {
		return enterName;
	}
	/**
	 * 设置：
	 */
	public void setDataid(String dataid) {
		this.dataid = dataid;
	}
	/**
	 * 获取：
	 */
	public String getDataid() {
		return dataid;
	}
}
