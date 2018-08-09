package io.renren.service.imp;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.renren.dao.Sc_yidaoyangxiDao;
import io.renren.service.Sc_yidaoyangxiService;
import io.renren.vo.GwrysjVo;

/**
 * 以盗养吸
 * @author moulin
 *
 */
@Service("sc_yidaoyangxiService")
public class Sc_yidaoyangxiServiceImpl implements Sc_yidaoyangxiService {
	
	@Autowired
	private Sc_yidaoyangxiDao sc_yidaoyangxiDao;
	
	@Override
	public List<GwrysjVo> queryList(Map<String, Object> map) {
		List<GwrysjVo> list = this.sc_yidaoyangxiDao.queryList(map);
		return list;
	}

	@Override
	public int queryTotal(Map<String, Object> map) {
		return sc_yidaoyangxiDao.queryTotal(map);
	}

	@Override
	public List<GwrysjVo> list(Map<String, Object> map) {
		List<GwrysjVo> list = sc_yidaoyangxiDao.list(map);
		return list;
	}

	@Override
	public int total(Map<String, Object> map) {
		return sc_yidaoyangxiDao.total(map);
	}

	@Override
	public List<GwrysjVo> find(Map<String, Object> map) {
		return sc_yidaoyangxiDao.find(map);
	}

	@Override
	public int count(Map<String, Object> map) {
		return sc_yidaoyangxiDao.count(map);
	}

}
