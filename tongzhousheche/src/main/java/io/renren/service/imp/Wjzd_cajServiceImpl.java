package io.renren.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.renren.dao.Wjcd_cajDao;
import io.renren.service.Wjcd_cajService;


@Service("wjzd_cajService")
public class Wjzd_cajServiceImpl implements Wjcd_cajService {
	
	@Autowired
	private Wjcd_cajDao cajDao;
	
	@Override
	public int insertcaj(String a1,
			String a2,
			String a3,
			String a4,
			String a5,
			String a6,
			String a7,
			String a8,
			String a9,
			String a10,
			String a11,
			String a12,
			String a13,
			String a14) {
		int co = cajDao.insertcaj( a1,
				 a2,
				 a3,
				 a4,
				 a5,
				 a6,
				 a7,
				 a8,
				 a9,
				 a10,
				 a11,
				 a12,
				 a13,
				 a14);
		return co;
	}
	
	
}
