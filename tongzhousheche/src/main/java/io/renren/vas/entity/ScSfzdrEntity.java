package io.renren.vas.entity;

import java.io.Serializable;
import java.util.Date;


/**
 * 
 * 
 * @author pwk
 * @email sunlightcs@gmail.com
 * @date 2018-02-09 15:19:33
 */
public class ScSfzdrEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private String xm;
	//
	private String zjhm;
	//
	private Date fssj;
	//
	private String hjdxz;
	//
	private String dataid;

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
	public void setZjhm(String zjhm) {
		this.zjhm = zjhm;
	}
	/**
	 * 获取：
	 */
	public String getZjhm() {
		return zjhm;
	}
	/**
	 * 设置：
	 */
	public void setFssj(Date fssj) {
		this.fssj = fssj;
	}
	/**
	 * 获取：
	 */
	public Date getFssj() {
		return fssj;
	}
	/**
	 * 设置：
	 */
	public void setHjdxz(String hjdxz) {
		this.hjdxz = hjdxz;
	}
	/**
	 * 获取：
	 */
	public String getHjdxz() {
		return hjdxz;
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
