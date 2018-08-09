package io.renren.dao;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import io.renren.modules.sys.dao.BaseDao;
import io.renren.vas.entity.ScDtcldzdanEntity;
import io.renren.vo.DtcldzdansVo;

/**
 * 
 * 
 * @author pwk
 * @email sunlightcs@gmail.com
 * @date 2018-02-26 11:56:26
 */
@Mapper
public interface Sc_sgycDao extends BaseDao<DtcldzdansVo> {
	 @Select("<script>SELECT plate,owner,color,model,brand,accident FROM sc_dtcldzdan \n" + 
		 		"<where>\n" + 
		 		" accident > 90" +
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
		 		"               order by accident desc\n" + 
		 		"			</otherwise>\n" + 
		 		"        </choose>\n" + 
		 		"		<if test=\"offset != null and limit != null\">\n" + 
		 		"			limit #{limit} offset #{offset}\n" + 
		 		"		</if></script>")
		List<DtcldzdansVo> queryList(Map<String, Object> map);
		
		@Select("<script>\r\n" + 
		 		"select count(id) from sc_dtcldzdan\r\n" + 
		 		"<where>\r\n" + 
		 		" accident > 90" +
		 		"<if test=\"plate != NULL and plate != ''\">\r\n" + 
		 		"	AND sc_dtcldzdan.plate = #{plate}\r\n" + 
		 		"</if>\r\n" + 
		 		"</where>\r\n" + 
		 		"</script>")
		int queryTotal(Map<String, Object> map);
		
		@Update("update sc_dtcldzdan set obscured = 0")
		void setupdate();
		
		@Update("update sc_dtcldzdan dt set dt.accident=("
			  + "select atan(count(1)/10)*200/3.14 FROM sc_violation t "
			  + "where t.plateno = #{plate} and t.vtime > #{time} and t.isaccident = 1")
		void setAccident(@Param("plate") String plate, @Param("time") String time);
		
		@Select("SELECT accident FROM sc_dtcldzdan WHERE plate = #{plate} LIMIT 1 OFFSET 0")
		ScDtcldzdanEntity getAccident(String plate);
}
