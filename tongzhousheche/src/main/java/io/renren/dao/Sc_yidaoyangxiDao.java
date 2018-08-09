package io.renren.dao;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import io.renren.vo.GwrysjVo;

/**
 * 
 * 
 * @author pwk
 * @email sunlightcs@gmail.com
 * @date 2018-02-26 11:56:26
 */
@Mapper
public interface Sc_yidaoyangxiDao { 
	 @Select("<script>SELECT * FROM sc_gwrysj " +
	 		"<where>" + 
			"	<if test=\"xm != NULL and xm != ''\">" + 
			"		AND xm = #{xm} " + 
			"   </if>" +
			"	<if test=\"qincai != NULL and qincai != ''\">" + 
			"		and JYAQ LIKE '%侵财%' " + 
			"   </if>" + 
			"	<if test=\"shedu != NULL and shedu != ''\">\r\n" + 
			"		and JYAQ LIKE '%涉毒%' " + 
			"	</if>" + 
			"	<if test=\"yidaoyangxi != NULL and yidaoyangxi != ''\">\r\n" + 
			"		and JYAQ LIKE '%以盗养吸%' " + 
			"	</if>" +
			"</where>" 
	       + "<choose>"
	       + "  <when test='sidx.trim() !=\"\"  and  sidx != null' >"
	       + "     order by ${sidx} ${order}"
	       + "  </when>"
	       + "  <otherwise> "
	       + "     order by id desc"
	       + "  </otherwise> "
	       + "</choose> "
	       + "<if test='offset != null and limit != null'>"
	       + "	  limit #{limit} offset #{offset}"
		   + "</if>"
	 	   + "</script>")
		List<GwrysjVo> queryList(Map<String, Object> map);
	 
		@Select("<script> "
				  + "select * from sc_gwrysj "
		          + "<choose>"
		          + "  <when test='sidx.trim() !=\"\"  and  sidx != null' >"
		          + "     order by ${sidx} ${order}"
		          + "  </when>"
				  + "  <otherwise> "
		          + "     order by id desc"
				  + "  </otherwise> "
		          + "</choose> "
				  + "<if test='offset != null and limit != null'>"
				  + "	  limit #{limit} offset #{offset}"
			      + "</if>"
				  + "</script>")
		List<GwrysjVo> list(Map<String, Object> map);
		
		
		@Select("<script> " +
			    "select * from sc_gwrysj " +
			    "<where>" +
			    "	<if test=\"xm != NULL and xm != ''\">" + 
			    "		AND xm = #{xm} " + 
			    "   </if>" + 
			    "	<if test=\"qincai != NULL and qincai != ''\">" + 
			    "		AND JYAQ LIKE '%侵财%' " + 
			    "   </if>" + 
			    "	<if test=\"shedu != NULL and shedu != ''\">\r\n" + 
			    "		AND JYAQ LIKE '%涉毒%' " + 
			    "	</if>" + 
			    "	<if test=\"yidaoyangxi != NULL and yidaoyangxi != ''\">\r\n" + 
			    "		AND JYAQ LIKE '%以盗养吸%' " + 
			    "	</if>" + 
			    "</where>" 
		          + "<choose>"
		          + "  <when test='sidx.trim() !=\"\"  and  sidx != null' >"
		          + "     order by ${sidx} ${order}"
		          + "  </when>"
				  + "  <otherwise> "
		          + "     order by id desc"
				  + "  </otherwise> "
		          + "</choose> "
				  + "<if test='offset != null and limit != null'>"
				  + "	  limit #{limit} offset #{offset}"
			      + "</if>"
				  + "</script>")
		List<GwrysjVo> find(Map<String, Object> map);
		
		@Select("<script> " +
			    "select count(1) from sc_gwrysj " +
			    "<where>" +
			    "   <if test=\"xm != NULL and xm != ''\">" + 
			    "		AND xm = #{xm} " + 
			    "   </if>" + 
			    "	<if test=\"qincai != NULL and qincai != ''\">" + 
			    "		AND JYAQ LIKE '%侵财%' " + 
			    "   </if>" + 
			    "	<if test=\"shedu != NULL and shedu != ''\">\r\n" + 
			    "		AND JYAQ LIKE '%涉毒%' " + 
			    "	</if>" + 
			    "	<if test=\"yidaoyangxi != NULL and yidaoyangxi != ''\">\r\n" + 
			    "		AND JYAQ LIKE '%以盗养吸%' " + 
			    "	</if>" + 
			    "</where>" +
			    "</script>")
		int count(Map<String, Object> map);
	 
		@Select("SELECT count(*) FROM sc_gwrysj")
		int queryTotal(Map<String, Object> map);
		
		@Select("<script>" + 
				"SELECT count(1) FROM sc_gwrysj " +
				"<where>" + 
				"	 <if test=\"xm != NULL and xm != ''\">" + 
				"		AND xm = #{xm} " + 
				"   </if>" + 
				"	<if test=\"qincai != NULL and qincai != ''\">" + 
				"		and JYAQ LIKE '%侵财%' " + 
				"   </if>" + 
				"	<if test=\"shedu != NULL and shedu != ''\">\r\n" + 
				"		and JYAQ LIKE '%涉毒%' " + 
				"	</if>" + 
				"	<if test=\"yidaoyangxi != NULL and yidaoyangxi != ''\">\r\n" + 
				"		and JYAQ LIKE '%以盗养吸%' " + 
				"	</if>" +
				"</where>" + 
				"</script>") 
		int total(Map<String, Object> map);
}
