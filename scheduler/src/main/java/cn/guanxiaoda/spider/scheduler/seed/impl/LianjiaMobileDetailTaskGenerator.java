package cn.guanxiaoda.spider.scheduler.seed.impl;

import cn.guanxiaoda.spider.core.dao.HouseDao;
import cn.guanxiaoda.spider.core.enums.Entity;
import cn.guanxiaoda.spider.core.enums.Site;
import cn.guanxiaoda.spider.core.enums.Source;
import cn.guanxiaoda.spider.core.enums.Type;
import cn.guanxiaoda.spider.core.item.Task;
import cn.guanxiaoda.spider.core.po.HouseInfo;
import cn.guanxiaoda.spider.scheduler.annotation.TaskGenerator;
import cn.guanxiaoda.spider.scheduler.seed.BaseTaskGenerator;
import cn.guanxiaoda.spider.scheduler.seed.ITaskGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by guanxiaoda on 2018/2/2.
 */
@Slf4j
@TaskGenerator(site = Site.LIANJIA, source = Source.MOBEL, entity = Entity.HOUSE, type = Type.DETAIL)
public class LianjiaMobileDetailTaskGenerator extends BaseTaskGenerator implements ITaskGenerator {

    @Autowired
    private HouseDao houseDao;

    @Override
    public List<Task> getTaskList(int site, int source, int entity, int type) {
        List<HouseInfo> houseList = houseDao.findAll();
        log.info("{} read {} items from database.", this.getClass().getSimpleName(), houseList.size());
        return houseList.stream().map(house -> {
            Task task = new Task(site, source, entity, type);
            task.getMeta().put("id", String.valueOf(house.getId()));
            task.getMeta().put("itemId", house.getItemId());
            task.genTaskId();

            return task;
        }).collect(Collectors.toList());
    }
}
