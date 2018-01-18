package cn.guanxiaoda.spider.engine.component.extractor;

import cn.guanxiaoda.spider.engine.component.expression.Expression;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * Created by guanxiaoda on 2018/1/18.
 */
public abstract class BaseExtractor {

    ReadContext jsonDoc;
    Document cssDoc;
    String fetchContent;

    public BaseExtractor(String content) {
        this.fetchContent = content;
    }

    boolean check(Expression expression) {
        try {
            switch (expression.getExpressionType()) {
                case CSS:
                    if (cssDoc == null) {
                        cssDoc = Jsoup.parse(this.fetchContent);
                    }
                    break;
                case JSON:
                    if (jsonDoc == null) {
                        jsonDoc = JsonPath.parse(this.fetchContent);
                    }
                    break;
                case REGEX:
                    break;
                case XPATH:
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            return false;
        }
        return true;

    }
}
