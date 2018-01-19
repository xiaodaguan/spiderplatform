package cn.guanxiaoda.spider.engine.component;

import cn.guanxiaoda.spider.core.db.HouseInfo;
import cn.guanxiaoda.spider.core.enums.Status;
import cn.guanxiaoda.spider.core.item.ParseResult;
import cn.guanxiaoda.spider.core.item.Task;
import im.nll.data.extractor.Extractors;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * xpath 解析暂未支持
 * Created by guanxiaoda on 2018/1/18.
 */
@Slf4j
public abstract class BaseParser<T> implements IParser {


    @Override
    public ParseResult process(Task task) {
        Extractors extractors = Extractors.on(task.getFetchResult().getContent());
        return new ParseResult(extractList(extractors), extractTotal(extractors), Status.SUCCESS);
    }

    protected abstract int extractTotal(Extractors extractors);

    protected abstract List<T> extractList(Extractors extractors);
}
