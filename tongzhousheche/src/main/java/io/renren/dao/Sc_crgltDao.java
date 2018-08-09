package io.renren.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import io.renren.vo.Sc_rcglsVo;

@Mapper
public interface Sc_crgltDao {
	@Select("select  * from sc_rcgl where cp = #{cp}")
	List<Sc_rcglsVo> queryList(@Param("cp") String cp);
	

	
}
