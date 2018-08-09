package io.renren.vas.service;


import io.renren.vas.entity.ScJczxx113Entity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author pwk
 * @email sunlightcs@gmail.com
 * @date 2018-02-09 15:19:34
 */
public interface ScJczxx113Service {
	
	ScJczxx113Entity queryObject(String id);
	
	List<ScJczxx113Entity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(ScJczxx113Entity scJczxx113);
	
	void update(ScJczxx113Entity scJczxx113);
	
	void delete(String id);
	
	void deleteBatch(String[] ids);
	
	ScJczxx113Entity getId();
	
	List<ScJczxx113Entity> find(Map<String, Object> map);
	
	int total(Map<String, Object> map);
}
