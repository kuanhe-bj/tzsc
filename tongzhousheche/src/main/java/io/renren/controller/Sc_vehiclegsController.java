package io.renren.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.common.utils.R;
import io.renren.dao.Sc_vehiclegsDao;
import io.renren.service.Sc_vehiclegsService;
import io.renren.vo.HighRisk;
import io.renren.vo.VehiclegsVo;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;

@Slf4j
@RestController
@RequestMapping("/generator/vehiclegs")
public class Sc_vehiclegsController {
	/**
	 * 车辆归属地分析
	 */
	@Autowired
	Sc_vehiclegsService vehiclegsService;

	@Autowired
	private Sc_vehiclegsDao vehiclegsdao;

	@RequestMapping("/carnumList")
	public R list(@RequestParam Map<String, Object> params) {
		log.info("进入到Sc_vehiclegsController", "车辆归属地分析");
		Query query = new Query(params);
		List<VehiclegsVo> list = this.vehiclegsService.queryList(query);
		int total = vehiclegsService.queryTotal(query);
		PageUtils pageUtil = new PageUtils(list, total, query.getLimit(), query.getPage());
		return R.ok().put("page", pageUtil);
	}

	@RequestMapping("/dList")
	public R dList(@RequestParam Map<String, Object> params) {
	
		String entertime = (String) params.get("entertime");
		if (!StringUtils.isNotBlank(entertime)) {
            params.remove("entertime");
            params.remove("exittime");
            Query query = new Query(params);
            log.info("时间参数为null：{}", query);
    		List<VehiclegsVo> list = this.vehiclegsService.queryList(query);
    		int total = vehiclegsService.queryTotal(query);
    		log.info("total：{}", total);
    		PageUtils pageUtil = new PageUtils(list, total, query.getLimit(), query.getPage());
    		return R.ok().put("page", pageUtil);
        }else{
        	String province = (String) params.get("province");
    		int total=0;
    		if (!StringUtils.isNotBlank(province)) {
    			 params.remove("province");
    			 total = 31;
			}else {
				total = 1;
			}
        	Query query = new Query(params);
        	log.info("有时间参数：{}", query);
    		List<VehiclegsVo> list =vehiclegsService.selecttime(query);
    		log.info("list：{}", list);
    		log.info("total：{}", total);
    		PageUtils pageUtil = new PageUtils(list, total, query.getLimit(), query.getPage());
    		return R.ok().put("page", pageUtil);
        }
	}

	@RequestMapping("/cargsd")
	public R listall(@RequestParam Map<String, Object> params) {
		params.put("page", 1);
		params.put("limit", 100);
		Query query = new Query(params);
		List<VehiclegsVo> list = this.vehiclegsService.queryList(query);
		int total = vehiclegsService.queryTotal(query);
		PageUtils pageUtil = new PageUtils(list, total, query.getLimit(), query.getPage());
		return R.ok().put("page", pageUtil);
	}

	/**
	 * 高危地区数据
	 */
	@RequestMapping("/listhighrisk")
	public R listhighrisk(@RequestParam Map<String, Object> params) {
		log.info("进入到Sc_vehiclegsController", "车辆归属地分析");
		List<VehiclegsVo> highriskList = vehiclegsService.highriskList();
		List<HighRisk> list = new ArrayList<>();
		for (VehiclegsVo vehiclegsVo : highriskList) {
			HighRisk h = new HighRisk();
			h.setValue(vehiclegsVo.getCarnum() + "");
			h.setName(vehiclegsVo.getProvince());
			list.add(h);
		}
		Gson gson = new Gson();
		String str = gson.toJson(list);
		JSONArray jsonArray = JSONArray.fromObject(str);
		log.info("返回结果：{}", jsonArray);
		return R.ok().put("list", jsonArray);
	}
	
	/**
	 * 归属地柱状图
	 */
	@RequestMapping("/listzzt")
	public R listzzt(@RequestParam Map<String, Object> params) {
		log.info("进入到Sc_vehiclegsController", "车辆归属地分析");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(new Date());
		List<VehiclegsVo> list1 = vehiclegsdao.listnotbeijing(date);
		List<HighRisk> list2 = new ArrayList<>();
		for (int i = 0; i < list1.size(); i++) {
			HighRisk h = new HighRisk();
			h.setValue(list1.get(i).getCarnum() + "");
			h.setName(list1.get(i).getProvince());
			if (i%4==0) {
				h.setColor("#4572a7");
			}else if(i%4==1){
				h.setColor("#cbab4f");
			}else if(i%4==2){
				h.setColor("#c12c44");
			}else if(i%4==3){
				h.setColor("#a56f8f");
			}
			
			list2.add(h);
		}
		Gson gson = new Gson();
		String str1 = gson.toJson(list2);
		JSONArray jsonArray = JSONArray.fromObject(str1);
		log.info("返回结果：{}", jsonArray);
		return R.ok().put("list", jsonArray);
	}
	
	/**
	 * 归属地饼状图
	 */
	@RequestMapping("/listbzt")
	public R listbzt(@RequestParam Map<String, Object> params) {
		log.info("进入到Sc_vehiclegsController", "车辆归属地分析");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(new Date());
		List<VehiclegsVo> list1 = vehiclegsdao.listnotbeijing(date);
		List<HighRisk> list2 = new ArrayList<>();
		for (VehiclegsVo vehiclegsVo : list1) {
			String randColorCode = vehiclegsService.getRandColorCode();
			HighRisk h = new HighRisk();
			h.setValue(vehiclegsVo.getCarnum() + "");
			h.setName(vehiclegsVo.getProvince());
			h.setColor(randColorCode);
			list2.add(h);
		}
		Gson gson = new Gson();
		String str1 = gson.toJson(list2);
		JSONArray jsonArray = JSONArray.fromObject(str1);
		log.info("返回结果：{}", jsonArray);
		return R.ok().put("list", jsonArray);
	}

	@RequestMapping("/getcarnum")
	public R getpercent(@RequestParam Map<String, Object> params) {
		log.info("进入到Sc_vehiclegsController", "车辆归属地分析");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(new Date());
		VehiclegsVo vehiclegsVo = vehiclegsdao.getcarnum(date);
		log.info("返回结果：{}", vehiclegsVo);
		return R.ok().put("vehiclegsVo", vehiclegsVo);
	}

	@RequestMapping("/list")
	public R list1(@RequestParam Map<String, Object> params) {
		log.info("进入到Sc_vehiclegsController", "车辆归属地分析");
		List<VehiclegsVo> list1 = vehiclegsdao.list();
		List<HighRisk> list2 = new ArrayList<>();
		for (VehiclegsVo vehiclegsVo : list1) {
			HighRisk h = new HighRisk();
			h.setValue(vehiclegsVo.getCarnum() + "");
			h.setName(vehiclegsVo.getProvince());
			list2.add(h);
		}
		Gson gson = new Gson();
		String str1 = gson.toJson(list2);
		JSONArray jsonArray = JSONArray.fromObject(str1);
		log.info("返回结果：{}", jsonArray);
		return R.ok().put("list", jsonArray);
	}
}
