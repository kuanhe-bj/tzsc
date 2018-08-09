package io.renren.controller;

import io.renren.common.utils.R;
import io.renren.service.Dsj_xxcjService;
import io.renren.service.Sc_blacklistService;
import io.renren.service.Sc_dtcldzdanService;
import io.renren.service.Sc_scajService;
import io.renren.vas.entity.ScBlacklistEntity;
import io.renren.vas.entity.ScDtcldzdanEntity;
import io.renren.vas.entity.ScScajEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/generator/sc_scaj")
public class Sc_scajController {

    @Autowired
    private Sc_dtcldzdanService sc_dtcldzdanService;

    @Autowired
    private Sc_blacklistService sc_blacklistService;

    @Autowired
    private Sc_scajService scScajService;

    @Autowired
    private Dsj_xxcjService dsj_xxcjService;

    /**
     * 动态车辆电子档案
     */
    @RequestMapping("/check")
    public R check(@RequestBody String plate) {

        log.info("进入到sc_scajController check", "动态车辆电子档案");

        List<ScDtcldzdanEntity> list = sc_dtcldzdanService.plateByList(plate);

        return R.ok().put("list", list);
    }

    @RequestMapping("/save")
    public R save(@RequestBody String carNum) {

        log.info("-------------------添加功能開始-------------------");

        ScBlacklistEntity scBlacklistEntity = new ScBlacklistEntity();

        scBlacklistEntity.setCarnum(carNum);

        scBlacklistEntity.setCreateuser("admin1");

        sc_blacklistService.save(scBlacklistEntity);

        log.info("-------------------添加功能結束-------------------");

        return R.ok().put("num", 0);
    }

    @RequestMapping("/update")
    public R update(@RequestBody String plate) {

        log.info("-------------------修改功能開始-------------------");

        List<ScDtcldzdanEntity> list = sc_dtcldzdanService.plateByList(plate);

        if (list.size() != 0) {
            ScBlacklistEntity scBlacklistEntity = new ScBlacklistEntity();
            scBlacklistEntity.setCarnum(list.get(0).getPlate());
            scBlacklistEntity.setCreatereason("涉案车辆");
            scBlacklistEntity.setCreateuser("admin1");
            sc_dtcldzdanService.plateUpdate(plate);
        }

        log.info("-------------------修改功能結束-------------------");

        return R.ok().put("num", 0);
    }

    @RequestMapping("/saveScaj")
    public R saveScaj(@RequestBody ScScajEntity scScaj) {
        List<ScScajEntity> list = dsj_xxcjService.id_cz();
        String str = list.get(0).getId();
        int id = Integer.parseInt(str) + 1;
        scScaj.setId(id + "");
        scScajService.save(scScaj);
        return R.ok();
    }

    @RequestMapping("/updateScaj")
    public R updateScaj(@RequestBody ScScajEntity scScaj) {
        scScajService.update(scScaj);
        return R.ok();
    }
}
