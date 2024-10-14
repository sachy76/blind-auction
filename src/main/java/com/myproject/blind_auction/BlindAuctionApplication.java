package com.myproject.blind_auction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableAsync
@EnableCaching
public class BlindAuctionApplication implements WebMvcConfigurer {

	public static void main(String[] args) {

		SpringApplication.run(BlindAuctionApplication.class, args);

	}
}
