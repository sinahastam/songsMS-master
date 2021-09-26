package htwb.ai.SIAB.lyricsmicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * Song lyrics microservice
 *
 */
@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
public class LyricsApplication {

	
	/**
	 * Main method
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(LyricsApplication.class, args);
	}
	
	/**
	 * RestTemplate Bean
	 * @return RestTemplate instance
	 */
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

}
