package io.renren.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.renren.common.utils.R;
import io.renren.service.MainService;
import io.renren.vo.ScEtcpEntity;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/main")
public class MainController {
	
	@Autowired 
	MainService mainService ;
	
	//主页实时数据
	@RequestMapping("/query")
	public R query(){
		log.info("进入");
		int zfyc = mainService.zfycQuery();
		int tpxy = mainService.tpxyQuery();
		int ymcl = mainService.ymclQuery();
		int scjc = mainService.scjcQuery();
		int tcsl = mainService.tcslQuery();
		int jrrc = mainService.jrrcQuery();
		int jrcc = mainService.jrccQuery();
		int bkzl = mainService.bkzlQuery();
		int jrbk = mainService.jrbkQuery();
		int ljbj = mainService.ljbjQuery();
		int jrbj = mainService.jrbjQuery();
		
		Map<String, Integer> map = new HashMap<>();
		map.put("zfyc", zfyc);
		map.put("tpxy", tpxy);
		map.put("ymcl", ymcl);
		map.put("scjc", scjc);
		map.put("tcsl", tcsl);
		map.put("jrrc", jrrc);
		map.put("jrcc", jrcc);
		map.put("bkzl", bkzl);
		map.put("jrbk", jrbk);
		map.put("ljbj", ljbj);
		map.put("jrbj", jrbj);
		log.info("离开");
		return R.ok().put("page", map);
	}
	
	//实时停车场与卡口数量
	@RequestMapping("/total")
	public R total(){
		int tNum = mainService.tccTotal();
		int kNum = mainService.kakouTotal();
		Map<String, Integer> map = new HashMap<>();
		map.put("tNum", tNum);
		map.put("kNum", kNum);
		return R.ok().put("page", map);
	}
	
	//实时滚动数据
	@RequestMapping("/getInfo")
	public R getInfo(){
		List<ScEtcpEntity> list = mainService.getInfo(); 
		return R.ok().put("page", list);
	}
	
	//政府附近徘徊车辆实时数据
	@RequestMapping("/getCarsph")
	public R getCarInfo(){
		List<ScEtcpEntity> list = mainService.getCarInfo();
		mainService.alarm(list);
		return R.ok().put("page", list);
	}
}
