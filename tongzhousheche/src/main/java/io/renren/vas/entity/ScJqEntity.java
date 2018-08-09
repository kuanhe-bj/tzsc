package io.renren.vas.entity;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import io.renren.common.utils.DateJsonDeserializer;
import io.renren.common.utils.DateJsonSerializer;

public class ScJqEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //
    private String id;
    //
    private String jcjbh;
    @JsonSerialize(using=DateJsonSerializer.class)  
    @JsonDeserialize(using=DateJsonDeserializer.class)
    private Date asjfssj;
    @JsonSerialize(using=DateJsonSerializer.class)  
    @JsonDeserialize(using=DateJsonDeserializer.class)
    private Date asjjssj;
    //
    private String asjfsddid;
    //
    private String asjfsxz;
    //
    private String fynr;
    //
    private String sspcsid;
    //
    private String asjlxid;
    //
    private String cph;
    //
    private String barmc;
    //
    private String badwmc;

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
    public void setJcjbh(String jcjbh) {
        this.jcjbh = jcjbh;
    }
    /**
     * 获取：
     */
    public String getJcjbh() {
        return jcjbh;
    }
    /**
     * 设置：
     */
    public void setAsjfssj(Date asjfssj) {
        this.asjfssj = asjfssj;
    }
    /**
     * 获取：
     */
    public Date getAsjfssj() {
        return asjfssj;
    }
    /**
     * 设置：
     */
    public void setAsjjssj(Date asjjssj) {
        this.asjjssj = asjjssj;
    }
    /**
     * 获取：
     */
    public Date getAsjjssj() {
        return asjjssj;
    }
    /**
     * 设置：
     */
    public void setAsjfsddid(String asjfsddid) {
        this.asjfsddid = asjfsddid;
    }
    /**
     * 获取：
     */
    public String getAsjfsddid() {
        return asjfsddid;
    }
    /**
     * 设置：
     */
    public void setAsjfsxz(String asjfsxz) {
        this.asjfsxz = asjfsxz;
    }
    /**
     * 获取：
     */
    public String getAsjfsxz() {
        return asjfsxz;
    }
    /**
     * 设置：
     */
    public void setFynr(String fynr) {
        this.fynr = fynr;
    }
    /**
     * 获取：
     */
    public String getFynr() {
        return fynr;
    }
    /**
     * 设置：
     */
    public void setSspcsid(String sspcsid) {
        this.sspcsid = sspcsid;
    }
    /**
     * 获取：
     */
    public String getSspcsid() {
        return sspcsid;
    }
    /**
     * 设置：
     */
    public void setAsjlxid(String asjlxid) {
        this.asjlxid = asjlxid;
    }
    /**
     * 获取：
     */
    public String getAsjlxid() {
        return asjlxid;
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
    public void setBarmc(String barmc) {
        this.barmc = barmc;
    }
    /**
     * 获取：
     */
    public String getBarmc() {
        return barmc;
    }
    /**
     * 设置：
     */
    public void setBadwmc(String badwmc) {
        this.badwmc = badwmc;
    }
    /**
     * 获取：
     */
    public String getBadwmc() {
        return badwmc;
    }
}
