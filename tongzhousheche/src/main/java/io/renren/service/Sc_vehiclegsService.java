package io.renren.service;



import java.util.List;
import java.util.Map;

import io.renren.common.utils.Query;
import io.renren.vo.VehiclegsVo;
public interface Sc_vehiclegsService {
	
	List<VehiclegsVo> queryList(Map<String, Object> params);
	
	int queryTotal(Map<String, Object> params);
	
	void gsd();
	
	List<VehiclegsVo> highriskList();
	
	List<VehiclegsVo> dList(Map<String, Object> params);
	
	VehiclegsVo getcarnum();
	
	List<VehiclegsVo> selecttime(Map<String, Object> params);
	
    String getRandColorCode();

	int queryTotals(Map<String, Object> params);
		
	
}
