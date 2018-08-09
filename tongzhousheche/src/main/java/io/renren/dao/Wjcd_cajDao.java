package io.renren.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * 
 * @author pwk
 * @email sunlightcs@gmail.com
 * @date 2018-02-09 15:19:34
 */
@Mapper
public interface Wjcd_cajDao  {
	@Insert("insert into sc_scaj values ( ${a1},${a2}, ${a3},"
			+ " ${a4},${a5}, ${a6}, ${a7}, ${a8},${a9},${a10},${a11},"
			+ "${a12}, ST_GeographyFromText('SRID=4326;POINT(${a13} ${a14})'))")
		int insertcaj(@Param("a1") String a1,
				@Param("a2") String a2,
				@Param("a3") String a3,
				@Param("a4") String a4,
				@Param("a5") String a5,
				@Param("a6") String a6,
				@Param("a7") String a7,
				@Param("a8") String a8,
				@Param("a9") String a9,
				@Param("a10") String a10,
				@Param("a11") String a11,
				@Param("a12") String a12,
				@Param("a13") String a13,
				@Param("a14") String a14
				);
}