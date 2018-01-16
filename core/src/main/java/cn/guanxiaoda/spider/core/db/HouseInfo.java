package cn.guanxiaoda.spider.core.db;

import lombok.Data;
import org.nutz.dao.entity.annotation.Table;

/**
 * 二手房信息
 * https://m.lianjia.com/bj/ershoufang/pg2/?_t=1
 * Created by guanxiaoda on 2018/1/16.
 */
@Table("house_info")
@Data
public class HouseInfo {

    private String name;

    private String itemId;

    // 户型
    private String houseType;
    // 朝向
    private String orientation;

    private String floor;

    private String area;

    // 标签, e.g. '地铁,满五年,有电梯'
    private String tags;

    // 总价
    private Integer price;

    // 单价
    private Float unitPrice;

    // 挂牌日期
    private String onlineDate;

    // 楼型 e.g. 塔楼
    private String buildType;

    // 装修 e.g. 简装
    private String decoration;

    // 用途 e.g. 普通住宅
    private String purpose;

    // 权属 e.g. 私产
    private String propertyRight;

    // 建筑年代
    private String buildYear;

    // 首付预算
    private String downPayment;

    // 小区
    private String communityId;


    /**
     * 户型分间 e.g.
     * '客厅: 9.4㎡/ 北/ 普通窗,卧室: 14.53㎡/ 南/ 普通窗,厨房: 4.05㎡/ 北/ 普通窗'
     */
    private String houseTypeDetail;

    private Float lat;
    private Float lng;
    // 坐标系
    private Integer coordinateFlag;

    // 房源介绍
    private String desc;
    // 房主自荐
    private String selfIntro;
    // 带看反馈(list)
    private String feedbackId;

    // 房源动态
    // 7日带看
    private Integer inspectTimeWeek;
    // 30日带看
    private Integer inspectTimeMonth;

    // 关注人
    private Integer focus;

    // 同小区成交量
    private Integer communityDealCount;
}
