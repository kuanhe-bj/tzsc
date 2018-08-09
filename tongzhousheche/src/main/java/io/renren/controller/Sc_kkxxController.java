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
import io.renren.service.Sc_alarminfoService;
import io.renren.service.Sc_kkxxService;
import io.renren.vas.entity.ScAlarminfoEntity;
import io.renren.vo.KakouRecordVo;
import io.renren.vo.KakoubsVo;
import lombok.extern.slf4j.Slf4j;


/**
 * 卡口伴随信息
 * 
 * @author pwk
 * @email sunlightcs@gmail.com
 * @date 2018-02-09 15:19:34
 */
@Slf4j
@RestController
@RequestMapping("/generator/sc_kkxx")
public class Sc_kkxxController {
	
	@Autowired
	private Sc_kkxxService sc_kkxxService;
	
	@Autowired
	private Sc_alarminfoService sc_alarminfoService;
	
	//查詢
	@RequestMapping("/find")
	public R find(@RequestParam Map<String, Object> params) {
		
		log.info("-------------------查詢功能開始-------------------");
		
		Query query = new Query(params);
		
		List<KakouRecordVo> list = sc_kkxxService.findKkxx(query);
		
		int total = sc_kkxxService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(list, total, query.getLimit(), query.getPage());
		
		log.info("-------------------查詢功能開始-------------------");
		
		return R.ok().put("page", pageUtil);
	}
	
	//卡口信息數據交互
	@RequestMapping("/list")
	public R list(@RequestBody KakouRecordVo vo) {
		
		String kid = vo.getDistrictNmId();
		
		String start = vo.getStartTime();
		
		String end = vo.getEndTime();
		
		List<KakouRecordVo> list = sc_kkxxService.find(kid, start, end);
		
		return R.ok().put("data", list);
	}
	
	//查詢伴隨次數
	@RequestMapping("/bansui")
	public R bansui(@RequestParam Map<String, Object> params) {
		log.info("-------------------伴随车辆分析-------------------");
		Query query = new Query(params);
		log.info("参数：{}",query);
		List<KakouRecordVo> list = sc_kkxxService.checkKkxx(query);
		if (query.getPage()==1) {
			if (list!=null&&list.size()!=0) {
				for (int i = 0; i < list.size(); i++) {
					if(list.get(i).getNum()==list.get(0).getNum()){
						list.get(i).setDistrictNmId("1");
					};
				}
			}
		}
		int total = sc_kkxxService.total(query);
		PageUtils pageUtil = new PageUtils(list, total, query.getLimit(), query.getPage());
		return R.ok().put("page", pageUtil);
	}
	
	//顯示伴隨詳細信息
	@RequestMapping("/bs")
	public R bs(@RequestParam Map<String, Object> params) {
		
		log.info("-------------------伴隨詳細信息開始-------------------");
		
		Query query = new Query(params);
		
		List<KakoubsVo> list = sc_kkxxService.kkbs(query);
		
		int total = sc_kkxxService.getCount(query);
		
		PageUtils pageUtil = new PageUtils(list, total, query.getLimit(), query.getPage());
		
		log.info("-------------------伴隨詳細信息結束-------------------");
		
		return R.ok().put("page", pageUtil);
	}
	
	//伴随详细信息数据交互
	@RequestMapping("/ban")
	public R ban(@RequestBody KakoubsVo vo) {
		String cph1 = vo.getCph1();
		String cph2 = vo.getCph2();
		int time = vo.getTime();
		List<KakoubsVo> list = sc_kkxxService.checkKkbs(cph1, cph2, time);
		return R.ok().put("data", list);
	}
	
	@RequestMapping("/alarm")
	public R save(@RequestBody ScAlarminfoEntity alarminfoVo){
		
		String carnum = alarminfoVo.getCarnum();
		
		alarminfoVo.setMessage(carnum + "是伴随车辆！"); 
		
		sc_alarminfoService.insert(alarminfoVo);
		
		return R.ok();
	}
	
	
}
