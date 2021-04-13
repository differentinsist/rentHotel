package com.renthotel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.renthotel.user.mapper")//扫描mapper接口写在引导类这里
public class RentHotelUserApplication {
    public static void main(String[] args) {
        SpringApplication.run(RentHotelUserApplication.class);
    }
}
