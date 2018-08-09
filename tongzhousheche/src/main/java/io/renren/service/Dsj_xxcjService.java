package io.renren.service;

import java.util.List;
import java.util.Map;

import io.renren.vas.entity.ScDtcldzdanEntity;
import io.renren.vas.entity.ScScajEntity;


public interface Dsj_xxcjService {

	List<ScScajEntity> list(Map<String, Object> query);
	
	int count(Map<String, Object> query);
	
	int queryTotal(Map<String, Object> map);
	
	List<ScScajEntity> list_ID(int id);

	int insert( int id,String ajbh,String ajmc,String pcsgx, 
			String jq, String fadd, String faddxz, String fakssj,
			String sacph, double jd, double wd, String jyaq,double
			gisx,double gisy);
	
	int update( String ajbh,String ajmc,String pcsgx, 
			String jq, String fadd, String faddxz, String fakssj,
			String sacph, double jd, double wd, String jyaq,String id,double
			gisx,double gisy);
	
	List<ScDtcldzdanEntity> list_dan( String plate);
	
	int update_dan(String plate);
	
	int insert_blick(String cph, String user,String createReason);
	
	List<ScScajEntity> id_cz();
	
	List<ScScajEntity> cx(Map<String, Object> query);
}
