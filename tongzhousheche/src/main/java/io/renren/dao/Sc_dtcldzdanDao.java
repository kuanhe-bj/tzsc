package io.renren.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import io.renren.modules.sys.dao.BaseDao;
import io.renren.vas.entity.ScDtcldzdanEntity;
import io.renren.vas.entity.ScEtcptjdEntity;
import io.renren.vas.entity.ScJdcjbxxEntity;
import io.renren.vo.Brand;
import io.renren.vo.Color;
import io.renren.vo.DtcldzdansVo;
import io.renren.vo.Model;
import io.renren.vo.ScEtcpEntity;
import io.renren.vo.ViolationVo;
import io.renren.vo.XianXingVo;

/**
 * 套牌分析
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-03-22 10:55:46
 */
@Mapper
public interface Sc_dtcldzdanDao extends BaseDao<ScDtcldzdanEntity> {

	@Select("<script>" 
			+ "select * from sc_dtcldzdan"
			+ "<where>"
			+ "<if test='plate!=null '>" 
			+ "  and plate = #{plate}" 
			+ "</if>"
			+ "</where>"
			+ "<choose>" 
			+ "<when test='sidx.trim() !=\"\"  and  sidx != null  '>" 
			+ "  order by ${sidx} ${order}"
			+ "</when>" 
			+ "<otherwise>" 
			+ "  order by isfake desc" 
			+ "</otherwise>" 
			+ " </choose>"
			+ "<if test='offset != null and limit != null'>" 
			+ "	 limit #{limit} offset #{offset}" 
			+ "</if>" 
			+ "</script>")
	List<ScDtcldzdanEntity> queryList(Map<String, Object> map);

	@Select("SELECT * FROM sc_dtcldzdan WHERE SFZ = #{sfz} order by id ")
	List<ScDtcldzdanEntity> sfzByList(String sfz);

	@Update("UPDATE sc_dtcldzdan SET isSuspects = 1 WHERE sfz = #{sfz}")
	int sfzUpdate(String sfz);

	@Select("SELECT * FROM sc_dtcldzdan WHERE plate = #{plate}")
	List<ScDtcldzdanEntity> plateByList(String plate);

	@Update("UPDATE sc_dtcldzdan SET isInvolved = 1 WHERE plate = #{plate}")
	int plateUpdate(String plate);

	@Select("select count(id) from sc_dtcldzdan ")
	int queryTotal(Map<String, Object> map);
	
	//限行start
	@Select("SELECT plate cph FROM sc_dtcldzdan")
	List<XianXingVo> queryCp();
	
	@Select("SELECT CAR_NUMBER cph,EXIT_TIME time1 FROM sc_etcptjd where CAR_NUMBER =#{cph} and EXIT_TIME between '${time1}' and '${time2}'")
	List<XianXingVo> queryXC(Map<String, Object> map);
	
	@Select("<script>"
			+ "select * from sc_xianxing "
			+ "where startTime &lt;='${timeXC}' and endTime &gt;= '${timeXC}'"
			+ "<if test=\' zhou1!=\"\" and zhou1 !=null \'>"
			+ " and zhou1 like '%${cph}%'"
			+ "</if>"
			+ "<if test=\' zhou2!=\"\" and zhou2 !=null \'>"
			+ " and zhou2 like '%${cph}%'"
			+ "</if>"
			+ "<if test=\' zhou3!=\"\" and zhou3 !=null \'>"
			+ " and zhou3 like '%${cph}%'"
			+ "</if>"
			+ "<if test=\' zhou4!=\"\" and zhou4 !=null \'>"
			+ " and zhou4 like '%${cph}%'"
			+ "</if>"
			+ "<if test=\' zhou5!=\"\" and zhou5 !=null \'>"
			+ " and zhou5 like '%${cph}%'"
			+ "</if>"
			+ "</script>")
	List<XianXingVo> queryWH(Map<String, Object> map);

	@Update("UPDATE sc_dtcldzdan SET limits = ${limits} WHERE plate = #{plate}")
	int xxUpdate(@Param("plate") String plate,@Param("limits")float limits);
	
	@Update("update sc_dtcldzdan "
			+ " set limits = ${count} "
			+ "WHERE plate = #{plate} ")
	int xxUpdate2(@Param("count") double count, @Param("plate") String plate);
	
	@Select("select count(1) as count,plateno "
		  + " FROM sc_violation where plateno in (${plate}) "
		  + " and vtime > #{time} "
		  + " and islimitout = 1 "
		  + " group by plateno ")
	List<ViolationVo> limit(@Param("plate") String plate, @Param("time") String time);
	
	@Update("update sc_dtcldzdan set limits=0")
	int updateLimits();
	
	@Select("SELECT * FROM sc_dtcldzdan ORDER BY id LIMIT 1 OFFSET ${i}")
	ScDtcldzdanEntity queryOne(@Param("i") int i);
	//限行end
	
	//连续违章分析start
	@Update(" update sc_dtcldzdan dt set dt.contViolation=("
			+" select atan(count(1))*200/3.14 FROM sc_violation t "
			+" where t.plateno=dt.plate and t.vtime>date_sub(curdate(), interval 1 month))")
	int weizhangUpdate();
	
	@Update("update sc_dtcldzdan set contViolation=0")
	int updateContViolation();
	
	//连续违章分析end
	
	@Select("select * from sc_dtcldzdan GROUP BY plate,id ")
	List<ScDtcldzdanEntity> total();
	
	@Select("SELECT plate from sc_dtcldzdan ORDER BY id LIMIT 1000 offset ${count} ")
	List<ScDtcldzdanEntity> getScDtcldzdanEntity(@Param("count") int count);
	
	@Select("SELECT count(eid) AS count FROM sc_etcptjd WHERE car_number IN "
		  + "(SELECT plate FROM sc_dtcldzdan WHERE limits >= 90) "
		  + " GROUP BY extract(DAY from EXIT_TIME),extract(MONTH from EXIT_TIME),extract(YEAR from EXIT_TIME) "
		  + " ORDER BY count DESC"
		  + " limit 1 OFFSET ${num} ")
	List<ScEtcptjdEntity> getCount(@Param("num") int num);
	
	@Select("SELECT car_number AS carNumber,vehiclecolor AS VehicleColor,vehiclemodel AS VehicleModel,vehiclebrand AS VehicleBrand FROM sc_etcptjd " 
          + " where extract(DAY from enter_time) = ${day} "
          + " AND extract(MONTH from enter_time) = ${month} "
          + " AND extract(YEAR from enter_time) = ${year} "
          + " AND NOT EXISTS (SELECT 1 FROM sc_dtcldzdan) "
          + " GROUP BY car_number,vehiclecolor,vehiclemodel,vehiclebrand "
          + " LIMIT 1000 OFFSET ${count} ")
	List<ScEtcpEntity> getCarNumber(@Param("day") int day, @Param("month") int month, @Param("year") int year, @Param("count") int count);
	
	@Select("SELECT color FROM sc_color WHERE value = #{value} ")
	List<Color> getColor(@Param("value") String value);
	
	@Select("SELECT model FROM sc_model WHERE value = #{value} ")
	List<Model> getModel(@Param("value") String value);
	
	@Select("SELECT contact FROM sc_brand WHERE value = #{value} ")
	List<Brand> getBrand(@Param("value") String value);
	
	@Select("SELECT sfzmhm,syr FROM sc_jdcjbxx WHERE cph = #{plate} ")
	List<ScJdcjbxxEntity> getSfzAndName(@Param("plate") String plate);
	
	@Insert("INSERT INTO sc_dtcldzdan ("
		  + " plate,owner,color,brand,model,sfz,abnormal,violation,nightout,highrisk,"
		  + " accident,hidden,isfake,isindoubt,isinvolved,issuspects,isfirstin,times,"
		  + " limits,summary,onlyenter,overspeed,contviolation,abtravel,wander,efence,"
		  + " multiplate,sensitive,robbery,obscured,facecover,noplate,records,drug,atlarge,"
		  + " relative,morning,address,timeabnormal,longstay,tendency,black"
		  + " ) VALUES ( "
		  + " #{plate},#{owner},#{color},#{brand},#{model},#{sfz},0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,"
		  + " 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0)")
	void saveDtcldzdansVo(DtcldzdansVo DtcldzdansVo);
}
