package cn.guanxiaoda.spider.engine.component.impl.parser;

import cn.guanxiaoda.spider.core.db.HouseInfo;
import cn.guanxiaoda.spider.core.enums.*;
import cn.guanxiaoda.spider.core.item.ParseResult;
import cn.guanxiaoda.spider.core.item.Task;
import cn.guanxiaoda.spider.engine.annotation.Parser;
import cn.guanxiaoda.spider.engine.component.BaseParser;
import cn.guanxiaoda.spider.engine.component.IParser;
import im.nll.data.extractor.Extractors;

import java.util.List;

/**
 * Created by guanxiaoda on 2018/1/18.
 */
@Parser(site = Site.LIANJIA, source = Source.MOBEL, entity = Entity.HOUSE, type = Type.DETAIL)
public class LianjiaMobileHouseListParser extends BaseParser<HouseInfo> implements IParser {
    @Override
    public ParseResult process(Task task) {

        List<HouseInfo> results = Extractors.on(task.getFetchResult().getContent())
                .split(Extractors.selector("div.mod_cont>ul.lists>li.pictext.html"))
                .extract("name", Extractors.selector("div.flexbox>div.item_list>div.item_main"))
                .extract("price", Extractors.selector("div.flexbox>div.item_list>div.item_minor>span.price_total>em"))
                .extract("unitPrice", Extractors.selector("div.flexbox>div.item_list>div.item_minor>span.unit_price.text"))
                .extract("tags", Extractors.selector("div.flexbox>div.item_list>div.tag_box>span"))
                .asBeanList(HouseInfo.class);


        ParseResult<List> result = new ParseResult<>();
        result.setParseResult(results);
        result.setStatus(Status.SUCCESS);
        return result;
    }

//    List<HouseInfo> constructResultList(List<Map<String, String>> mapList){
//        mapList.stream().forEach(entry ->{
//
//                }
//        );
//    }
}
