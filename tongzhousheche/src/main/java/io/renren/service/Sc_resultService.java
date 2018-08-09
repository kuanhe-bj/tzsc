package io.renren.service;

import java.util.List;
import java.util.Map;

import io.renren.vo.ResultVo;

public interface Sc_resultService {
	
	List<ResultVo> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
}
