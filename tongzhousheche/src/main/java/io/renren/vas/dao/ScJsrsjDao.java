package io.renren.vas.dao;


import io.renren.common.utils.Query;
import io.renren.modules.sys.dao.BaseDao;
import io.renren.vas.entity.ScJsrsjEntity;

import java.util.List;

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
public interface ScJsrsjDao extends BaseDao<ScJsrsjEntity> {
	@Select("select * from sc_jsrsj where sfzmhm = #{sfz} limit #{limit} offset #{offset}")
	List<ScJsrsjEntity> queryBy(Query query);
	
	@Select("select count(*) from sc_jsrsj where sfzmhm = #{sfz} ")
	int sfzTotal(Query query);
	
}
