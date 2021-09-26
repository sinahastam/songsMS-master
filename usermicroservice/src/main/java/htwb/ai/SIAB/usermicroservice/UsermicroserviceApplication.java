package htwb.ai.SIAB.usermicroservice;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import htwb.ai.SIAB.usermicroservice.model.User;

@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
public class UsermicroserviceApplication {

	/**
	 * Main method
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(UsermicroserviceApplication.class, args);
	}
	
	@Bean
	public ArrayList<String> getArrayList() {
		return new ArrayList<String>();
	}
	
	@Bean
	public HashMap<String, User> getHashMap() {
		return new HashMap<String, User>();
	}
	

}
