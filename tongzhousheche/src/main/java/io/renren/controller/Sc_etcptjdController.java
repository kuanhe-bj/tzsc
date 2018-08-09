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
import io.renren.service.Sc_etcptjdService;
import io.renren.vas.entity.ScEtcptjdEntity;
import lombok.extern.slf4j.Slf4j;


/**
 * 落脚點分析
 * 
 * @author pwk
 * @email sunlightcs@gmail.com
 * @date 2018-02-09 15:19:34
 */
@Slf4j
@RestController
@RequestMapping("/generator/sc_etcptjd")
public class Sc_etcptjdController {
	@Autowired
	private Sc_etcptjdService sc_etcptjdService;
	
	//查詢
	@RequestMapping("/find")
	public R find(@RequestParam Map<String, Object> params) {
		
		log.info("-------------------查詢功能開始-------------------");

		Query query = new Query(params);
		
		List<ScEtcptjdEntity> list = sc_etcptjdService.findByCPH(query);
		
		int total = sc_etcptjdService.total(query);
		
		PageUtils pageUtil = new PageUtils(list, total, query.getLimit(), query.getPage());
		
		log.info("-------------------查詢功能結束-------------------");
		
		return R.ok().put("page", pageUtil);
	}
	
	//顯示列表数据
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		
		log.info("-------------------顯示列表開始-------------------");
		
		Query query = new Query(params);

		List<ScEtcptjdEntity> scRedlistList = sc_etcptjdService.queryList(query);
		
		int total = sc_etcptjdService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(scRedlistList, total, query.getLimit(), query.getPage());
		
		log.info("-------------------顯示列表結束-------------------");
		
		return R.ok().put("page", pageUtil);
	}
	
	@RequestMapping("/num")
	public R getNum() {
		
		int num = sc_etcptjdService.num();
		
		return R.ok().put("num", num);
	}
}
