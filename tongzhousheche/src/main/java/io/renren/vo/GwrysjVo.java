package io.renren.vo;

import java.util.Date;

/**
 * 高危重点人
 * @author moulin
 *
 */
public class GwrysjVo {
	private int id;//主键
	private String DRYBH;//重点人员编号
	private String XM;//姓名
	private int XB;//性别
	private String CP;//车牌号
	private Date CSRQ;//出生日期
	private String SFZH;//身份证号
	private String HJDQH;//户籍地区划
	private String HJDXZ;//户籍地详址
	private String XZDQH;//现住地区划
	private String XZDXZ;//现住地详址
	private String DIANHUA;//电话手机
	private String WANGLUO;//网络身份
	private String MZ;//民族
	private Boolean SHEKONG;//涉恐
	private Boolean SHEJIANG;//涉疆
	private Boolean SHEWEN;//涉稳
	private Boolean SHEZANG;//涉藏
	private Boolean SHEDU;//涉毒
	private Boolean SHANGFANG;//上访
	private Boolean JINGSHENBING;//精神病
	private Boolean ZAITAO;//在逃
	private Boolean ZAIYA;//在押
	private Boolean ZHAOSHI;//肇事肇祸
	private Boolean DAOQIE;//盗窃
	private Boolean RUSHI;//入室
	private Boolean TONGXUN;//通讯诈骗
	private Boolean PAQIE;//扒窃
	private int DAOCHE;//盗车
	private Boolean WEIZHU;//维族重点人
	private Boolean YIDAOYANGXI;//以盗养吸
	private Boolean QINCAI;//侵财
	private Boolean QIANKE;//前科
	private Boolean GAOWEIDIQU;//高危地区
	private Boolean ZONGHE;//综合指数
	private String JYAQ;//简要案情
	private int age;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getJYAQ() {
		return JYAQ;
	}
	public void setJYAQ(String jYAQ) {
		JYAQ = jYAQ;
	}
	public String getDRYBH() {
		return DRYBH;
	}
	public void setDRYBH(String dRYBH) {
		DRYBH = dRYBH;
	}
	public String getXM() {
		return XM;
	}
	public void setXM(String xM) {
		XM = xM;
	}
	public int getXB() {
		return XB;
	}
	public void setXB(int xB) {
		XB = xB;
	}
	public Date getCSRQ() {
		return CSRQ;
	}
	public void setCSRQ(Date cSRQ) {
		CSRQ = cSRQ;
	}
	public String getSFZH() {
		return SFZH;
	}
	public void setSFZH(String sFZH) {
		SFZH = sFZH;
	}
	public String getHJDQH() {
		return HJDQH;
	}
	public void setHJDQH(String hJDQH) {
		HJDQH = hJDQH;
	}
	public String getHJDXZ() {
		return HJDXZ;
	}
	public void setHJDXZ(String hJDXZ) {
		HJDXZ = hJDXZ;
	}
	public String getXZDQH() {
		return XZDQH;
	}
	public void setXZDQH(String xZDQH) {
		XZDQH = xZDQH;
	}
	public String getXZDXZ() {
		return XZDXZ;
	}
	public void setXZDXZ(String xZDXZ) {
		XZDXZ = xZDXZ;
	}
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
	public String getMZ() {
		return MZ;
	}
	public void setMZ(String mZ) {
		MZ = mZ;
	}
	public Boolean getSHEKONG() {
		return SHEKONG;
	}
	public void setSHEKONG(Boolean sHEKONG) {
		SHEKONG = sHEKONG;
	}
	public Boolean getSHEJIANG() {
		return SHEJIANG;
	}
	public void setSHEJIANG(Boolean sHEJIANG) {
		SHEJIANG = sHEJIANG;
	}
	public Boolean getSHEWEN() {
		return SHEWEN;
	}
	public void setSHEWEN(Boolean sHEWEN) {
		SHEWEN = sHEWEN;
	}
	public Boolean getSHEZANG() {
		return SHEZANG;
	}
	public void setSHEZANG(Boolean sHEZANG) {
		SHEZANG = sHEZANG;
	}
	public Boolean getSHEDU() {
		return SHEDU;
	}
	public void setSHEDU(Boolean sHEDU) {
		SHEDU = sHEDU;
	}
	public Boolean getSHANGFANG() {
		return SHANGFANG;
	}
	public void setSHANGFANG(Boolean sHANGFANG) {
		SHANGFANG = sHANGFANG;
	}
	public Boolean getJINGSHENBING() {
		return JINGSHENBING;
	}
	public void setJINGSHENBING(Boolean jINGSHENBING) {
		JINGSHENBING = jINGSHENBING;
	}
	public Boolean getZAITAO() {
		return ZAITAO;
	}
	public void setZAITAO(Boolean zAITAO) {
		ZAITAO = zAITAO;
	}
	public Boolean getZAIYA() {
		return ZAIYA;
	}
	public void setZAIYA(Boolean zAIYA) {
		ZAIYA = zAIYA;
	}
	public Boolean getZHAOSHI() {
		return ZHAOSHI;
	}
	public void setZHAOSHI(Boolean zHAOSHI) {
		ZHAOSHI = zHAOSHI;
	}
	public Boolean getDAOQIE() {
		return DAOQIE;
	}
	public void setDAOQIE(Boolean dAOQIE) {
		DAOQIE = dAOQIE;
	}
	public Boolean getRUSHI() {
		return RUSHI;
	}
	public void setRUSHI(Boolean rUSHI) {
		RUSHI = rUSHI;
	}
	public Boolean getTONGXUN() {
		return TONGXUN;
	}
	public void setTONGXUN(Boolean tONGXUN) {
		TONGXUN = tONGXUN;
	}
	public Boolean getPAQIE() {
		return PAQIE;
	}
	public void setPAQIE(Boolean pAQIE) {
		PAQIE = pAQIE;
	}
	
	public Boolean getWEIZHU() {
		return WEIZHU;
	}
	public void setWEIZHU(Boolean wEIZHU) {
		WEIZHU = wEIZHU;
	}
	public Boolean getYIDAOYANGXI() {
		return YIDAOYANGXI;
	}
	public void setYIDAOYANGXI(Boolean yIDAOYANGXI) {
		YIDAOYANGXI = yIDAOYANGXI;
	}
	public Boolean getQINCAI() {
		return QINCAI;
	}
	public void setQINCAI(Boolean qINCAI) {
		QINCAI = qINCAI;
	}
	public Boolean getQIANKE() {
		return QIANKE;
	}
	public void setQIANKE(Boolean qIANKE) {
		QIANKE = qIANKE;
	}
	public Boolean getGAOWEIDIQU() {
		return GAOWEIDIQU;
	}
	public void setGAOWEIDIQU(Boolean gAOWEIDIQU) {
		GAOWEIDIQU = gAOWEIDIQU;
	}
	public Boolean getZONGHE() {
		return ZONGHE;
	}
	public void setZONGHE(Boolean zONGHE) {
		ZONGHE = zONGHE;
	}
	public String getCP() {
		return CP;
	}
	public void setCP(String cP) {
		CP = cP;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getDAOCHE() {
		return DAOCHE;
	}
	public void setDAOCHE(int dAOCHE) {
		DAOCHE = dAOCHE;
	}
	
	
	
	
}
