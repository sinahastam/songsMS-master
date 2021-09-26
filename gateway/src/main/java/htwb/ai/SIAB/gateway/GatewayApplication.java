package htwb.ai.SIAB.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * Netflix Zuul Api Gateway
 *
 */
@SpringBootApplication
@EnableZuulProxy
public class GatewayApplication {
	
	/**
    * Main method
    * @param args
 	*/
	public static void main(String[] args) {
      SpringApplication.run(GatewayApplication.class, args);
   }
}
