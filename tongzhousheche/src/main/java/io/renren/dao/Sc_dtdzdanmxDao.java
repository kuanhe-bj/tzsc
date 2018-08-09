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
public interface Sc_dtdzdanmxDao{
	 @Select("<script> " 
	 		 + "SELECT *,(extract(year from NOW()) - extract(year from CSRQ)) AS age FROM sc_gwrysj " 
			 + "<choose>" 
			 + "<when test='sidx.trim() !=\"\"  and  sidx != null  '>" 
			 + "  order by ${sidx} ${order}"
			 + "</when>" 
			 + "<otherwise>" 
			 + "  order by id desc" 
			 + "</otherwise>" 
		     + "</choose>"
			 + "<if test='offset != null and limit != null'>" 
			 + "	 limit #{limit} offset #{offset}" 
			 + "</if>" 
	 		 + "</script>")
		List<GwrysjVo> queryList(Map<String, Object> map);
		
		@Select("SELECT count(*) FROM sc_gwrysj ")
		int queryTotal(Map<String, Object> map);	
		
		@Select("<script>" +
				"SELECT * FROM sc_gwrysj " + 
				" WHERE cp LIKE '%${search}%' " + 
		 		"	OR XM LIKE '%${search}%'" + 
		 		"	OR to_char(csrq,'YYYY-MM-DD') LIKE '%${search}%'" + 
		 		"	OR MZ LIKE '%${search}%'" + 
		 		"	OR JYAQ LIKE '%${search}%'"
		 		+ "<choose>" 
				+ "<when test='sidx.trim() !=\"\"  and  sidx != null  '>" 
				+ "  order by ${sidx} ${order}"
				+ "</when>" 
				+ "<otherwise>" 
				+ "  order by id desc" 
				+ "</otherwise>" 
				+ "</choose>"
				+ "<if test='offset != null and limit != null'>" 
				+ "	 limit #{limit} offset #{offset}" 
				+ "</if>" 
	 		    + "</script>")
		List<GwrysjVo> list(Map<String, Object> map);
		
		@Select("<script>" +
				"SELECT * FROM sc_gwrysj " + 
				" where CP LIKE '%${search1}%' OR CP LIKE '%${search2}%'" + 
		 		"	OR XM LIKE '%${search1}%' OR XM LIKE '%${search2}%'" + 
		 		"	OR to_char(csrq,'YYYY-MM-DD') LIKE '%${search1}%' OR to_char(csrq,'YYYY-MM-DD') LIKE '%${search2}%'" + 
		 		"	OR MZ LIKE '%${search1}%' OR MZ LIKE '%${search2}%'" + 
		 		"	OR JYAQ LIKE '%${search1}%' OR JYAQ LIKE '%${search2}%'"
		 		+ "<choose>" 
				+ "<when test='sidx.trim() !=\"\"  and  sidx != null  '>" 
				+ "  order by ${sidx} ${order}"
				+ "</when>" 
				+ "<otherwise>" 
				+ "  order by id desc" 
				+ "</otherwise>" 
				+ "</choose>"
				+ "<if test='offset != null and limit != null'>" 
				+ "	 limit #{limit} offset #{offset}" 
				+ "</if>" 
	 		    + "</script>")
		List<GwrysjVo> listAnd(Map<String, Object> map);
		
		@Select("<script>" +
				"SELECT * FROM sc_gwrysj " + 
		 		"where CP NOT IN (SELECT CP FROM sc_gwrysj WHERE CP LIKE '%${search}%') " + 
		 		"	OR XM NOT IN (SELECT XM FROM sc_gwrysj WHERE XM LIKE '%${search}%') " + 
		 		"	OR to_char(csrq,'YYYY-MM-DD') NOT IN (SELECT to_char(csrq,'YYYY-MM-DD') FROM sc_gwrysj WHERE to_char(csrq,'YYYY-MM-DD') LIKE '%${search}%')" + 
		 		"	OR MZ NOT IN (SELECT MZ FROM sc_gwrysj WHERE MZ LIKE  '%${search}%')" + 
		 		"	OR JYAQ NOT IN (SELECT JYAQ FROM sc_gwrysj WHERE JYAQ LIKE  '%${search}%')"
		 		+ "<choose>" 
				+ "<when test='sidx.trim() !=\"\"  and  sidx != null  '>" 
				+ "  order by ${sidx} ${order}"
				+ "</when>" 
				+ "<otherwise>" 
				+ "  order by id desc" 
				+ "</otherwise>" 
				+ "</choose>"
				+ "<if test='offset != null and limit != null'>" 
				+ "	 limit #{limit} offset #{offset}" 
				+ "</if>" 
	 		    + "</script>")
		List<GwrysjVo> listNot(Map<String, Object> map);
		
		@Select("<script>"
			  + "SELECT *,(extract(year from NOW()) - extract(year from CSRQ)) AS age "
			  + "FROM sc_gwrysj "
			  + "WHERE (extract(year from NOW()) - extract(year from CSRQ)) = #{search}"
			  + " OR cp like '%${search}%' "
			  + " OR to_char(csrq,'YYYY-MM-DD') like '%${search}%'"
			  + " OR jyaq like '%${search}%' "
			  + "<choose>" 
			  + "<when test='sidx.trim() !=\"\"  and  sidx != null  '>" 
			  + "  order by ${sidx} ${order}"
			  + "</when>" 
			  + "<otherwise>" 
			  + "  order by id desc" 
			  + "</otherwise>" 
			  + "</choose>"
			  + "<if test='offset != null and limit != null'>" 
			  + "	 limit #{limit} offset #{offset}" 
			  + "</if>" 
			  + "</script>")
		List<GwrysjVo> listAge(Map<String, Object> map);
		
		@Select("SELECT * FROM sc_gwrysj " + 
				" WHERE cp LIKE '%${search}%' " + 
		 		"	OR XM LIKE '%${search}%'" + 
		 		"	OR to_char(csrq,'YYYY-MM-DD') LIKE '%${search}%'" + 
		 		"	OR MZ LIKE '%${search}%'" + 
		 		"	OR JYAQ LIKE '%${search}%' ")
		List<GwrysjVo> listTotal(Map<String, Object> map);
		
		@Select("SELECT *,(extract(year from NOW()) - extract(year from CSRQ)) AS age "
			  + "FROM sc_gwrysj "
			  + "WHERE (extract(year from NOW()) - extract(year from CSRQ)) = ${search} "
			  + " OR cp like '%${search}%' "
			  + " OR to_char(csrq,'YYYY-MM-DD') like '%${search}%'"
			  + " OR jyaq like '%${search}%' ")
		List<GwrysjVo> listAgeTotal(Map<String, Object> map);
		
		@Select("SELECT * FROM sc_gwrysj " + 
				" where CP LIKE '%${search1}%' OR CP LIKE '%${search2}%'" + 
		 		"	OR XM LIKE '%${search1}%' OR XM LIKE '%${search2}%'" + 
		 		"	OR to_char(csrq,'YYYY-MM-DD') LIKE '%${search1}%' OR to_char(csrq,'YYYY-MM-DD') LIKE '%${search2}%'" + 
		 		"	OR MZ LIKE '%${search1}%' OR MZ LIKE '%${search2}%'" + 
		 		"	OR JYAQ LIKE '%${search1}%' OR JYAQ LIKE '%${search2}%'")
		List<GwrysjVo> listAndTotal(Map<String, Object> map);
		
		@Select("SELECT * FROM sc_gwrysj " + 
		 		" where CP NOT IN (SELECT CP FROM sc_gwrysj WHERE CP LIKE '%${search}%') " + 
		 		"	OR XM NOT IN (SELECT XM FROM sc_gwrysj WHERE XM LIKE '%${search}%') " + 
		 		"	OR to_char(csrq,'YYYY-MM-DD') NOT IN (SELECT to_char(csrq,'YYYY-MM-DD') FROM sc_gwrysj WHERE to_char(csrq,'YYYY-MM-DD') LIKE '%${search}%')" + 
		 		"	OR MZ NOT IN (SELECT MZ FROM sc_gwrysj WHERE MZ LIKE  '%${search}%')" + 
		 		"	OR JYAQ NOT IN (SELECT JYAQ FROM sc_gwrysj WHERE JYAQ LIKE  '%${search}%')")
		List<GwrysjVo> listNotTotal(Map<String, Object> map);
}
