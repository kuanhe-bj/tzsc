package io.renren.vo;

import java.util.Date;

/**
 * 限行
 * @author moulin
 *
 */
public class XianXingVo {
	private String cph;// 车牌号
	private Integer count;// 违反限行次数
	private String weihao1;
	private String weihao2;
	private Date time1;
	private Date time2;

	public XianXingVo(String cph, Integer count, String weihao1, String weihao2) {
		super();
		this.cph = cph;
		this.count = count;
		this.weihao1 = weihao1;
		this.weihao2 = weihao2;
	}

	public XianXingVo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getCph() {
		return cph;
	}

	public void setCph(String cph) {
		this.cph = cph;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getWeihao1() {
		return weihao1;
	}

	public void setWeihao1(String weihao1) {
		this.weihao1 = weihao1;
	}

	public String getWeihao2() {
		return weihao2;
	}

	public void setWeihao2(String weihao2) {
		this.weihao2 = weihao2;
	}

	public Date getTime1() {
		return time1;
	}

	public void setTime1(Date time1) {
		this.time1 = time1;
	}

	public Date getTime2() {
		return time2;
	}

	public void setTime2(Date time2) {
		this.time2 = time2;
	}
	
}
