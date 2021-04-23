package com.rentHotel.item.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rentHotel.common.pojo.PageResult;
import com.rentHotel.item.mapper.UserDemandMapper;
import com.rentHotel.item.pojo.Demand;
import com.rentHotel.item.pojo.Hotelgoods;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;


import java.util.ArrayList;
import java.util.List;

@Service
public class UserDemandServiceImpl {

    @Autowired
    private UserDemandMapper userDemandMapper;

    //展示所有的需求列表数据
//    public PageResult<Demand> queryAllUserDemand(Integer page, Integer rows,Boolean desc) {
//        String booleanToString = desc ? "\"desc\"" : "\"asc\"";
//        System.out.println("打印看看是什么类型"+booleanToString);
//
//        PageHelper.startPage(page,rows);  //分页要写在mapper查询方法之前；不然分页无效
////        List<Demand> demandList = this.userDemandMapper.queryAllUserDemand(booleanToString);
//        List<Demand> demandList = this.userDemandMapper.selectAll();
//        //把id和demand的内容组合在一起，中间加上“@：”连接在一起。
////        List<Demand> demandList = addNameToDemand(this.userDemandMapper.queryAllUserDemand(booleanToString));
//
//        PageInfo<Demand> pageInfo = new PageInfo<>(demandList);
//        return new PageResult<>(pageInfo.getTotal(), pageInfo.getPages(), pageInfo.getList());
//    }
    //展示所有的需求列表数据 (想一下如果自己写语句；如何实现，如何写mapper层)
    public PageResult<Demand> queryAllUserDemand(
            Integer page, Integer rows,String sortBy,Integer status,Integer userid, Boolean desc) {
        //初始化example对象
        Example example = new Example(Demand.class);
        Example.Criteria criteria = example.createCriteria();
        if ( status != null ){
            criteria.orEqualTo("status",status);//注意这里的""引号没有空格的话是会报错的；
            // 因为相当于语句拼接； select xx from xx order by sortBy desc/asc (记得空格)
        }
        if ( userid != null ){
            criteria.andEqualTo("userid",userid);
            // 因为相当于语句拼接； select xx from xx order by sortBy desc/asc (记得空格)
        }


        //判断是否要排序；就是看是否传递值过来（）
        if (StringUtils.isNotBlank(sortBy)){
            example.setOrderByClause(sortBy + " " + (desc ? "desc" : "asc"));//注意这里的""引号没有空格的话是会报错的；
            // 因为相当于语句拼接； select xx from xx order by sortBy desc/asc (记得空格)
        }

        PageHelper.startPage(page,rows);  //分页要写在mapper查询方法之前；不然分页无效
//        List<Demand> demandList = this.userDemandMapper.queryAllUserDemand(booleanToString);
        //把id和demand的内容组合在一起，中间加上“@：”连接在一起。
        List<Demand> demandList1 = this.userDemandMapper.selectByExample(example);
        System.out.println("看看返回来的数据；确认为什么停止"+demandList1);//selectByExample这个的返回值居然不是Demand对象；而不是一些分页参数
        System.out.println("\n");
        List<Demand> demandList = addNameToDemand(demandList1);  //这里没起作用
//        List<Demand> demandList = this.userDemandMapper.selectByExample(example);

        PageInfo<Demand> pageInfo = new PageInfo<>(demandList1);
        return new PageResult<>(pageInfo.getTotal(), pageInfo.getPages(), pageInfo.getList());
    }



    //保存用户的需求（）
    public void saveUserDemand(Integer userid,String dname, String demand,Integer status) {
        this.userDemandMapper.saveUserDemand(userid,dname,demand,status);
        System.out.println("保存用户的需求到数据库了吧---------"+demand);
    }







    //抽取一个方法来做字符拼接；
    public static List<Demand> addNameToDemand(List<Demand> demands){
        //System.out.println("先打印一下传递进来的demands"+demands);
        //Demand demand = new Demand();  在循环外面定义不对的；（在相同对象的情况下？因为外部变量会覆盖局部变量？）
        List<Demand> changeDemand = new ArrayList<>();
        //把id和demand的内容组合在一起，中间加上“@：”连接在一起。
        for(Demand de : demands){
            //记得这个你写在循环外面的话；只会获得一条相同的数据(只会得到集合里面的条数据)；而不是得不到每一条
            Demand demand = new Demand();

            String toge = de.getDname()+"@: "+de.getDemand();
            demand.setId(de.getId());
            demand.setDname(de.getDname());
            demand.setDemand(toge);
            demand.setCreatetime(de.getCreatetime());
            demand.setStatus(de.getStatus());
            changeDemand.add(demand);
        System.out.println("组合后=="+demand);
        }
        return changeDemand;
    }

}
