package cn.guanxiaoda.spider.scheduler.ratelimiter;

/**
 * Created by guanxiaoda on 2018/1/17.
 */
public interface SimpleRateLimiter {
    boolean acquire(String limitKey, double qps);
}
