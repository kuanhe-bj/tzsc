package io.renren.controller;


import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
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
import io.renren.modules.sys.controller.AbstractController;
import io.renren.modules.sys.entity.SysUserEntity;
import io.renren.service.Sc_redlistService;
import io.renren.vas.entity.ScRedlistEntity;
import io.renren.vo.Sc_jdc;
import lombok.extern.slf4j.Slf4j;


/**
 * 
 * 
 * @author pwk
 * @email sunlightcs@gmail.com
 * @date 2018-02-11 13:39:58
 */
@Slf4j
@RestController
@RequestMapping("/generator/sc_redlist")
public class Sc_redlistController extends AbstractController {
	@Autowired
	private Sc_redlistService sc_redlistService;
	
	//顯示列表数据
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		
		log.info("-------------------顯示列表開始-------------------");
		
        Query query = new Query(params);

		List<ScRedlistEntity> scRedlistList = sc_redlistService.queryList(query);
		
		int total = sc_redlistService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(scRedlistList, total, query.getLimit(), query.getPage());
		
		log.info("-------------------顯示列表結束-------------------");
		
		return R.ok().put("page", pageUtil);
	}
	
	//查詢
	@RequestMapping("/find")
	public R checkList(@RequestParam Map<String, Object> params){
		
		log.info("-------------------查詢功能開始-------------------");
		
        Query query = new Query(params);

		List<ScRedlistEntity> list = sc_redlistService.checkList(query);
		
		int total = sc_redlistService.total(query);
		
		PageUtils pageUtil = new PageUtils(list, total, query.getLimit(), query.getPage());
		
		log.info("-------------------查詢功能結束-------------------");
		
		return R.ok().put("page", pageUtil);
	}
	
	//增加
	@RequestMapping("/save")
	public R save(@RequestBody ScRedlistEntity scRedlist) {
		
		log.info("进入到这里了", "红名单添加");
		
		SysUserEntity user = (SysUserEntity) SecurityUtils.getSubject().getSession().getAttribute("user");
		
		String username = user.getUsername();
		
		ScRedlistEntity scRedlistEntity = sc_redlistService.getId();
		
		if(scRedlistEntity == null) {
			scRedlist.setId(1);
		} else {
			scRedlist.setId(scRedlistEntity.getId() + 1);
		}
		
		scRedlist.setCreateuser(username);
		
		sc_redlistService.save(scRedlist);
		
		log.info("进入到这里了", "保存了吗！！！");
		
		return R.ok().put("num", 0);
	}
	
	//驗證
	@RequestMapping("/check")
	public R check(@RequestBody String carNum){
		
		log.info("-------------------驗證功能開始-------------------");
		
		String[] str;
		try {
			str = URLDecoder.decode(carNum,"UTF-8").split("=");
			//System.out.println(URLDecoder.decode(carNum,"UTF-8"));
			List<Sc_jdc> list = sc_redlistService.check(str[1]);
			//System.out.println(str[1]);
			if(list.size() == 0) {
				return R.ok().put("num", 1);
			} else {
				return R.ok().put("num", 0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping("/same")
	public R same(@RequestBody String carNum) {
		
		String[] str;
		try {
			str = URLDecoder.decode(carNum,"UTF-8").split("=");
			List<ScRedlistEntity> list = sc_redlistService.find(str[1]);
			if(list.size() != 0) {
				return R.ok().put("num", 1);
			} else {
				return R.ok().put("num", 0);
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping("/check1")
	public R check1(@RequestBody String str) {
		
		SysUserEntity user = (SysUserEntity) SecurityUtils.getSubject().getSession().getAttribute("user");
		
		String username = user.getUsername();
		
		String[] strs =  str.split("&amp;"); 
		
		try {
			String str1 = URLDecoder.decode(strs[0],"UTF-8");
			String str2 = URLDecoder.decode(strs[1],"UTF-8");
			sc_redlistService.updatePass(username, str2.split("=")[1], str1.split("=")[1]);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return R.ok().put("num", 0);
	}
	
	@RequestMapping("/check2")
	public R check2(@RequestBody String cph) {
		
		SysUserEntity user = (SysUserEntity) SecurityUtils.getSubject().getSession().getAttribute("user");
		
		String username = user.getUsername();
		
		//System.out.println(username + "," + cph);
		
		sc_redlistService.updateNotPass(username, cph);
		
		return R.ok().put("num", 0);
	}

}
