package io.renren.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.common.utils.R;
import io.renren.service.ScajService;
import io.renren.vo.ScajVo;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/scaj")
public class ScajController {
	@Autowired
	private ScajService service;
	@RequestMapping("/query")
	public R list(@RequestParam Map<String, Object> params){
		log.info("---进入---");
		String jyaq = params.get("param")+"";
		Query query = new Query(params);
		List<ScajVo> list = new ArrayList<>();
		if(!jyaq.equals("null") && !"".equals(jyaq)){
			query.put("jyaq", jyaq);		
			list = service.queryby(query);
		}else{
			list = service.query(query);
		}
		int total = service.count(query);
		PageUtils pageUtil = new PageUtils(list,total, query.getLimit(), query.getPage());
		log.info("---离开---");
		return R.ok().put("page", pageUtil);
	}
}
