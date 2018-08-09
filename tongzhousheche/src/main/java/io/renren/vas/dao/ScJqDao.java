package io.renren.vas.dao;

import io.renren.modules.sys.dao.BaseDao;
import io.renren.vas.entity.ScJqEntity;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ScJqDao extends BaseDao<ScJqEntity> {
	
	@Select("<script>"
			  + "SELECT * FROM sc_jq WHERE cph = #{cph} "
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
	List<ScJqEntity> find(Map<String, Object> map);
	
	@Select("SELECT count(*) FROM sc_jq WHERE cph = #{cph} ")
	int total(Map<String, Object> map);

}
