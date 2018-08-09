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
import io.renren.vas.entity.ScKkjbxx113Entity;
import io.renren.vas.service.ScKkjbxx113Service;


/**
 * 
 * 
 * @author pwk
 * @email sunlightcs@gmail.com
 * @date 2018-02-09 15:19:34
 */
@RestController
@RequestMapping("/generator/sckkjbxx113")
public class ScKkjbxx113Controller {
	@Autowired
	private ScKkjbxx113Service scKkjbxx113Service;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<ScKkjbxx113Entity> scKkjbxx113List = scKkjbxx113Service.queryList(query);
		int total = scKkjbxx113Service.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(scKkjbxx113List, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	@RequestMapping("/find")
	public R find(@RequestParam Map<String, Object> params){

		Query query = new Query(params);

		List<ScKkjbxx113Entity> scKkjbxx113List = scKkjbxx113Service.find(query);
		
		int total = scKkjbxx113Service.total(query);
		
		PageUtils pageUtil = new PageUtils(scKkjbxx113List, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	public R info(@PathVariable("id") String id){
		ScKkjbxx113Entity scKkjbxx113 = scKkjbxx113Service.queryObject(id);
		
		return R.ok().put("scKkjbxx113", scKkjbxx113);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public R save(@RequestBody ScKkjbxx113Entity scKkjbxx113){
		
		ScKkjbxx113Entity scKkjbxx113Entity = scKkjbxx113Service.getIdMax();
		
		if(scKkjbxx113Entity == null) {
			scKkjbxx113.setId(1);
		} else {
			int id = scKkjbxx113Entity.getId(); 
			scKkjbxx113.setId(id + 1);
		}
		
		scKkjbxx113Service.save(scKkjbxx113);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public R update(@RequestBody ScKkjbxx113Entity scKkjbxx113){
		scKkjbxx113Service.update(scKkjbxx113);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public R delete(@RequestBody String[] ids){
		scKkjbxx113Service.deleteBatch(ids);
		
		return R.ok();
	}
	
}
