package cn.guanxiaoda.spider.core.po;

import lombok.Data;
import org.nutz.dao.entity.annotation.Column;

import java.util.Date;

/**
 * Created by guanxiaoda on 2018/1/16.
 */
@Data
public class BaseEntity {

    @Column("site")
    private Integer site;

    @Column("source")
    private Integer source;

    @Column("create_time")
    private Date createTime;

    @Column("update_time")
    private Date updateTime;
}
