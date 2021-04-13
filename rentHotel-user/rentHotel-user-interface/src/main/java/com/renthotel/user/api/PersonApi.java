package com.renthotel.user.api;

import com.renthotel.user.pojo.Person;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

//feign远程调用的写法；就是别的微服务要调用到我当前的这个服务的接口；那么怎么实现调用此服务的接口呢
//就是写一个api
//@RequestMapping("/person")就因为在前面写多了一个斜杠就报错了
@RequestMapping("person")   //这里要和Controller的一样才行，feign的远程调用
public interface PersonApi {

    @PostMapping("query")   //就这样子写个和controller一样的接口方法头就行；其他微服务继承此接口就可以调用当前微服务的接口
    public Person queryPerson(@RequestParam("username") String name, @RequestParam("password") String password);
}
