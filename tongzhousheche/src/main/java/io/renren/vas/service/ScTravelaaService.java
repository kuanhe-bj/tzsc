package io.renren.vas.service;


import io.renren.vas.entity.ScTravelaaEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author pwk
 * @email sunlightcs@gmail.com
 * @date 2018-02-26 11:56:26
 */
public interface ScTravelaaService {
	
	ScTravelaaEntity queryObject(String id);
	
	List<ScTravelaaEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(ScTravelaaEntity scTravelaa);
	
	void update(ScTravelaaEntity scTravelaa);
	
	void delete(String id);
	
	void deleteBatch(String[] ids);
}
