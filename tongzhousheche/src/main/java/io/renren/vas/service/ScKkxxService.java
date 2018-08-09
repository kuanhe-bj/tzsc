package io.renren.vas.service;


import io.renren.vas.entity.ScKkxxEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author pwk
 * @email sunlightcs@gmail.com
 * @date 2018-02-09 15:19:34
 */
public interface ScKkxxService {
	
	ScKkxxEntity queryObject(String id);
	
	List<ScKkxxEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(ScKkxxEntity scKkxx);
	
	void update(ScKkxxEntity scKkxx);
	
	void delete(String id);
	
	void deleteBatch(String[] ids);
	
	List<ScKkxxEntity> cx(Map<String,Object> map);
	
	int total(Map<String,Object> map);
}
