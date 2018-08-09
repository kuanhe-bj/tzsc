package io.renren.vas.controller;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.common.utils.R;
import io.renren.vas.entity.ScWsjdcjbxxEntity;
import io.renren.vas.service.ScWsjdcjbxxService;


/**
 * 
 * 
 * @author pwk
 * @email sunlightcs@gmail.com
 * @date 2018-02-09 15:19:34
 */
@RestController
@RequestMapping("/generator/scwsjdcjbxx")
public class ScWsjdcjbxxController {
	@Autowired
	private ScWsjdcjbxxService scWsjdcjbxxService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")

	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<ScWsjdcjbxxEntity> scWsjdcjbxxList = scWsjdcjbxxService.queryList(query);
		int total = scWsjdcjbxxService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(scWsjdcjbxxList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	@RequestMapping("/find")
	public R find(@RequestParam Map<String, Object> params){

		Query query = new Query(params);

		List<ScWsjdcjbxxEntity> scWsjdcjbxxList = scWsjdcjbxxService.find(query);
		
		int total = scWsjdcjbxxService.total(query);
		
		PageUtils pageUtil = new PageUtils(scWsjdcjbxxList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{dataid}")
	
	public R info(@PathVariable("dataid") String dataid){
		ScWsjdcjbxxEntity scWsjdcjbxx = scWsjdcjbxxService.queryObject(dataid);
		
		return R.ok().put("scWsjdcjbxx", scWsjdcjbxx);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")

	public R save(@RequestBody ScWsjdcjbxxEntity scWsjdcjbxx){
		scWsjdcjbxxService.save(scWsjdcjbxx);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	
	public R update(@RequestBody ScWsjdcjbxxEntity scWsjdcjbxx){
		scWsjdcjbxxService.update(scWsjdcjbxx);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")

	public R delete(@RequestBody String[] dataids){
		scWsjdcjbxxService.deleteBatch(dataids);
		
		return R.ok();
	}
	
}
