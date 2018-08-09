package io.renren.service.imp;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.renren.dao.Sc_rcgltDao;
import io.renren.service.Sc_rcgltService;
import io.renren.vo.Sc_rcglsVo;

/**
 * 人车关联图
 * @author moulin
 *
 */
@Service("sc_rcgltService")
public class Sc_rcgltServiceImpl implements Sc_rcgltService{
	
	@Autowired
	private Sc_rcgltDao sc_rcgltDao;
	
	@Override
	public List<Sc_rcglsVo> queryList(String sfz) {
		return sc_rcgltDao.querylist(sfz);
	}
}
