package io.renren.dao;

import io.renren.vas.entity.ETccEntity;
import io.renren.vo.ScEtcpEntity;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface EtccDao{
	// 数据来 源，1 停车车场，2 路网，3 拥堵点，4 大车流，5 113卡口，6 检查站，7 核录
    //实时查询
    @Select("SELECT e_jingdu jingdu,e_weidu weidu,sum(count) FROM e_tcc where sjly = ${sjly} and querytime BETWEEN '${qssj}' AND '${jssj}' GROUP BY e_jingdu,e_weidu LIMIT 100 OFFSET 0 ")
    List<ETccEntity> queryList(Map<String, Object> map);

    @Select("SELECT e_jingdu jingdu,e_weidu weidu,sum(count) FROM e_tcc where sjly = ${sjly} and querytime BETWEEN '${riqi}' AND '${shijian}' GROUP BY e_jingdu,e_weidu ")
	List<ETccEntity> queryList2(Map<String, Object> params);
    
    @Select("SELECT jingdu,weidu,count(1) FROM sc_etcptjd where sjly = #{sjly} and enter_time BETWEEN '${qssj}' AND '${jssj}' GROUP BY jingdu,weidu ")
	List<ScEtcpEntity> queryList3(Map<String, Object> params);
}