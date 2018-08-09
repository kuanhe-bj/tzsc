package io.renren.service;

import java.util.List;
import java.util.Map;


import io.renren.vo.Sc_jkrwVo;
import io.renren.vo.Sc_ssjkVo;

public interface Sc_jkrwService {
	
	List<Sc_jkrwVo> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	List<Sc_jkrwVo> list(Map<String, Object> map);
	
	void deleteBatch(String[] ids);
	
	void update1(String id);
	
	void update2(String id);
	
	List<Sc_jkrwVo> list(String id);
	
	List<Sc_jkrwVo> check(String username, int mytype);
	
	List<Sc_ssjkVo> find(String taskid);
	
}
