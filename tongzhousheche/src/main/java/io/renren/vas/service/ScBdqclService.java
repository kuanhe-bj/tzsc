package io.renren.vas.service;

import java.util.List;
import java.util.Map;

import io.renren.vas.entity.ScBdqclEntity;

/**
 * 
 * 
 * @author pwk
 * @email sunlightcs@gmail.com
 * @date 2018-02-09 15:19:34
 */
public interface ScBdqclService {
	
	ScBdqclEntity queryObject(String id);
	
	List<ScBdqclEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(ScBdqclEntity scBdqcl);
	
	void update(ScBdqclEntity scBdqcl);
	
	void delete(String id);
	
	void deleteBatch(String[] ids);
	
	ScBdqclEntity getId();
	
	List<ScBdqclEntity> cx(Map<String,Object> map);
}
