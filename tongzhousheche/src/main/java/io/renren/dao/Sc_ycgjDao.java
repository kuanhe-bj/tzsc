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
public interface Sc_ycgjDao extends BaseDao<DtcldzdansVo> {
	 @Select("<script>SELECT plate,owner,color,model,brand,abTravel FROM sc_dtcldzdan \n" + 
		 		"		<where>\n" + 
		 		" abTravel > 90 " +
		 		"			<if test=\"plate != null and plate != ''\">\n" + 
		 		"			and	sc_dtcldzdan.plate  = #{plate}\n" + 
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
		 		"               order by abtravel desc\n" + 
		 		"			</otherwise>\n" + 
		 		"        </choose>\n" + 
		 		"		<if test=\"offset != null and limit != null\">\n" + 
		 		"			limit #{limit} offset #{offset}\n" + 
		 		"		</if></script>")
		List<DtcldzdansVo> queryList(Map<String, Object> map);
		
		@Select("<script>\r\n" + 
		 		"select count(id) from sc_dtcldzdan\r\n" + 
		 		"<where>\r\n" + 
		 		" abTravel > 90 " +
		 		"<if test=\"plate != NULL and plate != ''\">\r\n" + 
		 		"	and sc_dtcldzdan.plate = #{plate}\r\n" + 
		 		"</if>\r\n" + 
		 		"</where>\r\n" + 
		 		"</script>")
		int queryTotal(Map<String, Object> map);
		
		@Select("SELECT * FROM sc_etcptjd " 
			  + "WHERE CAR_NUMBER = #{plate} AND lower(sjly) = 'k' "
			  + "AND ENTER_TIME BETWEEN #{start} AND #{end} ")
		List<ScEtcptjdEntity> showList(@Param("plate") String plate, @Param("start") String start, @Param("end") String end);
		
		@Update("UPDATE sc_dtcldzdan SET abTravel = ${f} WHERE plate = #{plate}	 ")
		void ycgj(@Param("f") double f, @Param("plate") String plate);
}
