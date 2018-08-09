package io.renren.vas.service;


import io.renren.vas.entity.ScTwkkxx113Entity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author pwk
 * @email sunlightcs@gmail.com
 * @date 2018-02-09 15:19:34
 */
public interface ScTwkkxx113Service {
	
	ScTwkkxx113Entity queryObject(String id);
	
	List<ScTwkkxx113Entity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(ScTwkkxx113Entity scTwkkxx113);
	
	void update(ScTwkkxx113Entity scTwkkxx113);
	
	void delete(String id);
	
	void deleteBatch(String[] ids);
	
	ScTwkkxx113Entity getId();
	
	List<ScTwkkxx113Entity> find(Map<String, Object> map);
	
	int total(Map<String, Object> map);
}
