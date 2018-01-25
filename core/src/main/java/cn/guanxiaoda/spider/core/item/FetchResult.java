package cn.guanxiaoda.spider.core.item;

import cn.guanxiaoda.spider.core.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.http.Header;

/**
 * 不允许空值
 * Created by guanxiaoda on 2018/1/14.
 */
@Data
@AllArgsConstructor
public class FetchResult {

    Status status;
    String content;
    Header[] resHeaders;
}
