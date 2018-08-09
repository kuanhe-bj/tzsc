package io.renren.vas.service;


import io.renren.vas.entity.ScKkjbxx113Entity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author pwk
 * @email sunlightcs@gmail.com
 * @date 2018-02-09 15:19:34
 */
public interface ScKkjbxx113Service {
	
	ScKkjbxx113Entity queryObject(String id);
	
	List<ScKkjbxx113Entity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(ScKkjbxx113Entity scKkjbxx113);
	
	void update(ScKkjbxx113Entity scKkjbxx113);
	
	void delete(String id);
	
	void deleteBatch(String[] ids);
	
	ScKkjbxx113Entity getIdMax();
	
	List<ScKkjbxx113Entity> find(Map<String, Object> map);
	
	int total(Map<String, Object> map);
}
