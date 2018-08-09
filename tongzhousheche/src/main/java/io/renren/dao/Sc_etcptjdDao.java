package io.renren.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import io.renren.modules.sys.dao.BaseDao;
import io.renren.vas.entity.ScEtcptjdEntity;

/**
 * 
 * 
 * @author pwk
 * @email sunlightcs@gmail.com
 * @date 2018-02-09 15:19:34
 */
@Mapper
public interface Sc_etcptjdDao extends BaseDao<ScEtcptjdEntity> {
	
	//落脚点查询
	@Select("<script>" 
		  + " select car_number,jingdu,weidu,count(eid) as count,adress from sc_etcptjd " 
		  + " where CAR_NUMBER = #{carNum} "
		  + " AND ENTER_TIME BETWEEN #{start} AND #{end} " 
		  + " group by jingdu,weidu,car_number,adress "
		  + "<choose>"
		  + "  <when test='sidx.trim() !=\"\"  and  sidx != null' >" 
		  + "     order by ${sidx} ${order}" 
		  + "  </when>"
		  + "  <otherwise> " 
		  + "     order by count desc" 
		  + "  </otherwise> " 
		  + "</choose> "
		  + "<if test='offset != null and limit != null'>" 
		  + "	  limit #{limit} offset #{offset}" 
		  + "</if>"
		  + "</script>")
	List<ScEtcptjdEntity> findByCPH(Map<String, Object> map);

	@Select("SELECT * FROM sc_etcptjd WHERE CAR_NUMBER = #{cph} ")
	List<ScEtcptjdEntity> list(String carNum);

	@Select("<script> " 
		  + "select * from sc_etcptjd " 
		  + "<choose>"
		  + "  <when test='sidx.trim() !=\"\"  and  sidx != null' >" 
		  + "     order by ${sidx} ${order}" 
		  + "  </when>"
		  + "  <otherwise> " 
		  + "     order by eid desc" 
		  + "  </otherwise> " 
		  + "</choose> "
		  + "<if test='offset != null and limit != null'>" 
		  + "	  limit #{limit} offset #{offset}" 
		  + "</if>"
		  + "</script>")
	List<ScEtcptjdEntity> queryList(Map<String, Object> map);

	@Select("select count(eid) from sc_etcptjd ")
	int queryTotal(Map<String, Object> map);
	
	@Select("select count(eid) as count from sc_etcptjd "
		  + "WHERE CAR_NUMBER = #{carNum} "
		  + "AND ENTER_TIME BETWEEN #{start} AND #{end} "
		  + "group by jingdu,weidu,car_number,adress ")
	List<ScEtcptjdEntity> total(Map<String, Object> map);
	
	@Select("SELECT car_number,jingdu,weidu,enter_time FROM sc_etcptjd WHERE CAR_NUMBER = "
			+ "(SELECT plate from sc_dtcldzdan ORDER BY id LIMIT 1 offset ${num}) "
			+ "AND enter_time BETWEEN #{start} AND #{end} ")
	List<ScEtcptjdEntity> check(@Param("num") int count, @Param("start") String start, @Param("end") String end);

	@Update("UPDATE sc_dtcldzdan SET multiPlate = 1 WHERE plate = #{plate}")
	int update(String plate);

	@Select("SELECT CAR_NUMBER,exit_time FROM (SELECT car_number,exit_time FROM sc_etcptjd WHERE sjly = 'p') AS a "
		  + " WHERE CAR_NUMBER = "
		  + "  (SELECT plate FROM sc_dtcldzdan ORDER BY id LIMIT 1 offset ${num})"
		  + " AND EXIT_TIME BETWEEN #{start} AND #{end} "
		  + " GROUP BY CAR_NUMBER,exit_time,eid ")
	List<ScEtcptjdEntity> hidden(@Param("num") int num, @Param("start") String start, @Param("end") String end);

	@Update("UPDATE sc_dtcldzdan SET hidden = 1 WHERE plate in (${plates}) ")
	int setHidden(@Param("plates") String plates);

	@Select("SELECT plate from sc_dtcldzdan ORDER BY id LIMIT 1 offset ${count}")
	String getPlate(int count);

	@Select("SELECT CAR_NUMBER,exit_time FROM sc_etcptjd WHERE CAR_NUMBER = "
		  + "(SELECT plate FROM sc_dtcldzdan ORDER BY id LIMIT 1 offset ${count})"
		  + " AND sjly = 'p' ")
	List<ScEtcptjdEntity> OnlyEnter(@Param("count") int count);

	@Update("UPDATE sc_dtcldzdan SET onlyEnter = 0 ")
	int updateOnlyEnter();

	@Update("UPDATE sc_dtcldzdan SET onlyEnter = 1 WHERE plate = #{cph}")
	int setOnlyEnter(String cph);
	
	@Select("SELECT count(eid) FROM sc_etcptjd WHERE sjly = 'k' ")
	int num();

}
