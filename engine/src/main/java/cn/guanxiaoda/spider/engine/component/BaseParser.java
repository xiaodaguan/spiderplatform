package cn.guanxiaoda.spider.engine.component;

import cn.guanxiaoda.spider.core.constant.Const;
import cn.guanxiaoda.spider.core.item.ParseResult;
import cn.guanxiaoda.spider.core.item.Task;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 * xpath 解析暂未支持
 * Created by guanxiaoda on 2018/1/18.
 */
@Slf4j
public abstract class BaseParser implements IParser {

    private ReadContext jsonDoc;
    private Document cssDoc;
    private String fetchContent;

    @Override
    public ParseResult process(Task task) {
        fetchContent = task.getFetchResult().getContent();
        return null;
    }

    private boolean checkGrammar(String select) {
        if (select.startsWith(Const.Parsers.JPATH_PREFIX)) {
            if (jsonDoc == null) {
                jsonDoc = JsonPath.parse(fetchContent);
            }
        } else if (select.startsWith(Const.Parsers.CPATH_PREFIX)) {
            if (cssDoc == null) {
                cssDoc = Jsoup.parse(fetchContent);
            }
            if (!select.endsWith(Const.Parsers.TEXT_POSTFIX) && !select.endsWith(Const.Parsers.ATTR_POSTFIX)) {
                log.warn("check grammar failure: {}", select);
                return false;
            }
        } else {
            log.warn("check grammar failure: {}", select);
            return false;
        }


        return true;
    }

    @Override
    public IParser getPropertyAsList(String property, String select, int expectCount) {
        if (!checkGrammar(select)) {
            return this;
        }
        if (select.startsWith(Const.Parsers.CPATH_PREFIX)) {
            Elements elements = cssDoc.select(select.substring(3));
            if(select.endsWith(Const.Parsers.ATTR_POSTFIX)){
                elements = elements.attr()
            }
        }
        return this;
    }


    @Override
    public IParser getPropertyAsList(String property, String select) {
        if (!checkGrammar(select)) {
            log.warn("grammar incorrect: {}", select);
            return this;
        }

        return null;
    }

    @Override
    public IParser getSingleProperty(String property, String select, boolean required) {
        if (!checkGrammar(select)) {
            log.warn("grammar incorrect: {}", select);
            return this;
        }
        return null;
    }

    @Override
    public IParser getSingleProperty(String property, String select) {
        if (!checkGrammar(select)) {
            log.warn("grammar incorrect: {}", select);
            return this;
        }
        return null;
    }
}
