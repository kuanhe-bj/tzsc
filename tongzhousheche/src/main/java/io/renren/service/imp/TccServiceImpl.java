package io.renren.service.imp;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import io.renren.dao.TccDao;
import io.renren.service.TccService;
import io.renren.vas.entity.ScEtcptjdEntity;
import org.springframework.stereotype.Service;

/**
 * 停车场
 * @author moulin
 *
 */
@Service("tccService")
public class TccServiceImpl implements TccService {

	@Autowired
	private TccDao tccDao;

	@Override
	public List<ScEtcptjdEntity> xq(Map<String, Object> map) {

		List<ScEtcptjdEntity> list = tccDao.xq(map);

		return list;
	}

}