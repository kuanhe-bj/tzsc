package io.renren.vas.service;


import io.renren.vas.entity.ScSfzdrEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author pwk
 * @email sunlightcs@gmail.com
 * @date 2018-02-09 15:19:33
 */
public interface ScSfzdrService {
	
	ScSfzdrEntity queryObject(String dataid);
	
	List<ScSfzdrEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(ScSfzdrEntity scSfzdr);
	
	void update(ScSfzdrEntity scSfzdr);
	
	void delete(String dataid);
	
	void deleteBatch(String[] dataids);
	
	List<ScSfzdrEntity> cx(Map<String,Object> map);
}
