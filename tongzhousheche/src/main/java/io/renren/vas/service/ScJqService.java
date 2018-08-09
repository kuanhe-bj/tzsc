package io.renren.vas.service;

import io.renren.vas.entity.ScJqEntity;

import java.util.List;
import java.util.Map;

public interface ScJqService {
    ScJqEntity queryObject(String id);

    List<ScJqEntity> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    void save(ScJqEntity scJq);

    void update(ScJqEntity scJq);

    void delete(String id);

    void deleteBatch(String[] ids);
    
	List<ScJqEntity> find(Map<String, Object> map);
	
	int total(Map<String, Object> map);
}