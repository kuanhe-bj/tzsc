package io.renren.vas.entity;

import java.io.Serializable;

public class Sc_travel implements Serializable {

	
	
	private static final long serialVersionUID = -3440635492612478870L;
	
	private String cph;
	private String czxm;
	private String clys;
	private String clxh;
	private String travelabnormal;
	private String illegalabnormal;
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
	@Override
	public String toString() {
		return "Sc_travel [cph=" + cph + ", czxm=" + czxm + ", clys=" + clys + ", clxh=" + clxh + ", travelabnormal="
				+ travelabnormal + ", illegalabnormal=" + illegalabnormal + "]";
	}
	
	

}
