package io.renren.vas.service;


import io.renren.vas.entity.ScJdcjbxxEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author pwk
 * @email sunlightcs@gmail.com
 * @date 2018-02-09 15:19:34
 */
public interface ScJdcjbxxService {
	
	ScJdcjbxxEntity queryObject(String dataid);
	
	List<ScJdcjbxxEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(ScJdcjbxxEntity scJdcjbxx);
	
	void update(ScJdcjbxxEntity scJdcjbxx);
	
	void delete(String dataid);
	
	void deleteBatch(String[] dataids);
	
	List<ScJdcjbxxEntity> find(Map<String, Object> map);
	
	int total(Map<String, Object> map);
}
