package io.renren.vas.service;


import io.renren.vas.entity.ScJxsjEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author pwk
 * @email sunlightcs@gmail.com
 * @date 2018-02-09 15:19:34
 */
public interface ScJxsjService {
	
	ScJxsjEntity queryObject(String dataid);
	
	List<ScJxsjEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(ScJxsjEntity scJxsj);
	
	void update(ScJxsjEntity scJxsj);
	
	void delete(String dataid);
	
	void deleteBatch(String[] dataids);
	
	List<ScJxsjEntity> cx(Map<String,Object> map);
}
