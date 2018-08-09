package io.renren.vo;

/**
 * 人车关联
 * @author moulin
 *
 */
public class Sc_rcglsVo {
	private String cp;//车牌号
	private String owner;//车主
	private String brand;//车的品牌
	private String color;//车的颜色
	private String xm;//姓名
	private String sfz;//身份证
	private String sjly;//数据来源
	private String DIANHUA;//电话手机
	private String WANGLUO;//网络身份
	public String getDIANHUA() {
		return DIANHUA;
	}
	public void setDIANHUA(String dIANHUA) {
		DIANHUA = dIANHUA;
	}
	public String getWANGLUO() {
		return WANGLUO;
	}
	public void setWANGLUO(String wANGLUO) {
		WANGLUO = wANGLUO;
	}
	public String getCp() {
		return cp;
	}
	public void setCp(String cp) {
		this.cp = cp;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getXm() {
		return xm;
	}
	public void setXm(String xm) {
		this.xm = xm;
	}
	public String getSfz() {
		return sfz;
	}
	public void setSfz(String sfz) {
		this.sfz = sfz;
	}
	public String getSjly() {
		return sjly;
	}
	public void setSjly(String sjly) {
		this.sjly = sjly;
	}
	
}
