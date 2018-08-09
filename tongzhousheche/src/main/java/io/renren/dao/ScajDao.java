package io.renren.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import io.renren.vo.ScajVo;

@Mapper
public interface ScajDao {
	@Select("select * from sc_scaj limit ${limit} OFFSET ${offset}")
	List<ScajVo> query(Map<String, Object> params);
	
	@Select("<script>"
			+ "select count(1) from sc_scaj "
			+ "<where>"
			+ "<if test=\' jyaq!=\"\" and jyaq!=null \'>"
			+ " jyaq like '%${jyaq}%'"
			+ "</if>"
			+ "</where>"
			+ "</script>")
    int count(Map<String, Object> params);
	
	@Select("select * from sc_scaj where jyaq like '%${jyaq}%' limit ${limit} OFFSET ${offset}")
	List<ScajVo> queryby(Map<String, Object> params);
}
