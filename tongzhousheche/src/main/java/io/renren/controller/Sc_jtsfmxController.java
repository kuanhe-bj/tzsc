package io.renren.controller;


import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.common.utils.R;
import io.renren.service.Sc_alarminfoService;
import io.renren.service.Sc_jtsfmxService;
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
@RequestMapping("/generator/sc_jtsfmx")
public class Sc_jtsfmxController {
	
	@Autowired
	private Sc_jtsfmxService sc_jtsfmxService;
	
	@Autowired
	private Sc_alarminfoService sc_alarminfoService;
	
	/**
	 * 集体上访挖掘分析模型
	 */
	@RequestMapping("/find")
	public R checkList(@RequestParam Map<String, Object> params,HttpServletRequest request){
		//查询列表数据
		log.info("进入到Sc_jtsfmxController", "集体上访挖掘分析模型");
		String shangfang = (String)params.get("shangfang");
		String jingshenbing = (String)params.get("jingshenbing");
		if(shangfang.equals("true")) {
			params.put("shangfang", 1);
		} else {
			params.put("shangfang", 0);
		}

		if(jingshenbing.equals("true")) {
			params.put("jingshenbing", 1);
		} else {
			params.put("jingshenbing", 0);
		}
        Query query = new Query(params);
		List<GwrysjVo> list = sc_jtsfmxService.queryList(query);
		int total = sc_jtsfmxService.total(query);
		PageUtils pageUtil = new PageUtils(list, total, query.getLimit(), query.getPage());
		log.info("返回结果：{}", pageUtil);
		return R.ok().put("page", pageUtil);
	}
	
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
		log.info("进入到Sc_jtsfmxController", "集体上访挖掘分析模型");
        Query query = new Query(params);
		List<GwrysjVo> list = sc_jtsfmxService.list(query);
		int total = sc_jtsfmxService.queryTotal(query);
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
		alarminfoVo.setMessage(carnum + "是集体上访车辆"); 
		
		sc_alarminfoService.insert(alarminfoVo);
		
		return R.ok();
	}
	
}
