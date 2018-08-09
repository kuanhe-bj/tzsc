package io.renren.service.imp;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.renren.dao.GwrysjDao;
import io.renren.service.GwrysjService;
import io.renren.vo.GwrysjVo;

/**
 * 情报专题挖掘分析
 * @author moulin
 *
 */
@Service
public class GwrysjServiceImpl implements GwrysjService {
	
	@Autowired
	private GwrysjDao dao;
	
	@Override
	public List<GwrysjVo> query(Map<String, Object> params) {
		List<GwrysjVo> list = dao.query(params);
		return list;
	}
	
	@Override
	public List<GwrysjVo> queryby(Map<String, Object> params) {
		List<GwrysjVo> list = dao.queryby(params);
		return list;
	}
	
	@Override
	public int count(Map<String, Object> params) {
		return dao.count(params);
	}

}
