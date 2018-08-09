package io.renren.service;

import java.util.List;
import java.util.Map;

import io.renren.vo.GwrysjVo;

public interface Sc_yidaoyangxiService {
	
	List<GwrysjVo> queryList(Map<String, Object> map);
	
	List<GwrysjVo> list(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	int total(Map<String, Object> map);
	
	List<GwrysjVo> find(Map<String, Object> map);
	
	int count(Map<String, Object> map);
}
