package io.renren.vas.service;


import io.renren.vas.entity.ScGwrysjEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author pwk
 * @email sunlightcs@gmail.com
 * @date 2018-02-09 15:19:33
 */
public interface ScGwrysjService {
	
	ScGwrysjEntity queryObject(String drybh);
	
	List<ScGwrysjEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(ScGwrysjEntity scGwrysj);
	
	void update(ScGwrysjEntity scGwrysj);
	
	void delete(String drybh);
	
	void deleteBatch(String[] drybhs);
	
	List<ScGwrysjEntity> find(Map<String, Object> map);
	
	int total(Map<String, Object> map);
	
	ScGwrysjEntity getDrybh();
}
