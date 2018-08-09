package io.renren.vas.service;


import io.renren.vas.entity.ScJczxxEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author pwk
 * @email sunlightcs@gmail.com
 * @date 2018-02-09 15:19:34
 */
public interface ScJczxxService {
	
	ScJczxxEntity queryObject(String id);
	
	List<ScJczxxEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	ScJczxxEntity idMax1();
	
	List<ScJczxxEntity> cx(Map<String,Object> map);
	
	void save(ScJczxxEntity scJczxx);
	
	void update(ScJczxxEntity scJczxx);
	
	void delete(String id);
	
	void deleteBatch(String[] ids);
}
