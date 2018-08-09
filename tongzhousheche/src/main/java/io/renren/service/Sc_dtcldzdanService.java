package io.renren.service;


import io.renren.vas.entity.ScDtcldzdanEntity;
import io.renren.vo.XianXingVo;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-03-22 10:55:46
 */
public interface Sc_dtcldzdanService {
	
	List<ScDtcldzdanEntity> queryList(Map<String, Object> map);
	
	List<ScDtcldzdanEntity> sfzByList(String sfz);
	
	void sfzUpdate(String sfz);
	
	List<ScDtcldzdanEntity> plateByList(String plate);
	
	void plateUpdate(String plate);
	
	int queryTotal(Map<String, Object> map);
	//获取限行车辆
	List<XianXingVo> queryXX();
	//修改有限行的车辆记录
	int xxUpdate(String plate,float limits);
	//获取现行尾号
	List<XianXingVo> queryWH(Map<String, Object> map);

	void xianxing();
	
	void lxWeiZhang();
	
	List<Integer> getCount();
	
	void saveDtcldzdan();
}
