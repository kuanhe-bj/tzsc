package io.renren.service;


import java.util.List;
import java.util.Map;


import io.renren.vas.entity.ScAlarminfoEntity;
import io.renren.vas.entity.ScDtcldzdanEntity;
import io.renren.vas.entity.ScEtcptjdEntity;
import io.renren.vas.entity.ScGwrysjEntity;
import io.renren.vas.entity.ScScajEntity;
import io.renren.vas.entity.ZfssEntity;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-03-22 10:55:46
 */
public interface ZfService {
	
	 List<ScDtcldzdanEntity> pl(Map map);
	 
	 List<ScDtcldzdanEntity> al(Map map);
	 
	 int queryTotal(Map map);
	 
     int total(Map map);
     
	 List<ScDtcldzdanEntity> baojing();
	 
	 List<ScAlarminfoEntity> bd(String message);
	 
	 int info(String receiver,String message);
	 
	 int update();
	 
	 List<ScDtcldzdanEntity> etcp_list();
	 
	 List<ScEtcptjdEntity> etcp();
	 
	 int cr_n( float n,String plate);
	 
	 List<ScEtcptjdEntity> ss_c(String fxc,String fxd,
	    		String btc,String btd,
	    		int btcxc,int btcxd);

	
	   List<ScEtcptjdEntity> etcp_all(String time);
	   
	  
	   int etcp_count(String time);

	  
	   double etcp_numb(String time);
	   
	   List<ScEtcptjdEntity> ss_all(Map map);
	   //分页TOTAL
	   int ss_total(); 
	   //实时功能查询插入
	   int insert(String plate,String num,
      		 String count,String exit_time,
      		 String  uu_id);
	   //实时功能显示
	   List<ZfssEntity> select(Map map);
	   //实时数据删除
	   void delete();
	   //实时查询得到所有车牌号
	   List<String> plate();
	   //实时功能查询车辆
	   List<ScEtcptjdEntity>   all_cardt(String plate,double jingdu,double weidu,int count);

	   List<ScEtcptjdEntity>  card(Map<String, Object> map);
	   
	   List<ScEtcptjdEntity>  zdrsj(String plate);
	   
	   ScDtcldzdanEntity  dan(String plate);
	   
	   List<ScEtcptjdEntity> wd(String plate);
	   
	   ScScajEntity sa(String plate);
	   
	   ScGwrysjEntity gw(String plate);
	   
	   List<ScDtcldzdanEntity> cxyc(String plate);
	   
	   List<Integer> getCount();
	   
	   void zfyc();
}
