package io.renren.controller;


import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.renren.common.utils.R;
import io.renren.modules.sys.entity.SysUserEntity;
import io.renren.service.Sc_alarminfoService;
import io.renren.vas.entity.ScAlarminfoEntity;


/**
 * 
 * 
 * @author pwk
 * @email sunlightcs@gmail.com
 * @date 2018-02-11 13:53:44
 */
@RestController
@RequestMapping("/generator/sc_alarminfo")
public class Sc_alarminfoController {
	
	@Autowired
	private Sc_alarminfoService sc_alarminfoService;
	
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public R save(@RequestBody ScAlarminfoEntity alarminfoVo){
		
		String carnum = alarminfoVo.getCarnum();
		
		alarminfoVo.setReadstate(0);
		
		alarminfoVo.setPushstate(1);
		
		alarminfoVo.setReceiver("admin1");
		
		alarminfoVo.setMessage(carnum + "报警信息！"); 
		
		sc_alarminfoService.insert(alarminfoVo);
		
		return R.ok();
	}
	
	
	@RequestMapping("/check1")
	public R check1(@RequestBody String[] ids) {
		
		sc_alarminfoService.updateBatch(ids);

		return R.ok().put("num", 0);
	}
	
	@RequestMapping("/check2")
	public R check2(@RequestBody String cph) {
				
		sc_alarminfoService.update2(cph);

		return R.ok().put("num", 0);
	}
	
	@RequestMapping("/list")
	public R list(){
		
		SysUserEntity user = (SysUserEntity) SecurityUtils.getSubject().getSession().getAttribute("user");
		
		String username = user.getUsername();
		
		int num = sc_alarminfoService.find(username);
		
		return R.ok().put("num", num);
	}
	
}
