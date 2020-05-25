package pancake;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication/*Данная аннотация говрит сама за себя. Однако Она также тянет за собой три другие: @SpringBootConfiguration, 
@EnableAutoConfiguration, @ComponentScan(позволяет включать @Component, @Controller, @Service). 
*/
public class PancakeCloudApplication {

  public static void main(String[] args) {
    SpringApplication.run(PancakeCloudApplication.class, args);
  }
  
}
