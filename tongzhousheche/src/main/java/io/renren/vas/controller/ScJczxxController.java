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
import io.renren.vas.entity.ScJczxxEntity;
import io.renren.vas.service.ScJczxxService;


/**
 * 
 * 
 * @author pwk
 * @email sunlightcs@gmail.com
 * @date 2018-02-09 15:19:34
 */
@RestController
@RequestMapping("/generator/scjczxx")
public class ScJczxxController {
	@Autowired
	private ScJczxxService scJczxxService;
	
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<ScJczxxEntity> scJczxxList = scJczxxService.queryList(query);
		int total = scJczxxService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(scJczxxList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	//查询
	@RequestMapping("/cx")
	public R cx(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<ScJczxxEntity> scJczxxList = scJczxxService.cx(query);
		int total = scJczxxService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(scJczxxList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	public R info(@PathVariable("id") String id){
		ScJczxxEntity scJczxx = scJczxxService.queryObject(id);
		
		return R.ok().put("scJczxx", scJczxx);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public R save(@RequestBody ScJczxxEntity scJczxx){
		 ScJczxxEntity idMax1 = scJczxxService.idMax1();
		if(idMax1 == null) {
			scJczxx.setId(1);
		}else {
			int id = idMax1.getId();
			
			scJczxx.setId(id+1);
		}
		
		scJczxxService.save(scJczxx);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public R update(@RequestBody ScJczxxEntity scJczxx){
		scJczxxService.update(scJczxx);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public R delete(@RequestBody String[] ids){
		scJczxxService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
