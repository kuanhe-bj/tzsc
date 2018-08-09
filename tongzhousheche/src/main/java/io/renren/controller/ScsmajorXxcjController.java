package io.renren.controller;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.common.utils.R;
import io.renren.service.Dsj_xxcjService;
import io.renren.vas.entity.ScDtcldzdanEntity;
import io.renren.vas.entity.ScScajEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/generator/dsj_xxcj")
@Slf4j
public class ScsmajorXxcjController {
    @Autowired
    private Dsj_xxcjService dsj_xxcjService;

    /* 大事件信息采集显示列表LIST */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params) throws ParseException {
        // 查询列表数据
        Query query = new Query(params);
        List<ScScajEntity> list = dsj_xxcjService.list(query);
        int total = dsj_xxcjService.queryTotal(query);
        log.info("大事件信息采集列表方法运行");
        PageUtils pageUtil = new PageUtils(list, total, query.getLimit(), query.getPage());
        return R.ok().put("page", pageUtil);
    }

    /* 大事件信息采集添加 */
    @RequestMapping("/insert")
    public R insert(String ajbh, String ajmc, String pcsgx, String jq, String fadd, String faddxz, String fakssj,
                    String sacph, String jd, String wd, String jyaq, String gisx, String gisy) throws ParseException {
        // 查询列表数据
        double gx = Double.parseDouble(gisx);
        double gy = Double.parseDouble(gisy);
        double j = Double.parseDouble(jd);
        double w = Double.parseDouble(wd);
        List<ScScajEntity> e = id_sc();
        int id = 1;
        if (e.get(0).getId() != null) {

            String a = e.get(0).getId();
            id = Integer.parseInt(a);
            id = id + 1;
        }
        int count = dsj_xxcjService.insert(id, ajbh, ajmc, pcsgx, jq, fadd, faddxz, fakssj, sacph, j, w, jyaq, gx, gy);
        log.info("大事件信息采集添加方法运行");
        return R.ok().put("count", count);
    }

    /* ID查看 */
    @RequestMapping("/select_ID")
    public R insert(String id) throws ParseException {
        // 查询列表数据
        int d = Integer.parseInt(id);
        List<ScScajEntity> list = dsj_xxcjService.list_ID(d);
        log.info("大事件信息查找修改数据方法运行");
        return R.ok().put("list", list);
    }

    /* ID修改 */
    @RequestMapping("/update")
    public R update(String ajbh, String ajmc, String pcsgx, String jq, String fadd, String faddxz, String fakssj,
                    String sacph, String jd, String wd, String jyaq, String id, String gisx, String gisy)
            throws ParseException {
        // 查询列表数据
        double j = Double.parseDouble(jd);
        double w = Double.parseDouble(wd);
        double gx = Double.parseDouble(gisx);
        double gy = Double.parseDouble(gisy);
        int count = dsj_xxcjService.update(ajbh, ajmc, pcsgx, jq, fadd, faddxz, fakssj, sacph, j, w, jyaq, id, gx, gy);
        System.out.println("count=" + count);
        log.info("大事件信息采集修改方法运行");
        return R.ok().put("count", count);
    }

    /* 查看 dan表是否存在车牌 */
    @RequestMapping("/list_dan")
    public R list_dan(String plate) throws ParseException {
        // 查询列表数据
        List<ScDtcldzdanEntity> list = dsj_xxcjService.list_dan(plate);
        log.info("大事件信息查找查看dan表中是否存在车牌方法运行");
        return R.ok().put("list", list);
    }

    /* 添加dan表中isInvoled字段值为1 */
    @RequestMapping("/update_dan")
    public R update_dan(String plate) throws ParseException {
        // 查询列表数据
        int count = dsj_xxcjService.update_dan(plate);
        log.info("大事件信息查找查看dan表中是否存在车牌方法运行");
        return R.ok().put("count", count);
    }

    /* blacklist表中添加车牌信息 */
    @RequestMapping("/insert_blick")
    public R insert_blick(String cph, String user, String createReason) throws ParseException {
        // 查询列表数据
        int count = dsj_xxcjService.insert_blick(cph, user, createReason);
        log.info("大事件信息查找查看dan表中是否存在车牌方法运行");
        return R.ok().put("count", count);
    }

    public List<ScScajEntity> id_sc() {
        List<ScScajEntity> e = dsj_xxcjService.id_cz();
        return e;
    }

    @RequestMapping("/cx")
    public R cx(@RequestParam Map<String, Object> params) {
        log.info("大事件信息查找运行");
        Query query = new Query(params);
        List<ScScajEntity> list = dsj_xxcjService.cx(query);
        int total = dsj_xxcjService.count(query);
        log.info("大事件信息采集列表方法运行");
        PageUtils pageUtil = new PageUtils(list, total, query.getLimit(), query.getPage());
        return R.ok().put("page", pageUtil);

    }
}
