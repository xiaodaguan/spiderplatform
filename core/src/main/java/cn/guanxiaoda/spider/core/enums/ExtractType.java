package cn.guanxiaoda.spider.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Created by guanxiaoda on 2018/1/18.
 */
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public enum ExtractType {

    LIST("property list"),
    SINGLE("single property"),;

    private String name;
}
