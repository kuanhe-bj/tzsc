package io.renren.vas.dao;


import io.renren.modules.sys.dao.BaseDao;
import io.renren.vas.entity.ScJxsjEntity;

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
public interface ScJxsjDao extends BaseDao<ScJxsjEntity> {
	@Select("select * from sc_jxsj where license_plate=#{a}")
	List<ScJxsjEntity> cx(Map<String,Object> map);
}
