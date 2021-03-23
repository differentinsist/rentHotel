package com.renthotel.person.controller;

import com.renthotel.person.pojo.Person;
import com.renthotel.person.service.PersonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
@RequestMapping("person")
public class PersonController {

    @Autowired
    private PersonServiceImpl personService;

    /**【校验用户注册的名字】（就是看数据库有没有人已经使用了）
     *
     * @param personname
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
        Boolean bool = this.personService.checkPerson(personNameOrPhone, type);
        if(bool == null){   //判断一个【变量】是否为null是直接这样子判断？？？
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(bool);
    }

    /**
     * 保存用户到数据库（就是经过用户注册数据校验之后保存到数据库）
     * @param person
     * @return  为啥没有返回值
     */
    @PostMapping("register")
    public ResponseEntity<Void> registerPerson(
            @RequestParam(name = "username")String name,
            @RequestParam("password")String password,
            @RequestParam(name = "idcard")String idcard,
            @RequestParam(name = "birthday", required = false)Date birthday,
            @RequestParam(name = "phone", required = false)String phone
    ){
        this.personService.registerPerson(name,password,idcard,birthday,phone);
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
        Person person = this.personService.queryPerson(name, password);
        if(person == null){
            //像下面这样子返回坏的请求；你在前端F12打开看的话是红色的失败请求；我觉得应该改一下；返回信息提示也好啊
            return ResponseEntity.badRequest().build(); //当没有查询到用户的时候，返回的是这个？？
        }
        return ResponseEntity.ok(person);
    }


}
