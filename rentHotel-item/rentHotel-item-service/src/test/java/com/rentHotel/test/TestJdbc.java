package com.rentHotel.test;


import com.rentHotel.item.mapper.TestJdbcConnectMapper;
import com.rentHotel.item.pojo.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestJdbc {

    @Autowired
    private TestJdbcConnectMapper testJdbcConnectMapper;


    @Test
    public void testConnect(){
        List<Person> personList = this.testJdbcConnectMapper.selectAll();
        System.out.println("---------------------"+personList);
    }



}
