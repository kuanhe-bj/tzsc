package io.renren.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import io.renren.modules.sys.dao.BaseDao;
import io.renren.vas.entity.ScDtcldzdanEntity;
import io.renren.vas.entity.ScEtcptjdEntity;
import io.renren.vas.entity.ScJczxx113Entity;

@Mapper
public interface TccDao extends BaseDao<ScDtcldzdanEntity> {

    @Select("select *,count(eid) as count from sc_etcptjd where DISTRICT_NM_ID=#{districtNmId} or PARK_NM=#{parkNm}")
    List<ScEtcptjdEntity> xq(Map<String, Object> map);
   
    @Select("select * from sc_kkjbxx113 where CPH=#{cph}")
    List<ScJczxx113Entity> xq2(Map<String, Object> map);
}
