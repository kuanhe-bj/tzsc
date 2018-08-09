package io.renren.vas.dao;


import io.renren.modules.sys.dao.BaseDao;
import io.renren.vas.entity.ScKkxxEntity;

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
public interface ScKkxxDao extends BaseDao<ScKkxxEntity> {
	@Select("<script>"
		  + "select * from sc_kkxx "
		  + "<where> "
		  + "<if test=\' id != \"\" and id != null \'>"
		  + "  AND id = #{id}"
		  + "</if>"
		  + "<if test=\' mc != \"\" and mc != null \'>"
		  + "  AND mc = #{mc}"
		  + "</if>"
		  + "</where>"
		  + "<choose>"
		  + "<when test='sidx.trim() !=\"\"  and  sidx != null '>" 
		  + "   order by ${sidx} ${order}"
		  + "</when>" 
		  + "<otherwise>" 
		  + "   order by id desc" 
		  + "</otherwise>" 
		  + "</choose>"
		  + "<if test='offset != null and limit != null'>" 
		  + "	limit #{limit} offset #{offset}" 
		  + "</if>" 
		  + "</script>")
	List<ScKkxxEntity> cx(Map<String,Object> map);
	
	@Select("<script>"
		  + "select count(id) from sc_kkxx "
		  + "<where> "
		  + "<if test=\' id != \"\" and id !=null \'>"
		  + "  AND id = #{id}"
		  + "</if>"
		  + "<if test=\' mc != \"\" and mc !=null \'>"
		  + "  AND mc = #{mc}"
		  + "</if>"
		  + "</where>"
		  + "</script>")
	int total(Map<String,Object> map);
}
