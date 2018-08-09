package io.renren.vas.entity;

import java.io.Serializable;


/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-03-22 10:55:46
 */
public class ScGsdEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键
	private Integer id;
	//省,直辖市
	private String province;
	//各省车辆占的百分比
	private Double percent;
	//车的数量
	private Integer carnum;
	//省会
	private String city;

	/**
	 * 设置：主键
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：主键
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：省,直辖市
	 */
	public void setProvince(String province) {
		this.province = province;
	}
	/**
	 * 获取：省,直辖市
	 */
	public String getProvince() {
		return province;
	}
	/**
	 * 设置：各省车辆占的百分比
	 */
	public void setPercent(Double percent) {
		this.percent = percent;
	}
	/**
	 * 获取：各省车辆占的百分比
	 */
	public Double getPercent() {
		return percent;
	}
	/**
	 * 设置：车的数量
	 */
	public void setCarnum(Integer carnum) {
		this.carnum = carnum;
	}
	/**
	 * 获取：车的数量
	 */
	public Integer getCarnum() {
		return carnum;
	}
	/**
	 * 设置：省会
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * 获取：省会
	 */
	public String getCity() {
		return city;
	}
}
