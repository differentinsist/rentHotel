package com.rentHotel.item.mapper;

import com.rentHotel.item.pojo.Happytimeimage;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

//拿去happyitme的图片或保存URL到数据库
public interface HappyTimeImageMapper extends Mapper<Happytimeimage> {


    // ASC默认是升序；也就是1排列在前面   参数过多的话要通过注解指定
//    @Select("<script> SELECT * FROM happytimeimage WHERE imagetype = #{imagetype} ORDER BY createtime ${sortbytime} </script>")
//发现一个问题；就是使用#会导致sql执行时排序的关键字多个双引号导致报错；所以改为$的意思是原样输出对吗；但是不安全是吧？还有群友说加标签的
//    @Select("SELECT * FROM happytimeimage WHERE imagetype = #{imagetype} ORDER BY createtime ${sortbytime}")
    @Select("SELECT * FROM happytimeimage WHERE imagetype = #{imagetype} ORDER BY createtime ${sortbytime}")
    public abstract List<Happytimeimage> selectImagesByTypeSortByTime(
            @Param("imagetype")String imagetype,
            @Param("sortbytime")String sortbytime);

}
