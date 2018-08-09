package io.renren.dao;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import io.renren.modules.sys.dao.BaseDao;
import io.renren.vas.entity.ScTravelaaEntity;
import io.renren.vo.DtcldzdansVo;

/**
 * 
 * 
 * @author pwk
 * @email sunlightcs@gmail.com
 * @date 2018-02-26 11:56:26
 */
@Mapper
public interface Sc_lxwzDao extends BaseDao<DtcldzdansVo> {
	 @Select("<script>" + 
	 		 " SELECT plate,owner,color,model,brand,contViolation FROM sc_dtcldzdan " + 
	 		 "<where>" +
	 		 " contViolation > 90 " +
	 		 "<if test=\"plate != NULL and plate != ''\"> " + 
	 		 "	AND sc_dtcldzdan.plate = #{plate} " + 
	 		 "</if>" + 
	 		 "</where>" +
		 	 "		   <choose>\n" + 
		 	 "            <when test=\"sidx != null and sidx.trim() != ''\">\n" + 
		 	 "                order by ${sidx} ${order}\n" + 
		 	 "            </when>\n" + 
		 	 "			<otherwise>\n" + 
		 	 "               order by contviolation desc\n" + 
		 	 "			</otherwise>\n" + 
		 	 "        </choose>\n" + 
		 	 "		<if test=\"offset != null and limit != null\">\n" + 
		 	 "			limit #{limit} offset #{offset}\n" + 
		 	 "		</if></script>")
		List<DtcldzdansVo> queryList(Map<String, Object> map);
		
		@Select("<script>\r\n" + 
		 		"select count(id) from sc_dtcldzdan\r\n" + 
		 		"<where>\r\n" + 
		 		" contViolation > 90 " +
		 		"<if test=\"plate != NULL and plate != ''\">\r\n" + 
		 		"	AND sc_dtcldzdan.plate = #{plate}\r\n" + 
		 		"</if>\r\n" + 
		 		"</where>\r\n" + 
		 		"</script>")
		int queryTotal(Map<String, Object> map);	
		
		@Select("SELECT abnormal3Month,carNum FROM sc_travelaa ORDER BY id LIMIT 1 offset ${count}")
		ScTravelaaEntity ContViolation(@Param("count") int count);
		
		@Select("select count(1) from sc_travelaa ")
		int total();
		
		@Update("UPDATE sc_dtcldzdan SET contViolation = ${f} WHERE plate = #{plate}")
		int setContViolation(@Param("f") double f, @Param("plate") String plate);
}
