package com.rentHotel.upload.service;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.rentHotel.upload.client.SavePictureUrlClient;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
public class UploadFileServiceImpl {

    //    feign远程调用；保存头像URL到数据库
    @Autowired
    private SavePictureUrlClient savePictureUrlClient;


    private static final List<String> CONTENT_TYPES = Arrays.asList("image/gif","image/jpeg","image/png"); //静态的应用场景
    private static final Logger LOGGER = LoggerFactory.getLogger(UploadFileServiceImpl.class);//报错时的日志
    @Autowired
    private FastFileStorageClient storageClient; //使用FastFDS改造

    /**上传头像  （把图片保存在FastDFS服务器中；不用把URL保存在数据库；用不到数据库的）
     * 图片上传与显示用不到数据库；不用把URL保存在数据库；图片是保存在虚拟机的这个路径:/leyou/storage里面；FastDFS服务配置
     * 图片保存在这里面；然后会返回一个图片所在位置的URL；我们把FastDFS和Nginx绑定了；所以可以通过URL访问到图片；前端就是
     * 那这个URL来访问
     * @param file
     * @return
     */
    public String uploadHeadPortrait(MultipartFile file, String name) {

        String originalFilename = file.getOriginalFilename();
        //校验文件类型
        String contentType = file.getContentType();//这样获取到前端Headers里面的那个Content-Type后面的字符时是什么来判断
        if (!CONTENT_TYPES.contains(contentType)){
            LOGGER.info("文件(即图片)类型不合法: {}", originalFilename);
            return null;
        }
        try {
            //校验文件内容(意思是判断.jpg等名称的文件是不是图片文件;有些名字是但是不是图片格式)
            BufferedImage bufferedImage = ImageIO.read(file.getInputStream());
            if (bufferedImage == null ){
                LOGGER.info("文件(即图片)内容不合法: {}", originalFilename);
                return null;
            }
            //保存到服务器(就是用户自己上传的图片；我们把它保存到服务器)
            //file.transferTo(new File("D:\\OrmProject\\image\\"+ originalFilename));//原来的。
            String ext = StringUtils.substringAfterLast(originalFilename,".");//使用FastFDS改造
            StorePath storePath = this.storageClient.uploadFile(file.getInputStream(), file.getSize(),ext,null);
            //返回url；进行回显
            //return "http://image.leyou.com/" + originalFilename;
//            String touxiangURL = "http://image.leyou.com/" + storePath.getFullPath();【本机】
//阿里云的：            String touxiangURL = "http://8.129.187.106/" + storePath.getFullPath();
            String touxiangURL = "http://192.168.217.128/" + storePath.getFullPath();

            //(在上传头像到FastDFS成功后就)保存头像URL到数据库
            this.savePictureUrlClient.savePersonHeadPortrait(touxiangURL,name);

            return touxiangURL;
        } catch (IOException e) {
            LOGGER.info("服务器内部错误：" + originalFilename);
            e.printStackTrace();
        }
        return null;//为什么返回null（在里面的return直接跳出去了；来不到这里；只是为了不报错而已）

    }
}
