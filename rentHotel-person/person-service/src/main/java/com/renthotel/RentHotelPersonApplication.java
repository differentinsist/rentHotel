package com.renthotel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.renthotel.person.mapper")
public class RentHotelPersonApplication {
    public static void main(String[] args) {
        SpringApplication.run(RentHotelPersonApplication.class);
    }
}
