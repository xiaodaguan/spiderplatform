package cn.guanxiaoda.spider.core.dao;

import cn.guanxiaoda.spider.core.po.HouseInfo;
import cn.guanxiaoda.spider.engine.EngineApplication;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

/**
 * Created by guanxiaoda on 2018/2/6.
 */
@SpringBootTest(classes = EngineApplication.class)
@ContextConfiguration
@RunWith(SpringRunner.class)
@EnableJpaRepositories("cn.guanxiaoda.spider.core.dao")
@EntityScan("cn.guanxiaoda.spider.core.po")
public class SpringDataJpaDaoTest {

    @Autowired
    HouseDao houseDao;


    HouseInfo newHouseInfo;

    @Before
    public void createEntity() {
        newHouseInfo = new HouseInfo();
        newHouseInfo.setId(666);
        newHouseInfo.setItemId("99999");
        newHouseInfo.setName("test");
        newHouseInfo.setSite(0);
        newHouseInfo.setSource(0);
        newHouseInfo.setCreateTime(new Date());
        newHouseInfo.setUpdateTime(new Date());
        newHouseInfo.setFetchDate(new java.sql.Date(System.currentTimeMillis()));
    }

    @Test
    public void createTest() {
        houseDao.save(newHouseInfo);
    }

    @Test
    public void readTest() {
        List<HouseInfo> houseInfoList = houseDao.findAll();
        Assert.assertNotEquals(0, houseInfoList.size());

    }

    @Test
    public void readSingleTest() {
    }

    @Test
    public void updateTest() {
        houseDao.save(newHouseInfo);
    }

    @Test
    public void deleteTest() {
    }
}
