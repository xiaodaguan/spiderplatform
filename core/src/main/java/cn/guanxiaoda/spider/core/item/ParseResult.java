package cn.guanxiaoda.spider.core.item;

import cn.guanxiaoda.spider.core.enums.Status;
import lombok.Data;

/**
 * Created by guanxiaoda on 2018/1/16.
 */
@Data
public class ParseResult<T> {

    T parseResult;
    private Status status;
}
