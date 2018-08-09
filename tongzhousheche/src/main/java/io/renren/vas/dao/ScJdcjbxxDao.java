package io.renren.vas.dao;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import io.renren.modules.sys.dao.BaseDao;
import io.renren.vas.entity.ScJdcjbxxEntity;

/**
 * 
 * 
 * @author pwk
 * @email sunlightcs@gmail.com
 * @date 2018-02-09 15:19:34
 */
@Mapper
public interface ScJdcjbxxDao extends BaseDao<ScJdcjbxxEntity> {
	
	@Select("<script>"
			  + "SELECT * FROM sc_scaj WHERE cph = #{cph} "
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
	List<ScJdcjbxxEntity> find(Map<String, Object> map);
	
	@Select("SELECT count(*) FROM sc_scaj WHERE cph = #{cph} ")
	int total(Map<String, Object> map);
	
}
