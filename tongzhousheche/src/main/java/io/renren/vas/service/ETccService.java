package io.renren.vas.service;


import io.renren.vas.entity.ETccEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-03-24 11:48:37
 */
public interface ETccService {
	
	ETccEntity queryObject(String eId);
	
	List<ETccEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(ETccEntity eTcc);
	
	void update(ETccEntity eTcc);
	
	void delete(String eId);
	
	void deleteBatch(String[] eIds);
}
