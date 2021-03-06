package io.renren.vas.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import io.renren.modules.sys.dao.BaseDao;
import io.renren.vas.entity.ScQgztryxxEntity;

/**
 * 
 * 
 * @author pwk
 * @email sunlightcs@gmail.com
 * @date 2018-02-09 15:19:33
 */
@Mapper
public interface ScQgztryxxDao extends BaseDao<ScQgztryxxEntity> {
	
	@Select("<script>"
			  + "SELECT * FROM sc_qgztryxx WHERE sfzh = #{sfzh} "
			  + "<choose>"
		      + "  <when test='sidx.trim() !=\"\"  and  sidx != null' >"
		      + "     order by ${sidx} ${order}"
		      + "  </when>"
		      + "  <otherwise> "
		      + "     order by rybh desc"
			  + "  </otherwise> "
		      + "</choose> "
		      + "<if test='offset != null and limit != null'>"
		      + "	  limit #{limit} offset #{offset}"
			  + "</if>"
			  + "</script>")
	List<ScQgztryxxEntity> find(Map<String, Object> map);
	
	@Select("SELECT count(*) FROM sc_qgztryxx WHERE sfzh = #{sfzh}")
	int total(Map<String, Object> map);
	
	@Select("SELECT rybh FROM sc_qgztryxx ORDER BY rybh DESC LIMIT 1 OFFSET 0 ")
	ScQgztryxxEntity getRybh();
	
}
