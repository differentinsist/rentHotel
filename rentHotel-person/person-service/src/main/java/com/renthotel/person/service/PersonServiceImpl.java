package com.renthotel.person.service;

import com.renthotel.person.mapper.PersonMapper;
import com.renthotel.person.pojo.Person;
import com.renthotel.person.utils.CodecUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl {

    //因为我数据库有一个pid的字段；相当于用户的编号，String类型，没有设置啥，手动添加的，我这里设置他自增，然后在转换为String类型就可以了
    private static int PID = 00005 ; //从3开始，因为数据库已经有可1和2了
    //这样子会不会也有问题？只要项目重新运行；会不会又从3开始，想办法永久保存这个计数，或者查询数据库最后一条的数据，拿到它的pid，然后加1就行；但是不好
    //所以上面的静态变量行不通，在每次项目重启后都会重新从00003开始了的。  我直接删除数据库的pid字段得了，不合理的设计

    @Autowired
    private PersonMapper personMapper;

    //检查用户注册的用户名和的话是否符合要求
    public Boolean checkPerson(String nameOrPhone, Integer type) {
        Person person = new Person();
        if (type == 1){  //type参数为1的话，说明验证的是用户名
            person.setName(nameOrPhone);
        }else if (type == 2){
            person.setPhone(nameOrPhone); //type参数为2的话，说明验证的是用户的手机号（我还没做）
        }

        return this.personMapper.selectCount(person) == 0; //因为selectCount的参数要求是对象类型；所以要new对象
        //selectCount方法是查询数据，返回值是有多少条记录，int值，对于0的话说明没有，也就是用户名还没有被使用
    }


    //保存新祖册的用户
    public void registerPerson(Person person) {
        //-1.查询redis中的验证码，

        //0.校验验证码

        //1.生成盐salt（为了密码更加安全复杂）
        String salt = CodecUtils.generateSalt();
        person.setSalt(salt);
        //2.使用盐对密码进行加密      因为我在Controller层是Person对象接收参数，所以参数已经在person这个变量里面了
        person.setPassword(CodecUtils.md5Hex(person.getPassword(),salt));
        //3.保存注册的用户
        int pidadd = PID;
        //把int类型转换为String类型的三种方法区别【注意：在转换的时候我发现比如00003int类型转换为String类型会把前面的零不要只留下3了】
        String PIDstr = pidadd + "";             //(方法1)会产生两个String对象
        //String PIDstr = Integer.toString(pidadd);//(方法2)
//        String PIDstr = String.valueOf(pidadd);    //(方法3)直接使用String类的静态方法，只产生一个对象
        //person.setPid(PIDstr); //就是我数据库定义有一个pid字段（最后我把数据库的这个字段删除了）
        this.personMapper.insertSelective(person);
    }

    //查询用户的名字和密码是否正确，正确就运行登陆 （注意用户登陆时输入的密码是没加密的的原始密码，那我们比较密码是否正确，思路是通
    // 过这个用户名查询到这个用户的salt盐，然后再加密一起比较是否相同，也即是说比较的是加密的）
    public Person queryPerson(String name, String password) {
        Person record = new Person();
        record.setName(name);
        Person person =  this.personMapper.selectOne(record); //查询到该用户的数据，拿到salt，
        if(person == null){
            return null; //就是没有查到用户的话返回空吗??????????
        }
        String salt = person.getSalt();//拿到当前用户的盐salt
        //加密密码然后和数据库的比较
        String beforePassword = CodecUtils.md5Hex(password,salt);//拿到传递进来的原始密码加密
        if(StringUtils.equals(beforePassword,person.getPassword())){
            return person;
        }
        return null;       //查询单个用户使用的是selectOne()这个方法；select()这个的话返回的是List集合
    }
}

