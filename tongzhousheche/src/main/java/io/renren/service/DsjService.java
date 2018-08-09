package io.renren.service;

import java.util.List;
import java.util.Map;

import io.renren.vas.entity.ScEtcptjdEntity;
import io.renren.vas.entity.ScScajEntity;


public interface DsjService {

	List<ScScajEntity> fin(Map<String, Object> map);

	List<ScScajEntity> jw(String cph);

	List<ScEtcptjdEntity> allcar(Map<String, Object> map);

	List<ScScajEntity> x(String ajbh,String ajmc);
	
	int total(Map<String, Object> map);
	
	 List<ScScajEntity> fin_ds(Map<String, Object> map);
	 
	 List<ScScajEntity> fin_dsall(Map<String, Object> map);
}
