package io.renren.dao;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import io.renren.vo.DtcldzdansVo;

/**
 * 
 * 
 * @author pwk
 * @email sunlightcs@gmail.com
 * @date 2018-02-26 11:56:26
 */
@Mapper
public interface Sc_wpcljfmxDao {
	 @Select("<script>SELECT plate,owner,color,model,brand,noPlate FROM sc_dtcldzdan \n" + 
		 		"		<where>\n" + 
		 		"			<if test=\"plate != null and plate != ''\">\n" + 
		 		"				AND sc_dtcldzdan.plate  = #{plate}\n" + 
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
		 		"               order by noplate desc\n" + 
		 		"			</otherwise>\n" + 
		 		"        </choose>\n" + 
		 		"		<if test=\"offset != null and limit != null\">\n" + 
		 		"			limit #{limit} offset #{offset}\n" + 
		 		"		</if></script>")
		List<DtcldzdansVo> queryList(Map<String, Object> map);
		
		 @Select("<script>\r\n" + 
		 		"select count(id) from sc_dtcldzdan\r\n" + 
		 		"<where>\r\n" + 
		 		"<if test=\"plate != NULL and plate != ''\">\r\n" + 
		 		"	AND sc_dtcldzdan.plate = #{plate}\r\n" + 
		 		"</if>\r\n" + 
		 		"</where>\r\n" + 
		 		"</script>")
		int queryTotal(Map<String, Object> map);	  
		 
		 @Update("update sc_dtcldzdan set noPlate=0")
		 int wcp_cs();
		 
		 @Update("update sc_dtcldzdan dt set dt.noPlate=1 where (select count(1) from sc_violation t where t.plateno=dt.plate and t.noplate=1)>0")
		 int wcp_sz();
		 
		 @Update("update sc_dtcldzdan dt "
			   + "set dt.noPlate = 1 "
		 	   + "where (select count(1) from sc_violation t "
		 	   + " where t.plateno = #{plate} and t.noplate=1) > 0 ")
		 void wpcl(@Param("plate") String plate);
		 
		 @Select("SELECT noPlate FROM sc_dtcldzdan WHERE plate = #{plate} ")
		 List<DtcldzdansVo> getNoPlate(@Param("plate") String plate);
}
