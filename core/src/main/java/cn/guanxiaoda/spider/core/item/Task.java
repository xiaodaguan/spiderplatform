package cn.guanxiaoda.spider.core.item;

import cn.guanxiaoda.spider.core.enums.Entity;
import cn.guanxiaoda.spider.core.enums.Site;
import cn.guanxiaoda.spider.core.enums.Source;
import cn.guanxiaoda.spider.core.enums.Type;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Created by guanxiaoda on 2018/1/11.
 */

@Data
public class Task {

    private String taskId = "task";

    private int entity = Entity.DEFAULT.ordinal();
    private int type = Type.DEFAULT.ordinal();

    private int site = Site.DEFAULT.ordinal();
    private int source = Source.PC.ordinal();

    private LocalDateTime createTime = LocalDateTime.now();

}
