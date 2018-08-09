package io.renren.service;

import java.util.List;
import java.util.Map;


import io.renren.vo.MissionVo;

public interface Sc_missionService {
	
	List<MissionVo> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void insert(MissionVo missionVo);
	
	void deleteBatch(String[] ids);
	
	void updating(int id);
	
	int total(Map<String, Object> map);
	
	List<MissionVo> list(Map<String, Object> map);
	
	void hidden(int id);
	
	void ycgj(int id);
	
	void taopai(int id);
	
	void xxfx(int id);
	
	void csfx(int id);
	
	void dqcl(int id);
	
	void zfyc(int id);
	
	List<MissionVo> find(int id);
}
