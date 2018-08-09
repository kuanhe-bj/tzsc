package io.renren.vas.controller;


import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.common.utils.R;
import io.renren.vas.entity.ScQgztryxxEntity;
import io.renren.vas.service.ScQgztryxxService;


/**
 * 
 * 
 * @author pwk
 * @email sunlightcs@gmail.com
 * @date 2018-02-09 15:19:33
 */
@RestController
@RequestMapping("/generator/scqgztryxx")
public class ScQgztryxxController {
	@Autowired
	private ScQgztryxxService scQgztryxxService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")

	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<ScQgztryxxEntity> scQgztryxxList = scQgztryxxService.queryList(query);
		int total = scQgztryxxService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(scQgztryxxList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	@RequestMapping("/find")
	public R find(@RequestParam Map<String, Object> params){
		
        Query query = new Query(params);

		List<ScQgztryxxEntity> scQgztryxxList = scQgztryxxService.find(query);
		
		int total = scQgztryxxService.total(query);
		
		PageUtils pageUtil = new PageUtils(scQgztryxxList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{rybh}")

	public R info(@PathVariable("rybh") String rybh){
		ScQgztryxxEntity scQgztryxx = scQgztryxxService.queryObject(rybh);
		
		return R.ok().put("scQgztryxx", scQgztryxx);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public R save(@RequestBody ScQgztryxxEntity scQgztryxx){
		
		String a = UUID.randomUUID().toString();
		scQgztryxx.setRybh(a);
		scQgztryxxService.save(scQgztryxx);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	
	public R update(@RequestBody ScQgztryxxEntity scQgztryxx){
		scQgztryxxService.update(scQgztryxx);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")

	public R delete(@RequestBody String[] rybhs){
		scQgztryxxService.deleteBatch(rybhs);
		
		return R.ok();
	}
	
}
