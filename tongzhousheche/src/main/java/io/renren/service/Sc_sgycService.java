package io.renren.service;

import java.util.List;
import java.util.Map;

import io.renren.vo.DtcldzdansVo;

public interface Sc_sgycService {
	
	List<DtcldzdansVo> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void setUpdate();
	
	void setAccident();
}
