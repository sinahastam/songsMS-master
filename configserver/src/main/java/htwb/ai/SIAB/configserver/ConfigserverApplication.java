package htwb.ai.SIAB.configserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * Config Server Class
 *
 */
@SpringBootApplication
@EnableConfigServer
public class ConfigserverApplication {

	/**
	 * Main method
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(ConfigserverApplication.class, args);
	}

}
