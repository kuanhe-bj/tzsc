package io.renren.vo;

import java.util.Date;
import java.util.List;

/**
 * 公安部一所车辆对象
 *
 * @author zhangyulong
 * @date 2018/3/6
 */
public class MotorVehicleVo {
    /**
     * 车辆标识 string(48)
     */
    String MotorVehicleID;
    /**
     * 信息分类
     */
    int InfoKind;
    /**
     * 来源标识 string(41)
     */
    String SourceID;
    /**
     * 关联卡口编号 string(20)
     */
    String TollgateID;
    /**
     * 设备编码 string(20)
     */
    String DeviceID;
    /**
     * 近景照片 string(256)
     */
    String StorageUrl1;
    /**
     * 车牌照片 string(256)
     */
    String StorageUrl2;
    /**
     * 远景照片 string(256)
     */
    String StorageUrl3;
    /**
     * 合成图 string(256)
     */
    String StorageUrl4;
    /**
     * 缩略图 string(256)
     */
    String StorageUrl5;
    /**
     * 位置标记时间
     */
    Date MarkTime;
    /**
     * 车辆出现时间
     */
    Date AppearTime;
    /**
     * 车辆消失时间
     */
    Date DisappearTime;
    /**
     * 车道号
     */
    int LaneNo;
    /**
     * 有无车牌
     */
    boolean HasPlate;
    /**
     * 号牌种类 string(2)
     */
    String PlateClass;
    /**
     * 车牌颜色 string(2)
     */
    String PlateColor;
    /**
     * 车牌号 string(0..15)
     */
    String PlateNo;
    /**
     * 挂车牌号 string(0..15)
     */
    String PlateNoAttach;
    /**
     * 车牌描述 string(64)
     */
    String PlateDescribe;
    /**
     * 是否套牌
     */
    boolean IsDecked;
    /**
     * 是否涂改
     */
    boolean IsAltered;
    /**
     * 是否遮挡
     */
    boolean IsCovered;
    /**
     * 行驶速度 km/h
     */
    double Speed;
    /**
     * 行驶方向
     */
    String Direction;
    /**
     * 行驶状态代码 string(4)
     */
    String DrivingStatusCode;
    /**
     * 车辆使用性质代码
     */
    int UsingPropertiesCode;
    /**
     * 车辆类型 string(3)
     */
    String VehicleClass;
    /**
     * 车辆品牌 string(3)
     */
    String VehicleBrand;
    /**
     * 车辆型号 string(0..32)
     */
    String VehicleModel;
    /**
     * 车辆年款
     */
    String VehicleStyles;
    /**
     * 车辆长度
     */
    String VehicleLength;
    /**
     * 车辆宽度
     */
    String VehicleWidth;
    /**
     * 车辆高度
     */
    String VehicleHeight;
    /**
     * 车身颜色 string(2)
     */
    String VehicleColor;
    /**
     * 颜色深浅
     */
    String VehicleColorDepth;
    /**
     * 改装标志
     */
    boolean IsModified;
    /**
     * 撞痕信息
     */
    String HitMarkInfo;
    /**
     * 车内人数
     */
    int NumOfPassenger;
    /**
     * 经过时刻
     */
    Date PassTime;
    /**
     * 经过道路名称
     */
    String NameOfPassedRoad;
    /**
     * 是否可疑车
     */
    String IsSuspicious;
    /**
     * 号牌识别可信度
     */
    String PlateReliability;
    /**
     * 图像列表
     */
    List<SubImageInfoVo> SubImageList;

    public String getMotorVehicleID() {
        return MotorVehicleID;
    }

    public void setMotorVehicleID(String motorVehicleID) {
        MotorVehicleID = motorVehicleID;
    }

    public int getInfoKind() {
        return InfoKind;
    }

    public void setInfoKind(int infoKind) {
        InfoKind = infoKind;
    }

    public String getSourceID() {
        return SourceID;
    }

    public void setSourceID(String sourceID) {
        SourceID = sourceID;
    }

    public String getTollgateID() {
        return TollgateID;
    }

    public void setTollgateID(String tollgateID) {
        TollgateID = tollgateID;
    }

    public String getDeviceID() {
        return DeviceID;
    }

    public void setDeviceID(String deviceID) {
        DeviceID = deviceID;
    }

    public String getStorageUrl1() {
        return StorageUrl1;
    }

    public void setStorageUrl1(String storageUrl1) {
        StorageUrl1 = storageUrl1;
    }

    public String getStorageUrl2() {
        return StorageUrl2;
    }

    public void setStorageUrl2(String storageUrl2) {
        StorageUrl2 = storageUrl2;
    }

    public String getStorageUrl3() {
        return StorageUrl3;
    }

    public void setStorageUrl3(String storageUrl3) {
        StorageUrl3 = storageUrl3;
    }

    public String getStorageUrl4() {
        return StorageUrl4;
    }

    public void setStorageUrl4(String storageUrl4) {
        StorageUrl4 = storageUrl4;
    }

    public String getStorageUrl5() {
        return StorageUrl5;
    }

    public void setStorageUrl5(String storageUrl5) {
        StorageUrl5 = storageUrl5;
    }

    public Date getMarkTime() {
        return MarkTime;
    }

    public void setMarkTime(Date markTime) {
        MarkTime = markTime;
    }

    public Date getAppearTime() {
        return AppearTime;
    }

    public void setAppearTime(Date appearTime) {
        AppearTime = appearTime;
    }

    public Date getDisappearTime() {
        return DisappearTime;
    }

    public void setDisappearTime(Date disappearTime) {
        DisappearTime = disappearTime;
    }

    public int getLaneNo() {
        return LaneNo;
    }

    public void setLaneNo(int laneNo) {
        LaneNo = laneNo;
    }

    public boolean isHasPlate() {
        return HasPlate;
    }

    public void setHasPlate(boolean hasPlate) {
        HasPlate = hasPlate;
    }

    public String getPlateClass() {
        return PlateClass;
    }

    public void setPlateClass(String plateClass) {
        PlateClass = plateClass;
    }

    public String getPlateColor() {
        return PlateColor;
    }

    public void setPlateColor(String plateColor) {
        PlateColor = plateColor;
    }

    public String getPlateNo() {
        return PlateNo;
    }

    public void setPlateNo(String plateNo) {
        PlateNo = plateNo;
    }

    public String getPlateNoAttach() {
        return PlateNoAttach;
    }

    public void setPlateNoAttach(String plateNoAttach) {
        PlateNoAttach = plateNoAttach;
    }

    public String getPlateDescribe() {
        return PlateDescribe;
    }

    public void setPlateDescribe(String plateDescribe) {
        PlateDescribe = plateDescribe;
    }

    public boolean isDecked() {
        return IsDecked;
    }

    public void setDecked(boolean decked) {
        IsDecked = decked;
    }

    public boolean isAltered() {
        return IsAltered;
    }

    public void setAltered(boolean altered) {
        IsAltered = altered;
    }

    public boolean isCovered() {
        return IsCovered;
    }

    public void setCovered(boolean covered) {
        IsCovered = covered;
    }

    public double getSpeed() {
        return Speed;
    }

    public void setSpeed(double speed) {
        Speed = speed;
    }

    public String getDirection() {
        return Direction;
    }

    public void setDirection(String direction) {
        Direction = direction;
    }

    public String getDrivingStatusCode() {
        return DrivingStatusCode;
    }

    public void setDrivingStatusCode(String drivingStatusCode) {
        DrivingStatusCode = drivingStatusCode;
    }

    public int getUsingPropertiesCode() {
        return UsingPropertiesCode;
    }

    public void setUsingPropertiesCode(int usingPropertiesCode) {
        UsingPropertiesCode = usingPropertiesCode;
    }

    public String getVehicleClass() {
        return VehicleClass;
    }

    public void setVehicleClass(String vehicleClass) {
        VehicleClass = vehicleClass;
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

    public String getVehicleStyles() {
        return VehicleStyles;
    }

    public void setVehicleStyles(String vehicleStyles) {
        VehicleStyles = vehicleStyles;
    }

    public String getVehicleLength() {
        return VehicleLength;
    }

    public void setVehicleLength(String vehicleLength) {
        VehicleLength = vehicleLength;
    }

    public String getVehicleWidth() {
        return VehicleWidth;
    }

    public void setVehicleWidth(String vehicleWidth) {
        VehicleWidth = vehicleWidth;
    }

    public String getVehicleHeight() {
        return VehicleHeight;
    }

    public void setVehicleHeight(String vehicleHeight) {
        VehicleHeight = vehicleHeight;
    }

    public String getVehicleColor() {
        return VehicleColor;
    }

    public void setVehicleColor(String vehicleColor) {
        VehicleColor = vehicleColor;
    }

    public String getVehicleColorDepth() {
        return VehicleColorDepth;
    }

    public void setVehicleColorDepth(String vehicleColorDepth) {
        VehicleColorDepth = vehicleColorDepth;
    }

    public boolean isModified() {
        return IsModified;
    }

    public void setModified(boolean modified) {
        IsModified = modified;
    }

    public String getHitMarkInfo() {
        return HitMarkInfo;
    }

    public void setHitMarkInfo(String hitMarkInfo) {
        HitMarkInfo = hitMarkInfo;
    }

    public int getNumOfPassenger() {
        return NumOfPassenger;
    }

    public void setNumOfPassenger(int numOfPassenger) {
        NumOfPassenger = numOfPassenger;
    }

    public Date getPassTime() {
        return PassTime;
    }

    public void setPassTime(Date passTime) {
        PassTime = passTime;
    }

    public String getNameOfPassedRoad() {
        return NameOfPassedRoad;
    }

    public void setNameOfPassedRoad(String nameOfPassedRoad) {
        NameOfPassedRoad = nameOfPassedRoad;
    }

    public String getIsSuspicious() {
        return IsSuspicious;
    }

    public void setIsSuspicious(String isSuspicious) {
        IsSuspicious = isSuspicious;
    }

    public String getPlateReliability() {
        return PlateReliability;
    }

    public void setPlateReliability(String plateReliability) {
        PlateReliability = plateReliability;
    }

    public List<SubImageInfoVo> getSubImageList() {
        return SubImageList;
    }

    public void setSubImageList(List<SubImageInfoVo> subImageList) {
        SubImageList = subImageList;
    }
}
