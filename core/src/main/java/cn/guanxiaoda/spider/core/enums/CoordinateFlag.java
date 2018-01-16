package cn.guanxiaoda.spider.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Created by guanxiaoda on 2018/1/16.
 */
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public enum CoordinateFlag {

    DEFAULT("未知"),
    BAIDU("百度"),
    GOOGLE("谷歌"),
    GAODE("高德"),
    TECENT("腾讯"),;

    private String name;
}
