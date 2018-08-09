package io.renren.service;

import java.util.List;
import java.util.Map;

import io.renren.vo.KshVo;

public interface KshService {
	List<KshVo> query(Map<String, Object> params);
}
