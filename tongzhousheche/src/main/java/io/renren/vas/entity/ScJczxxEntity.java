package io.renren.vas.entity;

import java.io.Serializable;


/**
 * 
 * 
 * @author pwk
 * @email sunlightcs@gmail.com
 * @date 2018-02-09 15:19:34
 */
public class ScJczxxEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private int id;
	//
	private String mc;
	//
	private String xzqh;
	//
	private String xz;
	//
	private String lx;

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
	public void setMc(String mc) {
		this.mc = mc;
	}
	/**
	 * 获取：
	 */
	public String getMc() {
		return mc;
	}
	/**
	 * 设置：
	 */
	public void setXzqh(String xzqh) {
		this.xzqh = xzqh;
	}
	/**
	 * 获取：
	 */
	public String getXzqh() {
		return xzqh;
	}
	/**
	 * 设置：
	 */
	public void setXz(String xz) {
		this.xz = xz;
	}
	/**
	 * 获取：
	 */
	public String getXz() {
		return xz;
	}
	/**
	 * 设置：
	 */
	public void setLx(String lx) {
		this.lx = lx;
	}
	/**
	 * 获取：
	 */
	public String getLx() {
		return lx;
	}
}
