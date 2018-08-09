package io.renren.vas.service;

import io.renren.vas.entity.ScHlsjEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author pwk
 * @email sunlightcs@gmail.com
 * @date 2018-02-09 15:19:34
 */
public interface ScHlsjService {
	
	ScHlsjEntity queryObject(String checkinfoid);
	
	List<ScHlsjEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(ScHlsjEntity scHlsj);
	
	void update(ScHlsjEntity scHlsj);
	
	void delete(String checkinfoid);
	
	void deleteBatch(String[] checkinfoids);
	
	List<ScHlsjEntity> find(Map<String, Object> map);
	
	int total(Map<String, Object> map);
}
