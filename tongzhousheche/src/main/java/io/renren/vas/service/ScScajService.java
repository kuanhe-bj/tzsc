package io.renren.vas.service;


import io.renren.vas.entity.ScScajEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author pwk
 * @email sunlightcs@gmail.com
 * @date 2018-02-09 15:19:34
 */
public interface ScScajService {
	
	ScScajEntity queryObject(String id);
	
	List<ScScajEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(ScScajEntity scScaj);
	
	void update(ScScajEntity scScaj);
	
	void delete(String id);
	
	void deleteBatch(String[] ids);
	
	List<ScScajEntity> find(Map<String, Object> map);
	
	int total(Map<String, Object> map);
}
