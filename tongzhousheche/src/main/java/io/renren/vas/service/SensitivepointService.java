package io.renren.vas.service;

import io.renren.vas.entity.SensitivepointEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-04-25 15:57:42
 */
public interface SensitivepointService {
	
	SensitivepointEntity queryObject(Integer id);
	
	List<SensitivepointEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(SensitivepointEntity sensitivepoint);
	
	void update(SensitivepointEntity sensitivepoint);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
