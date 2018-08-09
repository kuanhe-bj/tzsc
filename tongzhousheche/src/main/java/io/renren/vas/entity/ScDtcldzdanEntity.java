package io.renren.vas.entity;

import java.io.Serializable;


/**
 *
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-03-27 19:39:59
 */
public class ScDtcldzdanEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	//主键
	private Integer id;
	//车牌号
	private String plate;
	//车主姓名
	private String owner;
	//车辆颜色
	private String color;
	//车辆品牌
	private String brand;
	//车辆型号
	private String model;
	//出行异常指数
	private Double abnormal;
	//违章异常指数
	private Double violation;
	//昼伏夜出指数
	private Double nightout;
	//高危地区指数
	private Double highrisk;
	//事故异常指数
	private Double accident;
	//隐匿车辆指数
	private Double hidden;
	//假牌盗牌
	private Integer isfake;
	//存疑车辆
	private Integer isindoubt;
	//是否涉案
	private Integer isinvolved;
	//车主是否重点人
	private Integer issuspects;
	//是否首次入城
	private Integer isfirstin;
	//次数异常分析
	private Integer times;
	//限行分析指数
	private Double limits;
	//综合异常指数
	private Double summary;
	//车辆有进无出
	private Integer onlyenter;
	//频繁超速指数
	private Double overspeed;
	//连续违章指数
	private Double contviolation;
	//异常轨迹指数
	private Double abtravel;
	//重点区域徘徊指数
	private Double wander;
	//电子围栏高危地区分析指数
	private Double efence;
	//套牌指数
	private Double multiplate;
	//身份证
	private String sfz;
	//凌晨出现积分
	private Double morning;

	public Double getMorning() {
		return morning;
	}

	public void setMorning(Double morning) {
		this.morning = morning;
	}

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
	 * 设置：车牌号
	 */
	public void setPlate(String plate) {
		this.plate = plate;
	}

	/**
	 * 获取：车牌号
	 */
	public String getPlate() {
		return plate;
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
	 * 设置：车辆型号
	 */
	public void setModel(String model) {
		this.model = model;
	}

	/**
	 * 获取：车辆型号
	 */
	public String getModel() {
		return model;
	}

	/**
	 * 设置：出行异常指数
	 */
	public void setAbnormal(Double abnormal) {
		this.abnormal = abnormal;
	}

	/**
	 * 获取：出行异常指数
	 */
	public Double getAbnormal() {
		return abnormal;
	}

	/**
	 * 设置：违章异常指数
	 */
	public void setViolation(Double violation) {
		this.violation = violation;
	}

	/**
	 * 获取：违章异常指数
	 */
	public Double getViolation() {
		return violation;
	}

	/**
	 * 设置：昼伏夜出指数
	 */
	public void setNightout(Double nightout) {
		this.nightout = nightout;
	}

	/**
	 * 获取：昼伏夜出指数
	 */
	public Double getNightout() {
		return nightout;
	}

	/**
	 * 设置：高危地区指数
	 */
	public void setHighrisk(Double highrisk) {
		this.highrisk = highrisk;
	}

	/**
	 * 获取：高危地区指数
	 */
	public Double getHighrisk() {
		return highrisk;
	}

	/**
	 * 设置：事故异常指数
	 */
	public void setAccident(Double accident) {
		this.accident = accident;
	}

	/**
	 * 获取：事故异常指数
	 */
	public Double getAccident() {
		return accident;
	}

	/**
	 * 设置：隐匿车辆指数
	 */
	public void setHidden(Double hidden) {
		this.hidden = hidden;
	}

	/**
	 * 获取：隐匿车辆指数
	 */
	public Double getHidden() {
		return hidden;
	}

	/**
	 * 设置：假牌盗牌
	 */
	public void setIsfake(Integer isfake) {
		this.isfake = isfake;
	}

	/**
	 * 获取：假牌盗牌
	 */
	public Integer getIsfake() {
		return isfake;
	}

	/**
	 * 设置：存疑车辆
	 */
	public void setIsindoubt(Integer isindoubt) {
		this.isindoubt = isindoubt;
	}

	/**
	 * 获取：存疑车辆
	 */
	public Integer getIsindoubt() {
		return isindoubt;
	}

	/**
	 * 设置：是否涉案
	 */
	public void setIsinvolved(Integer isinvolved) {
		this.isinvolved = isinvolved;
	}

	/**
	 * 获取：是否涉案
	 */
	public Integer getIsinvolved() {
		return isinvolved;
	}

	/**
	 * 设置：车主是否重点人
	 */
	public void setIssuspects(Integer issuspects) {
		this.issuspects = issuspects;
	}

	/**
	 * 获取：车主是否重点人
	 */
	public Integer getIssuspects() {
		return issuspects;
	}

	/**
	 * 设置：是否首次入城
	 */
	public void setIsfirstin(Integer isfirstin) {
		this.isfirstin = isfirstin;
	}

	/**
	 * 获取：是否首次入城
	 */
	public Integer getIsfirstin() {
		return isfirstin;
	}

	/**
	 * 设置：次数异常分析
	 */
	public void setTimes(Integer times) {
		this.times = times;
	}

	/**
	 * 获取：次数异常分析
	 */
	public Integer getTimes() {
		return times;
	}

	/**
	 * 设置：限行分析指数
	 */
	public void setLimits(Double limits) {
		this.limits = limits;
	}

	/**
	 * 获取：限行分析指数
	 */
	public Double getLimits() {
		return limits;
	}

	/**
	 * 设置：综合异常指数
	 */
	public void setSummary(Double summary) {
		this.summary = summary;
	}

	/**
	 * 获取：综合异常指数
	 */
	public Double getSummary() {
		return summary;
	}

	/**
	 * 设置：车辆有进无出
	 */
	public void setOnlyenter(Integer onlyenter) {
		this.onlyenter = onlyenter;
	}

	/**
	 * 获取：车辆有进无出
	 */
	public Integer getOnlyenter() {
		return onlyenter;
	}

	/**
	 * 设置：频繁超速指数
	 */
	public void setOverspeed(Double overspeed) {
		this.overspeed = overspeed;
	}

	/**
	 * 获取：频繁超速指数
	 */
	public Double getOverspeed() {
		return overspeed;
	}

	/**
	 * 设置：连续违章指数
	 */
	public void setContviolation(Double contviolation) {
		this.contviolation = contviolation;
	}

	/**
	 * 获取：连续违章指数
	 */
	public Double getContviolation() {
		return contviolation;
	}

	/**
	 * 设置：异常轨迹指数
	 */
	public void setAbtravel(Double abtravel) {
		this.abtravel = abtravel;
	}

	/**
	 * 获取：异常轨迹指数
	 */
	public Double getAbtravel() {
		return abtravel;
	}

	/**
	 * 设置：重点区域徘徊指数
	 */
	public void setWander(Double wander) {
		this.wander = wander;
	}

	/**
	 * 获取：重点区域徘徊指数
	 */
	public Double getWander() {
		return wander;
	}

	/**
	 * 设置：电子围栏高危地区分析指数
	 */
	public void setEfence(Double efence) {
		this.efence = efence;
	}

	/**
	 * 获取：电子围栏高危地区分析指数
	 */
	public Double getEfence() {
		return efence;
	}

	/**
	 * 设置：套牌指数
	 */
	public void setMultiplate(Double multiplate) {
		this.multiplate = multiplate;
	}

	/**
	 * 获取：套牌指数
	 */
	public Double getMultiplate() {
		return multiplate;
	}

	/**
	 * 设置：身份证
	 */
	public void setSfz(String sfz) {
		this.sfz = sfz;
	}

	/**
	 * 获取：身份证
	 */
	public String getSfz() {
		return sfz;
	}
}
