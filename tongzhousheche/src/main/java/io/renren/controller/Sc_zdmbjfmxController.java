package io.renren.controller;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.common.utils.R;
import io.renren.service.Sc_zdmbjfmxService;
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
@Component
@RequestMapping("/generator/sc_zdmbjfmx")
public class Sc_zdmbjfmxController {
	
	@Autowired
	private Sc_zdmbjfmxService sc_zdmbjfmxService;
	
	/**
	 * 遮挡面部积分模型
	 */
	@RequestMapping("/find")
	public R checkList(@RequestParam Map<String, Object> params){
		//查询列表数据
		log.info("进入到Sc_zdmbjfmxController", "遮挡面部积分模型");
        Query query = new Query(params);
		List<DtcldzdansVo> list = sc_zdmbjfmxService.queryList(query);
		int total = sc_zdmbjfmxService.queryTotal(query);
		PageUtils pageUtil = new PageUtils(list, total, query.getLimit(), query.getPage());
		log.info("返回结果：{}", pageUtil);
		return R.ok().put("page", pageUtil);
	}
	
	//实现dan表face数据初始化设置为0
	public int dan_cs(){
		log.info("实现dan表face数据初始化设置为0");
		return sc_zdmbjfmxService.dan_cs();
		
	}
	
	//实现dan表face数据初始化设置为1
	public int dan_sz(){
		log.info("实现dan表face数据初始化设置为1");
		return sc_zdmbjfmxService.dan_sz();
		
	}
	

}
