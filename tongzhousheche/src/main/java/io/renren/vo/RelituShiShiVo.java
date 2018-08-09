package io.renren.vo;

/**
 * 热力图任务vo
 **/
public class RelituShiShiVo {
	private String id;
	private String username;//用户名
	private String mapname;//热力图名称
	private String mapsql;//热力图sql
	private String refreshtime;//刷新间隔时间
	private String createtime;//创建时间
	private Integer valid;//状态
	public RelituShiShiVo(String id, String username, String mapname, String mapsql, String refreshtime,
			String createtime, Integer valid) {
		super();
		this.id = id;
		this.username = username;
		this.mapname = mapname;
		this.mapsql = mapsql;
		this.refreshtime = refreshtime;
		this.createtime = createtime;
		this.valid = valid;
	}
	public RelituShiShiVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getMapname() {
		return mapname;
	}
	public void setMapname(String mapname) {
		this.mapname = mapname;
	}
	public String getMapsql() {
		return mapsql;
	}
	public void setMapsql(String mapsql) {
		this.mapsql = mapsql;
	}
	public String getRefreshtime() {
		return refreshtime;
	}
	public void setRefreshtime(String refreshtime) {
		this.refreshtime = refreshtime;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public Integer getValid() {
		return valid;
	}
	public void setValid(Integer valid) {
		this.valid = valid;
	}
	
}
