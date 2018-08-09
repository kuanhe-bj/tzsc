package io.renren.vas.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import io.renren.modules.sys.dao.BaseDao;
import io.renren.vas.entity.ScJdcjbxxEntity;


@Mapper
public interface CljsyxxDao extends BaseDao<ScJdcjbxxEntity>{

	List<ScJdcjbxxEntity> finj(Map<String, Object> map);
	

}
