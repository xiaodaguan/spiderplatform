package cn.guanxiaoda.spider.core.item;

import cn.guanxiaoda.spider.core.constant.Const;
import cn.guanxiaoda.spider.core.enums.Type;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by guanxiaoda on 2018/1/11.
 */

@Getter
@Setter
@ToString
@AllArgsConstructor
public class Task {

    public static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("yyyyMMdd");

    /**
     * meta:
     * pageNum
     */
    Map<String, String> meta = new HashMap<>();
    private String taskId;
    private int entity;
    private int type;
    private int site;
    private int source;
    private LocalDateTime startTime = LocalDateTime.now();
    private LocalDateTime endTime;
    private FetchResult fetchResult;
    private ParseResult parseResult;
    /**
     * 只有在fetch失败时重试
     */
    private int retryNum = 0;
    private int maxRetry = 3;

    public Task(int site, int source, int entity, int type) {
        this.site = site;
        this.source = source;
        this.entity = entity;
        this.type = type;
    }


    public void genTaskId() {
        StringBuilder taskIdBuilder = new StringBuilder().append(LocalDateTime.now().format(DTF))
                .append(Const.Seps.STRIKETHROUGH).append(site)
                .append(Const.Seps.STRIKETHROUGH).append(source)
                .append(Const.Seps.STRIKETHROUGH).append(entity)
                .append(Const.Seps.STRIKETHROUGH).append(type);
        if (Type.LIST == Type.valueOf(type)) {
            taskIdBuilder.append(Const.Seps.STRIKETHROUGH).append(this.getMeta().get(Const.TaskParams.PAGE_NUM) != null ? this.getMeta().get(Const.TaskParams.PAGE_NUM) : 1);
        } else if (Type.DETAIL == Type.valueOf(type)) {
            taskIdBuilder.append(Const.Seps.STRIKETHROUGH).append(this.getMeta().get("itemId"));
        }
        this.taskId = taskIdBuilder.toString();
    }


}
