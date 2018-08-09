package io.renren.vas.service;


import java.util.List;
import java.util.Map;

import io.renren.vas.entity.ScQgztryxxEntity;

/**
 * 
 * 
 * @author pwk
 * @email sunlightcs@gmail.com
 * @date 2018-02-09 15:19:33
 */
public interface ScQgztryxxService {
	
	ScQgztryxxEntity queryObject(String rybh);
	
	List<ScQgztryxxEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(ScQgztryxxEntity scQgztryxx);
	
	void update(ScQgztryxxEntity scQgztryxx);
	
	void delete(String rybh);
	
	void deleteBatch(String[] rybhs);
	
	List<ScQgztryxxEntity> find(Map<String, Object> map);
	
	int total(Map<String, Object> map);
	
	ScQgztryxxEntity getRybh();
}
