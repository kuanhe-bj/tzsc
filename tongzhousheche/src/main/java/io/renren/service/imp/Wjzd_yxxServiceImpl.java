package io.renren.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.renren.dao.Wjcd_yxxDao;
import io.renren.service.Wjcd_yxxService;


@Service("wjzd_yxxService")
public class Wjzd_yxxServiceImpl implements Wjcd_yxxService {
	
	@Autowired
	private Wjcd_yxxDao yxxDao;
	
	@Override
	public int insertyxx(String a1,
			String a2,
			String a3,
			String a4,
			String a5,
			String a6,
			String a7,
			String a8) {
		int co = yxxDao.insertyxx( a1,
				 a2,
				 a3,
				 a4,
				 a5,
				 a6,
				 a7,
				 a8);
		return co;
	}
	
	
}
