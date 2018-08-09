package io.renren.vas.service;


import io.renren.vas.entity.ScGsdEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-03-22 10:55:46
 */
public interface ScGsdService {
	
	ScGsdEntity queryObject(Integer id);
	
	List<ScGsdEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(ScGsdEntity scGsd);
	
	void update(ScGsdEntity scGsd);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
