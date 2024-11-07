package site.metacoding.hospi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@EnableScheduling
@SpringBootApplication
public class HospiApplication {

	public static void main(String[] args) {
		SpringApplication.run(HospiApplication.class, args);
	}

}
