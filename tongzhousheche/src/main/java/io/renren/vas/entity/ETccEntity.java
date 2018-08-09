package io.renren.vas.entity;

import java.io.Serializable;
import java.util.Date;


/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-03-24 11:48:37
 */
public class ETccEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	//
	private String eId;
	//
	private Double jingdu;
	//
	private Double weidu;
	//
	private Date querytime;
	//
	private Integer count;
	//
	private Integer sjly;
	public ETccEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ETccEntity(String eId, Double jingdu, Double weidu, Date querytime, Integer count, Integer sjly) {
		super();
		this.eId = eId;
		this.jingdu = jingdu;
		this.weidu = weidu;
		this.querytime = querytime;
		this.count = count;
		this.sjly = sjly;
	}
	public String geteId() {
		return eId;
	}
	public void seteId(String eId) {
		this.eId = eId;
	}
	public Double getJingdu() {
		return jingdu;
	}
	public void setJingdu(Double jingdu) {
		this.jingdu = jingdu;
	}
	public Double getWeidu() {
		return weidu;
	}
	public void setWeidu(Double weidu) {
		this.weidu = weidu;
	}
	public Date getQuerytime() {
		return querytime;
	}
	public void setQuerytime(Date querytime) {
		this.querytime = querytime;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Integer getSjly() {
		return sjly;
	}
	public void setSjly(Integer sjly) {
		this.sjly = sjly;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}
