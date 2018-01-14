package cn.guanxiaoda.spider.core.enums;

import lombok.*;

/**
 * Created by guanxiaoda on 2018/1/14.
 */
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public enum Status {

    SUCCESS("success"),
    FAILURE("failure"),
    ;
    private String name;

}
