package cn.guanxiaoda.spider.core.enums;

import lombok.*;

/**
 * Created by guanxiaoda on 2018/1/11.
 */
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public enum Source {
    /**
     * 未分类
     */
    DEFAULT("Default"),
    /**
     * pc浏览器
     */
    PC("Web"),
    /**
     * 移动浏览器
     */
    MOBEL("Mobile"),
    /**
     * app
     */
    APP("App"),
    ;

    private String name;

    public static Source valueOf(int ordinal) {
        if (ordinal < 0 || ordinal >= values().length) {
            throw new IndexOutOfBoundsException("Invalid Source ordinal.");
        }
        return values()[ordinal];
    }
}
