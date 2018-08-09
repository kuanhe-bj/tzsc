package io.renren.controller;


import java.util.Date;
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
import io.renren.service.Sc_alarminfoService;
import io.renren.service.Sc_yidaoyangxiService;
import io.renren.vas.entity.ScAlarminfoEntity;
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
@RequestMapping("/generator/sc_yidaoyangxi")
public class Sc_yidaoyangxiController {
	
	@Autowired
	private Sc_yidaoyangxiService sc_yidaoyangxiService;
	
	@Autowired
	private Sc_alarminfoService sc_alarminfoService;
	
	@RequestMapping("/find")
	public R checkList(@RequestParam Map<String, Object> params){
		//查询列表数据
		log.info("进入到Sc_yidaoyangxiController", "以盗养吸");
		String qincai = (String) params.get("qincai");
		String shedu = (String) params.get("shedu");
		String yidaoyangxi = (String) params.get("yidaoyangxi");
		if(qincai.equals("true")) {
			params.put("qincai", 1);
		} else {
			params.put("qincai", 0);
		}

		if(shedu.equals("true")) {
			params.put("shedu", 1);
		} else {
			params.put("shedu", 0);
		}
		if(yidaoyangxi.equals("true")) {
			params.put("yidaoyangxi", 1);
		} else {
			params.put("yidaoyangxi", 0);
		}
		Query query = new Query(params);
		List<GwrysjVo> list = sc_yidaoyangxiService.queryList(query);
		int total = sc_yidaoyangxiService.total(query);
		PageUtils pageUtil = new PageUtils(list, total, query.getLimit(), query.getPage());
		log.info("返回结果：{}", pageUtil);
		return R.ok().put("page", pageUtil);
		
	}
	
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
		log.info("进入到Sc_yidaoyangxiController", "以盗养吸");
        Query query = new Query(params);
		List<GwrysjVo> list = sc_yidaoyangxiService.list(query);
		int total = sc_yidaoyangxiService.queryTotal(query);
		PageUtils pageUtil = new PageUtils(list, total, query.getLimit(), query.getPage());
		log.info("返回结果：{}", pageUtil);
		return R.ok().put("page", pageUtil);
	}
	
	@RequestMapping("/alarm")
	public R save(@RequestBody ScAlarminfoEntity alarminfoVo){
		
		String carnum = alarminfoVo.getCarnum();
		
		alarminfoVo.setTriggertime(new Date());
		
		alarminfoVo.setReadstate(0);
		
		alarminfoVo.setPushstate(1);
		
		alarminfoVo.setMessage(carnum + "是以盗养吸车辆"); 
		
		sc_alarminfoService.insert(alarminfoVo);
		
		return R.ok();
	}
	

	
}
