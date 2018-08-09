package io.renren.vas.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import io.renren.modules.sys.dao.BaseDao;
import io.renren.vas.entity.ScAlarminfoEntity;

/**
 * 
 * 
 * @author pwk
 * @email sunlightcs@gmail.com
 * @date 2018-02-11 13:53:44
 */
@Mapper
public interface ScAlarminfoDao extends BaseDao<ScAlarminfoEntity> {
	
	@Select("<script>"
			  + "SELECT * FROM sc_alarminfo "
			  + "where carnum = #{cph} " 
			  + "<choose> "
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
	List<ScAlarminfoEntity> find(Map<String, Object> map);
	
	@Select("<script>"
			  + "SELECT count(*) FROM sc_alarminfo "
			  + "where	carnum = #{cph} " 
			  + "</script>")
	int total(Map<String, Object> map);
}
