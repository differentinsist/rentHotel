package com.rentHotel.item.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rentHotel.common.pojo.PageResult;
import com.rentHotel.item.mapper.HotelGoodsMapper;
import com.rentHotel.item.pojo.Hotelgoods;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class HotelGoodsServiceImpl {

    @Autowired
    private HotelGoodsMapper hotelGoodsMapper;

    public PageResult<Hotelgoods> showAllGoods(String key, Integer page, Integer rows, String sortBy, Boolean desc) {
        //初始化example对象
        Example example = new Example(Hotelgoods.class);
        Example.Criteria criteria = example.createCriteria();

        //判断是否需要模糊查询（就是是否传入key）
        if(StringUtils.isNotBlank(key)){
            criteria.andLike("goodsname", "%" + key + "%").orEqualTo("goodsbrand",key);
        }         //通过商品名称goodsname模糊查询失败了    通过商品品牌goodsbrand就成功了；看源码好像品牌的参数都会覆盖名称参数

        //添加分页条件
        PageHelper.startPage(page,rows);
        //判断是否要排序；就是看是否传递值过来
        if (StringUtils.isNotBlank(sortBy)){
            example.setOrderByClause(sortBy + " " + (desc ? "desc" : "asc"));//注意这里的""引号没有空格的话是会报错的；
            // 因为相当于语句拼接； select xx from xx order by sortBy desc/asc (记得空格)
        }
        List<Hotelgoods> hotelgoods = this.hotelGoodsMapper.selectByExample(example);

        //包装成PageInfo
        PageInfo<Hotelgoods> pageInfo = new PageInfo<>(hotelgoods);

        //包装成分页结果集返回
        return new PageResult<>(pageInfo.getTotal(),pageInfo.getPages(),pageInfo.getList());

    }
}
