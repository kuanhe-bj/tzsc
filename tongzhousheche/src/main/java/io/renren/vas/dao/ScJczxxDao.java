package io.renren.vas.dao;


import io.renren.modules.sys.dao.BaseDao;
import io.renren.vas.entity.ScJczxxEntity;

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
public interface ScJczxxDao extends BaseDao<ScJczxxEntity> {
	@Select("select * from sc_jczxx where xz=#{xz}")
	List<ScJczxxEntity> cx(Map<String,Object> map);
}
