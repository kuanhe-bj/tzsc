package io.renren.service;

import java.util.List;
import java.util.Map;

import io.renren.vo.GwrysjVo;

public interface Sc_weizuyinxingService {
	
	List<GwrysjVo> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void update(String cp);
	
	List<GwrysjVo> list(Map<String, Object> map);
	
	int total(Map<String, Object> map);
}
