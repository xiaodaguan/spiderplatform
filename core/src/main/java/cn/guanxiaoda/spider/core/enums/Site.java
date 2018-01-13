package cn.guanxiaoda.spider.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Created by guanxiaoda on 2018/1/11.
 */
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public enum Site {

    /**
     * 未分类
     */
    DEFAULT("Default"),
    /**
     * 链家
     */
    LIANJIA("Lianjia"),;

    private String name;

    public static Site valueOf(int ordinal) {
        if (ordinal < 0 || ordinal >= values().length) {
            throw new IndexOutOfBoundsException("Invalid Site ordinal.");
        }
        return values()[ordinal];
    }
}
