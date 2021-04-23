package com.renthotel.user.controller;

import com.renthotel.user.pojo.Person;
import com.renthotel.user.service.UserServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;


@Controller
@RequestMapping("person")
public class UserController {



    @Autowired
    private UserServiceImpl userService;

    /**【校验用户注册的名字】（就是看数据库有没有人已经使用了）
     *
     * @param
     * @param type 类型，1代表校验的是用户名，2代表校验的是手机号(手机号我没做)
     * @return
     */
//    @GetMapping("check")//这两种写法有什么区别前端的要求还是注解的不同看看前端是否是表单吗一般都是表单数据对吗
//    public ResponseEntity<Boolean> checkPerson(@RequestParam("personname") String personNameOrPhone, @RequestParam("type") Integer type){
    //@GetMapping("check/{personname}/{type}")  //写成这种的话前端vue请求我不会写路径
    // public ResponseEntity<Boolean> checkPerson(
    //        @PathVariable("personname") String personNameOrPhone,
    //        @PathVariable("type") Integer type)
    @GetMapping("check")
    public ResponseEntity<Boolean> checkPerson(
            @RequestParam("personname") String personNameOrPhone,
            @RequestParam("type") Integer type){
        Boolean bool = this.userService.checkPerson(personNameOrPhone, type);
        if(bool == null){   //判断一个【变量】是否为null是直接这样子判断？？？
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(bool);
    }

    /**
     * 保存用户到数据库（就是经过用户注册数据校验之后保存到数据库）
     * @param
     * @return  为啥没有返回值
     */
    @PostMapping("register")
    public ResponseEntity<Void> registerPerson(
            @RequestParam(name = "username")String name,
            @RequestParam("password")String password,
            @RequestParam(name = "idcard" ,required = false)String idcard,
            @RequestParam(name = "birthday", required = false)Date birthday,
            @RequestParam(name = "phone", required = false)String phone
    ){
        System.out.println("打印birthday参数看看没传递是null吗："+birthday);
        Integer result =  this.userService.registerPerson(name,password,idcard,birthday,phone);
        if(result < 0){
            return ResponseEntity.badRequest().build();  //result是受影响的条数;你插入一条数据返回值就是1;
        }
        System.out.println("打印防护值看看受影响的条数是1吗:"+result);
        return ResponseEntity.status(HttpStatus.CREATED).build(); //就是返回值为Void的话就写这种对吧
    }


    /**登陆验证
     * @param name
     * @param password
     * @return
     */
    @PostMapping("query")
    public ResponseEntity<Person> queryPerson(
            @RequestParam("username")String name,
            @RequestParam("password")String password){
        Person person = this.userService.queryPerson(name, password);
        if(person == null){
            //像下面这样子返回坏的请求；你在前端F12打开看的话是红色的失败请求；我觉得应该改一下；返回信息提示也好啊
            return ResponseEntity.badRequest().build(); //当没有查询到用户的时候，返回的是这个？？
        }
        return ResponseEntity.ok(person);
    }



    /**  根据用户id获取用户基本数据 + 修改用户的基本信息
     *
     * @param userid
     * @param username
     * @param phone  后面还要检验字段，电话不能使用中文，只能用数字；看你使用hibernate validate框架校验还是啥？
     * @param address
     * @return
     */
//    @GetMapping("findAndAlterPersonById")
//    public ResponseEntity<Person> findAndAlterPersonById(
//            @RequestParam("userid")Integer userid,
//            @RequestParam(name = "username", required = false)String username,
//            @RequestParam(name = "phone", required = false)String phone,
//            @RequestParam(name = "address", defaultValue = "广州市")String address
//    ){        【实现动态改变值】一次修改一个值，刚好username\phone\address都是字符串
    @GetMapping("findAndAlterPersonById")
    public ResponseEntity<Person> findAndAlterPersonById(
            @RequestParam("userid")Integer userid,
            @RequestParam("fieldName")String fieldName,
            @RequestParam("fieldValue")String fieldValue
    ){
        if(userid == null){
            return ResponseEntity.badRequest().build();
        }

        Person person = this.userService.findAndAlterPersonById(userid, fieldName, fieldValue);
        return ResponseEntity.ok(person);
    }







}
