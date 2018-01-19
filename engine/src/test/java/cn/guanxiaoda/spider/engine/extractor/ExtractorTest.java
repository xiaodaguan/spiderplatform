package cn.guanxiaoda.spider.engine.extractor;

import cn.guanxiaoda.spider.core.db.HouseInfo;
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
                .extract("tags", Extractors.selector("div.flexbox>div.item_list>div.tag_box>span"))
                .asBeanList(HouseInfo.class);

        System.out.println(results);
    }
}
