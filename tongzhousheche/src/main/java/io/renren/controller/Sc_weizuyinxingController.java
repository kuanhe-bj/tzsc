package io.renren.controller;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.common.utils.R;
import io.renren.service.Sc_weizuyinxingService;
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
@RequestMapping("/generator/sc_weizuyinxing")
public class Sc_weizuyinxingController {
	
	@Autowired
	private Sc_weizuyinxingService sc_weizuyinxingService;
	
	
	/**
	 * 维族隐形重点人
	 */
	@RequestMapping("/find")
	public R checkList(@RequestParam Map<String, Object> params){
		//查询列表数据
		log.info("进入到Sc_weizuyinxingController", "维族隐形重点人");
        Query query = new Query(params);
		List<GwrysjVo> list = sc_weizuyinxingService.queryList(query);
		//System.out.println(list);
		int total = sc_weizuyinxingService.queryTotal(query);
		PageUtils pageUtil = new PageUtils(list, total, query.getLimit(), query.getPage());
		log.info("返回结果：{}", pageUtil);
		return R.ok().put("page", pageUtil);
	}
	
	@RequestMapping("/update")
	public R list(@RequestBody String cp) {
		
		sc_weizuyinxingService.update(cp);
		
		return R.ok().put("num", 0);
	}
	
	@RequestMapping("/query")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<GwrysjVo> list = sc_weizuyinxingService.list(query);
		int total = sc_weizuyinxingService.total(query);
		PageUtils pageUtil = new PageUtils(list, total, query.getLimit(), query.getPage());
		return R.ok().put("page", pageUtil);
	}
	
}
