package io.renren.vas.dao;

import io.renren.modules.sys.dao.BaseDao;
import io.renren.vas.entity.ScBlacklistEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ExcelDao extends BaseDao<ScBlacklistEntity> {

    /**
     * 批量导入数据
     * @param scBlacklistEntityList
     */
    void batchInsert(List<ScBlacklistEntity> scBlacklistEntityList);
}
