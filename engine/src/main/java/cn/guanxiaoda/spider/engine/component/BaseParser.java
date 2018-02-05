package cn.guanxiaoda.spider.engine.component;

import cn.guanxiaoda.spider.core.enums.Status;
import cn.guanxiaoda.spider.core.enums.Type;
import cn.guanxiaoda.spider.core.item.ParseResult;
import cn.guanxiaoda.spider.core.item.Task;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * xpath 解析暂未支持
 *
 * @author guanxiaoda
 * @date 2018/1/18
 */
@Slf4j
public abstract class BaseParser<T> implements IParser {


    @Override
    public ParseResult process(Task task) {
        ParseResult parseResult = new ParseResult(null, 0, Status.FAILURE);

        try {
            if (task.getType() == Type.LIST.ordinal()) {
                parseResult = new ParseResult(extractList(task.getFetchResult().getContent()), extractTotal(task.getFetchResult().getContent()), Status.SUCCESS);
            } else if (task.getType() == Type.DETAIL.ordinal()) {
                parseResult = new ParseResult(extractSingle(task.getFetchResult().getContent()), 0, Status.SUCCESS);
            }
        } catch (Exception e) {
            log.error("parser err, taskId={}", task.getTaskId(), e);
        }
        return parseResult;
    }

    protected abstract int extractTotal(String content);

    protected abstract List<T> extractList(String content);

    protected abstract T extractSingle(String content);
}
