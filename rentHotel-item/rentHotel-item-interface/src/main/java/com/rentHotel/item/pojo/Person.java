package com.rentHotel.item.pojo;


import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "person")
public class Person {

    @Id   //声明属性为主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)//表示主键自动生成策略;一般和@id一起使用
    private Integer id; //id主键他们是用Long类型的？？？？
    private String pid; //用户的id编号
    private String name;
    private String idcard; //用户的身份证；数据库中可以用varchar（18）或char（18）
    private String age;
    private String adress; //用户的地址
    private String phone; //用户的号码
    private String personpicture; //用户的照片的URI

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPersonpicture() {
        return personpicture;
    }

    public void setPersonpicture(String personpicture) {
        this.personpicture = personpicture;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", pid='" + pid + '\'' +
                ", name='" + name + '\'' +
                ", idcard='" + idcard + '\'' +
                ", age='" + age + '\'' +
                ", address='" + adress + '\'' +
                ", phone='" + phone + '\'' +
                ", personpicture='" + personpicture + '\'' +
                '}';
    }
}
