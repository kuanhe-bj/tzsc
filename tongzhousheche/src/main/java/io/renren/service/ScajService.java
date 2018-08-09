package io.renren.service;

import java.util.List;
import java.util.Map;

import io.renren.vo.ScajVo;

public interface ScajService {
	List<ScajVo> query(Map<String, Object> params);
	List<ScajVo> queryby(Map<String, Object> params);
	int count(Map<String, Object> params);
}
