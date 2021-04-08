package com.rentHotel.item.controller;


import com.rentHotel.common.pojo.PageResult;
import com.rentHotel.item.pojo.Demand;
import com.rentHotel.item.service.UserDemandServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
@RequestMapping("demand")
public class UserDemandController {

    @Autowired
    private UserDemandServiceImpl userDemandService;


    /**查询所有的需求（需求多就分页展示）
     * @param
     * @param page
     * @param rows
     * @param sortBy
     * @param desc
     * @return
     */
    @GetMapping("queryAllUserDemand")
    public ResponseEntity<PageResult<Demand>> queryAllUserDemand(
            @RequestParam(value = "page", defaultValue = "1")Integer page,
            @RequestParam(value = "rows", defaultValue = "8")Integer rows,
            @RequestParam(value = "sortBy", defaultValue = "createtime")String sortBy,
            @RequestParam(value = "status", defaultValue = "1")Integer status,
            @RequestParam(value = "dname", required = false)String dname,
            @RequestParam(value = "desc", required = false)Boolean desc
    ){
        PageResult<Demand> demandList = this.userDemandService.queryAllUserDemand(page,rows,sortBy,status,dname,desc);

        List<Demand> orginDemadList =  demandList.getItems();
        List<Demand> newDemandList = UserDemandServiceImpl.addNameToDemand(orginDemadList);
        demandList.getItems().clear();
        demandList.setItems(newDemandList);

        if (CollectionUtils.isEmpty(demandList.getItems())){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(demandList);
    }


    /**
     * 保存当前用户提出的需求 （在前端还行实现已解决需求；折叠框弹出在右方，等待实现的需求在列表显示；还有自己解决不了的有个请求帮助
     *                             的输入框；让看见的大神给意见）
     * Demand对象主要有下面几个要保存的字段
     * dnamd用户名、demand用户建议、主要这两个
     * @return
     */
    @GetMapping("saveUserDemand")
    public ResponseEntity<Void> saveUserDemand(
            @RequestParam(name = "dname", defaultValue = "匿名用户")String dname,
            @RequestParam("demand")String demand,
            @RequestParam(name = "status", defaultValue = "1")Integer status
    ){
        if (StringUtils.isBlank(demand)){
            return ResponseEntity.badRequest().build();  //写反了吗
        }
        this.userDemandService.saveUserDemand(dname,demand,status);
        return ResponseEntity.status(HttpStatus.CREATED).build(); //这样写返回？？？
    }

    //还要写根据用户名修改需求内容的接口、或者删除

}
