package cn.guanxiaoda.spider.engine.component.impl.parser;

import cn.guanxiaoda.spider.core.db.HouseInfo;
import cn.guanxiaoda.spider.core.enums.*;
import cn.guanxiaoda.spider.core.item.ParseResult;
import cn.guanxiaoda.spider.core.item.Task;
import cn.guanxiaoda.spider.engine.annotation.Parser;
import cn.guanxiaoda.spider.engine.component.BaseParser;
import cn.guanxiaoda.spider.engine.component.IParser;
import cn.guanxiaoda.spider.engine.component.expression.Expression;
import cn.guanxiaoda.spider.engine.component.extractor.ListExtractor;

import java.util.List;

/**
 * Created by guanxiaoda on 2018/1/18.
 */
@Parser(site = Site.LIANJIA, source = Source.MOBEL, entity = Entity.HOUSE, type = Type.DETAIL)
public class LianjiaMobileHouseListParser extends BaseParser<HouseInfo> implements IParser {
    @Override
    public ParseResult process(Task task) {
        ListExtractor<HouseInfo> listExtractor = new ListExtractor<>(task.getFetchResult().getContent());
        List<HouseInfo> resultList = listExtractor
                .extractField("itemId", new Expression("$c.head"))
                .extractField("name", new Expression("$c.head"))
                .getResult();
        ParseResult<List> result = new ParseResult<>();
        result.setParseResult(resultList);
        result.setStatus(Status.SUCCESS);
        return result;
    }
}
