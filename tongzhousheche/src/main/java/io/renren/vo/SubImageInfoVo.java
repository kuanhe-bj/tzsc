package io.renren.vo;

import java.util.Date;

/**
 * 公安部一所图像对象
 * @author zhangyulong
 * @date 2018/3/9
 */
public class SubImageInfoVo {


    String ImageID;

    int EventSort;

    String DeviceID;

    String StoragePath;

    /**
     * 图像类型 详细请参照第三部分文档 3.53 图像类型
     */
    String Type;
    /**
     * 图像文件格式
     */
    String FileFormat;

    Date ShotTime;

    int Width;

    int Height;

    String Data;

    public String getImageID() {
        return ImageID;
    }

    public void setImageID(String imageID) {
        ImageID = imageID;
    }

    public int getEventSort() {
        return EventSort;
    }

    public void setEventSort(int eventSort) {
        EventSort = eventSort;
    }

    public String getDeviceID() {
        return DeviceID;
    }

    public void setDeviceID(String deviceID) {
        DeviceID = deviceID;
    }

    public String getStoragePath() {
        return StoragePath;
    }

    public void setStoragePath(String storagePath) {
        StoragePath = storagePath;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getFileFormat() {
        return FileFormat;
    }

    public void setFileFormat(String fileFormat) {
        FileFormat = fileFormat;
    }

    public Date getShotTime() {
        return ShotTime;
    }

    public void setShotTime(Date shotTime) {
        ShotTime = shotTime;
    }

    public int getWidth() {
        return Width;
    }

    public void setWidth(int width) {
        Width = width;
    }

    public int getHeight() {
        return Height;
    }

    public void setHeight(int height) {
        Height = height;
    }

    public String getData() {
        return Data;
    }

    public void setData(String data) {
        Data = data;
    }
}
