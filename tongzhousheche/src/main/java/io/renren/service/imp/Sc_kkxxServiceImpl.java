package io.renren.service.imp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.renren.dao.Sc_kkxxDao;
import io.renren.service.Sc_kkxxService;
import io.renren.vo.KakouRecordVo;
import io.renren.vo.KakoubsVo;

/**
 * 卡口信息，伴随分析
 * @author moulin
 *
 */
@Service("sc_kkxxService")
public class Sc_kkxxServiceImpl implements Sc_kkxxService {

	@Autowired
	private Sc_kkxxDao sc_kkxxDao;

	@Override
	public List<KakouRecordVo> findKkxx(Map<String, Object> map) {
		List<KakouRecordVo> list = sc_kkxxDao.findKkxx(map);
		double f = frequency(map);
		for (KakouRecordVo kakouRecordVo : list) {
			kakouRecordVo.setFrequency(f);
		}
		return list;
	}

	@Override
	public List<KakouRecordVo> find(String kid, String start, String end) {
		return sc_kkxxDao.find(kid, start, end);
	}

	@Override
	public double frequency(Map<String, Object> params) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		double f = 0;
		String start = (String) params.get("start");
		String end = (String) params.get("end");
		try {
			Date startTime = sdf.parse(start);
			Date endTime = sdf.parse(end);
			long day = (endTime.getTime() - startTime.getTime()) / (24 * 60 * 60 * 1000);
			List<KakouRecordVo> list = sc_kkxxDao.findKkxx(params);
			for (KakouRecordVo kakouRecordVo : list) {
				String num = kakouRecordVo.getNum();
				f = Integer.parseInt(num) / day;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return f;
	}

	@Override
	public List<KakouRecordVo> checkKkxx(Map<String, Object> params) {
		Integer num = Integer.parseInt(params.get("num").toString());
		Integer time = Integer.parseInt(params.get("time").toString());
		params.put("num", num);
		params.put("time", time);
		return sc_kkxxDao.list(params);
	}
	
	@Override
	public List<KakoubsVo> checkKkbs(String cph1, String cph2, int time) {
		// 封装跟随车辆的信息。
		List<KakoubsVo> KakoubsList = sc_kkxxDao.getCount(cph1, cph2, time);
		return KakoubsList;
	}

	@Override
	public int queryTotal(Map<String, Object> map) {
		return sc_kkxxDao.queryTotal();
	}

	@Override
	public List<KakoubsVo> kkbs(Map<String, Object> map) {
		return sc_kkxxDao.kkbs(map);
	}

	@Override
	public int total(Map<String, Object> params) {
		String carNum = (String) params.get("cph");
		String start = (String) params.get("start");
		String end = (String) params.get("end");
		Integer num = Integer.parseInt(params.get("num").toString());
		Integer time = Integer.parseInt(params.get("time").toString());
		return sc_kkxxDao.total(carNum, start, end, num, time);

	}

	@Override
	public int getCount(Map<String, Object> map) {
		String cph1 = (String) map.get("cph1");
		String cph2 = (String) map.get("cph2");
		int time = Integer.parseInt(map.get("time").toString());
		return sc_kkxxDao.getCount(cph1, cph2, time).size();
	}
}
