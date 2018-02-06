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
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.List;

/**
 * Created by guanxiaoda on 2018/1/20.
 */
@Storager(entity = Entity.HOUSE, type = Type.DETAIL)
@Slf4j
public class LianjiaMobileHouseDetailStorager extends BaseStorager<HouseInfo> implements IStorager {


    @Override
    protected List<HouseInfo> clean(List<HouseInfo> items, Task task) {return null;}

    @Override
    protected HouseInfo clean(HouseInfo item, Task task) {
        item.setUpdateTime(new Date());
        item.setSite(Site.LIANJIA.ordinal());
        item.setSource(Source.MOBEL.ordinal());
        item.setId(Integer.valueOf(task.getMeta().get("id")));
        return item;
    }

    @Override
    public boolean processe(Task task) {

//            dao.create(Entity.valueOf(task.getEntity()).getEntityClass(), false);

        HouseInfo item = JSON.parseObject(String.valueOf(task.getParseResult().getData()), HouseInfo.class);

        try {
            dao.updateIgnoreNull(clean(item, task));
        } catch (Exception e) {
            log.error("{} insert or update DB failure.", e);
        }


        return true;


    }
}
