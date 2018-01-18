package cn.guanxiaoda.spider.engine.component.expression;

import cn.guanxiaoda.spider.core.constant.Const;
import cn.guanxiaoda.spider.core.enums.ExpressionType;
import lombok.Getter;
import lombok.ToString;
import org.apache.commons.lang.StringUtils;

/**
 * Created by guanxiaoda on 2018/1/18.
 */
@ToString
@Getter
public class Expression {
    private String expression;
    private String attr;
    private ExpressionType expressionType;
    private String expressionBody;

    public Expression(String expression) {
        if (!expression.startsWith("$")) {
            throw new RuntimeException("invalid expression grammar: $");
        }
        if (!expression.contains("#")) {
            throw new RuntimeException("invalid expression grammar: #");
        } else {
            if (expression.contains("#attr:")) {
                String postfix = expression.substring(expression.lastIndexOf("#attr:"));
                attr = postfix.split(":")[1];
                if (StringUtils.isEmpty(attr)) {
                    throw new RuntimeException("invalid expression grammar: attr");
                }
            }
        }
        this.expression = expression;
        if (expression.startsWith(Const.Parsers.XPATH_PREFIX)) {
            this.expressionType = ExpressionType.XPATH;
        } else if (expression.startsWith(Const.Parsers.JPATH_PREFIX)) {
            this.expressionType = ExpressionType.JSON;
        } else if (expression.startsWith(Const.Parsers.CPATH_PREFIX)) {
            this.expressionType = ExpressionType.CSS;
        } else if (expression.startsWith(Const.Parsers.RPATH_PREFIX)) {
            this.expressionType = ExpressionType.REGEX;
        }
        this.expression = expression.substring(3, expression.lastIndexOf("#"));
    }

}
