package io.renren.vas.service;


import io.renren.vas.entity.ScBlacklistEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author pwk
 * @email sunlightcs@gmail.com
 * @date 2018-02-11 13:39:58
 */
public interface ScBlacklistService {
	
	ScBlacklistEntity queryObject(String id);
	
	List<ScBlacklistEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(ScBlacklistEntity scBlacklist);
	
	void update(ScBlacklistEntity scBlacklist);
	
	void delete(String id);
	
	void deleteBatch(String[] ids);
	
	String idMax();
}
