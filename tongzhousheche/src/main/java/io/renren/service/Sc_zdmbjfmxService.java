package io.renren.service;

import java.util.List;
import java.util.Map;

import io.renren.vo.DtcldzdansVo;

public interface Sc_zdmbjfmxService {
	
	List<DtcldzdansVo> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	int dan_cs();
	
	int dan_sz();
	
	void faceCover();
}
