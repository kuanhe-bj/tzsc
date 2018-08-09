package io.renren.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import io.renren.vas.entity.ScAlarminfoEntity;
import io.renren.vo.ScEtcpEntity;

@Mapper
public interface MainDao {
	//昼伏夜出
	@Select("SELECT count(1) FROM sc_dtcldzdan WHERE nightout >= 90 ")
	public int zfycQuery();
	//套牌嫌疑
	@Select("SELECT count(1) FROM sc_dtcldzdan WHERE multiPlate = 1 ")
	public int tpxyQuery();
	//隐秘车辆
	@Select("SELECT count(1) FROM sc_dtcldzdan WHERE hidden = 1 ")
	public int ymclQuery();
	//首次进城
	@Select("SELECT count(1) FROM sc_dtcldzdan WHERE isFirstIn = 1 ")
	public int scjcQuery();
	//停车位
	//暂时不计算
	@Select("")
	public int tcwQuery();
	//停车数量
	@Select("SELECT count(1) FROM sc_etcptjd WHERE lower(sjly) = 'p' ")
	public int tcslQuery(@Param("start") String start, @Param("end") String end);
	//今日入场
	@Select("SELECT count(1) FROM sc_etcptjd WHERE lower(sjly) = 'p' "
			+ "AND enter_time between #{start} and #{end} ")
	public int jrrcQuery(@Param("start") String start, @Param("end") String end);
	//今日出场
	@Select("SELECT count(1) FROM sc_etcptjd WHERE lower(sjly) = 'p' "
			+ "AND exit_time between #{start} and #{end} ")
	public int jrccQuery(@Param("start") String start, @Param("end") String end);
	//布控总量
	@Select("SELECT count(1) FROM sc_blacklist WHERE auditResults = 1")
	public int bkzlQuery();
	//今日布控
	@Select("SELECT count(1) FROM sc_blacklist WHERE auditResults = 1 "
			+ "AND audittime between #{start} and #{end} ")
	public int jrbkQuery(@Param("start") String start, @Param("end") String end);
	//累计报警
	@Select("SELECT count(1) FROM sc_alarminfo")
	public int ljbjQuery();
	//今日报警
	@Select("SELECT count(1) FROM sc_alarminfo "
			+ "WHERE triggerTime between #{start} and #{end} ")
	public int jrbjQuery(@Param("start") String start, @Param("end") String end);
	
	//实时卡口数据
	@Select("SELECT count(1) FROM sc_etcptjd WHERE sjly = 'k' AND enter_time between #{start} and #{end} ")
	int kakouTotal(@Param("start") String start, @Param("end") String end);
	
	//实时停车场数据
	@Select("SELECT count(1) FROM sc_etcptjd WHERE sjly = 'p' AND enter_time between #{start} and #{end} ")
	int tccTotal(@Param("start") String start, @Param("end") String end);
	
	//实时滚动数据
	@Select("SELECT car_number,adress,enter_time FROM sc_etcptjd "
		  + "WHERE enter_time between #{start} and #{end} "
		  + "ORDER BY enter_time DESC limit 100 offset 0 ")
	List<ScEtcpEntity> getInfo(@Param("start") String start, @Param("end") String end);
	
	//政府附近徘徊查询
	@Select("select aa.car_number as car_number,aa.num as num,a.enter_time as enter_time,a.adress as adress	from sc_etcptjd a, "
		  + " 	(select e.CAR_NUMBER as car_number,count(e.eid) as num "
		  + " 	from  sc_etcptjd e "
		  + " 	where jingdu BETWEEN 116.650876809 - 2 * 0.01 AND 116.650876809 + 2 * 0.01 "
    	  + "   AND weidu BETWEEN 39.908912673 - 2 * 0.01 AND 39.908912673 + 2 * 0.01 "
		  + " 	AND enter_time between #{start} and #{end} "
		  + " 	group by e.CAR_NUMBER ORDER BY num desc "
		  + " 	limit 50 offset 0) aa "
		  + " where a.car_number = aa.car_number and a.enter_time= "
		  + " (select max(enter_time) from sc_etcptjd where enter_time between #{start} and #{end} "
		  + " and car_number=aa.car_number) "
		  + " ORDER BY num desc ")
	List<ScEtcpEntity> getInformation(@Param("start") String start, @Param("end") String end);
	
	@Select("SELECT 1 AS num FROM sc_alarminfo WHERE carnum = #{cph} "
		  + "AND triggerTime between #{start} and #{end} "
		  + "AND message like '%政府附近徘徊%' ")
	List<ScAlarminfoEntity> checkCph(@Param("cph") String cph, @Param("start") String start, @Param("end") String end);
}
