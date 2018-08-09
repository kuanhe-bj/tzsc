package io.renren.service.imp;

import java.util.List;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.renren.dao.Sc_rcglsDao;
import io.renren.service.Sc_rcglsService;
import io.renren.vo.Sc_rcglsVo;

/**
 * 人车关联
 * @author moulin
 *
 */
@Service("sc_rcglsService")
public class Sc_rcglsSeviceImpl implements Sc_rcglsService{
	
	@Autowired
	private Sc_rcglsDao sc_rcglsDao;
	
	
	@Override
	public List<Sc_rcglsVo> queryList(Map<String, Object> map) {
		return sc_rcglsDao.queryList(map);
	}

	@Override
	public int queryTotal(Map<String, Object> map) {
		return sc_rcglsDao.queryTotal(map);
	}

}
