package io.renren.vas.entity;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import io.renren.common.utils.DateJsonDeserializer;
import io.renren.common.utils.DateJsonSerializer;


/**
 * 
 * 
 * @author pwk
 * @email sunlightcs@gmail.com
 * @date 2018-02-09 15:19:34
 */
public class ScHlsjEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private String checkinfoid;
	//
	private String policemanid;
    @JsonSerialize(using=DateJsonSerializer.class)  
    @JsonDeserialize(using=DateJsonDeserializer.class)
	private Date checktime;
	//
	private String checkyear;
	//
	private String checkmonth;
	//
	private String checkday;
	//
	private String checkaddress;
	//
	private String checkqu;
	//
	private String checkjiedao;
	//
	private String checkquname;
	//
	private String checkjiedaoname;
	//
	private String checkpersoncardnumber;
	//
	private String checkpersonname;
	//
	private String checkcarnumber;

	/**
	 * 设置：
	 */
	public void setCheckinfoid(String checkinfoid) {
		this.checkinfoid = checkinfoid;
	}
	/**
	 * 获取：
	 */
	public String getCheckinfoid() {
		return checkinfoid;
	}
	/**
	 * 设置：
	 */
	public void setPolicemanid(String policemanid) {
		this.policemanid = policemanid;
	}
	/**
	 * 获取：
	 */
	public String getPolicemanid() {
		return policemanid;
	}
	/**
	 * 设置：
	 */
	public void setChecktime(Date checktime) {
		this.checktime = checktime;
	}
	/**
	 * 获取：
	 */
	public Date getChecktime() {
		return checktime;
	}
	/**
	 * 设置：
	 */
	public void setCheckyear(String checkyear) {
		this.checkyear = checkyear;
	}
	/**
	 * 获取：
	 */
	public String getCheckyear() {
		return checkyear;
	}
	/**
	 * 设置：
	 */
	public void setCheckmonth(String checkmonth) {
		this.checkmonth = checkmonth;
	}
	/**
	 * 获取：
	 */
	public String getCheckmonth() {
		return checkmonth;
	}
	/**
	 * 设置：
	 */
	public void setCheckday(String checkday) {
		this.checkday = checkday;
	}
	/**
	 * 获取：
	 */
	public String getCheckday() {
		return checkday;
	}
	/**
	 * 设置：
	 */
	public void setCheckaddress(String checkaddress) {
		this.checkaddress = checkaddress;
	}
	/**
	 * 获取：
	 */
	public String getCheckaddress() {
		return checkaddress;
	}
	/**
	 * 设置：
	 */
	public void setCheckqu(String checkqu) {
		this.checkqu = checkqu;
	}
	/**
	 * 获取：
	 */
	public String getCheckqu() {
		return checkqu;
	}
	/**
	 * 设置：
	 */
	public void setCheckjiedao(String checkjiedao) {
		this.checkjiedao = checkjiedao;
	}
	/**
	 * 获取：
	 */
	public String getCheckjiedao() {
		return checkjiedao;
	}
	/**
	 * 设置：
	 */
	public void setCheckquname(String checkquname) {
		this.checkquname = checkquname;
	}
	/**
	 * 获取：
	 */
	public String getCheckquname() {
		return checkquname;
	}
	/**
	 * 设置：
	 */
	public void setCheckjiedaoname(String checkjiedaoname) {
		this.checkjiedaoname = checkjiedaoname;
	}
	/**
	 * 获取：
	 */
	public String getCheckjiedaoname() {
		return checkjiedaoname;
	}
	/**
	 * 设置：
	 */
	public void setCheckpersoncardnumber(String checkpersoncardnumber) {
		this.checkpersoncardnumber = checkpersoncardnumber;
	}
	/**
	 * 获取：
	 */
	public String getCheckpersoncardnumber() {
		return checkpersoncardnumber;
	}
	/**
	 * 设置：
	 */
	public void setCheckpersonname(String checkpersonname) {
		this.checkpersonname = checkpersonname;
	}
	/**
	 * 获取：
	 */
	public String getCheckpersonname() {
		return checkpersonname;
	}
	/**
	 * 设置：
	 */
	public void setCheckcarnumber(String checkcarnumber) {
		this.checkcarnumber = checkcarnumber;
	}
	/**
	 * 获取：
	 */
	public String getCheckcarnumber() {
		return checkcarnumber;
	}
}
