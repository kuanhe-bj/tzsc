package io.renren.vo;
/**
 * 归属地
 * @author moulin
 *
 */
public class VehiclegsVo {
	private int id;//主键
	private String province;//省，直辖市
	private double percent;//各省车辆占的百分比
	private int carnum;//车的数量
	private String city;//省会
	private String jiancheng;
	private String createtime;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public double getPercent() {
		return percent;
	}
	public void setPercent(double percent) {
		this.percent = percent;
	}
	public int getCarnum() {
		return carnum;
	}
	public void setCarnum(int carnum) {
		this.carnum = carnum;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getJiancheng() {
		return jiancheng;
	}
	public void setJiancheng(String jiancheng) {
		this.jiancheng = jiancheng;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	
	
	
}
