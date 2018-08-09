package io.renren.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import io.renren.vo.GwrysjVo;

@Mapper
public interface Sc_jtsfmxDao {
	@Select("<script>select * from sc_gwrysj" + 
			"<where>" + 
			" <if test=\"xm!=NULL and xm !=''\">" + 
			"	and xm=#{xm}" + 
			" </if>"+
			" <if test=\"shangfang!=NULL and shangfang != ''\">" + 
			"	and  JYAQ LIKE '%上访%' \r\n" + 
			" </if>" + 
			" <if test=\"jingshenbing!=NULL and jingshenbing != ''\">" + 
			"	and JYAQ LIKE '%精神病%'" + 
			" </if>" + 	
			"</where>" + 
			"<choose>" +
		    "  <when test='sidx.trim() !=\"\"  and  sidx != null' >" +
		    "     order by ${sidx} ${order}" +
		    "  </when>" +
		    "  <otherwise> " +
		    "     order by id desc"  +
		    "  </otherwise> " + 
		    "</choose> " +
		    "<if test='offset != null and limit != null'>" +
		    "	  limit #{limit} offset #{offset}" +
			"</if>" + 
			"</script>")
	List<GwrysjVo> queryList(Map<String, Object> map);
	
	@Select("select COUNT(1) from sc_gwrysj")
	int queryTotal(Map<String, Object> map);
	
	@Select("<script>select * from sc_gwrysj" +
			"<choose>\r\n" + 
			"			<when test=\"sidx != null and sidx.trim() != ''\">\r\n" + 
			"				order by ${sidx} ${order}\r\n" + 
			"			</when>\r\n" + 
			"			<otherwise>\r\n" + 
			"				order by id desc\r\n" + 
			"			</otherwise>\r\n" + 
			"	</choose>\r\n" + 
			"		<if test=\"offset != null and limit != null\">\r\n" + 
			"			limit #{limit} offset #{offset}\r\n" + 
			"		</if></script>")
	List<GwrysjVo> list(Map<String, Object> map);
	
	@Select("<script>" +
			" select COUNT(1) from sc_gwrysj " + 
			"<where>" + 
		    "  <if test=\"xm!=NULL and xm !=''\">" + 
		    "	  and xm=#{xm}" + 
		    "  </if>"+
			"  <if test=\"shangfang!=NULL and shangfang != ''\">" + 
			"	  and	JYAQ LIKE '%上访%'" + 
			"  </if>" + 
			"  <if test=\"jingshenbing!=NULL and jingshenbing != ''\">" + 
			"	  and	JYAQ LIKE '%精神病%'" + 
			"  </if>" + 
			"</where>" + 
			"</script>")
	int total(Map<String, Object> map);
}

