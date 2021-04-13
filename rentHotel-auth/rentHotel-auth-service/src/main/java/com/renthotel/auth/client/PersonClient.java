package com.renthotel.auth.client;


import com.renthotel.user.api.PersonApi;
import org.springframework.cloud.openfeign.FeignClient;

// 明白远程调用时怎么使用的
//@FeignClient("person-service")
@FeignClient("user-service")
public interface PersonClient extends PersonApi {
}
