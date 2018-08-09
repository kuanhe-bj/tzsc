package io.renren.vas.controller;


import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.common.utils.R;
import io.renren.modules.sys.entity.SysUserEntity;
import io.renren.vas.entity.ScAlarminfoEntity;
import io.renren.vas.service.ScAlarminfoService;


/**
 * 
 * 
 * @author pwk
 * @email sunlightcs@gmail.com
 * @date 2018-02-11 13:53:44
 */
@RestController
@RequestMapping("/generator/scalarminfo")
public class ScAlarminfoController {
	@Autowired
	private ScAlarminfoService scAlarminfoService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	//@RequiresPermissions("generator:scalarminfo:list")
	public R list(@RequestParam Map<String, Object> params){
		
		SysUserEntity user = (SysUserEntity) SecurityUtils.getSubject().getSession().getAttribute("user");
		
		String username = user.getUsername();
		
		params.put("receiver", username);
		
        Query query = new Query(params);

		List<ScAlarminfoEntity> scAlarminfoList = scAlarminfoService.queryList(query);
		
		int total = scAlarminfoService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(scAlarminfoList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	@RequestMapping("/find")
	//@RequiresPermissions("generator:scalarminfo:list")
	public R find(@RequestParam Map<String, Object> params){
		
		SysUserEntity user = (SysUserEntity) SecurityUtils.getSubject().getSession().getAttribute("user");
		
		String username = user.getUsername();
		
		params.put("receiver", username);
		
        Query query = new Query(params);

		List<ScAlarminfoEntity> scAlarminfoList = scAlarminfoService.find(query);
		
		//System.out.println(scAlarminfoList.size());
		
		int total = scAlarminfoService.total(query);
		
		PageUtils pageUtil = new PageUtils(scAlarminfoList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	//@RequiresPermissions("generator:scalarminfo:info")
	public R info(@PathVariable("id") String id){
		
		ScAlarminfoEntity scAlarminfo = scAlarminfoService.queryObject(Integer.parseInt(id));
		
		return R.ok().put("scAlarminfo", scAlarminfo);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	//@RequiresPermissions("generator:scalarminfo:save")
	public R save(@RequestBody ScAlarminfoEntity scAlarminfo){
		
		scAlarminfo.setReadstate(0);
		
		scAlarminfo.setPushstate(1);
		
		scAlarminfoService.save(scAlarminfo);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	//@RequiresPermissions("generator:scalarminfo:update")
	public R update(@RequestBody ScAlarminfoEntity scAlarminfo){
		scAlarminfoService.update(scAlarminfo);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	//@RequiresPermissions("generator:scalarminfo:delete")
	public R delete(@RequestBody String[] ids){
		scAlarminfoService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
