package com.personal.etfComparator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling           // This annotation is require to enable cron scheduling
public class EtfComparatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(EtfComparatorApplication.class, args);
	}

}
