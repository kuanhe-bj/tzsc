package io.renren.vas.dao;


import io.renren.modules.sys.dao.BaseDao;
import io.renren.vas.entity.ScKkjbxx113Entity;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.repository.query.Param;

/**
 * 
 * 
 * @author pwk
 * @email sunlightcs@gmail.com
 * @date 2018-02-09 15:19:34
 */
@Mapper
public interface ScKkjbxx113Dao extends BaseDao<ScKkjbxx113Entity> {
	
	List<ScKkjbxx113Entity> checkList(@Param("cph") Object cph);
	
	@Select("select * from sc_kkjbxx113 ORDER BY id DESC LIMIT 1 OFFSET 0")
	ScKkjbxx113Entity getIdMax();
	
	@Select("<script>"
			  + "SELECT * FROM sc_kkjbxx113 WHERE cph = #{cph} "
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
	List<ScKkjbxx113Entity> find(Map<String, Object> map);
	
	@Select("SELECT count(*) FROM sc_kkjbxx113 WHERE cph = #{cph} ")
	int total(Map<String, Object> map);
}
