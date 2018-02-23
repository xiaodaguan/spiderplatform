package cn.guanxiaoda.spider.core.po;

import lombok.Data;
import org.hibernate.annotations.SQLInsert;

import javax.persistence.*;
import java.util.Date;

/**
 * 二手房信息
 * https://m.lianjia.com/bj/ershoufang/pg2/?_t=1
 *
 * @author guanxiaoda
 * @date 2018/1/16
 */
@Data
@Entity
@Table(name = "house_info")
@SQLInsert(sql = "INSERT INTO house_info " +
        "(area, build_type, build_year, community_deal_count, community_id, " +
        "coordinate_flag, create_time, decoration, description, down_payment, " +
        "elevator, feedback_id, fetch_date, floor, focus, " +
        "house_type, house_type_detail, inspect_time_month, inspect_time_week, item_id, " +
        "lat, lng, name, online_date, orientation, " +
        "price, property_right, purpose, self_intro, site, " +
        "source, tags, unit_price, update_time) " +
        " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)" +
        " ON DUPLICATE KEY UPDATE " +
        " area = VALUES(`area`)," +
        " build_type = VALUES(`build_type`)," +
        " build_year = VALUES(`build_year`)," +
        " community_deal_count = VALUES(`community_deal_count`)," +
        " community_id = VALUES(`community_id`)," +
        " coordinate_flag = VALUES(`coordinate_flag`)," +
        " decoration = VALUES(`decoration`)," +
        " description = VALUES(`description`)," +
        " down_payment = VALUES(`down_payment`)," +
        " elevator = VALUES(`elevator`)," +
        " feedback_id = VALUES(`feedback_id`)," +
        " floor = VALUES(`floor`)," +
        " focus = VALUES(`focus`)," +
        " house_type = VALUES(`house_type`)," +
        " house_type_detail = VALUES(`house_type_detail`)," +
        " inspect_time_month = VALUES(`inspect_time_month`)," +
        " inspect_time_week = VALUES(`inspect_time_week`)," +
        " lat = VALUES(`lat`)," +
        " lng = VALUES(`lng`)," +
        " name = VALUES(`name`)," +
        " online_date = VALUES(`online_date`)," +
        " orientation = VALUES(`orientation`)," +
        " price = VALUES(`price`)," +
        " property_right = VALUES(`property_right`)," +
        " purpose = VALUES(`purpose`)," +
        " self_intro = VALUES(`self_intro`)," +
        " tags = VALUES(`tags`)," +
        " unit_price = VALUES(`unit_price`)," +
        " update_time = VALUES(`update_time`)," +
        " fetch_date = VALUES(`fetch_date`)")
public class HouseInfo {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "item_id")
    private String itemId;

    // 户型
    @Column(name = "house_type")
    private String houseType;
    // 朝向
    @Column(name = "orientation")
    private String orientation;

    @Column(name = "floor")
    private String floor;

    @Column(name = "elevator")
    private String elevator;

    @Column(name = "area")
    private String area;

    // 标签, e.g. '地铁,满五年,有电梯'
    @Column(name = "tags")
    private String tags;

    // 总价
    @Column(name = "price")
    private String price;

    // 单价
    @Column(name = "unit_price")
    private String unitPrice;

    // 挂牌日期
    @Column(name = "online_date")
    private String onlineDate;

    // 楼型 e.g. 塔楼
    @Column(name = "build_type")
    private String buildType;

    // 装修 e.g. 简装
    @Column(name = "decoration")
    private String decoration;

    // 用途 e.g. 普通住宅
    @Column(name = "purpose")
    private String purpose;

    // 权属 e.g. 私产
    @Column(name = "property_right")
    private String propertyRight;

    // 建筑年代
    @Column(name = "build_year")
    private String buildYear;

    // 首付预算
    @Column(name = "down_payment")
    private String downPayment;

    // 小区
    @Column(name = "community_id")
    private String communityId;


    /**
     * 户型分间 e.g.
     * '客厅: 9.4㎡/ 北/ 普通窗,卧室: 14.53㎡/ 南/ 普通窗,厨房: 4.05㎡/ 北/ 普通窗'
     */
    @Column(name = "house_type_detail")
    private String houseTypeDetail;

    @Column(name = "lat")
    private String lat;

    @Column(name = "lng")
    private String lng;
    // 坐标系
    @Column(name = "coordinate_flag")
    private Integer coordinateFlag;

    // 房源介绍
    @Column(name = "description")
    private String description;
    // 房主自荐
    @Column(name = "self_intro")
    private String selfIntro;
    // 带看反馈(list)
    @Column(name = "feedback_id")
    private String feedbackId;

    // 房源动态
    // 7日带看
    @Column(name = "inspect_time_week")
    private Integer inspectTimeWeek;
    // 30日带看
    @Column(name = "inspect_time_month")
    private Integer inspectTimeMonth;

    // 关注人数
    @Column(name = "focus")
    private Integer focus;

    // 同小区成交量
    @Column(name = "community_deal_count")
    private Integer communityDealCount;

    @Column(name = "site")
    private Integer site;
    @Column(name = "source")
    private Integer source;
    @Column(name = "create_time")
    private Date createTime;
    @Column(name = "update_time")
    private Date updateTime;

    @Column(name = "fetch_date")
    private java.sql.Date fetchDate;
}
