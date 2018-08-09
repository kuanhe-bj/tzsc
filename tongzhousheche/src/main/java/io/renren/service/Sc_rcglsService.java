package io.renren.service;

import java.util.List;
import java.util.Map;

import io.renren.vo.Sc_rcglsVo;

public interface Sc_rcglsService {
	List<Sc_rcglsVo> queryList(Map<String, Object> map);
	int queryTotal(Map<String, Object> map);
}
