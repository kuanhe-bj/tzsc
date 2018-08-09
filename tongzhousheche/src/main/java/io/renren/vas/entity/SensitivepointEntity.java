package io.renren.vas.entity;

import java.io.Serializable;


/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-04-25 15:57:42
 */
public class SensitivepointEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//敏感地区类型：圆形(0)、方形(1)、椭圆形(2)
	private Integer stype;
	//
	private String center;
	//长
	private Double x;
	//高
	private Double y;
	//敏感地区级别：0-9级
	private Integer grade;

	/**
	 * 设置：
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：敏感地区类型：圆形(0)、方形(1)、椭圆形(2)
	 */
	public void setStype(Integer stype) {
		this.stype = stype;
	}
	/**
	 * 获取：敏感地区类型：圆形(0)、方形(1)、椭圆形(2)
	 */
	public Integer getStype() {
		return stype;
	}
	/**
	 * 设置：
	 */
	public void setCenter(String center) {
		this.center = center;
	}
	/**
	 * 获取：
	 */
	public String getCenter() {
		return center;
	}
	/**
	 * 设置：长
	 */
	public void setX(Double x) {
		this.x = x;
	}
	/**
	 * 获取：长
	 */
	public Double getX() {
		return x;
	}
	/**
	 * 设置：高
	 */
	public void setY(Double y) {
		this.y = y;
	}
	/**
	 * 获取：高
	 */
	public Double getY() {
		return y;
	}
	/**
	 * 设置：敏感地区级别：0-9级
	 */
	public void setGrade(Integer grade) {
		this.grade = grade;
	}
	/**
	 * 获取：敏感地区级别：0-9级
	 */
	public Integer getGrade() {
		return grade;
	}
}
