package cmms.humanresource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.FeignClient;

@SpringBootApplication
@FeignClient
public class HumanResourceApplication {

	public static void main(String[] args) {
		SpringApplication.run(HumanResourceApplication.class, args);
	}

}
