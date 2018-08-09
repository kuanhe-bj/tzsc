package io.renren.vas.service;


import io.renren.vas.entity.ScDtcldzdanEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-03-22 10:55:46
 */
public interface ScDtcldzdanService {
	
	ScDtcldzdanEntity queryObject(Integer id);
	
	List<ScDtcldzdanEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(ScDtcldzdanEntity scDtcldzdan);
	
	void update(ScDtcldzdanEntity scDtcldzdan);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
	
	List<ScDtcldzdanEntity> all();
}
