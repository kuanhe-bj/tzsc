package io.renren.vo;

/**
 * 实时任务结果
 * @author moulin
 *
 */
public class ResultVo {

	private int id;//编号
	private int taskid;//任务id
	private String plateno;//车牌号
	private int createtime;//分析时间
	private String result;//分析结果
	private double rvalue;//分析值，100分制
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getTaskid() {
		return taskid;
	}
	public void setTaskid(int taskid) {
		this.taskid = taskid;
	}
	public String getPlateno() {
		return plateno;
	}
	public void setPlateno(String plateno) {
		this.plateno = plateno;
	}
	public int getCreatetime() {
		return createtime;
	}
	public void setCreatetime(int createtime) {
		this.createtime = createtime;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public double getRvalue() {
		return rvalue;
	}
	public void setRvalue(double rvalue) {
		this.rvalue = rvalue;
	}
	
}
