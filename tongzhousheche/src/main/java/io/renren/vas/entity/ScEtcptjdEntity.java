package io.renren.vas.entity;

import java.io.Serializable;
import java.util.Date;


/**
 * 
 * 
 * @author pwk
 * @email sunlightcs@gmail.com
 * @date 2018-02-09 15:19:34
 */
public class ScEtcptjdEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private String eid;
	//
	private String carNumber;
	//
	private Date enterTime;
	//
	private Date exitTime;
	//
	private String enterImg;
	//
	private String exitImg;
	//
	private String parkNm;
	//
	private String adress;
	//
	private String districtNm;
	//
	private String jingdu;
	//
	private String weidu;
	//
	private String districtNmId;
	//
	private String sjly;
	
	private String count;
	
	private Integer num = 0;
	
private double numb;
	
public void setNumb(double numb) {
	this.numb = numb;
}
/**
 * 获取：
 */
public double getNumb() {
	return numb;
}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	/**
	 * 设置：
	 */
	public void setEid(String eid) {
		this.eid = eid;
	}
	/**
	 * 获取：
	 */
	public String getEid() {
		return eid;
	}
	/**
	 * 设置：
	 */
	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}
	/**
	 * 获取：
	 */
	public String getCarNumber() {
		return carNumber;
	}
	/**
	 * 设置：
	 */
	public void setEnterTime(Date enterTime) {
		this.enterTime = enterTime;
	}
	/**
	 * 获取：
	 */
	public Date getEnterTime() {
		return enterTime;
	}
	/**
	 * 设置：
	 */
	public void setExitTime(Date exitTime) {
		this.exitTime = exitTime;
	}
	/**
	 * 获取：
	 */
	public Date getExitTime() {
		return exitTime;
	}
	/**
	 * 设置：
	 */
	public void setEnterImg(String enterImg) {
		this.enterImg = enterImg;
	}
	/**
	 * 获取：
	 */
	public String getEnterImg() {
		return enterImg;
	}
	/**
	 * 设置：
	 */
	public void setExitImg(String exitImg) {
		this.exitImg = exitImg;
	}
	/**
	 * 获取：
	 */
	public String getExitImg() {
		return exitImg;
	}
	/**
	 * 设置：
	 */
	public void setParkNm(String parkNm) {
		this.parkNm = parkNm;
	}
	/**
	 * 获取：
	 */
	public String getParkNm() {
		return parkNm;
	}
	/**
	 * 设置：
	 */
	public void setAdress(String adress) {
		this.adress = adress;
	}
	/**
	 * 获取：
	 */
	public String getAdress() {
		return adress;
	}
	/**
	 * 设置：
	 */
	public void setDistrictNm(String districtNm) {
		this.districtNm = districtNm;
	}
	/**
	 * 获取：
	 */
	public String getDistrictNm() {
		return districtNm;
	}
	/**
	 * 设置：
	 */
	public void setJingdu(String jingdu) {
		this.jingdu = jingdu;
	}
	/**
	 * 获取：
	 */
	public String getJingdu() {
		return jingdu;
	}
	/**
	 * 设置：
	 */
	public void setWeidu(String weidu) {
		this.weidu = weidu;
	}
	/**
	 * 获取：
	 */
	public String getWeidu() {
		return weidu;
	}
	/**
	 * 设置：
	 */
	public void setDistrictNmId(String districtNmId) {
		this.districtNmId = districtNmId;
	}
	/**
	 * 获取：
	 */
	public String getDistrictNmId() {
		return districtNmId;
	}
	/**
	 * 设置：
	 */
	public void setSjly(String sjly) {
		this.sjly = sjly;
	}
	/**
	 * 获取：
	 */
	public String getSjly() {
		return sjly;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
}
