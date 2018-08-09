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
import io.renren.service.SensitivePointService;
import io.renren.vas.entity.SensitivepointEntity;




/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-04-25 14:37:56
 */
@RestController
@RequestMapping("/generator/sensitivePoint")
public class SensitivePointController {
	
	@Autowired
	private SensitivePointService sensitivePointService;
	
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		
        Query query = new Query(params);

		List<SensitivepointEntity> sensitivepointList = sensitivePointService.queryList(query);
		
		int total = sensitivePointService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(sensitivepointList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	@RequestMapping("/find")
	public R find(@RequestParam Map<String, Object> params){
		
		String jingdu = (String) params.get("jingdu");
		
		String weidu = (String) params.get("weidu");
		
		params.put("center", (jingdu + " " + weidu));
		
        Query query = new Query(params);

		List<SensitivepointEntity> sensitivepointList = sensitivePointService.list(query);
		
		int total = sensitivePointService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(sensitivepointList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
}
