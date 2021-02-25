package com.rentHotel.item.service;


import com.rentHotel.item.mapper.PersonMapper;
import com.rentHotel.item.pojo.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl {

    @Autowired
    private PersonMapper personMapper;


    //查询当前用户信息
    public Person queryPersonByPid(String pid) {
        Person person = this.personMapper.findPersonByPid(pid);
        System.out.println("-------------------"+person);
        return person;
    }
}
