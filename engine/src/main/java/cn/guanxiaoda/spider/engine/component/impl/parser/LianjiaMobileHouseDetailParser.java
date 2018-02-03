package cn.guanxiaoda.spider.engine.component.impl.parser;

import cn.guanxiaoda.spider.core.enums.Entity;
import cn.guanxiaoda.spider.core.enums.Site;
import cn.guanxiaoda.spider.core.enums.Source;
import cn.guanxiaoda.spider.core.enums.Type;
import cn.guanxiaoda.spider.core.po.HouseInfo;
import cn.guanxiaoda.spider.engine.annotation.Parser;
import cn.guanxiaoda.spider.engine.component.BaseParser;
import cn.guanxiaoda.spider.engine.component.IParser;
import im.nll.data.extractor.Extractors;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * Created by guanxiaoda on 2018/1/18.
 */
@Parser(site = Site.LIANJIA, source = Source.MOBEL, entity = Entity.HOUSE, type = Type.DETAIL)
@Slf4j
public class LianjiaMobileHouseDetailParser extends BaseParser<HouseInfo> implements IParser {


    @Override
    protected int extractTotal(String content) {return 0;}

    @Override
    protected List<HouseInfo> extractList(String content) {return null;}

    @Override
    protected HouseInfo extractSingle(String content) {
        return Extractors.on(content)
                .extract("price", Extractors.selector("div.similar_data_detail:nth-child(1)>p.red"))
                .extract("houseType", Extractors.selector("div.similar_data_detail:nth-child(2)>p.red"))
                .extract("area", Extractors.selector("div.similar_data_detail:nth-child(3)>p.red"))
                .extract("unitPrice", Extractors.selector("ul.house_description>li:contains(单价)"))
                .extract("buildType", Extractors.selector("ul.house_description>li:contains(楼型)"))
                .extract("orientation", Extractors.selector("ul.house_description>li:contains(朝向)"))
                .extract("elevator", Extractors.selector("ul.house_description>li:contains(电梯)"))
                .extract("floor", Extractors.selector("ul.house_description>li:contains(楼层)"))
                .extract("buildYear", Extractors.selector("ul.house_description>li:contains(年代)"))
                .extract("purpose", Extractors.selector("ul.house_description>li:contains(用途)"))
                .asBean(HouseInfo.class);
    }

}
