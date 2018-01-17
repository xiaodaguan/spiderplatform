package cn.guanxiaoda.spider.engine.manager.ratelimiter;

/**
 * Created by guanxiaoda on 2018/1/17.
 */
public interface SimpleRateLimiter {
    boolean acquire(String limitKey, double qps);
}
