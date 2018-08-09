package io.renren.vas.service;


import io.renren.vas.entity.ScRedlistEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author pwk
 * @email sunlightcs@gmail.com
 * @date 2018-02-11 13:39:58
 */
public interface ScRedlistService {
	
	ScRedlistEntity
	queryObject(String id);
	
	List<ScRedlistEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(ScRedlistEntity scRedlist);
	
	void update(ScRedlistEntity scRedlist);
	
	void delete(String id);
	
	void deleteBatch(String[] ids);
	
	String idMax();
}
