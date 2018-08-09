package io.renren.service;

import java.util.List;
import java.util.Map;

import io.renren.vo.KakouRecordVo;
import io.renren.vo.KakoubsVo;

/**
 * 
 * 
 * @author pwk
 * @email sunlightcs@gmail.com
 * @date 2018-02-09 15:19:34
 */
public interface Sc_kkxxService {
	
	List<KakouRecordVo> findKkxx(Map<String, Object> map);
	
	List<KakouRecordVo> find(String kid, String start, String end);
	
	double frequency(Map<String, Object> params);
	
	List<KakouRecordVo> checkKkxx(Map<String, Object> params);
	
	List<KakoubsVo> checkKkbs(String cph1, String cph2, int time);
	
	List<KakoubsVo> kkbs(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	int total(Map<String, Object> params);
	
	int getCount(Map<String, Object> map);
}
