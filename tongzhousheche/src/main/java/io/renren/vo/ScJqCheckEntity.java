package io.renren.vo;

/**
 * 精确查询
 */
import java.io.Serializable;
import java.util.Date;

public class ScJqCheckEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //
    private String id;
    //
    private String cph;
    //
    private Date qssj;
    //
    private Date jssj;
    //
    private String address;
    //
    private String laiyuan;
    //
	private String time;
    //
    private String jingdu;
    //
    private String weidu;
    /**
     * 设置：
     */
    public void setId(String id) {
        this.id = id;
    }
    /**
     * 获取：
     */
    public String getId() {
        return id;
    }
    /**
     * 设置：
     */
    public void setCph(String cph) {
        this.cph = cph;
    }
    /**
     * 获取：
     */
    public String getCph() {
        return cph;
    }
    /**
     * 设置：
     */
    public void setQssj(Date qssj) {
        this.qssj = qssj;
    }
    /**
     * 获取：
     */
    public Date getQssj() {
        return qssj;
    }
    /**
     * 设置：
     */
    public void setJssj(Date jssj) {
        this.jssj = jssj;
    }
    /**
     * 获取：
     */
    public Date getJssj() {
        return jssj;
    }
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getLaiyuan() {
		return laiyuan;
	}
	public void setLaiyuan(String laiyuan) {
		this.laiyuan = laiyuan;
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
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
    
}
