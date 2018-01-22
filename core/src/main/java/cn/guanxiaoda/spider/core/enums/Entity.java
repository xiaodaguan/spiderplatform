package cn.guanxiaoda.spider.core.enums;

import cn.guanxiaoda.spider.core.po.BaseEntity;
import cn.guanxiaoda.spider.core.po.HouseInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Created by guanxiaoda on 2018/1/12.
 */
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public enum Entity {
    /**
     * 未分类
     */
    DEFAULT("Default", BaseEntity.class),
    /**
     * 房
     */
    HOUSE("House", HouseInfo.class),;
    private String name;
    private Class entityClass;

    public static Entity valueOf(int ordinal) {
        if (ordinal < 0 || ordinal >= values().length) {
            throw new IndexOutOfBoundsException("Invalid Entity ordinal.");
        }
        return values()[ordinal];
    }

}
