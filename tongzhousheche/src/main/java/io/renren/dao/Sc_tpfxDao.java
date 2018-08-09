package io.renren.dao;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import io.renren.modules.sys.dao.BaseDao;
import io.renren.vo.DtcldzdansVo;

/**
 * 
 * 
 * @author pwk
 * @email sunlightcs@gmail.com
 * @date 2018-02-26 11:56:26
 */
@Mapper
public interface Sc_tpfxDao extends BaseDao<DtcldzdansVo> {
	 @Select("<script>\r\n" + 
	 		"SELECT plate,owner,color,model,brand,multiPlate FROM sc_dtcldzdan " +
	 		"<where>" +
	 		" and multiPlate = 1 "+
	 		"<if test=\"plate != NULL and plate != ''\">" + 
	 		"	AND sc_dtcldzdan.plate = #{plate} " + 
	 		"</if>" +
	 		"</where>" + 
		 	"<choose> " + 
		 	"<when test=\"sidx != null and sidx.trim() != ''\">" + 
		 	"   order by ${sidx} ${order}\n" + 
		 	"</when>" + 
		 	"<otherwise>" + 
		 	"   order by multiplate desc" + 
		 	"</otherwise>" + 
		 	"</choose>" + 
		 	"<if test=\"offset != null and limit != null\">" + 
		 	"	limit #{limit} offset #{offset}" + 
		 	"</if>" +
		 	"</script>")
		List<DtcldzdansVo> queryList(Map<String, Object> map);
		
		@Select("<script>\r\n" + 
		 		"select count(id) from sc_dtcldzdan\r\n" + 
		 		"<where>\r\n" +
		 		" and multiPlate = 1 "+
		 		"<if test=\"plate != NULL and plate != ''\">\r\n" + 
		 		"	AND sc_dtcldzdan.plate = #{plate}\r\n" + 
		 		"</if>\r\n" + 
		 		"</where>\r\n" + 
		 		"</script>")
		int queryTotal(Map<String, Object> map);	   
}
