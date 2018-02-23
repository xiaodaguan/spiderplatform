package cn.guanxiaoda.spider.core.dao;

import cn.guanxiaoda.spider.core.po.HouseInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.sql.Date;

/**
 * Created by guanxiaoda on 2018/2/6.
 */
@Service
public interface HouseDao extends JpaRepository<HouseInfo, Integer> {

}
