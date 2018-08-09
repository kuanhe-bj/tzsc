package io.renren.service;

import java.util.List;
import java.util.Map;

import io.renren.vas.entity.ScEtcptjdEntity;
import io.renren.vo.DtcldzdansVo;

public interface Sc_scrcService {
	
	List<DtcldzdansVo> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	List<DtcldzdansVo> list();
	
	List<ScEtcptjdEntity> list_etcp();
	
	 List<DtcldzdansVo> list_dan();
	 
	 void scrc();
}
