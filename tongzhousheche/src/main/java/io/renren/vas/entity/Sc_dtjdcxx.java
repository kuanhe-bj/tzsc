package io.renren.vas.entity;

import java.io.Serializable;

public class Sc_dtjdcxx implements Serializable {
	
	private static final long serialVersionUID = -8694470807796087963L;
	
	private Integer id;
	private String cph;
	private String czxm;
	private String clys;
	private String clpp;
	private String clxh;
	private String travelabnormal;
	private String illegalabnormal;
	//昼伏夜出指数
	private String nightOut;
	//高危地区指数
	private String highrisk;
	//事故异常指数
	private String accident;
	//隐匿车辆指数
	private String hidden;
	//假牌盗牌
	private boolean isFake;
	//存疑车辆
	private boolean isInDoubt;
	//是否涉案
	private boolean isInvolved; 
	// 车主是否重点人 
	private boolean isSuspects; 
	//是否 首次入 城
	private boolean isFirstIn;
	//次数异常分析
	private boolean times;
	//限 行分析指数
	private String limitNum;
	//综合异常指数
	private String summary;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCph() {
		return cph;
	}
	public void setCph(String cph) {
		this.cph = cph;
	}
	public String getCzxm() {
		return czxm;
	}
	public void setCzxm(String czxm) {
		this.czxm = czxm;
	}
	public String getClys() {
		return clys;
	}
	public void setClys(String clys) {
		this.clys = clys;
	}
	public String getClpp() {
		return clpp;
	}
	public void setClpp(String clpp) {
		this.clpp = clpp;
	}
	public String getClxh() {
		return clxh;
	}
	public void setClxh(String clxh) {
		this.clxh = clxh;
	}
	public String getTravelabnormal() {
		return travelabnormal;
	}
	public void setTravelabnormal(String travelabnormal) {
		this.travelabnormal = travelabnormal;
	}
	public String getIllegalabnormal() {
		return illegalabnormal;
	}
	public void setIllegalabnormal(String illegalabnormal) {
		this.illegalabnormal = illegalabnormal;
	}
	public String getNightOut() {
		return nightOut;
	}
	public void setNightOut(String nightOut) {
		this.nightOut = nightOut;
	}
	public String getHighrisk() {
		return highrisk;
	}
	public void setHighrisk(String highrisk) {
		this.highrisk = highrisk;
	}
	public String getAccident() {
		return accident;
	}
	public void setAccident(String accident) {
		this.accident = accident;
	}
	public String getHidden() {
		return hidden;
	}
	public void setHidden(String hidden) {
		this.hidden = hidden;
	}
	public boolean isFake() {
		return isFake;
	}
	public void setFake(boolean isFake) {
		this.isFake = isFake;
	}
	public boolean isInDoubt() {
		return isInDoubt;
	}
	public void setInDoubt(boolean isInDoubt) {
		this.isInDoubt = isInDoubt;
	}
	public boolean isInvolved() {
		return isInvolved;
	}
	public void setInvolved(boolean isInvolved) {
		this.isInvolved = isInvolved;
	}
	public boolean isSuspects() {
		return isSuspects;
	}
	public void setSuspects(boolean isSuspects) {
		this.isSuspects = isSuspects;
	}
	public boolean isFirstIn() {
		return isFirstIn;
	}
	public void setFirstIn(boolean isFirstIn) {
		this.isFirstIn = isFirstIn;
	}
	public boolean isTimes() {
		return times;
	}
	public void setTimes(boolean times) {
		this.times = times;
	}
	public String getLimitNum() {
		return limitNum;
	}
	public void setLimitNum(String limitNum) {
		this.limitNum = limitNum;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	} 
	
}
