package io.renren.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.renren.common.utils.R;
import io.renren.service.EtccService;
import io.renren.vas.entity.ETccEntity;
import io.renren.vo.ScEtcpEntity;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@RestController
@RequestMapping("/etcc")
public class EtccController {
	@Autowired
	private EtccService eservice;
	
	@RequestMapping("/relitu")
	public R etcclist(@RequestBody Map<String, Object> params){
		log.info("进入到EtccController", "热力图");
		log.info("参数:{}",params);
    	List<ETccEntity> list = eservice.queryList(params);
    	log.info("返回结果：{}", list);
		return R.ok().put("list", list);
	}
	
	@RequestMapping("/relitu2")
	public R etcclist2(@RequestBody Map<String, Object> params){
		log.info("进入到EtccController", "热力图");
    	List<ETccEntity> list = eservice.queryList2(params);
    	log.info("返回结果：{}", list);
		return R.ok().put("list", list);
	}
	@RequestMapping("/relitu3")
	public R etcclist3(@RequestBody Map<String, Object> params){
		log.info("进入到EtccController", "热力图");
    	List<ScEtcpEntity> list = eservice.queryList3(params);
    	log.info("返回结果：{}", list);
		return R.ok().put("list", list);
	}
}
