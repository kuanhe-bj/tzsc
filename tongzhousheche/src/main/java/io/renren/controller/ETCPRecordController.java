package io.renren.controller;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.common.utils.R;
import io.renren.modules.sys.entity.SysUserEntity;
import io.renren.service.ETCPService;
import io.renren.vas.entity.ScKkxxEntity;
import io.renren.vo.ScEtcpEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author zq
 * @email 256096868@qq.com
 * @date 2018-03-09 15:19:34
 */
@Slf4j
@RestController
@RequestMapping(value = {"/etcp", "/interface/MotorVehicles"})
public class ETCPRecordController {

    @Autowired
    private ETCPService scETCPService;


    /**
     * ETCP查询
     */
    @RequestMapping(value = {"/etcpList", "/list"})
    public R etcpList(@RequestParam Map<String, Object> params) {

        log.info("进入到这里了：{}", "ETCP查询");
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, -7);
        Date today = calendar.getTime();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
        String result = df.format(today);
        String jssj = (String) params.get("jssj");

        if (!StringUtils.isNotBlank(jssj)) {

            params.put("jssj", df.format(date));
            params.put("qssj", result);

        }
        String cph = (String) params.get("cph");
        log.info("params的值：{}", params);
        if (!StringUtils.isNotBlank(cph)) {
            params.remove("cph");
        }
        String park = (String) params.get("park");
        if (!StringUtils.isNotBlank(park)) {
            params.remove("park");
        }
        String address = (String) params.get("address");
        if (!StringUtils.isNotBlank(address)) {
            params.remove("address");
        }
        Query query = new Query(params);
        log.info("query的值：{}", query);
        List<ScEtcpEntity> vehicleRecordVoList = scETCPService.etcpList(query);
        int total = scETCPService.queryTotal(query);
        PageUtils pageUtil = new PageUtils(vehicleRecordVoList, total, query.getLimit(), query.getPage());

        log.info("返回结果：{}", pageUtil);
        R.ok().put("total", total);
        return R.ok().put("page", pageUtil);
    }

    /**
     * 一所回流数据
     */
    @RequestMapping("/yiSuoList")
    public R yiSuoList(@RequestParam Map<String, Object> params) {

        log.info("进入到这里了：{}", "精确查询");
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, -7);
        Date today = calendar.getTime();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
        String jssj = (String) params.get("jssj");
        String result = df.format(today);
        String cph = (String) params.get("cph");
        log.info("params的值：{}", params);
        if (!StringUtils.isNotBlank(jssj)) {
            params.put("jssj", df.format(date));
            params.put("qssj", result);

        }
        String color = (String) params.get("color");
        if (!StringUtils.isNotBlank(color)) {
            params.remove("color");
        }

        if (!StringUtils.isNotBlank(cph)) {
            params.remove("cph");
        }
        String model = (String) params.get("model");
        if (!StringUtils.isNotBlank(model)) {
            params.remove("model");
        }
        String brand = (String) params.get("brand");
        if (!StringUtils.isNotBlank(brand)) {
            params.remove("brand");
        }
        String sjly = (String) params.get("sjly");
        if (!StringUtils.isNotBlank(sjly)) {
            params.remove("sjly");
        }
        String qclass = (String) params.get("qclass");
        if (!StringUtils.isNotBlank(qclass)) {
            params.remove("qclass");
        }
        String paltecolor = (String) params.get("paltecolor");
        if (!StringUtils.isNotBlank(paltecolor)) {
            params.remove("paltecolor");
        }
        String pclass = (String) params.get("pclass");
        if (!StringUtils.isNotBlank(pclass)) {
            params.remove("pclass");
        }
        String calling = (String) params.get("calling");
        if (!StringUtils.isNotBlank(calling)) {
            params.remove("calling");
        }
        String safetyBelt = (String) params.get("safetyBelt");
        if (!StringUtils.isNotBlank(safetyBelt)) {
            params.remove("safetyBelt");
        }
        String speed = (String) params.get("speed");
        if (!StringUtils.isNotBlank(speed)) {
            params.remove("speed");
        }
        String numOfPassenger = (String) params.get("numOfPassenger");
        if (!StringUtils.isNotBlank(numOfPassenger)) {
            params.remove("numOfPassenger");
        }
        Query query = new Query(params);
        log.info("query的值：{}", query);
        List<ScEtcpEntity> vehicleRecordVoList = scETCPService.yiSuoList(query);
        int total = scETCPService.yiSuoListTotals(query);
        PageUtils pageUtil = new PageUtils(vehicleRecordVoList, total, query.getLimit(), query.getPage());

        log.info("返回结果：{}", pageUtil);
        R.ok().put("total", total);
        return R.ok().put("page", pageUtil);
    }

    /**
     * 一所回流数据快速查询
     */
    @RequestMapping("/fastList")
    public R fastList(@RequestParam Map<String, Object> params) {

        log.info("进入到这里了：{}", "快速查询");
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, -7);
        Date today = calendar.getTime();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
        String jssj = (String) params.get("jssj");
        String result = df.format(today);
        String cph = (String) params.get("cph");
        log.info("params的值：{}", params);
        if (!StringUtils.isNotBlank(jssj)) {
            params.put("jssj", df.format(date));
            params.put("qssj", result);

        }
        String color = (String) params.get("color");
        if (!StringUtils.isNotBlank(color)) {
            params.remove("color");
        }
        String sjly = (String) params.get("sjly");
        if (!StringUtils.isNotBlank(sjly)) {
            params.remove("sjly");
        }
        if (!StringUtils.isNotBlank(cph)) {
            params.remove("cph");
        }
        String model = (String) params.get("model");
        if (!StringUtils.isNotBlank(model)) {
            params.remove("model");
        }
        String brand = (String) params.get("brand");
        if (!StringUtils.isNotBlank(brand)) {
            params.remove("brand");
        }
        String infoKind = (String) params.get("infoKind");
        if (!StringUtils.isNotBlank(infoKind)) {
            params.remove("infoKind");
        }
        String qclass = (String) params.get("qclass");
        if (!StringUtils.isNotBlank(qclass)) {
            params.remove("qclass");
        }
        String paltecolor = (String) params.get("paltecolor");
        if (!StringUtils.isNotBlank(paltecolor)) {
            params.remove("paltecolor");
        }
        String pclass = (String) params.get("pclass");
        if (!StringUtils.isNotBlank(pclass)) {
            params.remove("pclass");
        }
        String calling = (String) params.get("calling");
        if (!StringUtils.isNotBlank(calling)) {
            params.remove("calling");
        }
        String safetyBelt = (String) params.get("safetyBelt");
        if (!StringUtils.isNotBlank(safetyBelt)) {
            params.remove("safetyBelt");
        }
        String speed = (String) params.get("speed");
        if (!StringUtils.isNotBlank(speed)) {
            params.remove("speed");
        }
        String numOfPassenger = (String) params.get("numOfPassenger");
        if (!StringUtils.isNotBlank(numOfPassenger)) {
            params.remove("numOfPassenger");
        }
        Query query = new Query(params);
        log.info("query的值：{}", query);
        List<ScEtcpEntity> vehicleRecordVoList = scETCPService.fastList(query);
        int total = 1000000;
        PageUtils pageUtil = new PageUtils(vehicleRecordVoList, total, query.getLimit(), query.getPage());
        log.info("返回结果：{}", pageUtil);
        R.ok().put("total", total);
        return R.ok().put("page", pageUtil);
    }

    /**
     * 无分页查询
     */
    @RequestMapping("/etcpDataList")
    public R etcpDataList(@RequestBody Map<String, Object> params) {
        log.info("进入：{}", "轨迹查询");
        // 查询列表数据
        log.info("参数：{}", params);
        List<ScEtcpEntity> vehicleRecordVoList = scETCPService.etcpDataList(params);
        log.info("返回结果：{}", vehicleRecordVoList);
        return R.ok().put("page", vehicleRecordVoList);
    }

    @RequestMapping("/etcpGJList")
    public R etcpGJList(@RequestBody Map<String, Object> params) {
        log.info("进入：{}", "轨迹查询");
        log.info("参数：{}", params);
        List<ScEtcpEntity> list = scETCPService.etcpDataList(params);
        log.info("返回结果：{}", list);
        return R.ok().put("page", list);
    }


    /**
     * 颜色
     *
     * @param params
     * @return
     */
    @RequestMapping("/color{colorNum}")
    public R color(@RequestParam String colorNum) {
        log.info("进入：{}", "颜色转换" + colorNum);

        // 查询列表数据
        String color = scETCPService.colorName(colorNum);
        log.info("返回结果：{}", color);
        return R.ok().put("color", color);
    }

    /**
     * 品牌转换
     *
     * @param params
     * @return
     */
    @RequestMapping("/brand{brandNum}")
    public R brand(@RequestParam String brandNum) {
        log.info("进入：{}", "品牌转换" + brandNum);

        // 查询列表数据
        String brand = scETCPService.brandName(brandNum);
        log.info("返回结果：{}", brand);
        return R.ok().put("brand", brand);
    }

    /**
     * 模糊查询
     */
    @RequestMapping("/moHuList")
    public R moHuList(@RequestParam Map<String, Object> params) {
        log.info("进入：{}", "模糊查询");
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, -7);
        Date today = calendar.getTime();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
        String jssj = (String) params.get("jssj");
        String result = df.format(today);
        if (!StringUtils.isNotBlank(jssj)) {
            params.put("jssj", df.format(date));
            params.put("qssj", result);

        }
        String color = (String) params.get("color");
        if (!StringUtils.isNotBlank(color)) {
            params.remove("color");
        }
        String tags = (String) params.get("tags");
        if (!StringUtils.isNotBlank(tags)) {
            params.remove("tags");
        }else {
        	List<ScKkxxEntity> scKkxxEntity=scETCPService.kakouByTags(tags);
        	if (scKkxxEntity!=null) {
        		 params.put("DeviceID", scKkxxEntity.get(0).getId());	
			}
		}
        String cph = (String) params.get("cph");
        if (!StringUtils.isNotBlank(cph)) {
            params.remove("cph");
        }else{
       	 cph=cph.replaceAll("\\*", "_");
       	 params.put("cph", cph);
       }
        String model = (String) params.get("model");
        if (!StringUtils.isNotBlank(model)) {
            params.remove("model");
        }
        String brand = (String) params.get("brand");
        if (!StringUtils.isNotBlank(brand)) {
            params.remove("brand");
        }
        String infoKind = (String) params.get("infoKind");
        if (!StringUtils.isNotBlank(infoKind)) {
            params.remove("infoKind");
        }
        String qclass = (String) params.get("qclass");
        if (!StringUtils.isNotBlank(qclass)) {
            params.remove("qclass");
        }
        String paltecolor = (String) params.get("paltecolor");
        if (!StringUtils.isNotBlank(paltecolor)) {
            params.remove("paltecolor");
        }
        String pclass = (String) params.get("pclass");
        if (!StringUtils.isNotBlank(pclass)) {
            params.remove("pclass");
        }
        String calling = (String) params.get("calling");
        if (!StringUtils.isNotBlank(calling)) {
            params.remove("calling");
        }
        String safetyBelt = (String) params.get("safetyBelt");
        if (!StringUtils.isNotBlank(safetyBelt)) {
            params.remove("safetyBelt");
        }
        String speed = (String) params.get("speed");
        if (!StringUtils.isNotBlank(speed)) {
            params.remove("speed");
        }
        String numOfPassenger = (String) params.get("numOfPassenger");
        if (!StringUtils.isNotBlank(numOfPassenger)) {
            params.remove("numOfPassenger");
        }
        Query query = new Query(params);
        // 查询列表数据
        log.info("参数：{}", query);
        List<ScEtcpEntity> vehicleRecordVoList = scETCPService.moHuList(query);

        int total = scETCPService.total(query);
        PageUtils pageUtil = new PageUtils(vehicleRecordVoList, total, query.getLimit(), query.getPage());
        log.info("返回结果：{}", pageUtil);


        return R.ok().put("page", pageUtil);
    }

    /**
     * 车辆实时位置 监控
     *
     * @param params
     * @return
     */
    @RequestMapping("/rtp")
    public R rtp() {
        log.info("进入：{}", "实时位置监控");
        // 查询最新位置数据
        SysUserEntity user = (SysUserEntity) SecurityUtils.getSubject().getSession().getAttribute("user");
        String username = user.getUsername();
        String cph = scETCPService.moreCphs(username);
        String[] cphs = cph.split(",");
        List<ScEtcpEntity> list = new ArrayList<ScEtcpEntity>();
        for (int i = 0; i < cphs.length; i++) {
            String plate = cphs[i];
            List<ScEtcpEntity> plates = scETCPService.rtpCph(plate);
            list.add(plates.get(0));
        }
        String shua = scETCPService.sTime(username);
        R.ok().put("shua", "shua");
        log.info("返回结果：{}", list);
        return R.ok().put("page", list);
    }

    @RequestMapping("/shijian")
    public R shijian(@RequestBody Map<String, Object> params) throws ParseException {
        log.info("进入：{},参数：{}", "轨迹查询",params);
        // 查询列表数据
        String cph = (String) params.get("cph");
        List<ScEtcpEntity> Sc = scETCPService.shijian(cph);
        //得到时间

        String exittime = Sc.get(0).getExitTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date ex = sdf.parse(exittime);
        Calendar cal = Calendar.getInstance();
        cal.setTime(ex);
        cal.add(Calendar.DAY_OF_YEAR, -7);
        //7天前
        Date timeq = cal.getTime();
        List<Object> li = new ArrayList<Object>();
        li.add(timeq);
        li.add(exittime);
        return R.ok().put("li", li);
    }
    
    
    /**
     * 卡口名称搜索
     *
     * @param params
     * @return
     */
    @RequestMapping("/SearchServlet")
    public R SearchServlet() {
    	List<ScKkxxEntity> ScKkxxList=scETCPService.kakouByName();
    	
    	return R.ok().put("list", ScKkxxList);
    }

}
