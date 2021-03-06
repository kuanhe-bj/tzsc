package io.renren.dao;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import io.renren.modules.sys.dao.BaseDao;
import io.renren.vas.entity.ScDtcldzdanEntity;
import io.renren.vas.entity.ScScajEntity;
import io.renren.vo.DtcldzdansVo;

/**
 * 
 * 
 * @author pwk
 * @email sunlightcs@gmail.com
 * @date 2018-02-26 11:56:26
 */
@Mapper
public interface Sc_clanjglfxDao extends BaseDao<DtcldzdansVo> {
	 @Select("<script>SELECT plate,owner,color,model,brand,isInvolved FROM sc_dtcldzdan   \n" + 
		 		"		<where>\n" + 
		 		" and isinvolved = 1 "+
		 		"			<if test=\"plate != null and plate != ''\">\n" + 
		 		"				and sc_dtcldzdan.plate  = #{plate}\n" + 
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
		 		"               order by isinvolved desc\n" + 
		 		"			</otherwise>\n" + 
		 		"        </choose>\n" + 
		 		"		<if test=\"offset != null and limit != null\">\n" + 
		 		"			limit #{limit} offset #{offset}\n" + 
		 		"		</if></script>")
		List<DtcldzdansVo> queryList(Map<String, Object> map);
		
	 	@Select("<script>\r\n" + 
		 		"select count(1) from sc_dtcldzdan   \r\n" + 
		 		"<where>\r\n" + 
		 		" and isinvolved = 1 "+
		 		"<if test=\"plate != NULL and plate != ''\">\r\n" + 
		 		"	and sc_dtcldzdan.plate = #{plate}\r\n" + 
		 		"</if>\r\n" + 
		 		"</where>\r\n" + 
		 		"</script>")
		int queryTotal(Map<String, Object> map);
	 	
	 	@Select("SELECT id FROM sc_scaj WHERE cph = #{plate} ")
	 	List<ScScajEntity> getScScajEntity(String plate);
		 
	 	@Update("UPDATE sc_dtcldzdan SET isinvolved = 0 ")
		void update(); 
	 	
		@Select("SELECT plate from sc_dtcldzdan ORDER BY id LIMIT 1000 offset ${_parameter}")
		List<ScDtcldzdanEntity> getScDtcldzdanEntity(int count);
	 	
	 	@Update("UPDATE sc_dtcldzdan SET isinvolved = 1 WHERE plate = #{plate} ")
	 	void clajgl(String plate);
}
