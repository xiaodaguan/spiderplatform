package cn.guanxiaoda.spider.engine.component.impl.storager;

import cn.guanxiaoda.spider.core.enums.Entity;
import cn.guanxiaoda.spider.core.enums.Site;
import cn.guanxiaoda.spider.core.enums.Source;
import cn.guanxiaoda.spider.core.enums.Type;
import cn.guanxiaoda.spider.core.item.Task;
import cn.guanxiaoda.spider.core.po.HouseInfo;
import cn.guanxiaoda.spider.engine.annotation.Storager;
import cn.guanxiaoda.spider.engine.component.BaseStorager;
import cn.guanxiaoda.spider.engine.component.IStorager;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by guanxiaoda on 2018/1/20.
 */
@Storager(entity = Entity.HOUSE, type = Type.LIST)
@Slf4j
public class LianjiaMobileHouseListStorager extends BaseStorager<HouseInfo> implements IStorager {


    @Override
    protected List<HouseInfo> clean(List<HouseInfo> items, Task task) {
        return items.parallelStream().peek(item -> {
            item.setUpdateTime(new Date());
            item.setSite(Site.LIANJIA.ordinal());
            item.setSource(Source.MOBEL.ordinal());
        }).collect(Collectors.toList());
    }

    @Override
    protected HouseInfo clean(HouseInfo item, Task task) {
        return null;
    }

    @Override
    public boolean processe(Task task) {
        {
            dao.create(Entity.valueOf(task.getEntity()).getEntityClass(), false);

            List<HouseInfo> itemList = JSON.parseObject(String.valueOf(task.getParseResult().getData()), new TypeReference<List<HouseInfo>>() {});

            clean(itemList, task).stream().forEach(
                    item -> {
                        try {
                            dao.insertOrUpdate(item);
                        } catch (Exception e) {
                            log.error("{} insert or update DB failure.", e);
                        }
                    }
            );


            return true;

        }
    }
}
