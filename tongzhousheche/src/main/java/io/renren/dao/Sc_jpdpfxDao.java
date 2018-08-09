package io.renren.dao;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import io.renren.modules.sys.dao.BaseDao;
import io.renren.vas.entity.ScDtcldzdanEntity;
import io.renren.vo.Color;
import io.renren.vo.DtcldzdansVo;
import io.renren.vo.Model;
import io.renren.vo.ScEtcpEntity;

/**
 * 
 * 
 * @author pwk
 * @email sunlightcs@gmail.com
 * @date 2018-02-26 11:56:26
 */
@Mapper
public interface Sc_jpdpfxDao extends BaseDao<DtcldzdansVo> {
	 @Select("<script>SELECT plate,owner,color,model,brand,isFake FROM sc_dtcldzdan \n" + 
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
		 		"               order by isfake desc\n" + 
		 		"			</otherwise>\n" + 
		 		"        </choose>\n" + 
		 		"		<if test=\"offset != null and limit != null\">\n" + 
		 		"			limit #{limit} offset #{offset}\n" + 
		 		"		</if></script>")
		List<DtcldzdansVo> queryList(Map<String, Object> map);
	 
		@Update("UPDATE sc_dtcldzdan SET multiPlate = 1 WHERE plate in "
				  + "(SELECT p.plate FROM "
	              + "(SELECT d.plate FROM sc_dtcldzdan as d JOIN "
	              + "(SELECT  k1.CAR_NUMBER as plate,"
		          + " ROUND("
		          + "  6378.138 * 2 * ASIN("
		          + "  SQRT(POW(SIN((k1.WEIDU * PI() / 180 - k2.WEIDU * PI() / 180) / 2),"
		          + "  2) + COS(k1.WEIDU * PI() / 180) * COS(k2.WEIDU * PI() / 180) * POW( "
		          + "  SIN((k1.JINGDU * PI() / 180 - k2.JINGDU * PI() / 180) / 2), "
		          + "  2))))/((k2.ENTER_TIME - k1.EXIT_TIME)/60/60) as f "
			      + "from sc_etcptjd k1 JOIN sc_etcptjd k2 "
			      + "ON k1.CAR_NUMBER = k2.CAR_NUMBER "
			      + "WHERE k1.ENTER_TIME != k2.ENTER_TIME "
			      + "AND k1.ADRESS != k2.ADRESS "
			      + "AND k2.ENTER_TIME > k1.EXIT_TIME "
			      + "HAVING f >= 160) AS t "
			      + "ON d.plate = t.plate) AS p)")
		int check();
		
		@Select("<script>\r\n" + 
		 		"select count(id) from sc_dtcldzdan\r\n" + 
		 		"<where>\r\n" + 
		 		"<if test=\"plate != NULL and plate != ''\">\r\n" + 
		 		"	AND sc_dtcldzdan.plate = #{plate}\r\n" + 
		 		"</if>\r\n" + 
		 		"</where>\r\n" + 
		 		"</script>")
		int queryTotal(Map<String, Object> map);
		
		@Select("SELECT * FROM sc_etcptjd WHERE CAR_NUMBER = #{plate}")
		List<ScEtcpEntity> checkList(String plate);
		
		@Select("SELECT * FROM sc_color WHERE value = ${col}")
		Color getColor(int col);
		
		@Select("SELECT * FROM sc_model WHERE value = ${mod}")
		Model getModel(int mod);
		
		@Update("UPDATE sc_dtcldzdan SET isfake = ${f} WHERE plate = #{plate}")
		int update(@Param("f") double f, @Param("plate") String plate);
		
		@Update("update sc_dtcldzdan set isFake = 0 ")
		void setupdate();
		
		@Update("update sc_dtcldzdan dt set dt.isFake = 100 where ( "
			  + "select count(1) FROM sc_violation t "
			  + "where t.plateno = #{plate} and  t.isFake = 1) > 0 ")
		void setIsFake(String plate);
			
		@Select("SELECT isFake FROM sc_dtcldzdan WHERE plate = #{plate} ")
		List<ScDtcldzdanEntity> getIsFake(String plate);
}
