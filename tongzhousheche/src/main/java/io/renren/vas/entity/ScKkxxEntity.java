package io.renren.vas.entity;

import java.io.Serializable;


/**
 * 
 * 
 * @author pwk
 * @email sunlightcs@gmail.com
 * @date 2018-02-09 15:19:34
 */
public class ScKkxxEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	//编号
	private String id;
	//卡口名称
	private String mc;
	//卡口行政区划
	private String xzqh;
	//卡口详细地址
	private String xz;
	//卡口类型
	private String lx;
	//
	private String jd;
	//
	private String wd;

	/**
	 * 设置：编号
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获取：编号
	 */
	public String getId() {
		return id;
	}
	/**
	 * 设置：卡口名称
	 */
	public void setMc(String mc) {
		this.mc = mc;
	}
	/**
	 * 获取：卡口名称
	 */
	public String getMc() {
		return mc;
	}
	/**
	 * 设置：卡口行政区划
	 */
	public void setXzqh(String xzqh) {
		this.xzqh = xzqh;
	}
	/**
	 * 获取：卡口行政区划
	 */
	public String getXzqh() {
		return xzqh;
	}
	/**
	 * 设置：卡口详细地址
	 */
	public void setXz(String xz) {
		this.xz = xz;
	}
	/**
	 * 获取：卡口详细地址
	 */
	public String getXz() {
		return xz;
	}
	/**
	 * 设置：卡口类型
	 */
	public void setLx(String lx) {
		this.lx = lx;
	}
	/**
	 * 获取：卡口类型
	 */
	public String getLx() {
		return lx;
	}
	/**
	 * 设置：
	 */
	public void setJd(String jd) {
		this.jd = jd;
	}
	/**
	 * 获取：
	 */
	public String getJd() {
		return jd;
	}
	/**
	 * 设置：
	 */
	public void setWd(String wd) {
		this.wd = wd;
	}
	/**
	 * 获取：
	 */
	public String getWd() {
		return wd;
	}
}
