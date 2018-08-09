package io.renren.vas.service;


import io.renren.vas.entity.ScGbzdrEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author pwk
 * @email sunlightcs@gmail.com
 * @date 2018-02-09 15:19:33
 */
public interface ScGbzdrService {
	
	ScGbzdrEntity queryObject(String drybh);
	
	List<ScGbzdrEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(ScGbzdrEntity scGbzdr);
	
	void update(ScGbzdrEntity scGbzdr);
	
	void delete(String drybh);
	
	void deleteBatch(String[] drybhs);
	
	List<ScGbzdrEntity> find(Map<String, Object> map);
	
	int total(Map<String, Object> map);
}
