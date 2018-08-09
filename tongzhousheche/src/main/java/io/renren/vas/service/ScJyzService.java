package io.renren.vas.service;


import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestParam;

import io.renren.common.utils.Query;
import io.renren.vas.entity.ScJyzEntity;

/**
 * 
 * 
 * @author pwk
 * @email sunlightcs@gmail.com
 * @date 2018-02-09 15:19:34
 */
public interface ScJyzService {
	
	ScJyzEntity queryObject(String id);
	
	List<ScJyzEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(ScJyzEntity scJyz);
	
	void update(ScJyzEntity scJyz);
	
	void delete(String id);
	
	void deleteBatch(String[] ids);
	
	public List<ScJyzEntity> queryBycph(@RequestParam Map<String, Object> params);

	int cphTotal(Query query);
}
