package io.renren.dao;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import io.renren.vo.GwrysjVo;

/**
 * 
 * 
 * @author pwk
 * @email sunlightcs@gmail.com
 * @date 2018-02-26 11:56:26
 */
@Mapper
public interface Sc_weizuyinxingDao { 
	
	@Select("<script> "
				  + "SELECT * FROM sc_gwrysj WHERE MZ = \'维吾尔\' "
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
	
	@Update("UPDATE sc_gwrysj SET WEIZHU = 1 WHERE CP = #{cp}")
	int update(String cp);
	 
	@Select("SELECT count(1) FROM sc_gwrysj WHERE MZ = \'维吾尔\'")
	int queryTotal(Map<String, Object> map);	
	
	@Select("<script> "
				  + "SELECT * FROM sc_gwrysj WHERE mz = '维吾尔' AND cp = #{cp} "
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
	
	@Select("SELECT count(1) FROM sc_gwrysj WHERE MZ = \'维吾尔\' AND cp = #{cp} ")
	int total(Map<String, Object> map);
}
