package cn.guanxiaoda.spider.engine.component.impl.storager;

import cn.guanxiaoda.spider.core.enums.Entity;
import cn.guanxiaoda.spider.core.enums.Type;
import cn.guanxiaoda.spider.core.item.ParseResult;
import cn.guanxiaoda.spider.core.item.Task;
import cn.guanxiaoda.spider.core.po.HouseInfo;
import cn.guanxiaoda.spider.engine.annotation.Storager;
import cn.guanxiaoda.spider.engine.component.BaseStorager;
import cn.guanxiaoda.spider.engine.component.IStorager;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * Created by guanxiaoda on 2018/1/20.
 */
@Storager(entity = Entity.HOUSE, type = Type.LIST)
@Slf4j
public class LianjiaMobileHouseListStorager extends BaseStorager<HouseInfo> implements IStorager {


    @Override
    public boolean processer(Task task) {
        dao.create(Entity.valueOf(task.getEntity()).getEntityClass(), false);
        ParseResult<HouseInfo> parseResult = task.getParseResult();

        List<HouseInfo> houseList = JSON.parseObject(String.valueOf(task.getParseResult().getData()), new TypeReference<List<HouseInfo>>() {});

        houseList.stream().forEach(
                houseInfo -> {
                    try {
                        dao.insertOrUpdate(houseInfo);
                    } catch (Exception e) {
                        log.error("{} insert or update DB failure.", e);
                    }
                }
        );


        return true;

    }
}
