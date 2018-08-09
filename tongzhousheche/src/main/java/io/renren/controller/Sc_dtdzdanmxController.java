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
import io.renren.service.Sc_dtdzdanmxService;
import io.renren.vo.GwrysjVo;
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
@RequestMapping("/generator/sc_dtdzdanmx")
public class Sc_dtdzdanmxController {
	@Autowired
	private Sc_dtdzdanmxService sc_dtdzdanmxService;
	
	/**
	 * 动态电子档案挖掘分析模型
	 */
	@RequestMapping("/find")
	public R checkList(@RequestParam Map<String, Object> params){
		
		log.info("进入到Sc_dtdzdanmxController", "动态电子档案挖掘分析模型");
		
		Query query = new Query(params);
		
		List<GwrysjVo> list = sc_dtdzdanmxService.queryList(query);
		
		int total = sc_dtdzdanmxService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(list, total, query.getLimit(), query.getPage());
		
		log.info("返回结果：{}", pageUtil);
		return R.ok().put("page", pageUtil);
	}
	
	/**
	 * 查询
	 */
	@RequestMapping("/query")
	public R check(@RequestParam Map<String, Object> params){
        Query query = new Query(params);
		List<GwrysjVo> list = sc_dtdzdanmxService.list(query);
		int total = sc_dtdzdanmxService.total(query);
		PageUtils pageUtil = new PageUtils(list, total, query.getLimit(), query.getPage());
		return R.ok().put("page", pageUtil);
	}
	
}
