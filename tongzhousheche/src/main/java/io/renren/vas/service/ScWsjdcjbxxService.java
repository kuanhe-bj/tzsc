package io.renren.vas.service;


import io.renren.vas.entity.ScWsjdcjbxxEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author pwk
 * @email sunlightcs@gmail.com
 * @date 2018-02-09 15:19:34
 */
public interface ScWsjdcjbxxService {
	
	ScWsjdcjbxxEntity queryObject(String dataid);
	
	List<ScWsjdcjbxxEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(ScWsjdcjbxxEntity scWsjdcjbxx);
	
	void update(ScWsjdcjbxxEntity scWsjdcjbxx);
	
	void delete(String dataid);
	
	void deleteBatch(String[] dataids);
	
	List<ScWsjdcjbxxEntity> find(Map<String, Object> map);
	
	int total(Map<String, Object> map);
}
