package io.renren.service;


import io.renren.vas.entity.SensitivepointEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-04-25 14:37:56
 */
public interface SensitivePointService {
	
	SensitivepointEntity queryObject(Integer id);
	
	List<SensitivepointEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(SensitivepointEntity sensitivepoint);
	
	void update(SensitivepointEntity sensitivepoint);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
	
	List<SensitivepointEntity> list(Map<String, Object> map);
	
	void sensitive();
}
