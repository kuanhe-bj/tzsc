package io.renren.vo;

/**
 * 可视化
 * @author moulin
 *
 */
public class KshVo {
	private String cph;
	private String jingdu;
	private String weidu;
	private double juli;
	private int num;

	public KshVo() {
		super();
	}

	public KshVo(String cph, String jingdu, String weidu, double juli) {
		super();
		this.cph = cph;
		this.jingdu = jingdu;
		this.weidu = weidu;
		this.juli = juli;
	}

	public String getCph() {
		return cph;
	}

	public void setCph(String cph) {
		this.cph = cph;
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

	public double getJuli() {
		return juli;
	}

	public void setJuli(double juli) {
		this.juli = juli;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}
}
