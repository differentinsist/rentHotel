package com.rentHotel.item.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rentHotel.common.pojo.PageResult;
import com.rentHotel.item.mapper.HotelRoomMapper;
import com.rentHotel.item.mapper.SaveRoomOrdersHistoryToDatabaseMapper;
import com.rentHotel.item.pojo.Hotelroom;
import com.rentHotel.item.pojo.RoomOrdersHistory;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service   //事务？？？
public class HotelRoomServiceImpl {

    @Autowired
    private HotelRoomMapper hotelRoomMapper;
    @Autowired
    private SaveRoomOrdersHistoryToDatabaseMapper roomHistoryMapper;


    /**查询所有的房间信息（分页+排序）  后面再做模糊查询
     *
     * @param page
     * @param rows
     * @param sortBy
     * @param desc
     * @return
     */
    public PageResult<Hotelroom> showAllRoomMessage(Integer page, Integer rows, String sortBy, Boolean desc) {
        //初始化example对象；通用mapper的还是mybatis的逆向工程？
        Example example = new Example(Hotelroom.class);
        Example.Criteria criteria = example.createCriteria();
        //模糊查询；没写

        //添加分页条件
        PageHelper.startPage(page,rows);//分页要写在mapper查询方法之前；不然分页无效

        //判断是否需要排序；前端是否传递排序参数过来
        if (StringUtils.isNotBlank(sortBy)) {
            example.setOrderByClause(sortBy + " " + (desc ? "desc" : "asc"));
        }

        //查询
        List<Hotelroom> hotelroomList = this.hotelRoomMapper.selectByExample(example);

        //包装成PageInfo
        PageInfo<Hotelroom> pageInfo = new PageInfo<>(hotelroomList);

        //包装成分页结果集返回
        //return  new PageResult<>(pageInfo.getTotal(),pageInfo.getList()); 构造方法里面少传递一个参数
        return  new PageResult<>(pageInfo.getTotal(),pageInfo.getPages(),pageInfo.getList());
        //上面其实就是使用构造方法new对象；构造方法就是我们实体类写的那几个有参无参构造方法；看需要返回什么数据；传递参数进来就行。
        //基本的构造方法

    }


    //根据用户（表）的id查出用户的开发历史记录
    public List<RoomOrdersHistory> queryAllHistoryByUserId(Integer userid) {
        Example example = new Example(RoomOrdersHistory.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userid",userid);
        List<RoomOrdersHistory> roomList = this.roomHistoryMapper.selectByExample(example);
        System.out.println("看看历史对象"+roomList);
        return roomList;
    }
}
