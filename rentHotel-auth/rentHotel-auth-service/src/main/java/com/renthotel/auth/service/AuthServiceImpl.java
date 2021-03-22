package com.renthotel.auth.service;


import com.renthotel.auth.client.PersonClient;
import com.renthotel.auth.config.JwtProperties;
import com.renthotel.common.pojo.UserInfo;
import com.renthotel.common.utils.JwtUtils;
import com.renthotel.person.bo.PersonBo;
import com.renthotel.person.pojo.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl {

    @Autowired
    private PersonClient personClient;
    @Autowired
    private JwtProperties jwtProperties;

    //调用person-service的微服务查看用户名和密码是否正确，正确就为其用户生成公钥、私钥、token
    public PersonBo accredit(String username, String password) {
        //根据用户名和密码调用person微服务的接口查询用户名和密码是否正确，并返回的是用户信息
        Person person = this.personClient.queryPerson(username,password);
        if (person == null){  //判断一个对象是否为null直接写吗？？问一下
            return null;
        }

        //这一步自己写的，后面删除
        PersonBo personBo = new PersonBo();
        personBo.setPerson(person);



        //下面是生成依据私钥、用户ID、用户名，使用JWT生成token的过程，可能为null；所以有异常
        try {
            UserInfo userInfo = new UserInfo();
            userInfo.setId(person.getId());
            userInfo.setUsername(person.getName());
            String token = JwtUtils.generateToken(userInfo, this.jwtProperties.getPrivateKey()); //调用工具类里面生成token的方法
            System.out.println("生成的token为："+token);
            personBo.setToken(token);
            return personBo;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
