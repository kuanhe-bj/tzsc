package io.renren.controller;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.common.utils.R;
import io.renren.service.Sc_wpcljfmxService;
import io.renren.vo.DtcldzdansVo;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * 
 * @author pwk
 * @email sunlightcs@gmail.com
 * @date 2018-02-26 11:56:26
 */
@Slf4j
@RestController
@RequestMapping("/generator/sc_wpcljfmx")
public class Sc_wpcljfmxController {
	@Autowired
	private Sc_wpcljfmxService sc_wpcljfmxService;
	
	/**
	 * 无牌车辆积分模型
	 */
	@RequestMapping("/find")
	public R checkList(@RequestParam Map<String, Object> params){
		//查询列表数据
		log.info("进入到Sc_wpcljfmxController", "无牌车辆积分模型");
        Query query = new Query(params);
		List<DtcldzdansVo> list = sc_wpcljfmxService.queryList(query);
		int total = sc_wpcljfmxService.queryTotal(query);
		PageUtils pageUtil = new PageUtils(list, total, query.getLimit(), query.getPage());
		log.info("返回结果：{}", pageUtil);
		return R.ok().put("page", pageUtil);
	}
	
	//设置noPlate=0
   public int wcp_cs(){
	   log.info("设置noPlate=0");
	   return sc_wpcljfmxService.wcp_cs();
   }
	
 //设置noPlate=1
   public int wcp_sz(){
	   log.info("设置noPlate=1");
	   return sc_wpcljfmxService.wcp_sz();
   }
   
}
