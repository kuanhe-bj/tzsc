package io.renren.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import io.renren.vo.DtcldzdansVo;

@Mapper
public interface Sc_zhyczsDao {
	@Select("select count(id) from sc_dtcldzdan")
	int queryTotal();
	
	@Select("select * from sc_dtcldzdan LIMIT 1000 offset #{count} ")
	List<DtcldzdansVo> querylist(int count);
	
	@Update("UPDATE SC_dtcldzdan SET summary =#{summary} WHERE plate = #{plate}")
	 int update(@Param("summary") Double summary,@Param("plate") String plate);
}
