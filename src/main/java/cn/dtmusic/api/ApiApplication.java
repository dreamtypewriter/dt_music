package cn.dtmusic.api;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.util.unit.DataSize;

import javax.servlet.MultipartConfigElement;

@SpringBootApplication
@MapperScan("cn.dtmusic.api.mapper")
@EnableTransactionManagement
public class ApiApplication {
	private static final Logger logger = LoggerFactory.getLogger(ApiApplication.class);

	@Bean
	public MultipartConfigElement multipartConfigElement() {
		MultipartConfigFactory factory = new MultipartConfigFactory();
		//单个文件最大
		factory.setMaxFileSize(DataSize.ofMegabytes(50)); //MB
		//factory.setMaxFileSize(DataSize.ofKilobytes(80)); //KB
		//factory.setMaxFileSize(DataSize.ofGigabytes(80)); //Gb
		/// 设置总上传数据总大小
		factory.setMaxRequestSize(DataSize.ofMegabytes(50));
		return factory.createMultipartConfig();
	}

	public static void main(String[] args) {
		logger.info("------------------------->项目启动了<------------------------------------");
		SpringApplication.run(ApiApplication.class, args);
		logger.info("------------------------->启动成功了<------------------------------------");
	}

}
