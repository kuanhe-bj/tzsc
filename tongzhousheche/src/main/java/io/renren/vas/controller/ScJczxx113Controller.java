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
import io.renren.vas.entity.ScJczxx113Entity;
import io.renren.vas.service.ScJczxx113Service;


/**
 * 
 * 
 * @author pwk
 * @email sunlightcs@gmail.com
 * @date 2018-02-09 15:19:34
 */
@RestController
@RequestMapping("/generator/scjczxx113")
public class ScJczxx113Controller {
	@Autowired
	private ScJczxx113Service scJczxx113Service;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<ScJczxx113Entity> scJczxx113List = scJczxx113Service.queryList(query);
		int total = scJczxx113Service.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(scJczxx113List, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	@RequestMapping("/find")
	public R find(@RequestParam Map<String, Object> params){
		
        Query query = new Query(params);

		List<ScJczxx113Entity> scJczxx113List = scJczxx113Service.find(query);
		
		int total = scJczxx113Service.total(query);
		
		PageUtils pageUtil = new PageUtils(scJczxx113List, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	public R info(@PathVariable("id") String id){
		ScJczxx113Entity scJczxx113 = scJczxx113Service.queryObject(id);
		
		return R.ok().put("scJczxx113", scJczxx113);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public R save(@RequestBody ScJczxx113Entity scJczxx113){
		
		ScJczxx113Entity scJczxx113Entity = scJczxx113Service.getId();
		
		if(scJczxx113Entity == null) {
			scJczxx113.setId(1);
		} else {
			int id = scJczxx113Entity.getId(); 
			scJczxx113.setId(id + 1);
		}
		
		scJczxx113Service.save(scJczxx113);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public R update(@RequestBody ScJczxx113Entity scJczxx113){
		scJczxx113Service.update(scJczxx113);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public R delete(@RequestBody String[] ids){
		scJczxx113Service.deleteBatch(ids);
		
		return R.ok();
	}
	
}
