package io.renren.service;

import java.util.List;
import java.util.Map;

import io.renren.vo.RelituShiShiVo;

public interface RelituShiShiService {
	List<RelituShiShiVo> queryAll(Map<String, Object> query);
	
	List<RelituShiShiVo> queryByName();

	int count(Map<String, Object> query);

	RelituShiShiVo queryById(String id);

	void save(RelituShiShiVo relitu);

	void update(RelituShiShiVo relitu);

	void deleteBatch(String string);
}
