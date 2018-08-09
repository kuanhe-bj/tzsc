package io.renren.vas.service;


import io.renren.common.utils.Query;
import io.renren.vas.entity.ScJsrsjEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author pwk
 * @email sunlightcs@gmail.com
 * @date 2018-02-09 15:19:33
 */
public interface ScJsrsjService {
	
	ScJsrsjEntity queryObject(String dataid);
	
	List<ScJsrsjEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(ScJsrsjEntity scJsrsj);
	
	void update(ScJsrsjEntity scJsrsj);
	
	void delete(String dataid);
	
	void deleteBatch(String[] dataids);

	List<ScJsrsjEntity> queryBy(Query query);

	int sfzTotal(Query query);
}
