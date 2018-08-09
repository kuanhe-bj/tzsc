package io.renren.service.imp;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.renren.dao.RelituShiShiDao;
import io.renren.service.RelituShiShiService;
import io.renren.vo.RelituShiShiVo;


/**
 * 热力图实时
 * @author moulin
 *
 */
@Service
public class RelituShiShiImpl implements RelituShiShiService {
	
	@Autowired
	private RelituShiShiDao relituShiShiDao;

	@Override
	public List<RelituShiShiVo> queryAll(Map<String, Object> query) {
		return relituShiShiDao.queryAll(query);
	}

	@Override
	public List<RelituShiShiVo> queryByName() {
		return relituShiShiDao.queryByName();
	}

	@Override
	public int count(Map<String, Object> query) {
		return relituShiShiDao.count(query);
	}

	@Override
	public RelituShiShiVo queryById(String id) {
		return relituShiShiDao.queryById(id);
	}

	@Override
	public void save(RelituShiShiVo relitu) {
		relituShiShiDao.save(relitu);
	}

	@Override
	public void update(RelituShiShiVo relitu) {
		relituShiShiDao.update(relitu);
	}

	@Override
	public void deleteBatch(String ids) {
		relituShiShiDao.deleteBatch(ids);
	}

}
