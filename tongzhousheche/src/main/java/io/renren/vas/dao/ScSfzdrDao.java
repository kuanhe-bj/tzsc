package io.renren.vas.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import io.renren.modules.sys.dao.BaseDao;
import io.renren.vas.entity.ScSfzdrEntity;

/**
 * 
 * 
 * @author pwk
 * @email sunlightcs@gmail.com
 * @date 2018-02-26 11:56:26
 */
@Mapper
public interface ScSfzdrDao extends BaseDao<ScSfzdrEntity>{
	
	@Select("select * from sc_sfzdr where zjhm=#{zjhm}")
	List<ScSfzdrEntity> cx(Map<String,Object> map);
	
}
