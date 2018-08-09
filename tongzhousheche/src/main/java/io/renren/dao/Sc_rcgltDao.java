package io.renren.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import io.renren.vo.Sc_rcglsVo;

@Mapper
public interface Sc_rcgltDao {

	@Select("select  * from sc_rcgl where sfz = #{sfz}")
	List<Sc_rcglsVo> querylist(@Param("sfz") String sfz);
}
