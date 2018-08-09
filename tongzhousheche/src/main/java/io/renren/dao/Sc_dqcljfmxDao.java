package io.renren.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import io.renren.vas.entity.ScBdqclEntity;
import io.renren.vo.GwrysjVo;
@Mapper
public interface Sc_dqcljfmxDao {
	 @Select("select count(1) from sc_gwrysj")
	 int queryTotal();
	 
	 @Select("select * from sc_gwrysj order by id LIMIT 1000 OFFSET ${j}")
	 List<GwrysjVo> queryList(@Param("j") int j);
	 
	 @Select("SELECT cph from sc_bdqcl WHERE cph = #{plate}")
	 List<ScBdqclEntity> getPlate(@Param("plate") String plate);
	 
	 @Update("UPDATE SC_dtcldzdan SET robbery =99 WHERE plate = #{plate} ")
	 int update(@Param("plate") String plate);
	 
	
	 
	 
}
