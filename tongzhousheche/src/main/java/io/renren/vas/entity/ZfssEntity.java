package io.renren.vas.entity;

public class ZfssEntity {
private int id;
private String plate;
private String num;
private String count;
private String exit_time;
private String uu_id;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getPlate() {
	return plate;
}
public void setPlate(String plate) {
	this.plate = plate;
}
public String getNum() {
	return num;
}
public void setNum(String num) {
	this.num = num;
}
public String getCount() {
	return count;
}
public void setCount(String count) {
	this.count = count;
}
public String getExit_time() {
	return exit_time;
}
public void setExit_time(String exit_time) {
	this.exit_time = exit_time;
}
public String getUu_id() {
	return uu_id;
}
public void setUu_id(String uu_id) {
	this.uu_id = uu_id;
}
@Override
public String toString() {
	return "ZfssEntity [id=" + id + ", plate=" + plate + ", num=" + num + ", count=" + count + ", exit_time="
			+ exit_time + ", uu_id=" + uu_id + "]";
}
public ZfssEntity() {
	super();
	// TODO Auto-generated constructor stub
}
public ZfssEntity(int id, String plate, String num, String count, String exit_time, String uu_id) {
	super();
	this.id = id;
	this.plate = plate;
	this.num = num;
	this.count = count;
	this.exit_time = exit_time;
	this.uu_id = uu_id;
}


}
