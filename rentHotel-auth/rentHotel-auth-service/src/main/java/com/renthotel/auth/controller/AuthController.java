package com.renthotel.auth.controller;


import com.renthotel.auth.service.AuthServiceImpl;
import com.renthotel.person.bo.PersonBo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
//头路径不用配置了，因为我在网关中配置了
public class AuthController {

    @Autowired
    private AuthServiceImpl authService;

    //用户授权(其实就是从数据库中查询用户，有用户的话就是生成token返回去，没有就没token就登陆不进去)而查询用户的实现是在person-service微
    // 服务,而不是在此微服务，此微服务只是调用接口，然后生成JWT、公钥、私钥、token而已。
    @PostMapping("accredit")
    public ResponseEntity<PersonBo> accredit(@RequestParam("username")String username, @RequestParam("password")String password){
        //生成token值，然后给前端保存在sessionStorage中
        PersonBo personBo = this.authService.accredit(username, password);
        if (personBo == null){ //token为null的话说明没有查到用户，因为只有数据库查询到用户时，我们才为其生成token值
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();//意思是未经授权的，可以理解为没有这个用户
        }
        return ResponseEntity.ok(personBo);  //我返回一个token给前端，要注意
        //浏览器可以直接手动生成sessionStorage里面的值，如果拿到你这个后台返回来的token在复制设置进去
        //sessionStorage中，这样就相当于作弊了，所以不能通过判断这两个相等与否
    }

}
