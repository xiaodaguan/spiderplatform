package cn.guanxiaoda.spider.core.item;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

/**
 * Created by guanxiaoda on 2018/1/11.
 */
public class ItemTest {



    @Test
    public void test(){
        Task t = new Task();
        System.out.println(JSON.toJSONString(t));
    }
}
