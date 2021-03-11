package com.rentHotel.upload.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class RentHotelCorsConfiguration {

    @Bean
    public CorsFilter corsFilter(){
        CorsConfiguration configuration = new CorsConfiguration();//初始化cors配置对象
        //允许跨域的域名；如果要携带cookie；就不能写* ；*代表所有的域名都可以跨域访问
        configuration.addAllowedOrigin("http://qian.rentHoter.com");
        configuration.addAllowedOrigin("http://localhost:6221");
        configuration.setAllowCredentials(true);//允许携带cookie
        configuration.addAllowedMethod("*");//代表所有的请求方法:GET、POST、PUT等
        configuration.addAllowedHeader("*");
        //初始化cors配置源对象
        UrlBasedCorsConfigurationSource configurationSource = new UrlBasedCorsConfigurationSource();
        configurationSource.registerCorsConfiguration("/**", configuration);
        return new CorsFilter(configurationSource);//返回conrsFilter实例，参数：cors配置源对象
    }
}
