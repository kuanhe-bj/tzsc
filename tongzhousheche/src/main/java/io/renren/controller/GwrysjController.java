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
import io.renren.service.GwrysjService;
import io.renren.vo.GwrysjVo;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/gwrysj")
public class GwrysjController {
	
	@Autowired
	private GwrysjService service;
	
	/**
	 * 情报专题查询
	 */
	@RequestMapping("/query")
	public R query( @RequestParam Map<String, Object> params){
		log.info("---查询开始---");
		Integer i = 0;
		//查询列表数据
		Query query = new Query(params);
		List<GwrysjVo> list = new ArrayList<>();
		String str = params.get("param")+"";
		if(!str.equals("null") && !"".equals(str)){
			if(str.indexOf("盗窃")!=-1){
				i = 1;
				Integer daoqie = 1;
				query.put("daoqie", daoqie);		
			}
			if(str.indexOf("入室")!=-1){
				i = 1;
				Integer rushi = 1;
				query.put("rushi", rushi);
			}
			if(str.indexOf("通讯")!=-1){
				i = 1;
				Integer tongxun = 1;
				query.put("tongxun", tongxun);
			}
			if(str.indexOf("扒窃")!=-1){
				i = 1;
				Integer paqie = 1;
				query.put("paqie", paqie);
			}			
			query.put("i", i);
			list = service.queryby(query);
		}else{
			list = service.query(query);
		}		
		i = 0;
		int total = service.count(query);
		PageUtils pageUtil = new PageUtils(list,total, query.getLimit(), query.getPage());
		log.info("---查询结束---");
		return R.ok().put("page", pageUtil);
	}
}
