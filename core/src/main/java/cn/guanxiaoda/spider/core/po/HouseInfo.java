package cn.guanxiaoda.spider.core.po;

import lombok.Data;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

/**
 * 二手房信息
 * https://m.lianjia.com/bj/ershoufang/pg2/?_t=1
 * Created by guanxiaoda on 2018/1/16.
 */
@Table("house_info")
@Data
public class HouseInfo extends BaseEntity {

    @Id
    @Column("id")
    private Integer id;

    @Column("name")
    @Name
    private String name;

    @Column("item_id")
    private String itemId;

    // 户型
    @Column("house_type")
    private String houseType;
    // 朝向
    @Column("orientation")
    private String orientation;

    @Column("floor")
    private String floor;

    @Column("elevator")
    private String elevator;

    @Column("area")
    private String area;

    // 标签, e.g. '地铁,满五年,有电梯'
    @Column("tags")
    private String tags;

    // 总价
    @Column("price")
    private String price;

    // 单价
    @Column("unit_price")
    private String unitPrice;

    // 挂牌日期
    @Column("online_date")
    private String onlineDate;

    // 楼型 e.g. 塔楼
    @Column("build_type")
    private String buildType;

    // 装修 e.g. 简装
    @Column("decoration")
    private String decoration;

    // 用途 e.g. 普通住宅
    @Column("purpose")
    private String purpose;

    // 权属 e.g. 私产
    @Column("property_right")
    private String propertyRight;

    // 建筑年代
    @Column("build_year")
    private String buildYear;

    // 首付预算
    @Column("down_payment")
    private String downPayment;

    // 小区
    @Column("community_id")
    private String communityId;


    /**
     * 户型分间 e.g.
     * '客厅: 9.4㎡/ 北/ 普通窗,卧室: 14.53㎡/ 南/ 普通窗,厨房: 4.05㎡/ 北/ 普通窗'
     */
    @Column("house_type_detail")
    private String houseTypeDetail;

    @Column("lat")
    private String lat;

    @Column("lng")
    private String lng;
    // 坐标系
    @Column("coordinate_flag")
    private Integer coordinateFlag;

    // 房源介绍
    @Column("description")
    private String desc;
    // 房主自荐
    @Column("self_intro")
    private String selfIntro;
    // 带看反馈(list)
    @Column("feedback_id")
    private String feedbackId;

    // 房源动态
    // 7日带看
    @Column("inspect_time_week")
    private Integer inspectTimeWeek;
    // 30日带看
    @Column("incpect_time_month")
    private Integer inspectTimeMonth;

    // 关注人数
    @Column("focus")
    private Integer focus;

    // 同小区成交量
    @Column("community_deal_count")
    private Integer communityDealCount;
}
