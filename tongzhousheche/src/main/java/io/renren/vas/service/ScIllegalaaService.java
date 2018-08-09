package io.renren.vas.service;

import io.renren.vas.entity.ScIllegalaaEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author pwk
 * @email sunlightcs@gmail.com
 * @date 2018-02-11 13:39:58
 */
public interface ScIllegalaaService {
	
	ScIllegalaaEntity queryObject(String id);
	
	List<ScIllegalaaEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(ScIllegalaaEntity scIllegalaa);
	
	void update(ScIllegalaaEntity scIllegalaa);
	
	void delete(String id);
	
	void deleteBatch(String[] ids);
}
