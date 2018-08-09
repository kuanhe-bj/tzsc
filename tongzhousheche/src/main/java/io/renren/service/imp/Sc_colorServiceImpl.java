package io.renren.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.renren.dao.Sc_colorDao;
import io.renren.service.Sc_colorService;
import io.renren.vo.Brand;
import io.renren.vo.Color;
import io.renren.vo.Model;


/**
 * 车辆颜色，品牌，类型关联
 * @author moulin
 *
 */
@Service("sc_colorService")
public class Sc_colorServiceImpl implements Sc_colorService {
	
	@Autowired
	private Sc_colorDao sc_colorDao;

	@Override
	public List<Color> getColor() {
		return sc_colorDao.getColor();
	}

	@Override
	public List<Brand> getBrand() {
		return sc_colorDao.getBrand();
	}

	@Override
	public List<Model> getModel() {
		return sc_colorDao.getModel();
	}

}
