package io.renren.service;

import java.util.List;
import java.util.Map;

import io.renren.vo.GwrysjVo;

public interface GwrysjService {
	List<GwrysjVo> query(Map<String, Object> params);
	List<GwrysjVo> queryby(Map<String, Object> params);
	int count(Map<String, Object> params);
}
