package com.rentHotel.item.controller;


import com.rentHotel.item.pojo.Person;
import com.rentHotel.item.service.PersonServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
@RequestMapping("person")
public class PersonController {

    @Autowired
    private PersonServiceImpl personService;


    /** 通过用户id查询信息；好像通用mapper没有这样的方法；我自己写了一个mapper方法
     * 这种个人信息的不适合用Get方式请求吧？
     * @return  api/item/person/queryPersonByPid?pid=000001
     */
    @GetMapping("queryPersonByPid")

    public ResponseEntity<Person> queryPersonByPid(@RequestParam("pid")String pid ){

        Person person = this.personService.queryPersonByPid(pid);

        //判断是否查到个人数据；应该用什么工具类；或者直接==然后在实体类重写equals方法吗
        if (person == null){
            System.out.println("判断出错了------------");
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(person);
    }

}
