package io.renren.service;


import java.util.List;
import java.util.Map;


import io.renren.vas.entity.ScBlacklistEntity;

/**
 * 
 * 
 * @author pwk
 * @email sunlightcs@gmail.com
 * @date 2018-02-11 13:39:58
 */
public interface Sc_blacklistService {
	
	List<ScBlacklistEntity> checkList(Map<String, Object> params);
	
	int check(String carNum);
	
	String idMax();
	
	void save(ScBlacklistEntity scBlacklistEntity);
	
	List<ScBlacklistEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	int total(Map<String, Object> map);

	ScBlacklistEntity checkCph(String carNun);
	
	public void insertQB(Map<String, Object> params);
	
	void updatePass(String username,  String result, String cph);
	
	void updateNotPass(String username, String cph);
	
	List<ScBlacklistEntity> find(String carNum);
	
	int updateBkStatus(String carnum);
}
