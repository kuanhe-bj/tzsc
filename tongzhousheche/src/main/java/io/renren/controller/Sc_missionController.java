package io.renren.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.common.utils.R;
import io.renren.service.Sc_missionService;
import io.renren.vo.MissionVo;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/generator/sc_mission")
public class Sc_missionController {
	
	@Autowired
	private Sc_missionService sc_missionService;
	
	/**
	 * 创建任务
	 */
	@RequestMapping("/find")
	public R checkList(@RequestParam Map<String, Object> params){
		//查询列表数据
		log.info("进入到Sc_missionController", "创建任务");
        Query query = new Query(params);
		List<MissionVo> list = sc_missionService.queryList(query);
		int total = sc_missionService.queryTotal(query);
		PageUtils pageUtil = new PageUtils(list, total, query.getLimit(), query.getPage());
		log.info("返回结果：{}", pageUtil);
		return R.ok().put("page", pageUtil);
	}
	
	 /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody String[] ids) {
    	
        sc_missionService.deleteBatch(ids);
        return R.ok();
    }
    
    /**
     * 复制任务
     */
	@RequestMapping("/copy")
	public R copy(@RequestBody String str){
		
		String[] strs =  str.split("&amp;"); 
		try {
			String str1 = URLDecoder.decode(strs[0],"UTF-8");
			String str2 = URLDecoder.decode(strs[1],"UTF-8");
			List<MissionVo> list = sc_missionService.find(Integer.parseInt(str1.split("=")[1]));
			if(list.size() != 0) {
				MissionVo missionVo = list.get(0);
				missionVo.setOwner(str2.split("=")[1]);
				sc_missionService.insert(missionVo);
				return R.ok().put("num", 0);
			} else {
				return R.ok().put("num", 1);
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping("/query")
	public R query(@RequestParam Map<String, Object> params){
		
		Query query = new Query(params);
		
		List<MissionVo> list = sc_missionService.list(query);
		
		int total = sc_missionService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(list, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	//分析方法
	@RequestMapping("/fenxi")
	public R list(@RequestBody String ids){
			
		int id = Integer.parseInt(ids);
			
		List<MissionVo> list = sc_missionService.find(id);
			
		String mission = list.get(0).getTasktype();
	
		if(mission.equals("隐匿车辆分析")) {
			sc_missionService.hidden(id);
		} else if(mission.equals("异常轨迹分析")) {				
			sc_missionService.ycgj(id);
		} else if(mission.equals("限行分析")) {
			sc_missionService.xxfx(id);
		} else if(mission.equals("次数异常分析")) {				
			sc_missionService.csfx(id);
		} else if(mission.equals("盗抢车辆分析")) {
			sc_missionService.dqcl(id);
		} else if(mission.equals("套牌分析")) {
			sc_missionService.taopai(id);
		} else if(mission.equals("昼伏夜出分析")) {
			sc_missionService.zfyc(id);
		}
		return R.ok().put("num", 0);
	}
	
	@RequestMapping("/update")
	public R update(@RequestBody String ids){
			
		int id = Integer.parseInt(ids);
		
		sc_missionService.updating(id);
		
		return R.ok().put("num", 0);
	}
	
}
