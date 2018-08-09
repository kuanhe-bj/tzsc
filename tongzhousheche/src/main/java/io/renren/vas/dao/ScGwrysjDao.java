package io.renren.vas.dao;


import io.renren.modules.sys.dao.BaseDao;
import io.renren.vas.entity.ScGwrysjEntity;

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
public interface ScGwrysjDao extends BaseDao<ScGwrysjEntity> {
	
	@Select("<script>"
			  + "SELECT * FROM sc_gwrysj WHERE sfzh = #{sfzh} "
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
	List<ScGwrysjEntity> find(Map<String, Object> map);
	
	@Select("SELECT count(*) FROM sc_gwrysj WHERE sfzh = #{sfzh}")
	int total(Map<String, Object> map);
	
	@Select("SELECT drybh FROM sc_gwrysj ORDER BY drybh DESC LIMIT 1 OFFSET 0 ")
	ScGwrysjEntity getDrybh();
	
}
