package io.renren.dao;

import io.renren.modules.sys.dao.BaseDao;
import io.renren.vas.entity.ScKkxxEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface ScDao extends BaseDao<ScKkxxEntity> {

    @Select("select * from sc_kkxx where id=#{id}")
    List<ScKkxxEntity> findById(String id);

    @Select("select * from sc_kkxx where mc=#{mc} limit #{page},#{limit}")
    List<ScKkxxEntity> findList(Map map);

}
