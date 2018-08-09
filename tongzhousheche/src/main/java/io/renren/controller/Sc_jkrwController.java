package io.renren.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.common.utils.R;
import io.renren.modules.sys.entity.SysUserEntity;
import io.renren.service.Sc_jkrwService;
import io.renren.vo.Sc_jkrwVo;
import io.renren.vo.Sc_ssjkVo;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/generator/sc_jkrw")
public class Sc_jkrwController {
	
	@Autowired
	private Sc_jkrwService sc_jkrwService;
	
	/**
	 * 查看分析结果
	 */
	@RequestMapping("/find")
	public R checkList(@RequestParam Map<String, Object> params){
		
		log.info("进入到Sc_jkrwController", "创建监控任务");
		
		SysUserEntity user = (SysUserEntity) SecurityUtils.getSubject().getSession().getAttribute("user");
		
		String username = user.getUsername();
		
		params.put("username", username);
		
        Query query = new Query(params);
        
		List<Sc_jkrwVo> list = sc_jkrwService.queryList(query);
		
		int total = sc_jkrwService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(list, total, query.getLimit(), query.getPage());
		
		log.info("返回结果：{}", pageUtil);
		
		return R.ok().put("page", pageUtil);
	}
	
	@RequestMapping("/query")
	public R query(@RequestParam Map<String, Object> params){
		
		SysUserEntity user = (SysUserEntity) SecurityUtils.getSubject().getSession().getAttribute("user");
		
		String username = user.getUsername();
		
		params.put("username", username);
		
		Query query = new Query(params);
		
		List<Sc_jkrwVo> list = sc_jkrwService.list(query);
		
		int total = sc_jkrwService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(list, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
    @RequestMapping("/delete")
    public R delete(@RequestBody String[] ids) {
    	sc_jkrwService.deleteBatch(ids);
        return R.ok();
    }
    
	@RequestMapping("/check1")
	public R check1(@RequestBody String id) {
		
		List<Sc_jkrwVo> list = sc_jkrwService.list(id);
		Sc_jkrwVo sc_jkrwVo = list.get(0);
		String username = sc_jkrwVo.getUsername();
		int mytype = sc_jkrwVo.getMytype();
		List<Sc_jkrwVo> listJK = sc_jkrwService.check(username, mytype);
		if(listJK.size() == 0) {
			sc_jkrwService.update1(id);
			return R.ok().put("num", 0);
		} else {
			return R.ok().put("num", 1);
		}
	}
	
	@RequestMapping("/check2")
	public R check2(@RequestBody String id) {
		
		sc_jkrwService.update2(id);

		return R.ok().put("num", 0);
	}
	
	@RequestMapping("/fenxi")
	public R fenxi(@RequestBody String id) {
		
		List<Sc_ssjkVo> list = sc_jkrwService.find(id);
		
		String plateno = list.get(0).getPlateno();
		
		System.out.println(plateno);
		
		return R.ok().put("num", 0).put("data", plateno);
	}
	
}
