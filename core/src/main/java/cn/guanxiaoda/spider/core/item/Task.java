package cn.guanxiaoda.spider.core.item;

import cn.guanxiaoda.spider.core.enums.Entity;
import cn.guanxiaoda.spider.core.enums.Site;
import cn.guanxiaoda.spider.core.enums.Source;
import cn.guanxiaoda.spider.core.enums.Type;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by guanxiaoda on 2018/1/11.
 */

@Data
public class Task {

    /**
     * meta
     */
    Map<String, String> meta = new HashMap<String, String>();
    private String taskId = "task";
    private int entity = Entity.DEFAULT.ordinal();
    private int type = Type.DEFAULT.ordinal();
    private int site = Site.DEFAULT.ordinal();
    private int source = Source.PC.ordinal();
    private LocalDateTime startTime = LocalDateTime.now();
    private LocalDateTime endTime;
    private FetchResult fetchResult;
    private ParseResult parseResult;
    /**
     * 只有在fetch失败时重试
     */
    private int retryNum = 0;
    private int maxRetry = 3;
}
