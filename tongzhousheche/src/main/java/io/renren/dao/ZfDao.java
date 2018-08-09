package io.renren.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import io.renren.vas.entity.ScAlarminfoEntity;
import io.renren.vas.entity.ScDtcldzdanEntity;
import io.renren.vas.entity.ScEtcptjdEntity;
import io.renren.vas.entity.ScGwrysjEntity;
import io.renren.vas.entity.ScScajEntity;
import io.renren.vas.entity.ZfssEntity;

@Mapper
public interface ZfDao {

	@Select("<script>" 
			+ "select plate,owner,color,model,nightout from sc_dtcldzdan "
			+ "<where>"
			+ " and nightout >= 90 "
			+ "<if test=\"plate != null and plate != ''\">" 
			+ " AND plate = #{plate}" 
			+ "</if>"
			+ "</where>"
			+ "<choose>" 
			+ "<when test = 'sidx.trim() != \"\"  and  sidx != null  '>" 
			+ " order by ${sidx} ${order}"
			+ "</when>" 
			+ "<otherwise>" 
			+ "  order by nightOut desc" 
			+ "</otherwise>" 
			+ " </choose>"
			+ "<if test='offset != null and limit != null'>" 
			+ "	limit  #{limit} offset #{offset}" 
			+ "</if>"
			+ "</script>")
	List<ScDtcldzdanEntity> pl(Map<String, Object> map);

	@Select("<script>" 
			+ "select count(id) from sc_dtcldzdan " 
			+"<where>"
			+ " and nightout >= 90 "
			+ "<if test=\"plate != null and plate != ''\"> " 
			+ "  and plate = #{plate}"
			+ "</if>"
			+"</where>"
			+ "</script>")
	int queryTotal(Map<String, Object> map);

	@Select("select * from sc_dtcldzdan order by nightout desc limit  #{limit} offset #{offset}")
	List<ScDtcldzdanEntity> al(Map<String, Object> map);

	@Select("select count(id) from sc_dtcldzdan")
	int total();

	@Select("select * from sc_dtcldzdan where nightout >= 90")
	List<ScDtcldzdanEntity> baojing();

	@Insert("insert into sc_alarminfo(receiver,message) values(#{receiver},#{message});")
	int info(@Param("receiver") String receiver, @Param("message") String message);

	@Select("select * from sc_alarminfo where message=#{message};")
	List<ScAlarminfoEntity> bd(@Param("message") String message);

	@Update("update sc_dtcldzdan as dt set nightout=(select CAST(atan(count(1))*200/3.14 as DECIMAL(13,2)) FROM sc_etcptjd t where t.PARK_NM='通州区' and cast(extract(hour from t.exit_time) as integer ) >21 or cast(extract(hour from t.exit_time) as integer )<5 and t.car_number=dt.plate );")
	int update();

	@Select("select * from sc_dtcldzdan ")
	List<ScDtcldzdanEntity> etcp_list();

	@Select("select * from sc_etcptjd ")
	List<ScEtcptjdEntity> etcp();

	@Update("update sc_dtcldzdan set morning=#{n}  where plate=#{plate};")
	int cr_n(@Param("n") float n, @Param("plate") String plate);

	@Select("select exit_time FROM sc_etcptjd t join " 
			+ "sc_dtcldzdan dt on dt.plate=t.car_number "
			+ "where t.PARK_NM='通州区' and  " 
			+ "t.exit_time >'${fxc} ' and " 
			+ "substring( t.exit_time,12,8)> '${btc}' "
			+ "and substring( t.exit_time,12,8)< '${btd}'  " 
			+ "and  t.exit_time<'${fxd} '   "
			+ "group by t.exit_time having count(*) > #{btcxc} and count(*) < #{btcxd};")
	List<ScEtcptjdEntity> ss_c(@Param("fxc") String fxc, @Param("fxd") String fxd, @Param("btc") String btc,
			@Param("btd") String btd, @Param("btcxc") int btcxc, @Param("btcxd") int btcxd);

	@Select("select * from sc_etcptjd where exit_time='${time}' ;")
	List<ScEtcptjdEntity> etcp_all(@Param("time") String time);

	@Select("select count(eid) as count from sc_etcptjd where exit_time='${time}' group by exit_time;")
	int etcp_count(@Param("time") String time);

	@Select("select CAST(atan(count(1))*200/3.14 as DECIMAL(13,2))  as numb FROM sc_etcptjd where exit_time='${time}';")
	double etcp_numb(@Param("time") String time);

	@Select("<script>" + "select * from sc_etcptjd" + "<if test='plate!=null '>" + " WHERE plate = #{plate} " + "</if>"
			+ "<choose>" + "<when test='sidx.trim() !=\"\"  and  sidx != null  '>" + " order by ${sidx} ${order}"
			+ "</when>" + "<otherwise>" + " order by nightOut desc" + "</otherwise>" + " </choose>"
			+ "<if test='offset != null and limit != null'>" + "	limit  '${limit}' offset '${offset}'" + "</if>"
			+ "</script>")
	List<ScEtcptjdEntity> ss_all(Map<String, Object> map);

	@Select("select count(1) from sc_zfss")
	int ss_total();

	@Insert("insert into sc_zfss values((select coalesce(max(id),0)+1 from sc_zfss),"
			+ "'${plate}','${num}','${count}','${exit_time}','${uu_id}')")
	int insert(@Param("plate") String plate, @Param("num") String num, @Param("count") String count,
			@Param("exit_time") String exit_time, @Param("uu_id") String uu_id);

	@Select("<script>" + "select * from sc_zfss" + "<choose>" + "<when test='sidx.trim() !=\"\"  and  sidx != null  '>"
			+ " order by ${sidx} ${order}" + "</when>" + " </choose>" + "<if test='offset != null and limit != null'>"
			+ "	limit  '${limit}' offset '${offset}'" + "</if>" + "</script>")
	List<ZfssEntity> select(Map<String, Object> map);

	@Delete("delete from sc_zfss where 1=1")
	void delete();

	@Select("select plate from sc_zfss ")
	List<String> plate();

	@Select("select *, ROUND(6378.138*2*ASIN(SQRT(POW(SIN((${weidu}*PI()/180-WEIDU*PI()/180)/2),2)+COS(${weidu}*PI()/180)*COS(WEIDU*PI()/180)*POW(SIN((${jingdu}*PI()/180-JINGDU*PI()/180)/2),2)))*1000) as juli "
			+ "from sc_etcptjd where car_number='${plate}' and juli <= ${count} ")
	List<ScEtcptjdEntity> all_cardt(@Param("plate") String plate, @Param("jingdu") double jingdu,
			@Param("weidu") double weidu, @Param("count") int count);

	@Select("<script>"
    	  + " select * from sc_etcptjd "
    	  + "<where>"
    	  + " ENTER_TIME BETWEEN NOW() - interval '10 min' AND NOW()"
    	  + "<if test = 'et == 1'>"
    	  + "  AND sjly = 'k' "
    	  + "</if>"
    	  + "<if test = 'zf == 1'>"
    	  + "  AND car_number IN (SELECT plate FROM sc_dtcldzdan WHERE nightout >= 90) "
    	  + "</if>"
    	  + "<if test = 'wd == 1'>"
    	  + "  AND car_number NOT LIKE '%京%' "
    	  + "</if>"
    	  + "<if test = 'gw == 1'>"
    	  + "  AND car_number IN (SELECT plate FROM sc_dtcldzdan WHERE highrisk >= 90) "
    	  + "</if>"
    	  + "<if test = 'sa == 1'>"
    	  + "  AND car_number IN (SELECT plate FROM sc_dtcldzdan WHERE isInvolved = 1) "
    	  + "</if>"
    	  + "<if test = 'cx == 1'>"
    	  + "  AND car_number IN (SELECT plate FROM sc_dtcldzdan WHERE abnormal >= 90) "
    	  + "</if>"
    	  + "</where>"
    	  + " GROUP BY eid "
    	  + " HAVING jingdu BETWEEN ${jind} - ${count} * 0.01 AND ${jind} + ${count} * 0.01 "
    	  + " AND weidu BETWEEN ${weid} - ${count} * 0.01 AND ${weid} + ${count} * 0.01 "
    	  + "</script>")
	List<ScEtcptjdEntity> card(Map<String, Object> map);

	@Select("select * from sc_etcptjd where car_number=#{plate}")
	List<ScEtcptjdEntity> zdrsj(@Param("plate") String plate);

	@Select("select * from sc_dtcldzdan where  plate=#{plate} ")
	ScDtcldzdanEntity dan(@Param("plate") String plate);

	@Select("select * FROM sc_etcptjd t where t.PARK_NM='通州区' and t.car_number=#{plate}")
	List<ScEtcptjdEntity> wd(@Param("plate") String plate);

	@Select("select * from sc_scaj where cph=#{plate}")
	ScScajEntity sa(@Param("plate") String plate);

	@Select("select * from sc_gwrysj where cp=#{plate}")
	ScGwrysjEntity gw(@Param("plate") String plate);

	@Select("select * from sc_dtcldzdan where abnormal>0 and plate=#{plate}")
	List<ScDtcldzdanEntity> cxyc(@Param("plate") String plate);
	
	@Select("SELECT count(eid) FROM sc_etcptjd "
		  + " WHERE cast(extract(hour from exit_time) as integer ) = ${time} "
		  + " AND extract(DAY from exit_time) = extract (DAY from NOW()) "
		  + " AND extract(MONTH from exit_time) = extract (MONTH from NOW()) "
		  + " AND extract(YEAR from exit_time) = extract (YEAR from NOW()) ")
	int getCount(@Param("time") int time);
	
	@Update("update sc_dtcldzdan as dt set nightout=("
		  + " select CAST(atan(count(1))*200/3.14 as DECIMAL(13,2)) "
		  + " FROM sc_etcptjd t "
		  + " where t.PARK_NM='通州区' "
		  + " and cast(extract(hour from t.enter_time) as integer ) > 21 or cast(extract(hour from t.enter_time) as integer ) < 5 "
		  + " and t.car_number= #{plate})"
		  + "where dt.plate = #{plate} ")
	void zfycUpdate(@Param("plate") String plate);
	
	@Select("SELECT nightout FROM sc_dtcldzdan WHERE plate = #{plate} ")
	List<ScDtcldzdanEntity> getNightout(@Param("plate") String plate);

}
