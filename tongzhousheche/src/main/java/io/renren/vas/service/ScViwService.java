package io.renren.vas.service;


import java.util.List;
import java.util.Map;

import io.renren.vas.entity.ScViwEntity;

/**
 * 
 * 
 * @author pwk
 * @email sunlightcs@gmail.com
 * @date 2018-02-11 13:39:58
 */
public interface ScViwService {
	
	ScViwEntity queryObject(String id);
	
	List<ScViwEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(ScViwEntity scViw);
	
	void update(ScViwEntity scViw);
	
	void delete(String id);
	
	void deleteBatch(String[] ids);
	
	List<ScViwEntity> find(Map<String, Object> map);
	
	int total(Map<String, Object> map);
}
