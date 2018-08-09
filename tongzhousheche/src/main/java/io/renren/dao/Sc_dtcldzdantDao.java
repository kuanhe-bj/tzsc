package io.renren.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import io.renren.vo.DtcldzdansVo;


@Mapper
public interface Sc_dtcldzdantDao {
	@Select("select  * from sc_dtcldzdan where plate = #{plate} ")
	DtcldzdansVo queryList(@Param("plate") String plate);
	

	
}
