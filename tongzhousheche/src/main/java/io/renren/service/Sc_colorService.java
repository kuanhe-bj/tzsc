package io.renren.service;


import java.util.List;

import io.renren.vo.Brand;
import io.renren.vo.Color;
import io.renren.vo.Model;

/**
 * 
 * 
 * @author pwk
 * @email sunlightcs@gmail.com
 * @date 2018-02-11 13:39:58
 */
public interface Sc_colorService {
	
	List<Color> getColor();
	
	List<Brand> getBrand();
	
	List<Model> getModel();
	
}
