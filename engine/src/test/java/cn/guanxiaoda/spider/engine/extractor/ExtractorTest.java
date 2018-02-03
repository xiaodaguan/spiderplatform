package cn.guanxiaoda.spider.engine.extractor;

import cn.guanxiaoda.spider.core.po.HouseInfo;
import com.google.common.io.Files;
import im.nll.data.extractor.Extractors;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

/**
 * Created by guanxiaoda on 2018/1/19.
 */
public class ExtractorTest {

    private static String listHtml;
    private static String detailHtml;

    @Before
    public void init() throws IOException {
        listHtml = Files.toString(new File(this.getClass().getClassLoader().getResource("files/m.lianjia.ershoufang.list.html").getPath()), Charset.defaultCharset());
        detailHtml = Files.toString(new File(this.getClass().getClassLoader().getResource("files/m.lianjia.ershoufang.detail.html").getPath()), Charset.defaultCharset());
    }

    @Test
    public void parseListTest() {
        List<HouseInfo> results = Extractors.on(listHtml)
                .split(Extractors.selector("div.mod_cont>ul.lists>li.pictext.html"))
                .extract("name", Extractors.selector("div.flexbox>div.item_list>div.item_main"))
                .extract("price", Extractors.selector("div.flexbox>div.item_list>div.item_minor>span.price_total>em"))
                .extract("unitPrice", Extractors.selector("div.flexbox>div.item_list>div.item_minor>span.unit_price.text"))
                .extract("tags", Extractors.selector("div.flexbox>div.item_list>div.tag_box.text"))
                .asBeanList(HouseInfo.class);

        System.out.println(results);
    }


    @Test
    public void parseSingleTest() {
        // 用途
        HouseInfo result = Extractors.on(detailHtml)

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
        System.out.println(result);
    }
}
