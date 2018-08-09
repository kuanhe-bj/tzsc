package io.renren.vo;

/**
 * 动态电子档案
 * @author moulin
 *
 */
public class DtcldzdansVo {
	private String plate;//车牌号
	private String owner;//车主姓名
	private String color;//车辆颜色
	private String brand;//车辆品牌
	private String model;//车辆型号
	private double abnormal;//出行异常指数
	private double violation;//违章异常指数
	private double nightOut;//昼伏夜出指数
	private double highrisk;//高危地区指数
	private double accident;//事故频率分析指数
	private double hidden;//隐匿车辆
	private double isFake;//假牌盗牌
	private double isInDoubt;//存疑车辆
	private double isInvolved;//是否涉案
	private double isSuspects;//车主是否重点人
	private double isFirstIn;//是否首次进城
	private double times;//次数异常
	private double limits;//限行分析指数
	private double summary;//综合异常指数
	private double onlyEnter;//车辆有进无出
	private double overSpeed;//频繁超速分析指数
	private double ContViolation;//连续违章指数
	private double abTravel;//异常轨迹指数
	private double wander;//重点区域徘徊指数
	private double efence;//电子围栏高危地区指数
	private double multiPlate;//套牌
	private String sfz;//身份证
	private float sensitive;//敏感地区指数
	private float robbery;//盗抢车辆指数
	private float obscured;//遮挡号牌
	private float faceCover;//遮挡面部
	private float noPlate;//无牌
	private float records;//前科记录
	private float drug;//涉毒
	private float atLarge;//在逃
	private float relative;//亲戚等关联人
	private float morning;//临晨出现指数
	private float address;//地点异常
	private float timeAbnormal;//时间异常
	private float longStay;//长期留京
	private float hugeAccident;//重大事故
	private float tendency;//犯罪倾向
	private float black;//黑车指数
	public float getSensitive() {
		return sensitive;
	}
	public void setSensitive(float sensitive) {
		this.sensitive = sensitive;
	}
	public double getIsFake() {
		return isFake;
	}
	public void setIsFake(double isFake) {
		this.isFake = isFake;
	}
	public double getIsInDoubt() {
		return isInDoubt;
	}
	public void setIsInDoubt(double isInDoubt) {
		this.isInDoubt = isInDoubt;
	}
	public double getIsInvolved() {
		return isInvolved;
	}
	public void setIsInvolved(double isInvolved) {
		this.isInvolved = isInvolved;
	}
	public double getIsSuspects() {
		return isSuspects;
	}
	public void setIsSuspects(double isSuspects) {
		this.isSuspects = isSuspects;
	}
	public double getIsFirstIn() {
		return isFirstIn;
	}
	public void setIsFirstIn(double isFirstIn) {
		this.isFirstIn = isFirstIn;
	}
	public double getTimes() {
		return times;
	}
	public void setTimes(double times) {
		this.times = times;
	}
	public double getOnlyEnter() {
		return onlyEnter;
	}
	public void setOnlyEnter(double onlyEnter) {
		this.onlyEnter = onlyEnter;
	}
	public float getRobbery() {
		return robbery;
	}
	public void setRobbery(float robbery) {
		this.robbery = robbery;
	}
	public float getObscured() {
		return obscured;
	}
	public void setObscured(float obscured) {
		this.obscured = obscured;
	}
	public float getFaceCover() {
		return faceCover;
	}
	public void setFaceCover(float faceCover) {
		this.faceCover = faceCover;
	}
	public float getNoPlate() {
		return noPlate;
	}
	public void setNoPlate(float noPlate) {
		this.noPlate = noPlate;
	}
	public float getRecords() {
		return records;
	}
	public void setRecords(float records) {
		this.records = records;
	}
	public float getDrug() {
		return drug;
	}
	public void setDrug(float drug) {
		this.drug = drug;
	}
	public float getAtLarge() {
		return atLarge;
	}
	public void setAtLarge(float atLarge) {
		this.atLarge = atLarge;
	}
	public float getRelative() {
		return relative;
	}
	public void setRelative(float relative) {
		this.relative = relative;
	}
	public float getMorning() {
		return morning;
	}
	public void setMorning(float morning) {
		this.morning = morning;
	}
	public float getAddress() {
		return address;
	}
	public void setAddress(float address) {
		this.address = address;
	}
	public float getTimeAbnormal() {
		return timeAbnormal;
	}
	public void setTimeAbnormal(float timeAbnormal) {
		this.timeAbnormal = timeAbnormal;
	}
	public float getLongStay() {
		return longStay;
	}
	public void setLongStay(float longStay) {
		this.longStay = longStay;
	}
	public float getHugeAccident() {
		return hugeAccident;
	}
	public void setHugeAccident(float hugeAccident) {
		this.hugeAccident = hugeAccident;
	}
	public float getTendency() {
		return tendency;
	}
	public void setTendency(float tendency) {
		this.tendency = tendency;
	}
	public float getBlack() {
		return black;
	}
	public void setBlack(float black) {
		this.black = black;
	}
	public double getLimits() {
		return limits;
	}
	public void setLimits(double limits) {
		this.limits = limits;
	}
	public double getMultiPlate() {
		return multiPlate;
	}
	public void setMultiPlate(double multiPlate) {
		this.multiPlate = multiPlate;
	}
	public String getSfz() {
		return sfz;
	}
	public void setSfz(String sfz) {
		this.sfz = sfz;
	}
	
	
	public double getAbTravel() {
		return abTravel;
	}
	public void setAbTravel(double abTravel) {
		this.abTravel = abTravel;
	}
	public double getWander() {
		return wander;
	}
	public void setWander(double wander) {
		this.wander = wander;
	}
	public double getEfence() {
		return efence;
	}
	public void setEfence(double efence) {
		this.efence = efence;
	}

	public double getOverSpeed() {
		return overSpeed;
	}
	public void setOverSpeed(double overSpeed) {
		this.overSpeed = overSpeed;
	}
	public String getPlate() {
		return plate;
	}
	public void setPlate(String plate) {
		this.plate = plate;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public double getAbnormal() {
		return abnormal;
	}
	public void setAbnormal(double abnormal) {
		this.abnormal = abnormal;
	}
	public double getViolation() {
		return violation;
	}
	public void setViolation(double violation) {
		this.violation = violation;
	}
	public double getNightOut() {
		return nightOut;
	}
	public void setNightOut(double nightOut) {
		this.nightOut = nightOut;
	}
	public double getHighrisk() {
		return highrisk;
	}
	public void setHighrisk(double highrisk) {
		this.highrisk = highrisk;
	}
	public double getAccident() {
		return accident;
	}
	public void setAccident(double accident) {
		this.accident = accident;
	}
	public double getHidden() {
		return hidden;
	}
	public void setHidden(double hidden) {
		this.hidden = hidden;
	}
	
	public double getSummary() {
		return summary;
	}
	public void setSummary(double summary) {
		this.summary = summary;
	}
	public double getContViolation() {
		return ContViolation;
	}
	public void setContViolation(double contViolation) {
		ContViolation = contViolation;
	}

}
