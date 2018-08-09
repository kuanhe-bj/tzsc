package io.renren.vas.dao;


import io.renren.modules.sys.dao.BaseDao;
import io.renren.vas.entity.ScGbzdrEntity;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * 
 * 
 * @author pwk
 * @email sunlightcs@gmail.com
 * @date 2018-02-09 15:19:33
 */
@Mapper
public interface ScGbzdrDao extends BaseDao<ScGbzdrEntity> {
	
	@Select("<script>"
		  + "SELECT * FROM sc_gbzdr WHERE sfzh = #{sfzh} "
		  + "<choose>"
	      + "  <when test='sidx.trim() !=\"\"  and  sidx != null' >"
	      + "     order by ${sidx} ${order}"
	      + "  </when>"
	      + "  <otherwise> "
	      + "     order by drybh desc"
		  + "  </otherwise> "
	      + "</choose> "
	      + "<if test='offset != null and limit != null'>"
	      + "	  limit #{limit} offset #{offset}"
		  + "</if>"
		  + "</script>")
	List<ScGbzdrEntity> find(Map<String, Object> map);
	
	@Select("SELECT count(*) FROM sc_gbzdr WHERE sfzh = #{sfzh}")
	int total(Map<String, Object> map);
	
}
