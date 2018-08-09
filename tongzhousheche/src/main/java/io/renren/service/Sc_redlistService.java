package io.renren.service;


import java.util.List;
import java.util.Map;

import io.renren.vas.entity.ScRedlistEntity;
import io.renren.vo.Sc_jdc;

/**
 * 
 * 
 * @author pwk
 * @email sunlightcs@gmail.com
 * @date 2018-02-11 13:39:58
 */
public interface Sc_redlistService {
	
	List<ScRedlistEntity> checkList(Map<String, Object> params);
	
	List<Sc_jdc> check(String carNum);
	
	String idMax();
	
	void save(ScRedlistEntity scRedlist);
	
	ScRedlistEntity getId();
	
	List<ScRedlistEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	int total(Map<String, Object> map);
	
	void updatePass(String username, String result, String cph);
	
	void updateNotPass(String username, String cph);
	
	List<ScRedlistEntity> find(String carNum);
}
