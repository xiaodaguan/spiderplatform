package cn.guanxiaoda.spider.engine.component.extractor;

import cn.guanxiaoda.spider.core.enums.ExpressionType;
import cn.guanxiaoda.spider.engine.component.expression.Expression;
import lombok.Getter;
import org.jsoup.select.Elements;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by guanxiaoda on 2018/1/18.
 */
@Getter
public class ListExtractor<T> extends BaseExtractor {
    List<T> result = new ArrayList<>();

    public ListExtractor(String content) {
        super(content);
    }


    public ListExtractor extractField(String name, Expression expression) {
        if (check(expression)) {
            if (ExpressionType.CSS == expression.getExpressionType()) {

                Elements elements = cssDoc.select(expression.getExpressionBody());
                List<String> propertyList = expression.getAttr() != null ? elements.eachAttr(expression.getAttr()) : elements.eachText();
                if (CollectionUtils.isEmpty(result)) {
                    // todo ...
                }
            }
        }
        return this;
    }
}
