package io.renren.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.common.utils.R;
import io.renren.service.ZfService;
import io.renren.vas.entity.ScAlarminfoEntity;
import io.renren.vas.entity.ScDtcldzdanEntity;
import io.renren.vas.entity.ScEtcptjdEntity;
import io.renren.vas.entity.ZfssEntity;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/generator/zfyc")
@Configuration
@EnableScheduling
@Component
@Slf4j
public class ZfycController {

	@Autowired
	private ZfService zfService;

	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params) throws ParseException {
		// 查询列表数据
		Query query = new Query(params);
		List<ScDtcldzdanEntity> list = zfService.pl(query);
		log.info("昼伏夜出功能显示停车场数据列表功能运行");
		int total = zfService.queryTotal(query);
		PageUtils pageUtil = new PageUtils(list, total, query.getLimit(), query.getPage());
		return R.ok().put("page", pageUtil);
	}

	@RequestMapping("/page")
	public R page(@RequestParam Map<String, Object> params) throws ParseException {
		// 停车场数据分页列表
		Query query = new Query(params);
		List<ScDtcldzdanEntity> list = zfService.pl(query);
		log.info("昼伏夜出功能显示停车场数据分页列表功能运行");
		int total = zfService.queryTotal(query);
		PageUtils pageUtil = new PageUtils(list, total, query.getLimit(), query.getPage());
		return R.ok().put("page", pageUtil);

	}

	/* 插入数据方法 */
	public void info(String receiver, String message) throws ParseException {
		// 报警
		int count = zfService.info(receiver, message);
		if (count == 1) {
			log.info("昼伏夜出功能报警插入功能运行成功");
		} else {
			log.info("昼伏夜出功能报警插入功能运行失败");

		}

	}

	/* 判断是否表中已经存在 */
	public Object bd(String message) throws ParseException {
		// 报警
		List<ScAlarminfoEntity> list = zfService.bd(message);
		
		return list;

	}

	/* 定时时间方法 */
	public String time(String ETCP_TIME) throws ParseException {
		/* 获取日历 */
		Calendar calend = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		/* 将输入时间转化为DATE格式 */
		Date ETCP_TI = sdf.parse(ETCP_TIME);
		/* 将带入时间转化为Calendar当前时间 */
		calend.setTime(ETCP_TI);
		log.info("凌晨出现积分模型计算时间前3个月运行");
		/*-3*/
		calend.add(Calendar.MONTH, -3);
		/* 转换时间格式String */
		String befor = sdf.format(calend.getTimeInMillis());
		return befor;
	}

	@RequestMapping("/ss_c")
	public R ss_c(String fxc, String fxd, String btc, String btd, String ycc, String ycd, String btcxc, String btcxd,
			String wscxc, String wscxd) throws ParseException {
		// 停车场数据分页列表
		int c1 = Integer.parseInt(btcxc);
		int c2 = Integer.parseInt(btcxd);
		int c3 = Integer.parseInt(wscxc);
		int c4 = Integer.parseInt(wscxd);

		// 白天
		List<ScEtcptjdEntity> list1 = zfService.ss_c(fxc, fxd, btc, btd, c1, c2);
		// 晚上
		List<ScEtcptjdEntity> list2 = zfService.ss_c(fxc, fxd, btc, btd, c3, c4);
		log.info("ETCP实时查询条件查询的时间功能运行");
		String uu_id = UUID.randomUUID().toString();
		for (int i = 0; i < list1.size(); i++) {
			String a = list1.get(i).getExitTime().toString();
			List<ScEtcptjdEntity> et_allb = zfService.etcp_all(a);

			for (int j = 0; j < et_allb.size(); j++) {
				String aa = et_allb.get(j).getExitTime().toString();
				int count = zfService.etcp_count(aa);
				double numb = zfService.etcp_numb(aa);
				et_allb.get(j).setCount(count + "");
				et_allb.get(j).setNumb(numb);
			}
			// 向临时表方数据
			for (int k = 0; k < et_allb.size(); k++) {
				String nu = String.valueOf(et_allb.get(k).getNumb());
				String co = String.valueOf(et_allb.get(k).getCount());
				zfService.insert(et_allb.get(k).getCarNumber(), nu, co, et_allb.get(k).getExitTime().toString(), uu_id);
			}
		}

		for (int i = 0; i < list2.size(); i++) {
			String bb = list2.get(i).getExitTime().toString();
			List<ScEtcptjdEntity> et_ally = zfService.etcp_all(bb);

			for (int j = 0; j < et_ally.size(); j++) {
				String aa = et_ally.get(j).getExitTime().toString();
				int count2 = zfService.etcp_count(aa);
				double numb2 = zfService.etcp_numb(aa);
				et_ally.get(j).setCount(count2 + "");
				et_ally.get(j).setNumb(numb2);
			}
			// 向临时表方数据
			for (int k = 0; k < et_ally.size(); k++) {
				String nu2 = String.valueOf(et_ally.get(k).getNumb());
				String co2 = String.valueOf(et_ally.get(k).getCount());
				zfService.insert(et_ally.get(k).getCarNumber(), nu2, co2, et_ally.get(k).getExitTime().toString(),
						uu_id);
			}
		}
		// 向表中放数据
		return R.ok().put("list", uu_id);

	}

	@RequestMapping("/ss_list")
	public R ss_list(@RequestParam Map<String, Object> params) throws ParseException {
		// 查询列表数据
		Query query = new Query(params);

		List<ZfssEntity> list = zfService.select(query);

		log.info("昼伏夜出功能显示停车场数据列表功能运行");
		
		int total = zfService.ss_total();
		
		PageUtils pageUtil = new PageUtils(list, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);

	}

	@RequestMapping("/delete")
	public R delete(@RequestParam Map<String, Object> params) {
		// 查询列表数据

		zfService.delete();

		log.info("昼伏夜出功能实时显示停车场数据列表删除功能运行");

		return R.ok();

	}

	@RequestMapping("/plate")
	public R plate(String jingdu, String weidu, String count) throws ParseException {
		// 查询列表数据
		List<ScEtcptjdEntity> L1 = new ArrayList<ScEtcptjdEntity>();
		log.info("昼伏夜出实时地图取车牌号列表功能运行");
		String plate = null;
		List<String> list = zfService.plate();
		double jin = Double.parseDouble(jingdu);
		double wei = Double.parseDouble(weidu);
		int co = Integer.parseInt(count);
		for (int i = 0; i < list.size(); i++) {
			plate = list.get(i);
			if (plate != null) {
				List<ScEtcptjdEntity> etcp = zfService.all_cardt(plate, jin, wei, co);
				for (int k = 0; k < etcp.size(); k++) {
					L1.add(etcp.get(k));
				}
			}
		}
		return R.ok().put("list", L1);

	}

	// 全部车辆
	@RequestMapping("/plat")
	public R plat(String jingdu, String weidu, String count, String et, String zf, String wd, String gw, String sa,
			String cx) throws ParseException {
		// 查询列表数据
		Map<String, Object> map = new HashMap<String, Object>();
		double jin = Double.parseDouble(jingdu);
		double wei = Double.parseDouble(weidu);
		int num = Integer.parseInt(count);
		map.put("jind", jin);
		map.put("weid", wei);
		map.put("count", num);
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
		List<ScEtcptjdEntity> list = zfService.card(map);
		return R.ok().put("list", list);

	}

	// 重点人
	@RequestMapping("/zdrsj")
	public R plate(String plate) throws ParseException {
		// 查询列表数据
		log.info("重点人车牌掉时间功能运行");
		Date ex = null;
		List<ScEtcptjdEntity> listd = zfService.zdrsj(plate);
		List<String> ld = new ArrayList<String>();
		if (listd.size() > 0) {
			ex = listd.get(0).getExitTime();
		} else {
			return R.ok().put("list", ld);
		}
		Calendar ca = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String a = sdf.format(ex);
		Date aa = sdf.parse(a);
		ca.setTime(aa);
		ca.add(Calendar.DAY_OF_YEAR, -7);
		Date sq = ca.getTime();
		String qsj = sdf.format(sq);
		ld.add(qsj);
		ld.add(a);
		return R.ok().put("list", ld);

	}

	// 昼伏夜出
	@RequestMapping("/dan")
	public R dan(String jingdu, String weidu, String count) throws ParseException {

		log.info("重点区域功能运行");
		List<ScEtcptjdEntity> Lz = new ArrayList<ScEtcptjdEntity>();
		log.info("昼伏夜出实时地图取车牌号列表功能运行");
		Map<String, Object> map = new HashMap<String, Object>();
		double jin = Double.parseDouble(jingdu);
		double wei = Double.parseDouble(weidu);
		int co = Integer.parseInt(count);
		map.put("jind", jin);
		map.put("weid", wei);
		map.put("count", co);
		List<ScEtcptjdEntity> list = zfService.card(map);
		if (list.size() > 300) {
			for (int k = 0; k < 300; k++) {
				ScDtcldzdanEntity dan = zfService.dan(list.get(k).getCarNumber());
				if (dan != null) {

					if (dan.getNightout() > 0) {

						Lz.add(list.get(k));
					}
				}
			}
		} else {
			for (int k = 0; k < list.size(); k++) {
				ScDtcldzdanEntity dan = zfService.dan(list.get(k).getCarNumber());
				if (dan != null) {
					if (dan.getNightout() > 0) {
						Lz.add(list.get(k));
					}
				}
			}
		}

		return R.ok().put("list", Lz);

	}

	// 外地车
	@RequestMapping("/wd")
	public R wd(String jingdu, String weidu, String count) throws ParseException {

		log.info("重点区域功能运行");
		List<ScEtcptjdEntity> Lw = new ArrayList<ScEtcptjdEntity>();
		log.info("外地车地图区域功能运行");
		Map<String, Object> map = new HashMap<String, Object>();
		double jin = Double.parseDouble(jingdu);
		double wei = Double.parseDouble(weidu);
		int co = Integer.parseInt(count);
		map.put("jind", jin);
		map.put("weid", wei);
		map.put("count", co);
		List<ScEtcptjdEntity> listw = zfService.card(map);
		if (listw.size() > 300) {
			for (int k = 0; k < 300; k++) {
				List<ScEtcptjdEntity> cp = zfService.wd(listw.get(k).getCarNumber());
				if (cp != null) {
					Lw.add(listw.get(k));
				}
			}
		} else {
			for (int k = 0; k < listw.size(); k++) {
				ScDtcldzdanEntity dan = zfService.dan(listw.get(k).getCarNumber());
				if (dan != null) {
					Lw.add(listw.get(k));
				}
			}
		}
		return R.ok().put("list", Lw);
	}

	@RequestMapping("/listZf")
	public R list() {

		List<Integer> list = zfService.getCount();

		return R.ok().put("list", list);
	}

}