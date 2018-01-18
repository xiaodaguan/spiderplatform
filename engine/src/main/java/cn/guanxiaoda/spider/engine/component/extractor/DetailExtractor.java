package cn.guanxiaoda.spider.engine.component.extractor;

import cn.guanxiaoda.spider.core.enums.ExtractType;
import cn.guanxiaoda.spider.engine.component.expression.Expression;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;
import lombok.Data;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.List;

/**
 * Created by guanxiaoda on 2018/1/18.
 */
public class DetailExtractor<T> extends BaseExtractor{
    T result;

    public DetailExtractor(String content) {
        super(content);
    }


    public DetailExtractor extractField(String name, Expression expression, boolean required) {

        return this;
    }


}
