package io.renren.vas.service;

import io.renren.vas.entity.ScEtcptjdEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author pwk
 * @email sunlightcs@gmail.com
 * @date 2018-02-09 15:19:34
 */
public interface ScEtcptjdService {
	
	ScEtcptjdEntity queryObject(String eid);
	
	List<ScEtcptjdEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(ScEtcptjdEntity scEtcptjd);
	
	void update(ScEtcptjdEntity scEtcptjd);
	
	void delete(String eid);
	
	void deleteBatch(String[] eids);
}
