package io.renren.controller;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.common.utils.R;
import io.renren.service.TccService;
import io.renren.vas.entity.ScEtcptjdEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
/**
 * 
 * 
 * @author pwk
 * @email sunlightcs@gmail.com
 * @date 2018-02-11 13:39:58
 */
@RestController
@RequestMapping("/generator/tcc")
public class TccController {
	@Autowired
	private TccService tccService;

	@RequestMapping("/xq")
	public R finc(@RequestParam Map<String, Object> params) {
		// 查询列表数据
		Query query = new Query(params);
		List<ScEtcptjdEntity> list = tccService.xq(query);
		PageUtils pageUtil = new PageUtils(list, list.size(), query.getLimit(), query.getPage());
		return R.ok().put("page", pageUtil);
	}
//	@RequestMapping("/fin")
//	public R fin(@RequestParam Map<String, Object> params) {
//		// 查询列表数据
//		Query query = new Query(params);
//		List<ScEtcptjdEntity> list = tccService.finc(query);
//		PageUtils pageUtil = new PageUtils(list, list.size(), query.getLimit(), query.getPage());
//		return R.ok().put("page", pageUtil);
//	}
//	
//	@RequestMapping("/finc")
//	public R checkListc(@RequestBody ScEtcptjdEntity scEtcptjdEntity) {
//		// 查询列表数据
//		String districtNmId = scEtcptjdEntity.getDistrictNmId();
//		String parkNm = scEtcptjdEntity.getParkNm();
//		List<ScEtcptjdEntity> list = tccService.finc(districtNmId, parkNm);
//		return R.ok().put("list", list);
//	}
//	

}
