package io.renren.service;

import java.util.List;
import java.util.Map;

import io.renren.vas.entity.ScEtcptjdEntity;

/**
 * 
 * 
 * @author pwk
 * @email sunlightcs@gmail.com
 * @date 2018-02-09 15:19:34
 */
public interface Sc_etcptjdService {
	
	List<ScEtcptjdEntity> findByCPH(Map<String, Object> params);
	
	List<ScEtcptjdEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	int total(Map<String, Object> map);
	
	void taopai();
	
	void hidden();
	
	void onlyEnter();
	
	int num();
	
}
