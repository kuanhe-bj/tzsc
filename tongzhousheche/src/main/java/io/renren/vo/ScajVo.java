package io.renren.vo;

/**
 * 涉车案件
 * @author moulin
 *
 */
public class ScajVo {
	private Integer id;
	private String ajbh;
	private String ajmc;//案件名称
	private String pcsgxid;//派出所管辖（流⽔号）#SSPCS 
	private String jq;//警区#JQ
	private String faddid;//发案地点（流⽔号）#XZQH 
	private String faddxz;//发案地点详址
	private String fakssj;//发案开始时间
	private String cph;
	private double jingdu;
	private double weidu;
	private String jyaq;
	public ScajVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ScajVo(Integer id, String ajbh, String ajmc, String pcsgxid, String jq, String faddid, String faddxz,
			String fakssj, String cph, double jingdu, double weidu, String jyaq) {
		super();
		this.id = id;
		this.ajbh = ajbh;
		this.ajmc = ajmc;
		this.pcsgxid = pcsgxid;
		this.jq = jq;
		this.faddid = faddid;
		this.faddxz = faddxz;
		this.fakssj = fakssj;
		this.cph = cph;
		this.jingdu = jingdu;
		this.weidu = weidu;
		this.jyaq = jyaq;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAjbh() {
		return ajbh;
	}
	public void setAjbh(String ajbh) {
		this.ajbh = ajbh;
	}
	public String getAjmc() {
		return ajmc;
	}
	public void setAjmc(String ajmc) {
		this.ajmc = ajmc;
	}
	public String getPcsgxid() {
		return pcsgxid;
	}
	public void setPcsgxid(String pcsgxid) {
		this.pcsgxid = pcsgxid;
	}
	public String getJq() {
		return jq;
	}
	public void setJq(String jq) {
		this.jq = jq;
	}
	public String getFaddid() {
		return faddid;
	}
	public void setFaddid(String faddid) {
		this.faddid = faddid;
	}
	public String getFaddxz() {
		return faddxz;
	}
	public void setFaddxz(String faddxz) {
		this.faddxz = faddxz;
	}
	public String getFakssj() {
		return fakssj;
	}
	public void setFakssj(String fakssj) {
		this.fakssj = fakssj;
	}
	public String getCph() {
		return cph;
	}
	public void setCph(String cph) {
		this.cph = cph;
	}
	public double getJingdu() {
		return jingdu;
	}
	public void setJingdu(double jingdu) {
		this.jingdu = jingdu;
	}
	public double getWeidu() {
		return weidu;
	}
	public void setWeidu(double weidu) {
		this.weidu = weidu;
	}
	public String getJyaq() {
		return jyaq;
	}
	public void setJyaq(String jyaq) {
		this.jyaq = jyaq;
	}
	
	
}
