package io.renren.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.common.utils.R;
import io.renren.service.DsjService;
import io.renren.vas.entity.ScEtcptjdEntity;
import io.renren.vas.entity.ScScajEntity;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/generator/dsj")
@Slf4j
public class DsjController {

	@Autowired
	private DsjService dsjService;

	/* 重大事件挖掘 */
	@RequestMapping("/list")
	public R findScsmajorEntitys(@RequestParam Map<String, Object> params) {
		log.info("重大事件挖掘：{}", "查询功能");
		/* 判断number和name是否存在，如果为NLL或空会实现remove */
		String number = (String) params.get("number");

		if (!StringUtils.isNotBlank(number)) {
			params.remove("number");
		}
		String name = (String) params.get("name");

		if (!StringUtils.isNotBlank(name)) {
			params.remove("name");
		}
		Query query = new Query(params);
		log.info("参数：{}", query);
		List<ScScajEntity> list = dsjService.fin(query);
		int n = dsjService.total(query);
		PageUtils pageUtil = new PageUtils(list, n, query.getLimit(), query.getPage());
		return R.ok().put("page", pageUtil);

	}

	/* 显示单个数据 ,此功能主要针对查询，为条件数据或关系 */
	@RequestMapping("/page")
	public R page(@RequestParam Map<String, Object> params) throws ParseException {
		// 查询列表数据
		Query query = new Query(params);
		List<ScScajEntity> list = dsjService.fin_ds(query);
		log.info("大事件挖掘功能显示停车场数据列表查询数据or", list);
		PageUtils pageUtil = new PageUtils(list, 1, 1, 1);
		return R.ok().put("page", pageUtil);

	}

	/* 显示单个数据,此功能主要针对查询，为条件数据和关系 */
	@RequestMapping("/fin_dsall")
	public R fin_dsall(@RequestParam Map<String, Object> params) throws ParseException {
		// 查询列表数据
		Query query = new Query(params);
		List<ScScajEntity> list = dsjService.fin_dsall(query);
		log.info("大事件挖掘功能显示停车场数据列表查询数据and", list);
		PageUtils pageUtil = new PageUtils(list, 1, 1, 1);
		return R.ok().put("page", pageUtil);

	}

	/* 车牌掉经 */
	@RequestMapping("/find_jwdu")
	public R jw(String cph) throws ParseException {
		// 查询列表数据
		log.info("大事件挖掘功能通过车牌调经纬度", cph);
		List<ScScajEntity> list = dsjService.jw(cph);
		return R.ok().put("page", list);

	}

	@RequestMapping("/tcc_list")
	public R findxx(String ajbh, String ajmc) {
		// 查询列表数据
		List<ScScajEntity> list = dsjService.x(ajbh, ajmc);
		log.info("大事件挖掘功能查询停车场列表数据", list);
		return R.ok().put("page", list);

	}

	/* 调圈内车 */
	@RequestMapping("/map_allcar")
	public R ac(String jind, String weid, String count, String qsj, String hsj, String et, String zf, String wd,
			String gw, String sa, String cx) throws ParseException {
		// 查询列表数据
		Map<String, Object> map = new HashMap<String, Object>();
		List<ScEtcptjdEntity> list = new ArrayList<ScEtcptjdEntity>();
		double a = Double.parseDouble(jind);
		double b = Double.parseDouble(weid);
		double num = 1;
		if (count == null || "".equals(count)) {
			num = 1;
		} else {
			num = Double.parseDouble(count);
		}
		map.put("jingdu", a);
		map.put("weidu", b);
		map.put("count", num);
		map.put("qsj", qsj);
		map.put("hsj", hsj);
		int etcp = Integer.parseInt(et);
		int zfyc = Integer.parseInt(zf);
		int wdc = Integer.parseInt(wd);
		int gwdq = Integer.parseInt(gw);
		int sac = Integer.parseInt(sa);
		int cxyc = Integer.parseInt(cx);
		map.put("et", etcp);
		map.put("zf", zfyc);
		map.put("wd", wdc);
		map.put("gw", gwdq);
		map.put("sa", sac);
		map.put("cx", cxyc);
		list = dsjService.allcar(map);
		return R.ok().put("page", list);
	}

}
