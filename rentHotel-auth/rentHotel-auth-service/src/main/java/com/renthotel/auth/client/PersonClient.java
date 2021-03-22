package com.renthotel.auth.client;

import com.renthotel.person.api.PersonApi;
import org.springframework.cloud.openfeign.FeignClient;

// 明白远程调用时怎么使用的
@FeignClient("person-service")
public interface PersonClient extends PersonApi {
}
