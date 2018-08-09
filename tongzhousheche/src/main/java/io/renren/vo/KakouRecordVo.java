package io.renren.vo;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import io.renren.common.utils.DateJsonDeserializer;
import io.renren.common.utils.DateJsonSerializer;

/**
 * 卡口信息热力图
 *
 * @author zhangyulong
 * @date 2018/3/9
 */
public class KakouRecordVo {

 

    /**
     * 数量
     */
    String num;
 
    String startTime;
    
    String endTime;
    
    double frequency;
    
	//
	private String eid;
	//
	private String carNumber;
	
    @JsonSerialize(using=DateJsonSerializer.class)  
    @JsonDeserialize(using=DateJsonDeserializer.class)
	private Date enterTime;
    
    @JsonSerialize(using=DateJsonSerializer.class)  
    @JsonDeserialize(using=DateJsonDeserializer.class)
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
	
	
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public double getFrequency() {
		return frequency;
	}
	public void setFrequency(double frequency) {
		this.frequency = frequency;
	}
	public String getEid() {
		return eid;
	}
	public void setEid(String eid) {
		this.eid = eid;
	}
	public String getCarNumber() {
		return carNumber;
	}
	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}
	public Date getEnterTime() {
		return enterTime;
	}
	public void setEnterTime(Date enterTime) {
		this.enterTime = enterTime;
	}
	public Date getExitTime() {
		return exitTime;
	}
	public void setExitTime(Date exitTime) {
		this.exitTime = exitTime;
	}
	public String getEnterImg() {
		return enterImg;
	}
	public void setEnterImg(String enterImg) {
		this.enterImg = enterImg;
	}
	public String getExitImg() {
		return exitImg;
	}
	public void setExitImg(String exitImg) {
		this.exitImg = exitImg;
	}
	public String getParkNm() {
		return parkNm;
	}
	public void setParkNm(String parkNm) {
		this.parkNm = parkNm;
	}
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
	public String getDistrictNm() {
		return districtNm;
	}
	public void setDistrictNm(String districtNm) {
		this.districtNm = districtNm;
	}
	public String getJingdu() {
		return jingdu;
	}
	public void setJingdu(String jingdu) {
		this.jingdu = jingdu;
	}
	public String getWeidu() {
		return weidu;
	}
	public void setWeidu(String weidu) {
		this.weidu = weidu;
	}
	public String getDistrictNmId() {
		return districtNmId;
	}
	public void setDistrictNmId(String districtNmId) {
		this.districtNmId = districtNmId;
	}
	public String getSjly() {
		return sjly;
	}
	public void setSjly(String sjly) {
		this.sjly = sjly;
	}
	
	

}
