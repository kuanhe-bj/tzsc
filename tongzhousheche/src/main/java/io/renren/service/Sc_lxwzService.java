package io.renren.service;

import java.util.List;
import java.util.Map;

import io.renren.vo.DtcldzdansVo;

public interface Sc_lxwzService {
	
	List<DtcldzdansVo> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	int lxwzCheck(Map<String, Object> map);
	
	int total();
	
	void ContViolation();
	
	int setContViolation(double f, String plate);
}
