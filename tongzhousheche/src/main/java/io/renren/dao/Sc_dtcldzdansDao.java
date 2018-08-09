package io.renren.dao;



import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import io.renren.modules.sys.dao.BaseDao;
import io.renren.vas.entity.ScDtcldzdanEntity;
import io.renren.vo.DtcldzdansVo;
import io.renren.vo.VehiclegsVo;


@Mapper
public interface Sc_dtcldzdansDao extends BaseDao<DtcldzdansVo> {
	
	@Select("select jiancheng from sc_gsd where province =#{province} and createtime = '1' ")
	VehiclegsVo jiancheng(Map<String, Object> map);
	
	@Select("<script>SELECT * FROM sc_dtcldzdan \n" + 
	 		"		<where>\n" + 
	 		"			<if test=\"plate != null and plate != ''\">\n" + 
	 		"			and	sc_dtcldzdan.plate  = #{plate}\n" + 
	 		"			</if>\n" + 
	 		"			<if test=\"jiancheng != null and jiancheng != ''\">\n" + 
	 		"			and	plate like '%${jiancheng}%' \n" + 
	 		"			</if>\n" + 
	 		"		</where>\n" + 
	 		"		   <choose>\n" + 
	 		"            <when test=\"sidx != null and sidx.trim() != ''\">\n" + 
	 		"                order by ${sidx} ${order}\n" + 
	 		"            </when>\n" + 
	 		"			<otherwise>\n" + 
	 		"               order by summary desc\n" + 
	 		"			</otherwise>\n" + 
	 		"        </choose>\n" + 
	 		"		<if test=\"offset != null and limit != null\">\n" + 
	 		"			limit #{limit} offset #{offset} \n" + 
	 		"		</if></script>")
	List<DtcldzdansVo> queryList(Map<String, Object> map);
	
	@Select("<script>\r\n" + 
	 		"select count(id) from sc_dtcldzdan\r\n" + 
	 		"<where>\r\n" +
	 		"<if test=\"jiancheng != null and jiancheng != ''\"> " + 
	 		"   and	plate like '%${jiancheng}%' " + 
	 		"</if>" + 
	 		"<if test=\"plate != NULL and plate != ''\">\r\n" + 
	 		"	and sc_dtcldzdan.plate = #{plate}\r\n" + 
	 		"</if>\r\n" + 
	 		"</where>\r\n" + 
	 		"</script>")
	int queryTotal(Map<String, Object> map);

	 
	 
    @Select("<script>"  
	            + "SELECT  * from sc_dtcldzdan"
			    + " WHERE 1=1"
	            + "<if test='cph!=null '>"  
	            + " and plate like '%${cph}%'" 
	            + "</if>"
	            + "<if test='color!=null '>"  
	            + " and color >= #{color}"  
	            + "</if>"
	            + "<if test='model!=null '>"  
	            + " and model &lt;= #{model}"  
	            + "</if>"
	            + "<if test='brand!=null '>"  
	            + " and brand = (SELECT contact FROM sc_brand WHERE name = #{brand})"  
	            + "</if>"
	            + " ORDER BY id ASC " 
	            + " limit #{limit} offset #{offset}"  
	            + "</script>")
	List<ScDtcldzdanEntity> mohuCheck(Map map);
	   
}
