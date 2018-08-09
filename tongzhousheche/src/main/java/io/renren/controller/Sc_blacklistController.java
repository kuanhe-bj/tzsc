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

import com.tceasy.common.utils.json.JsonUtil;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.common.utils.R;
import io.renren.modules.sys.controller.AbstractController;
import io.renren.modules.sys.entity.SysUserEntity;
import io.renren.service.Sc_blacklistService;
import io.renren.service.Sc_colorService;
import io.renren.vas.entity.ScBlacklistEntity;
import io.renren.vas.util.HttpUtil;
import io.renren.vo.Brand;
import io.renren.vo.Color;
import io.renren.vo.Model;
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
@RequestMapping("/generator/sc_blacklist")
public class Sc_blacklistController extends AbstractController {

	@Autowired
	private Sc_blacklistService sc_blacklistService;

	@Autowired
	private Sc_colorService sc_colorService;

	//顯示列表数据
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params) {

		log.info("-------------------顯示列表開始-------------------");

		Query query = new Query(params);

		List<ScBlacklistEntity> scRedlistList = sc_blacklistService.queryList(query);

		int total = sc_blacklistService.queryTotal(query);

		PageUtils pageUtil = new PageUtils(scRedlistList, total, query.getLimit(), query.getPage());

		log.info("-------------------顯示列表結束-------------------");

		return R.ok().put("page", pageUtil);
	}

	// 查詢
	@RequestMapping("/find")
	public R checkList(@RequestParam Map<String, Object> params) {

		log.info("-------------------查詢功能開始-------------------");

		Query query = new Query(params);

		List<ScBlacklistEntity> list = sc_blacklistService.checkList(query);

		int total = sc_blacklistService.total(query);
		
		PageUtils pageUtil = new PageUtils(list, total, query.getLimit(), query.getPage());

		log.info("-------------------查詢功能結束-------------------");

		return R.ok().put("page", pageUtil);
	}

	// 驗證
	@RequestMapping("/check")
	public R check(@RequestBody String carNum) {

		log.info("-------------------驗證功能開始-------------------");
		
		String[] str;
		try {
			str = URLDecoder.decode(carNum,"UTF-8").split("=");
			//System.out.println(URLDecoder.decode(carNum,"UTF-8"));
			int num = sc_blacklistService.check(str[1]);
			//System.out.println(str[1]);
			if(num == 0) {
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
			List<ScBlacklistEntity> list = sc_blacklistService.find(str[1]);
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

	// 增加
	@RequestMapping("/save")
	public R save(@RequestBody ScBlacklistEntity scBlacklistEntity) {

		log.info("-------------------添加功能開始-------------------");
		
		String carNum = scBlacklistEntity.getCarnum();
		
		List<ScBlacklistEntity> list = sc_blacklistService.find(carNum);
		
		if(list.size() == 0) {
			SysUserEntity user = (SysUserEntity) SecurityUtils.getSubject().getSession().getAttribute("user");
			
			String username = user.getUsername();
			
			scBlacklistEntity.setCreateuser(username);
			
			sc_blacklistService.save(scBlacklistEntity);
			
			log.info("-------------------添加功能結束-------------------");
			
			return R.ok().put("num", 0);
		} else {
			return R.ok().put("num", 1);
		}

	}

	@RequestMapping("/color")
	public R color() {

		List<Color> list = sc_colorService.getColor();

		return R.ok().put("list", list);
	}

	@RequestMapping("/brand")
	public R brand() {

		List<Brand> list = sc_colorService.getBrand();

		return R.ok().put("list", list);
	}
	
	@RequestMapping("/model")
	public R model() {

		List<Model> list = sc_colorService.getModel();

		return R.ok().put("list", list);
	}

	@RequestMapping("/check1")
	public R check1(@RequestBody String str) {

		SysUserEntity user = (SysUserEntity) SecurityUtils.getSubject().getSession().getAttribute("user");
		
		String username = user.getUsername();
		
		String[] strs =  str.split("&amp;"); 
		
		try {
			String str1 = URLDecoder.decode(strs[0],"UTF-8");
			String str2 = URLDecoder.decode(strs[1],"UTF-8");
			sc_blacklistService.updatePass(username, str2.split("=")[1], str1.split("=")[1]);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return R.ok().put("num", 0);
	}

	@RequestMapping("/check2")
	public R check2(@RequestBody String cph) {
		
		SysUserEntity user = (SysUserEntity) SecurityUtils.getSubject().getSession().getAttribute("user");
		
		String username = user.getUsername();

		sc_blacklistService.updateNotPass(username, cph);

		return R.ok().put("num", 0);
	}
	
	// 布控
	@RequestMapping(value = "/disp")
	public R disp(@RequestBody String plateNoType) {
		log.info("请求成功，返回数据：" + plateNoType);
		String requestPath = "http://11.33.29.91:8081/vas/VIID/dispopsitions/dispo";
		HttpUtil httpUtil = new HttpUtil();
		String msg = "{\"plateNoType\":\""+plateNoType + "\"}";
	    Map response = httpUtil.doPost(requestPath, null, msg);
	    log.info("HTTP_200：" + ((Integer) response.get("code")).intValue());
	    if (HttpUtil.HTTP_200 == ((Integer) response.get("code")).intValue()) {
	    	Map respon=JsonUtil.toMap((String) response.get("result").toString());
	    	
	    	log.info("返回数据respon：" + respon);
	    	log.info("返回数据code：" + respon.get("code"));
	    	if ("1".equals(respon.get("code"))) {
	    		sc_blacklistService.updateBkStatus(plateNoType);
	    		return R.ok().put("res", 1);
			}else{
				
				return R.ok().put("res", 0);
			}
	    }else{
	    	log.info("返回错误：" + "sssss");
		   return R.ok().put("res", 0);
	    }
	}
	@RequestMapping(value = "/chedisp")
	public R cheDisp(@RequestBody String plateNoType) {
		
		String [] a=plateNoType.split("=");
		String plate=a[0];
		String bkid=a[1];
		String requestPath = "http://11.33.29.91:8081/vas/VIID/dispopsitions/chedispo";
		HttpUtil httpUtil = new HttpUtil();
		String msg = "{\"plateNoType\":\""+plate + "\",\"bkid\":\""+bkid+"\"}";
	    Map response = httpUtil.doPost(requestPath, null, msg);
	    log.info("HTTP_200：" + ((Integer) response.get("code")).intValue());
	    if (HttpUtil.HTTP_200 == ((Integer) response.get("code")).intValue()) {
	    	Map respon=JsonUtil.toMap((String) response.get("result").toString());
	    	log.info("返回数据code：" + respon.get("code"));
	    	if ("1".equals(respon.get("code"))) {
	    		sc_blacklistService.updateBkStatus(plateNoType);
	    		return R.ok().put("res", 1);
			}else{
				
				return R.ok().put("res", 0);
			}
	    }else{
	    	log.info("返回错误：" + "sssss");
		   return R.ok().put("res", 0);
	    }
	}
	
	
}
