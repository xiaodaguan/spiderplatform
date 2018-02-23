package cn.guanxiaoda.spider.scheduler;

import com.google.common.io.Resources;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author guanxiaoda
 * @date 2018/2/22
 */
public class GenListTest {


    DocumentContext ctx;

    @Before
    public void before() throws IOException {
        ctx = JsonPath.parse(new File(Resources.getResource("list.bj.json").getPath()));
    }

    @Test
    public void getBizs() {
        List<String> bizs = ctx.read("$..data.info[*].district[*].bizcircle[*].bizcircle_quanpin", List.class);
        System.out.println(bizs);
    }
}
