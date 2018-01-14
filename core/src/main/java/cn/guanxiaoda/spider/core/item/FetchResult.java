package cn.guanxiaoda.spider.core.item;

import cn.guanxiaoda.spider.core.enums.Status;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * 不允许空值
 * Created by guanxiaoda on 2018/1/14.
 */
@Data
public class FetchResult {

    Status status;
    String content;
    Map<String, String> resHeaders = new HashMap<>();

    public FetchResult(Status status, String content, Map<String, String> resHeaders) {
        this.status = status;
        this.content = content;
        this.resHeaders = resHeaders;
    }
}
