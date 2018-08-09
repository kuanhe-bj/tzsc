package io.renren.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.renren.dao.Wjcd_qclDao;
import io.renren.service.Wjcd_qclService;


@Service("wjzd_qclService")
public class Wjzd_qclServiceImpl implements Wjcd_qclService {
	
	@Autowired
	private Wjcd_qclDao qclDao;
	
	@Override
	public int insert(String a1,
			String a2,
			String a3,
			String a4,
			String a5,
			String a6,
			String a7,
			String a8,
			String a9,
			String a10,
			String a11) {
		int co = qclDao.insert( a1,
				 a2,
				 a3,
				 a4,
				 a5,
				 a6,
				 a7,
				 a8,
				 a9,
				 a10,
				 a11);
		return co;
	}
	
	
}
