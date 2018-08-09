package io.renren.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import io.renren.vo.RelituShiShiVo;

@Mapper
public interface RelituShiShiDao {
	
	@Select("select * from sc_rltrw where valid != 99 limit ${limit} OFFSET ${offset}")
	List<RelituShiShiVo> queryAll(Map<String, Object> query);
	
	@Select("select * from sc_rltrw where mapname=#{mapname} and valid != 99 limit ${limit} OFFSET ${offset}")
	List<RelituShiShiVo> queryByName();

	@Select("select count(1) from sc_rltrw where valid != 99")
	int count(Map<String, Object> query);
	
	@Select("select * from sc_rltrw where id = #{id}")
	RelituShiShiVo queryById(String id);

	@Insert("insert into sc_rltrw (id,username,mapname,mapsql,refreshtime,createtime,valid)"
			+ "values(#{id},#{username},#{mapname},#{mapsql},#{refreshtime},#{createtime},#{valid})")
	void save(RelituShiShiVo relitu);

	@Update("<script>"
			+ " update sc_rltrw "
			+ " <set> "
			+ " <if test=\'username != \"\" and username!= null \'>"
			+ " username=#{username}"
			+ " </if>"
			+ " <if test=\'mapname != \"\" and mapname!= null \'>"
			+ " , mapname=#{mapname}"
			+ " </if>"
			+ " <if test=\'mapsql != \"\" and mapsql!= null \'>"
			+ " , mapsql=#{mapsql}"
			+ " </if>"
			+ " <if test=\'refreshtime != \"\" and refreshtime!= null \'>"
			+ " , refreshtime=${refreshtime}"
			+ " </if>"
			+ " </set>"
			+ " where id = #{id}"
			+ "</script>")
	void update(RelituShiShiVo relitu);

	@Delete("delete from sc_rltrw where id in(#{ids})")
	void deleteBatch(String ids);
}
