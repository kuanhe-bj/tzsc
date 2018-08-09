package io.renren.vas.controller;


import java.util.List;
import java.util.Map;

import io.renren.vas.entity.SensitivepointEntity;
import io.renren.vas.service.SensitivepointService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.common.utils.R;




/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-04-25 15:57:42
 */
@RestController
@RequestMapping("/generator/sensitivepoint")
public class SensitivepointController {
	@Autowired
	private SensitivepointService sensitivepointService;
	
	@RequestMapping("/list")
	@RequiresPermissions("generator:sensitivepoint:list")
	public R list(@RequestParam Map<String, Object> params){
		
        Query query = new Query(params);

		List<SensitivepointEntity> sensitivepointList = sensitivepointService.queryList(query);
		
		int total = sensitivepointService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(sensitivepointList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	//@RequiresPermissions("generator:sensitivepoint:info")
	public R info(@PathVariable("id") Integer id){
		SensitivepointEntity sensitivepoint = sensitivepointService.queryObject(id);
		
		return R.ok().put("sensitivepoint", sensitivepoint);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public R save(@RequestBody SensitivepointEntity sensitivepoint){
		sensitivepointService.save(sensitivepoint);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public R update(@RequestBody SensitivepointEntity sensitivepoint){
		sensitivepointService.update(sensitivepoint);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public R delete(@RequestBody Integer[] ids){
		sensitivepointService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
