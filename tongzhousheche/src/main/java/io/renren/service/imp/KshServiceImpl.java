package io.renren.service.imp;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.renren.dao.KshDao;
import io.renren.service.KshService;
import io.renren.vo.KshVo;

/**
 * 可视化
 * @author moulin
 *
 */
@Service("kshService")
public class KshServiceImpl implements KshService{
	
	@Autowired
	private KshDao kshdao;
	
	@Override
	public List<KshVo> query(Map<String, Object> params) {
		return kshdao.query(params);
	}

}
