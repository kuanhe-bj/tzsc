package io.renren.vas.controller;


import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.common.utils.R;
import io.renren.vas.entity.ScEtcptjdEntity;
import io.renren.vas.service.ScEtcptjdService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 * 
 * 
 * @author pwk
 * @email sunlightcs@gmail.com
 * @date 2018-02-09 15:19:34
 */
@RestController
@RequestMapping("/generator/scetcptjd")
public class ScEtcptjdController {
	@Autowired
	private ScEtcptjdService scEtcptjdService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<ScEtcptjdEntity> scEtcptjdList = scEtcptjdService.queryList(query);
		int total = scEtcptjdService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(scEtcptjdList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{eid}")
	@RequiresPermissions("generator:scetcptjd:info")
	public R info(@PathVariable("eid") String eid){
		ScEtcptjdEntity scEtcptjd = scEtcptjdService.queryObject(eid);
		
		return R.ok().put("scEtcptjd", scEtcptjd);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("generator:scetcptjd:save")
	public R save(@RequestBody ScEtcptjdEntity scEtcptjd){
		scEtcptjdService.save(scEtcptjd);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("generator:scetcptjd:update")
	public R update(@RequestBody ScEtcptjdEntity scEtcptjd){
		scEtcptjdService.update(scEtcptjd);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("generator:scetcptjd:delete")
	public R delete(@RequestBody String[] eids){
		scEtcptjdService.deleteBatch(eids);
		
		return R.ok();
	}
	
}
