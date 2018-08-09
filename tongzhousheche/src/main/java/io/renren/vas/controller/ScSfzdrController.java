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
import io.renren.vas.entity.ScSfzdrEntity;
import io.renren.vas.service.ScSfzdrService;


/**
 * 
 * 
 * @author pwk
 * @email sunlightcs@gmail.com
 * @date 2018-02-09 15:19:33
 */
@RestController
@RequestMapping("/generator/scsfzdr")
public class ScSfzdrController {
	@Autowired
	private ScSfzdrService scSfzdrService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")

	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<ScSfzdrEntity> scSfzdrList = scSfzdrService.queryList(query);
		int total = scSfzdrService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(scSfzdrList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	//查询
	@RequestMapping("/cx")

	public R cx(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<ScSfzdrEntity> scSfzdrList = scSfzdrService.cx(query);
		int total = scSfzdrService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(scSfzdrList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{dataid}")

	public R info(@PathVariable("dataid") String dataid){
		ScSfzdrEntity scSfzdr = scSfzdrService.queryObject(dataid);
		
		return R.ok().put("scSfzdr", scSfzdr);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")

	public R save(@RequestBody ScSfzdrEntity scSfzdr){
		String a=UUID.randomUUID().toString();
		scSfzdr.setDataid(a);
		scSfzdrService.save(scSfzdr);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")

	public R update(@RequestBody ScSfzdrEntity scSfzdr){
		
		scSfzdrService.update(scSfzdr);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")

	public R delete(@RequestBody String[] dataids){
		scSfzdrService.deleteBatch(dataids);
		
		return R.ok();
	}
	
}
