package io.renren.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.renren.common.utils.R;
import io.renren.service.KshService;
import io.renren.vo.KshVo;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/keshihua")
public class KshController {
	
	@Autowired
	private KshService service;
	
	//可视化查询
	@RequestMapping("/query")
	public R query(@RequestBody Map<String, Object> params){
		log.info("----------进入------------");
		params.put("weid", Double.parseDouble(params.get("weid")+""));
		params.put("jingd", Double.parseDouble(params.get("jingd")+""));
		params.put("count",Double.parseDouble(params.get("count")+""));
		List<KshVo> list = service.query(params);
		log.info("----------离开------------");
		return R.ok().put("page", list);
	}
}
