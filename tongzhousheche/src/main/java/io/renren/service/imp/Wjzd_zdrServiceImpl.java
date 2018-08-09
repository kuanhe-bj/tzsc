package io.renren.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.renren.dao.Wjcd_zdrDao;
import io.renren.service.Wjcd_zdrService;


@Service("wjzd_zdrService")
public class Wjzd_zdrServiceImpl implements Wjcd_zdrService {
	
	@Autowired
	private Wjcd_zdrDao zdrDao;
	
	@Override
	public int insertzdr(String a1,
			String a2,
			String a3,
			String a4,
			String a5,
			String a6,
			String a7,
			String a8,
			String a9) {
		int co = zdrDao.insertzdr( a1,
				 a2,
				 a3,
				 a4,
				 a5,
				 a6,
				 a7,
				 a8,
				 a9);
		return co;
	}
	
	
}
