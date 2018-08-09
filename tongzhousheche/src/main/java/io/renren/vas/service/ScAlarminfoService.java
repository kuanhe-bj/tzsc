package io.renren.vas.service;


import java.util.List;
import java.util.Map;

import io.renren.vas.entity.ScAlarminfoEntity;

/**
 * 
 * 
 * @author pwk
 * @email sunlightcs@gmail.com
 * @date 2018-02-11 13:53:44
 */
public interface ScAlarminfoService {
	
	ScAlarminfoEntity queryObject(int id);
	
	List<ScAlarminfoEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(ScAlarminfoEntity scAlarminfo);
	
	void update(ScAlarminfoEntity scAlarminfo);
	
	void delete(String id);
	
	void deleteBatch(String[] ids);
	
	List<ScAlarminfoEntity> find(Map<String, Object> map);
	
	int total(Map<String, Object> map);
}
