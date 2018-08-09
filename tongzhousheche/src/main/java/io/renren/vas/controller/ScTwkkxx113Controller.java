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
import io.renren.vas.entity.ScTwkkxx113Entity;
import io.renren.vas.service.ScTwkkxx113Service;


/**
 * 
 * 
 * @author pwk
 * @email sunlightcs@gmail.com
 * @date 2018-02-09 15:19:34
 */
@RestController
@RequestMapping("/generator/sctwkkxx113")
public class ScTwkkxx113Controller {
	@Autowired
	private ScTwkkxx113Service scTwkkxx113Service;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<ScTwkkxx113Entity> scTwkkxx113List = scTwkkxx113Service.queryList(query);
		int total = scTwkkxx113Service.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(scTwkkxx113List, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	@RequestMapping("/find")
	public R find(@RequestParam Map<String, Object> params){
		
        Query query = new Query(params);

		List<ScTwkkxx113Entity> scTwkkxx113List = scTwkkxx113Service.find(query);
		
		int total = scTwkkxx113Service.total(query);
		
		PageUtils pageUtil = new PageUtils(scTwkkxx113List, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	public R info(@PathVariable("id") String id){
		ScTwkkxx113Entity scTwkkxx113 = scTwkkxx113Service.queryObject(id);
		
		return R.ok().put("scTwkkxx113", scTwkkxx113);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public R save(@RequestBody ScTwkkxx113Entity scTwkkxx113){
		
		ScTwkkxx113Entity scTwkkxx113Entity = scTwkkxx113Service.getId();
		
		if(scTwkkxx113Entity == null) {
			scTwkkxx113.setId(1);
		} else {
			int id = scTwkkxx113Entity.getId(); 
			scTwkkxx113.setId(id + 1);
		}
		scTwkkxx113Service.save(scTwkkxx113);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public R update(@RequestBody ScTwkkxx113Entity scTwkkxx113){
		scTwkkxx113Service.update(scTwkkxx113);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public R delete(@RequestBody String[] ids){
		scTwkkxx113Service.deleteBatch(ids);
		
		return R.ok();
	}
	
}
