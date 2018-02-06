package cn.guanxiaoda.spider.engine.dao;

import cn.guanxiaoda.spider.core.po.HouseInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by guanxiaoda on 2018/2/6.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class DaoTest {

    @Autowired
    HouseDao houseDao;

    @Test
    public void createTest() {
        List<HouseInfo> houseInfoList = houseDao.findAll();
        System.out.println(houseInfoList.size());
    }
}
