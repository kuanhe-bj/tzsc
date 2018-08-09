package io.renren.service.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.renren.dao.Sc_dtdzdanmxDao;
import io.renren.service.Sc_dtdzdanmxService;
import io.renren.vo.GwrysjVo;

/**
 * 电子档案挖掘分析模型
 * @author moulin
 *
 */
@Service("sc_dtdzdanmxService")
public class Sc_dtdzdanmxServiceImpl implements Sc_dtdzdanmxService {
	
	@Autowired
	private Sc_dtdzdanmxDao sc_dtdzdanmxDao;
	
	@Override
	public List<GwrysjVo> queryList(Map<String, Object> map) {
		List<GwrysjVo> list = this.sc_dtdzdanmxDao.queryList(map);
//		for (GwrysjVo gwrysjVo : list) {
//			System.out.println(gwrysjVo.getAge());
//		}
		return list;
	}

	@Override
	public int queryTotal(Map<String, Object> map) {
		return sc_dtdzdanmxDao.queryTotal(map);
	}

	@Override
	public List<GwrysjVo> list(Map<String, Object> map) {
		String search = map.get("search").toString();
		
		//通配符查询
		String regex = "[a-zA-Z0-9\\u4e00-\\u9fa5]";
		String hanzi = "[\u4e00-\u9fa5]";
		String zimu = "[a-zA-Z]";
		String shuzi = "[0-9]";
		
		Matcher matcher = Pattern.compile(regex).matcher(search);
		Matcher matcherh = Pattern.compile(hanzi).matcher(search);
		Matcher matcherz = Pattern.compile(zimu).matcher(search);
		Matcher matchers = Pattern.compile(shuzi).matcher(search);
		StringBuffer sb = new StringBuffer();
		StringBuffer sb1 = new StringBuffer();
		StringBuffer sb2 = new StringBuffer();
		StringBuffer sb3 = new StringBuffer();
		while (matcher.find()) {
			sb.append(matcher.group());
		}
		while (matcherh.find()) {
			sb1.append(matcherh.group());
		}
		while (matcherz.find()) {
			sb2.append(matcherz.group());
		}
		while (matchers.find()) {
			sb3.append(matchers.group());
		}
		
		if(search.equals(sb1.toString())) {
			//System.out.println("4444");
			map.put("search", sb1.toString());
			search = sb1.toString();
			if(search.contains("和")) {
				String[] str = search.split("和");
				//System.out.println(str[0] + "," + str[1]);
				map.put("search1", str[0]);
				map.put("search2", str[1]);
				List<GwrysjVo> list1 = sc_dtdzdanmxDao.listAnd(map);
				return list1;
			} else if(search.contains("或")) {
				String[] str = search.split("或");
				map.put("search1", str[0]);
				map.put("search2", str[1]);
				List<GwrysjVo> list1 = sc_dtdzdanmxDao.listAnd(map);
				return list1;
			} else if(search.contains("非")) {
				String[] str = search.split("非");
				map.put("search", str[1]);
				List<GwrysjVo> list2 = sc_dtdzdanmxDao.listNot(map);
				return list2;
			} 
			List<GwrysjVo> list = sc_dtdzdanmxDao.list(map);
			return list;
		} else if(search.equals(sb2.toString())) {
			//System.out.println("3333");
			map.put("search", sb2.toString());
			List<GwrysjVo> list = sc_dtdzdanmxDao.list(map);
			return list;
		} else if(search.equals(sb3.toString())) {
			//System.out.println("1111");
			Integer sz = Integer.parseInt(sb3.toString());
			map.put("search", sz);
			List<GwrysjVo> list = sc_dtdzdanmxDao.listAge(map);
			return list;
		} else if(!search.equals(sb1.toString()) 
				|| !search.equals(sb2.toString()) 
				|| !search.equals(sb3.toString())) {
			//System.out.println("222");
			List<GwrysjVo> list = sc_dtdzdanmxDao.list(map);
			if(list.size() == 0) {
				List<GwrysjVo> listArray = new ArrayList<GwrysjVo>();
				for (int i = 0; i < search.length(); i++) {
					char c = search.charAt(i);
					map.put("search", c);
					List<GwrysjVo> listG = sc_dtdzdanmxDao.list(map);
					for (GwrysjVo gwrysjVo : listG) {
						listArray.add(gwrysjVo);
					}
				}
				return listArray;
			}
			return list;
		}
		return sc_dtdzdanmxDao.list(map);
	}
	
	@Override
	public int total(Map<String, Object> map) {
		String search = map.get("search").toString();
		String regex = "[a-zA-Z0-9\\u4e00-\\u9fa5]";
		String hanzi = "[\u4e00-\u9fa5]";
		String zimu = "[a-zA-Z]";
		String shuzi = "[0-9]";

		Matcher matcher = Pattern.compile(regex).matcher(search);
		Matcher matcherh = Pattern.compile(hanzi).matcher(search);
		Matcher matcherz = Pattern.compile(zimu).matcher(search);
		Matcher matchers = Pattern.compile(shuzi).matcher(search);
		StringBuffer sb = new StringBuffer();
		StringBuffer sb1 = new StringBuffer();
		StringBuffer sb2 = new StringBuffer();
		StringBuffer sb3 = new StringBuffer();
		while (matcher.find()) {
			sb.append(matcher.group());
		}
		while (matcherh.find()) {
			sb1.append(matcherh.group());
		}
		while (matcherz.find()) {
			sb2.append(matcherz.group());
		}
		while (matchers.find()) {
			sb3.append(matchers.group());
		}

		if (search.equals(sb1.toString())) {
			map.put("search", sb1.toString());
			search = sb1.toString();
			if (search.contains("和")) {
				String[] str = search.split("和");
				// System.out.println(str[0] + "," + str[1]);
				map.put("search1", str[0]);
				map.put("search2", str[1]);
				List<GwrysjVo> list1 = sc_dtdzdanmxDao.listAndTotal(map);
				return list1.size();
			} else if (search.contains("或")) {
				String[] str = search.split("或");
				map.put("search1", str[0]);
				map.put("search2", str[1]);
				List<GwrysjVo> list1 = sc_dtdzdanmxDao.listAndTotal(map);
				return list1.size();
			} else if (search.contains("非")) {
				String[] str = search.split("非");
				map.put("search", str[1]);
				List<GwrysjVo> list2 = sc_dtdzdanmxDao.listNotTotal(map);
				return list2.size();
			}
			List<GwrysjVo> list = sc_dtdzdanmxDao.listTotal(map);
			return list.size();
		} else if (search.equals(sb2.toString())) {
			map.put("search", sb2.toString());
			List<GwrysjVo> list = sc_dtdzdanmxDao.listTotal(map);
			return list.size();
		} else if (search.equals(sb3.toString())) {
			//System.out.println("11111");
			Integer sz = Integer.parseInt(sb3.toString());
			//System.out.println("sz" + sz);
			map.put("search", sz);
			List<GwrysjVo> list = sc_dtdzdanmxDao.listAgeTotal(map);
			return list.size();
		} else if (!search.equals(sb1.toString()) || !search.equals(sb2.toString()) || !search.equals(sb3.toString())) {
			List<GwrysjVo> list = sc_dtdzdanmxDao.listTotal(map);
			if (list.size() == 0) {
				List<GwrysjVo> listArray = new ArrayList<GwrysjVo>();
				for (int i = 0; i < search.length(); i++) {
					char c = search.charAt(i);
					map.put("search", c);
					List<GwrysjVo> listG = sc_dtdzdanmxDao.listTotal(map);
					for (GwrysjVo gwrysjVo : listG) {
						listArray.add(gwrysjVo);
					}
				}
				return listArray.size();
			}
			return list.size();
		}
		return sc_dtdzdanmxDao.listTotal(map).size();
	}

}
