package cn.guanxiaoda.spider.core.constant;

/**
 * Created by guanxiaoda on 2018/1/14.
 */
public class Const {

    public static final String KEY_FORMAT = "%s_%s_%s_%s";

    public class Strings {
        public static final String EMPTY = "";
    }

    public class Numbers {
        public static final int MINITE = 60;
    }

    public class Seps {
        public static final String COLON = ":";
        public static final String COMMA = ",";
        public static final String STRIKETHROUGH = "-";
        public static final String UNDERLINE = "_";
    }

    public class TaskParams {
        public static final String PAGE_NUM = "pageNum";
        public static final String PAGE_SIZE = "pageSize";
    }

    public class Parsers {
        public static final String JPATH_PREFIX = "$j.";
        public static final String XPATH_PREFIX = "$x.";
        public static final String CPATH_PREFIX = "$c.";
        public static final String RPATH_PREFIX = "$r.";

        public static final String TEXT_POSTFIX = "#text";
        public static final String ATTR_POSTFIX = "#attr";

    }

}
