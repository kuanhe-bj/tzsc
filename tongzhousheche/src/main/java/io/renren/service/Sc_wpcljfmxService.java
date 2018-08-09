package io.renren.service;

import java.util.List;
import java.util.Map;


import io.renren.vo.DtcldzdansVo;

public interface Sc_wpcljfmxService {
	
	List<DtcldzdansVo> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	int wcp_cs();
	
	int wcp_sz();
	
	 void wpcl();
}
