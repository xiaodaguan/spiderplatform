package cn.guanxiaoda.spider.core.item;

import cn.guanxiaoda.spider.core.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by guanxiaoda on 2018/1/16.
 */
@Data
@AllArgsConstructor
public class ParseResult<T> {

    T parseResult;
    int totalPage;
    private Status status;
}
