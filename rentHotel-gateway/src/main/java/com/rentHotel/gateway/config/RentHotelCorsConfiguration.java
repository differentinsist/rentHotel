package com.rentHotel.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration   //声明一个配置类
//这种配置要分为有nginx和无nginx；有域名还是IP的情况吗。
public class RentHotelCorsConfiguration {

    @Bean
    public CorsFilter corsFilter(){
        //初始化cors配置对象
        CorsConfiguration configuration = new CorsConfiguration();
        //允许跨域的域名；如果要携带cookie；就不能写* ；*代表所有的域名都可以跨域访问
        configuration.addAllowedOrigin("http://www.leyou.com"); //搜索服务也会跨域；所以这里也要设置允许跨域
        configuration.addAllowedOrigin("http://manage.leyou.com");
        configuration.addAllowedOrigin("http://qian.renthotel.com");

//        configuration.addAllowedOrigin("http://localhost:2262/api/item/person/queryPersonByPid");
//        configuration.addAllowedOrigin("http://localhost:1215");
        configuration.addAllowedOrigin("http://localhost:12261");
        configuration.addAllowedOrigin("http://localhost:6221");
        configuration.addAllowedOrigin("http://localhost:1215");//写前端的ip

        configuration.addAllowedOrigin("http://8.129.187.106:6221");//阿里云的
        configuration.addAllowedOrigin("http://8.129.187.106:80");//阿里云的
        configuration.addAllowedOrigin("http://8.129.187.106:8080");//阿里云的

        configuration.addAllowedOrigin("http://localhost:6221");//阿里云的
        configuration.addAllowedOrigin("http://localhost:80");//阿里云的
        configuration.addAllowedOrigin("http://localhost:8080");//阿里云的

        configuration.setAllowCredentials(true);//允许携带cookie
        configuration.addAllowedMethod("*");//代表所有的请求方法:GET、POST、PUT等
        configuration.addAllowedHeader("*");
        //初始化cors配置源对象
        UrlBasedCorsConfigurationSource configurationSource = new UrlBasedCorsConfigurationSource();
        configurationSource.registerCorsConfiguration("/**", configuration);
        //返回conrsFilter实例，参数：cors配置源对象
        return new CorsFilter(configurationSource);
    }

}
