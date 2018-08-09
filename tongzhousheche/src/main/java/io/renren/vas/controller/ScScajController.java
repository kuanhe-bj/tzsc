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
import io.renren.vas.entity.ScScajEntity;
import io.renren.vas.service.ScScajService;


/**
 * 
 * 
 * @author pwk
 * @email sunlightcs@gmail.com
 * @date 2018-02-09 15:19:34
 */
@RestController
@RequestMapping("/generator/scscaj")
public class ScScajController {
	@Autowired
	private ScScajService scScajService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<ScScajEntity> scScajList = scScajService.queryList(query);
		int total = scScajService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(scScajList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	@RequestMapping("/find")
	public R find(@RequestParam Map<String, Object> params){
        Query query = new Query(params);
        

		List<ScScajEntity> scScajList = scScajService.find(query);
		int total = scScajService.total(query);
		
		
		PageUtils pageUtil = new PageUtils(scScajList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	public R info(@PathVariable("id") String id){
		ScScajEntity scScaj = scScajService.queryObject(id);
		
		return R.ok().put("scScaj", scScaj);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public R save(@RequestBody ScScajEntity scScaj){
		scScaj.setId(UUID.randomUUID().toString());
		scScajService.save(scScaj);
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public R update(@RequestBody ScScajEntity scScaj){
		scScajService.update(scScaj);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public R delete(@RequestBody String[] ids){
		scScajService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
