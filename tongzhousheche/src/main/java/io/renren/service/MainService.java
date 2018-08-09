package io.renren.service;

import java.util.List;

import io.renren.vas.entity.ScEtcptjdEntity;
import io.renren.vo.ScEtcpEntity;

public interface MainService {
	public int query() ;
	//昼伏夜出
	public int zfycQuery();
	//套牌嫌疑
	public int tpxyQuery();
	//隐秘车辆
	public int ymclQuery();
	//首次进城
	public int scjcQuery();
	//停车位
	public int tcwQuery();
	//停车数量
	public int tcslQuery();
	//今日入场
	public int jrrcQuery();
	//今日出场
	public int jrccQuery();
	//布控总量
	public int bkzlQuery();
	//今日布控
	public int jrbkQuery();
	//累计报警
	public int ljbjQuery();
	//今日报警
	public int jrbjQuery();
	
	int kakouTotal();
	
	int tccTotal();
	
	List<ScEtcpEntity> getInfo();
	
	List<ScEtcpEntity> getCarInfo();
	
	void alarm(List<ScEtcpEntity> list);
}
