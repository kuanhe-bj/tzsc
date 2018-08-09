package io.renren.vas.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import io.renren.modules.sys.dao.BaseDao;
import io.renren.vas.entity.ScBdqclEntity;

/**
 * 
 * 
 * @author pwk
 * @email sunlightcs@gmail.com
 * @date 2018-02-09 15:19:34
 */
@Mapper
public interface ScBdqclDao extends BaseDao<ScBdqclEntity> {
	
	@Select("select * from sc_bdqcl ORDER BY id DESC LIMIT 1 OFFSET 0")
	ScBdqclEntity getId();	
	
	@Select("select * from sc_bdqcl where cph = #{cph}")
	List<ScBdqclEntity> cx(Map<String,Object> map);
}
