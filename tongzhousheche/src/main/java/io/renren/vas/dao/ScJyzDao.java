package io.renren.vas.dao;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.RequestParam;

import io.renren.common.utils.Query;
import io.renren.modules.sys.dao.BaseDao;
import io.renren.vas.entity.ScJyzEntity;

/**
 * 
 * 
 * @author pwk
 * @email sunlightcs@gmail.com
 * @date 2018-02-09 15:19:34
 */
@Mapper
public interface ScJyzDao extends BaseDao<ScJyzEntity> {
	@Select("select * from sc_jyz where cph = #{cph} limit #{limit} offset #{offset}")
	public List<ScJyzEntity> queryBycph(@RequestParam Map<String, Object> params);
	@Select("select count(1) from sc_jyz where cph = #{cph} ")
	public int cphTotal(Query query);
}
