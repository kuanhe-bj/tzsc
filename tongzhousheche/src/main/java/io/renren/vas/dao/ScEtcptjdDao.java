package io.renren.vas.dao;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

import io.renren.modules.sys.dao.BaseDao;
import io.renren.vas.entity.ScEtcptjdEntity;

/**
 * 
 * 
 * @author pwk
 * @email sunlightcs@gmail.com
 * @date 2018-02-09 15:19:34
 */
@Mapper
public interface ScEtcptjdDao extends BaseDao<ScEtcptjdEntity> {
	List<ScEtcptjdEntity> checkList(@Param("cph") Object cph);
}
