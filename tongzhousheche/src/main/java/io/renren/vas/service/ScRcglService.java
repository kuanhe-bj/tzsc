package io.renren.vas.service;


import io.renren.vas.entity.ScRcglEntity;

import java.util.List;
import java.util.Map;

/**
 * VIEW
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-03-28 15:52:43
 */
public interface ScRcglService {
	
	ScRcglEntity queryObject(String cp);
	
	List<ScRcglEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(ScRcglEntity scRcgl);
	
	void update(ScRcglEntity scRcgl);
	
	void delete(String cp);
	
	void deleteBatch(String[] cps);
}
