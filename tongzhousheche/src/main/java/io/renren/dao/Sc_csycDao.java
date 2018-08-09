package io.renren.dao;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import io.renren.modules.sys.dao.BaseDao;
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
public interface Sc_csycDao extends BaseDao<DtcldzdansVo> {
	 @Select("<script>SELECT plate,owner,color,model,brand,times FROM sc_dtcldzdan \n" + 
		 		"		<where>\n" +
		 		"			<if test=\"plate != null and plate != ''\">\n" + 
		 		"			AND	sc_dtcldzdan.plate  = #{plate}\n" + 
		 		"			</if>\n" + 
		 		"		 	<if test=\"plate == null\">\n" + 
		 		"				\n" + 
		 		"			</if>\n" + 
		 		"		</where>\n" + 
		 		"		   <choose>\n" + 
		 		"            <when test=\"sidx != null and sidx.trim() != ''\">\n" + 
		 		"                order by ${sidx} ${order}\n" + 
		 		"            </when>\n" + 
		 		"			<otherwise>\n" + 
		 		"               order by times desc\n" + 
		 		"			</otherwise>\n" + 
		 		"        </choose>\n" + 
		 		"		<if test=\"offset != null and limit != null\">\n" + 
		 		"			limit #{limit} offset #{offset}\n" + 
		 		"		</if></script>")
		List<DtcldzdansVo> queryList(Map<String, Object> map);
		
		@Select("SELECT CAR_NUMBER,count(eid) AS count FROM sc_etcptjd "
			  + " WHERE CAR_NUMBER = #{plate} "
			  + " AND EXIT_TIME BETWEEN #{start} AND #{end} "
			  + " GROUP BY extract(DAY from EXIT_TIME),"
			  + " extract(MONTH from EXIT_TIME),"
			  + " extract(YEAR from EXIT_TIME),CAR_NUMBER ")
		List<ScEtcptjdEntity> times(@Param("plate") String plate, @Param("start") String start, @Param("end") String end);
		
		@Select("SELECT CAR_NUMBER AS carNumber,COUNT(eid) AS count FROM sc_etcptjd "
			  + " WHERE CAR_NUMBER in "
			  + " (SELECT plate FROM sc_dtcldzdan ORDER BY id LIMIT 1000 offset ${j}) "
			  + "  AND EXIT_TIME BETWEEN #{start} AND #{end} "
			  + "  GROUP BY CAR_NUMBER ")
		List<ScEtcptjdEntity> getCount(@Param("j") int j, @Param("start") String start, @Param("end") String end);
		
		@Select("SELECT count(eid) AS count FROM sc_etcptjd WHERE CAR_NUMBER = #{plate}")
		int total(String plate);
		
		@Update("UPDATE sc_dtcldzdan SET times = 0")
		int update();
		
		@Update("UPDATE sc_dtcldzdan SET times = 1 WHERE plate = #{plate} ")
		int updateTimes(String plate);
		
		@Select("<script>\r\n" + 
		 		"select count(id) from sc_dtcldzdan\r\n" + 
		 		"<where>\r\n" +
		 		"<if test=\"plate != NULL and plate != ''\">\r\n" + 
		 		"	AND sc_dtcldzdan.plate = #{plate}\r\n" + 
		 		"</if>\r\n" + 
		 		"</where>\r\n" + 
		 		"</script>")
		int queryTotal(Map<String, Object> map);	   
}
