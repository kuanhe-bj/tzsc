package io.renren.service.imp;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.renren.dao.ScajDao;
import io.renren.service.ScajService;
import io.renren.vo.ScajVo;

/**
 * 涉车案件
 * @author moulin
 *
 */
@Service
public class ScajServiceImpl implements ScajService {
	
	@Autowired
	private ScajDao dao;
	
	@Override
	public List<ScajVo> query(Map<String, Object> params) {
		List<ScajVo> list = dao.query(params);
		return list;
	}
	
	@Override
	public List<ScajVo> queryby(Map<String, Object> params) {
		List<ScajVo> list = dao.queryby(params);
		return list;
	}
	
	@Override
	public int count(Map<String, Object> params) {
		return dao.count(params);
	}

}
