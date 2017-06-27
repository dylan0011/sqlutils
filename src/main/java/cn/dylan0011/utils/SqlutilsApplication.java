package cn.dylan0011.utils;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SqlutilsApplication {

	public static void main(String[] args) {
		SpringApplication springApplication = new SpringApplication(SqlutilsApplication.class);
		springApplication.setBanner(new MyBanner());
		springApplication.run(args);

	}
}
