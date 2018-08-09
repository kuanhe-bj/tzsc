package io.renren.dao;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

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
@Mapper
public interface Sc_colorDao {
	
	@Select("SELECT * FROM sc_color")
	List<Color> getColor();
	
	@Select("SELECT * FROM sc_brand group by id,value")
	List<Brand> getBrand();
	
	@Select("SELECT * FROM sc_model ")
	List<Model> getModel();
	
}
