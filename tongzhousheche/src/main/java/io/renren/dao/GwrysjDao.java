package io.renren.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import io.renren.vo.GwrysjVo;

@Mapper
public interface GwrysjDao {
	@Select("select * from sc_gwrysj limit ${limit} OFFSET ${offset} ")
	List<GwrysjVo> query(Map<String, Object> params);
	
	@Select("<script>"
			+ "select count(1) from sc_gwrysj "
			+ "<where>"
			+ "<if test=\'i ==0\'>"
			+ " 1 = 2"
			+ "</if>"
			+ "<if test=\'daoqie ==1\'>"
			+ " or daoqie = 1"
			+ "</if>"
			+ "<if test = \'rushi ==1\'>"
			+ " or rushi = 1"
			+ "</if>"
			+ "<if test = \'tongxun ==1\'>"
			+ " or tongxun = 1"
			+ "</if>"
			+ "<if test = \'paqie ==1\'>"
			+ " or paqie = 1"
			+ "</if>"
			+ "</where>"
			+ "</script>")
    int count(Map<String, Object> params);
	
	@Select("<script>"
			+ "select * from sc_gwrysj "
			+ "<where>"
			+ "<if test=\'i ==0\'>"
			+ " 1 = 2"
			+ "</if>"
			+ "<if test=\'daoqie ==1\'>"
			+ " or daoqie = 1"
			+ "</if>"
			+ "<if test =\'rushi ==1\'>"
			+ " or rushi = 1"
			+ "</if>"
			+ "<if test = \'tongxun ==1\'>"
			+ " or tongxun = 1"
			+ "</if>"
			+ "<if test = \'paqie ==1\'>"
			+ " or paqie = 1"
			+ "</if>"
			+ "</where>"
			+ "limit ${limit} OFFSET ${offset}"
			+ "</script>")
	List<GwrysjVo> queryby(Map<String, Object> params);
}
