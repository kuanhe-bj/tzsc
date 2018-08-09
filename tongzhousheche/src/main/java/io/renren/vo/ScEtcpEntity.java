package io.renren.vo;

import java.io.Serializable;

/**
 *
 * etcp行车数据
 * @author pwk
 * @email sunlightcs@gmail.com
 * @date 2018-02-09 15:19:34
 */
public class ScEtcpEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	//
	private String eid;
	//
	private String carNumber;
	//
	private String enterTime;
	//
	private String exitTime;
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
	// 停车
	private String count;

	private Integer num = 0;
	// 机动车颜
	private String VehicleColor;
	// 机动车品
	private String VehicleBrand;
	// 车辆型号
	private String VehicleModel;
	// 车辆类别
	private String VehicleClass;
	// 车辆年款
	private String VehicleStyles;
	// 采集类型
	private Integer InfoKind;
	// 来源标识
	private String SourceID;
	// 车道
	private Integer LaneNo;
	//	车牌种类
	private String PlateClass;
	//	车牌颜色
	private String PlateColor;
	//	遮阳板状
	private Integer Sunvisor;
	//	安全带状
	private Integer SafetyBelt;
	//	打电话状
	private Integer Calling;
	//	乘客人数
	private Integer NumOfPassenger;
	//	速度
	private Integer Speed;
	//	设备编号
	private String DeviceID;
	//	颜色深浅
	private Integer VehicleColorDepth;
	//	行车方向
	private Integer Direction;

	public Integer getDirection() {
		return Direction;
	}

	public void setDirection(Integer direction) {
		Direction = direction;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	/**
	 * 设置
	 */
	public void setEid(String eid) {
		this.eid = eid;
	}

	/**
	 * 获取
	 */
	public String getEid() {
		return eid;
	}

	/**
	 * 设置
	 */
	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}

	/**
	 * 获取
	 */
	public String getCarNumber() {
		return carNumber;
	}



	public String getEnterTime() {
		return enterTime;
	}

	public void setEnterTime(String enterTime) {
		this.enterTime = enterTime;
	}

	public String getExitTime() {
		return exitTime;
	}

	public void setExitTime(String exitTime) {
		this.exitTime = exitTime;
	}

	/**
	 * 设置
	 */
	public void setEnterImg(String enterImg) {
		this.enterImg = enterImg;
	}

	/**
	 * 获取
	 */
	public String getEnterImg() {
		return enterImg;
	}

	/**
	 * 设置
	 */
	public void setExitImg(String exitImg) {
		this.exitImg = exitImg;
	}

	/**
	 * 获取
	 */
	public String getExitImg() {
		return exitImg;
	}

	/**
	 * 设置
	 */
	public void setParkNm(String parkNm) {
		this.parkNm = parkNm;
	}

	/**
	 * 获取
	 */
	public String getParkNm() {
		return parkNm;
	}

	/**
	 * 设置
	 */
	public void setAdress(String adress) {
		this.adress = adress;
	}

	/**
	 * 获取
	 */
	public String getAdress() {
		return adress;
	}

	/**
	 * 设置
	 */
	public void setDistrictNm(String districtNm) {
		this.districtNm = districtNm;
	}

	/**
	 * 获取
	 */
	public String getDistrictNm() {
		return districtNm;
	}

	/**
	 * 设置
	 */
	public void setJingdu(String jingdu) {
		this.jingdu = jingdu;
	}

	/**
	 * 获取
	 */
	public String getJingdu() {
		return jingdu;
	}

	/**
	 * 设置
	 */
	public void setWeidu(String weidu) {
		this.weidu = weidu;
	}

	/**
	 * 获取
	 */
	public String getWeidu() {
		return weidu;
	}

	/**
	 * 设置
	 */
	public void setDistrictNmId(String districtNmId) {
		this.districtNmId = districtNmId;
	}

	/**
	 * 获取
	 */
	public String getDistrictNmId() {
		return districtNmId;
	}

	/**
	 * 设置
	 */
	public void setSjly(String sjly) {
		this.sjly = sjly;
	}

	/**
	 * 获取
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

	
	public String getVehicleStyles() {
		return VehicleStyles;
	}

	public void setVehicleStyles(String vehicleStyles) {
		VehicleStyles = vehicleStyles;
	}

	public Integer getInfoKind() {
		return InfoKind;
	}

	public void setInfoKind(Integer infoKind) {
		InfoKind = infoKind;
	}

	public String getSourceID() {
		return SourceID;
	}

	public void setSourceID(String sourceID) {
		SourceID = sourceID;
	}

	public Integer getLaneNo() {
		return LaneNo;
	}

	public void setLaneNo(Integer laneNo) {
		LaneNo = laneNo;
	}

	public String getPlateClass() {
		return PlateClass;
	}

	public void setPlateClass(String plateClass) {
		PlateClass = plateClass;
	}
	public Integer getSunvisor() {
		return Sunvisor;
	}

	public void setSunvisor(Integer sunvisor) {
		Sunvisor = sunvisor;
	}

	public Integer getSafetyBelt() {
		return SafetyBelt;
	}

	public void setSafetyBelt(Integer safetyBelt) {
		SafetyBelt = safetyBelt;
	}

	public Integer getCalling() {
		return Calling;
	}

	public void setCalling(Integer calling) {
		Calling = calling;
	}

	public Integer getNumOfPassenger() {
		return NumOfPassenger;
	}

	public void setNumOfPassenger(Integer numOfPassenger) {
		NumOfPassenger = numOfPassenger;
	}

	public Integer getSpeed() {
		return Speed;
	}

	public void setSpeed(Integer speed) {
		Speed = speed;
	}

	public String getDeviceID() {
		return DeviceID;
	}

	public void setDeviceID(String deviceID) {
		DeviceID = deviceID;
	}

	public Integer getVehicleColorDepth() {
		return VehicleColorDepth;
	}

	public void setVehicleColorDepth(Integer vehicleColorDepth) {
		VehicleColorDepth = vehicleColorDepth;
	}

	public String getVehicleColor() {
		return VehicleColor;
	}

	public void setVehicleColor(String vehicleColor) {
		VehicleColor = vehicleColor;
	}

	public String getVehicleBrand() {
		return VehicleBrand;
	}

	public void setVehicleBrand(String vehicleBrand) {
		VehicleBrand = vehicleBrand;
	}

	public String getVehicleModel() {
		return VehicleModel;
	}

	public void setVehicleModel(String vehicleModel) {
		VehicleModel = vehicleModel;
	}

	public String getVehicleClass() {
		return VehicleClass;
	}

	public void setVehicleClass(String vehicleClass) {
		VehicleClass = vehicleClass;
	}

	public String getPlateColor() {
		return PlateColor;
	}

	public void setPlateColor(String plateColor) {
		PlateColor = plateColor;
	}

}
