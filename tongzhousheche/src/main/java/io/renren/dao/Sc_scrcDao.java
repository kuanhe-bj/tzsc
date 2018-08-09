package io.renren.dao;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import io.renren.modules.sys.dao.BaseDao;
import io.renren.vas.entity.ScDtcldzdanEntity;
import io.renren.vas.entity.ScEtcptjdEntity;
import io.renren.vo.DtcldzdansVo;

/**
 * 
 * 
 * @author pwk
 * @email sunlightcs@gmail.com
 * @date 2018-02-26 11:56:26
 */
@Mapper
public interface Sc_scrcDao extends BaseDao<DtcldzdansVo> {
	 @Select("<script>SELECT plate,owner,color,model,brand,isFirstIn FROM sc_dtcldzdan \n" + 
		 		"		<where> " +
		 		"     and isfirstin = 1 " + 
		 		"			<if test=\"plate != null and plate != ''\">\n" + 
		 		"				and sc_dtcldzdan.plate  = #{plate}\n" + 
		 		"			</if>\n" + 
		 		"		</where>\n" + 
		 		"		   <choose>\n" + 
		 		"            <when test=\"sidx != null and sidx.trim() != ''\">\n" + 
		 		"                order by ${sidx} ${order}\n" + 
		 		"            </when>\n" + 
		 		"			<otherwise>\n" + 
		 		"               order by isfirstin desc\n" + 
		 		"			</otherwise>\n" + 
		 		"        </choose>\n" + 
		 		"		<if test=\"offset != null and limit != null\">\n" + 
		 		"			limit #{limit} offset #{offset}\n" + 
		 		"		</if></script>")
		List<DtcldzdansVo> queryList(Map<String, Object> map);
		
		 @Select("<script>\r\n" + 
		 		"select count(1) from sc_dtcldzdan \r\n" + 
		 		"<where>" +
		 		" and isfirstin = 1 " + 
		 		"<if test=\"plate != NULL and plate != ''\">\r\n" + 
		 		"	and sc_dtcldzdan.plate = #{plate}\r\n" + 
		 		"</if>\r\n" + 
		 		"</where>\r\n" + 
		 		"</script>")
		int queryTotal(Map<String, Object> map);	
		 
		 @Select("select * from sc_dtcldzdan")
		 List<DtcldzdansVo> list();
		 
		 @Select("select * from sc_etcptjd")
		 List<ScEtcptjdEntity> list_etcp();
		 
		 @Select("select * from sc_dtcldzdan where isFirstIn='1'")
		 List<DtcldzdansVo> list_dan();
		 
		 @Select("SELECT plate FROM sc_dtcldzdan WHERE plate NOT LIKE '%京%' ORDER BY id LIMIT 1000 OFFSET ${count} ")
		 List<ScDtcldzdanEntity> getScrc(@Param("count") int count);
		 
		 @Select("SELECT enter_time,car_number FROM sc_etcptjd "
			   + "WHERE car_number = #{plate} ORDER BY enter_time LIMIT 1 OFFSET 0")
		 List<ScEtcptjdEntity> getTime(@Param("plate") String plate);
		 
		 @Select("SELECT enter_time,car_number FROM sc_etcptjd "
		 	   + "WHERE car_number = #{plate} "
		 	   + "AND #{time} < (NOW() - interval '10 DAY')")
		 List<ScEtcptjdEntity> scrc(@Param("plate") String plate, @Param("time") String time);
		 
		 @Update("UPDATE sc_dtcldzdan SET isFirstIn= '1' WHERE plate = #{plate} ")
		 void updateScrc(@Param("plate") String plate);
		 
//		 @Update("update vas_b.sc_dtcldzdan dt set dt.isFirstIn= '0';")
//	     int update_false();
		 
//		 @Update("update vas_b.sc_dtcldzdan dt set dt.isFirstIn= '1' where left(dt.plate,1)<>'京' and (select TIMESTAMPDIFF(DAY,min(t.EXIT_TIME),SYSDATE()) from vas_b.sc_etcptjd t where t.PARK_NM='通州区' and car_number=dt.plate )<10;")
//	     int update();
}
