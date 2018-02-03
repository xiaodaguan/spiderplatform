package cn.guanxiaoda.spider.confCenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class ConfCenterApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfCenterApplication.class, args);
	}
}
