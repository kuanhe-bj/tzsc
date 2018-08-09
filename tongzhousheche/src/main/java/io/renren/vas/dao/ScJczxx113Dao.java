package io.renren.vas.dao;


import io.renren.modules.sys.dao.BaseDao;
import io.renren.vas.entity.ScJczxx113Entity;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * 
 * 
 * @author pwk
 * @email sunlightcs@gmail.com
 * @date 2018-02-09 15:19:34
 */
@Mapper
public interface ScJczxx113Dao extends BaseDao<ScJczxx113Entity> {
	
	@Select("select * from sc_jczxx113 ORDER BY id DESC LIMIT 1 OFFSET 0")
	ScJczxx113Entity getId();
	
	@Select("<script>"
			  + "SELECT * FROM sc_jczxx113 WHERE cph = #{cph} "
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
	List<ScJczxx113Entity> find(Map<String, Object> map);
	
	@Select("SELECT count(*) FROM sc_jczxx113 WHERE cph = #{cph} ")
	int total(Map<String, Object> map);
	
}
