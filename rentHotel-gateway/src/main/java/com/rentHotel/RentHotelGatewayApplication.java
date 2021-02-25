package com.rentHotel;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
//import org.springframework.cloud.netflix.zuul.EnableZuulServer; 不是这个

@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
public class RentHotelGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(RentHotelGatewayApplication.class);
    }
}
