package io.renren.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.renren.common.utils.R;
import io.renren.service.Sc_ycclService;
import io.renren.vas.entity.ScEtcptjdEntity;
import lombok.extern.slf4j.Slf4j;


/**
 * 預測車輛
 * 
 * @author pwk
 * @email sunlightcs@gmail.com
 * @date 2018-02-09 15:19:34
 */
@Slf4j
@RestController
@RequestMapping("/generator/sc_yccl")
public class Sc_ycclController {
	
	@Autowired
	private Sc_ycclService sc_ycclService;
	
	@RequestMapping("/mdd")
	public R ban(@RequestBody String st) {
		
		String [] sp=st.split(",");
		log.info("预测车辆：{}", sp[1]);
		String cph=sp[0];
		String atime=sp[1];
		String etime=sp[2];
		int num = Integer.parseInt(sp[3]);
		List<ScEtcptjdEntity> list = sc_ycclService.destination(cph,atime,etime,num);
		
		return R.ok().put("list", list);
	}
	
	@RequestMapping("/show")
	public R show(@RequestBody String cph) {
		
		ScEtcptjdEntity scEtcptjdEntity = sc_ycclService.listObject(cph);
		
		return R.ok().put("data", scEtcptjdEntity);
	}
	
	
}
