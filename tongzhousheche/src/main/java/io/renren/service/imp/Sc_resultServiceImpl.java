package io.renren.service.imp;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.renren.dao.Sc_resultDao;
import io.renren.service.Sc_resultService;
import io.renren.vo.ResultVo;

/**
 * 实时任务分析结果
 * @author moulin
 *
 */
@Service("sc_resultService")
public class Sc_resultServiceImpl implements Sc_resultService{
	
	@Autowired
	private Sc_resultDao sc_resultDao;
	
	@Override
	public List<ResultVo> queryList(Map<String, Object> map) {
		List<ResultVo> list = this.sc_resultDao.queryList(map);	
		return list;	
	}

	@Override
	public int queryTotal(Map<String, Object> map) {
		return sc_resultDao.queryTotal(map);
	}

}
