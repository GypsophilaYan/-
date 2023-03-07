package com.example.controller;

import com.example.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

/**
 * 文件上传和下载
 */
@RestController
@Slf4j
@RequestMapping("/common")
public class CommonController {

    @Value("${take-out.path}")
    String basePath;

    /**
     * 文件上传
     * @param file
     * @return
     */
    @PostMapping("/upload")
    public R<String> upload(MultipartFile file){            //file是一个临时文件，需要转存到指定位置，否则本次请求完成后临时文件会删除


        log.info("上传的文件名称：{}",file);

        //获取原始文件名
        String originalFilename = file.getOriginalFilename();
        //获取文件后缀，生成随机的UUID+文件后缀形成文件名
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));  //注意第一对()后是否要+1

        String fileName = UUID.randomUUID().toString() + suffix;        //suffix中包含“.xxx”,注意这个.

        //创建一个目录对象
        File dir = new File(basePath);
        //判断当前目录是否存在
        if (!dir.exists()){     //目录不存在，执行下面的语句
            dir.mkdirs();
        }

        try {
            //将临时文件转存到指定位置
            file.transferTo(new File(basePath + fileName));
//            file.transferTo(new File("E:\\program-picture\\1.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @RequestMapping("/download")
    public void download(String name, HttpServletResponse response) throws IOException {
        try {
            //输入流,通过输入流读取文件内容
            FileInputStream fileInputStream = new FileInputStream(new File(basePath + name));
            //输出流，通过输出流将文件写回浏览器，在浏览器展示图片
            ServletOutputStream outputStream = response.getOutputStream();

            response.setContentType("image/jpeg");
            int len = 0;
            byte[] bytes = new byte[1024];
            while ((len = fileInputStream.read(bytes)) != -1){
                outputStream.write(bytes,0,len);
                outputStream.flush();
            }
            outputStream.close();
            fileInputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
