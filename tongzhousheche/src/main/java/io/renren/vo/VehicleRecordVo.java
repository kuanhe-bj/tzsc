package io.renren.vo;

import java.util.Date;

/**
 * 车辆记录对象
 *
 * @author zhangyulong
 * @date 2018/3/9
 */
public class VehicleRecordVo {
	
	/**
     * 车牌号
     */
    String carNum;
    /**
     * 车牌号颜色
     */
    String carColor;
    /**
     * 经度
     */
    String longitude;
    /**
     * 纬度
     */
    String latitude;
    /**
     * 车辆型号
     */
    String type;
    /**
     * 车主
     */
    String onwer;

    /**
     * 数量
     */
    String number;
    /**
     * 编号
     */
    String DISTRICT_NM;
    /**
     * 编号
     */
    String jingdu;
    
    String weidu;
    
    Date recordTime;
    
    Date enterTime;
    
    Date exitTime;
    
    String cpssj;
    
    String ypssj;
    
    String kpssj;
    
    String tpssj;

    public String getWeidu() {
		return weidu;
	}

	public void setWeidu(String weidu) {
		this.weidu = weidu;
	}

	public String getCarNum() {
        return carNum;
    }

    public void setCarNum(String carNum) {
        this.carNum = carNum;
    }

    public String getCarColor() {
        return carColor;
    }

    public void setCarColor(String carColor) {
        this.carColor = carColor;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getOnwer() {
		return onwer;
	}

	public void setOnwer(String onwer) {
		this.onwer = onwer;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getDISTRICT_NM() {
		return DISTRICT_NM;
	}

	public void setDISTRICT_NM(String dISTRICT_NM) {
		DISTRICT_NM = dISTRICT_NM;
	}

	public String getJingdu() {
		return jingdu;
	}

	public void setJingdu(String jingdu) {
		this.jingdu = jingdu;
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

	public String getCpssj() {
		return cpssj;
	}

	public void setCpssj(String cpssj) {
		this.cpssj = cpssj;
	}

	public String getYpssj() {
		return ypssj;
	}

	public void setYpssj(String ypssj) {
		this.ypssj = ypssj;
	}

	public String getKpssj() {
		return kpssj;
	}

	public void setKpssj(String kpssj) {
		this.kpssj = kpssj;
	}

	public String getTpssj() {
		return tpssj;
	}

	public void setTpssj(String tpssj) {
		this.tpssj = tpssj;
	}

	public Date getRecordTime() {
		return recordTime;
	}

	public void setRecordTime(Date recordTime) {
		this.recordTime = recordTime;
	}
    
	
}
