package cn.guanxiaoda.spider.core.enums;

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
public enum Type {

    /**
     *
     */
    DEFAULT("Default"),
    /**
     * 列表
     */
    LIST("List"),
    /**
     * 详情
     */
    DETAIL("Detail"),
    ;
    private String name;

    public static Type valueOf(int ordinal) {
        if (ordinal < 0 || ordinal >= values().length) {
            throw new IndexOutOfBoundsException("Invalid Type ordinal.");
        }
        return values()[ordinal];
    }
}
