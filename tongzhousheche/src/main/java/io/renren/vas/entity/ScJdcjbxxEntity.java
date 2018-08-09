package io.renren.vas.entity;

import java.io.Serializable;


/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-03-09 11:10:23
 */
public class ScJdcjbxxEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//机动车所有人
	private String syr;
	//车牌号
	private String cph;
	//身份证明号码
	private String sfzmhm;
	//初次登记日期
	private String ccdjrq;
	//住所详细地址
	private String zsxxdz;
	//车辆型号
	private String clxh;
	//车身颜色代码
	private String csys;
	//主键
	private String dataid;

	/**
	 * 设置：机动车所有人
	 */
	public void setSyr(String syr) {
		this.syr = syr;
	}
	/**
	 * 获取：机动车所有人
	 */
	public String getSyr() {
		return syr;
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
	 * 设置：身份证明号码
	 */
	public void setSfzmhm(String sfzmhm) {
		this.sfzmhm = sfzmhm;
	}
	/**
	 * 获取：身份证明号码
	 */
	public String getSfzmhm() {
		return sfzmhm;
	}
	/**
	 * 设置：初次登记日期
	 */
	public void setCcdjrq(String ccdjrq) {
		this.ccdjrq = ccdjrq;
	}
	/**
	 * 获取：初次登记日期
	 */
	public String getCcdjrq() {
		return ccdjrq;
	}
	/**
	 * 设置：住所详细地址
	 */
	public void setZsxxdz(String zsxxdz) {
		this.zsxxdz = zsxxdz;
	}
	/**
	 * 获取：住所详细地址
	 */
	public String getZsxxdz() {
		return zsxxdz;
	}
	/**
	 * 设置：车辆型号
	 */
	public void setClxh(String clxh) {
		this.clxh = clxh;
	}
	/**
	 * 获取：车辆型号
	 */
	public String getClxh() {
		return clxh;
	}
	/**
	 * 设置：车身颜色代码
	 */
	public void setCsys(String csys) {
		this.csys = csys;
	}
	/**
	 * 获取：车身颜色代码
	 */
	public String getCsys() {
		return csys;
	}
	/**
	 * 设置：主键
	 */
	public void setDataid(String dataid) {
		this.dataid = dataid;
	}
	/**
	 * 获取：主键
	 */
	public String getDataid() {
		return dataid;
	}
}
