/*

package com.mmall.util;

import com.aliyun.oss.OSSClient;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.UUID;

public class OSSUtil {

    public static final String ENDPOINT = "oss-cn-shenzhen.aliyuncs.com";
    //阿里云api的密钥Access Key ID
    public static final String ACCESS_KEY_ID = "";
    //阿里云api的密钥Access Key Secret
    public static final String Access_KEY_SECRET = "";
    //阿里云api的bucket名称
    public static final String BUCKET_NAME = "";
    //阿里云api的文件夹名称
    public static final String FOLDER = "";


    public ModelAndView addStudent(MultipartFile file) throws IOException {
        ModelAndView modelAndView = new ModelAndView();


         // 上传图片到阿里云OSS


        if (file != null) {
            //原始图片名称
            String originalFileName = file.getOriginalFilename();
            System.out.println("原始图片名:" + originalFileName);
            String newFileName = null;
            if (StringUtils.isNotBlank(originalFileName)) {
                newFileName = UUID.randomUUID() +
                        originalFileName.substring(originalFileName.lastIndexOf("."));
                String filePath = LocalDateTime.now().getMonth().toString() + "/" + newFileName;
                //在OSS上存储图片的地址
                String picPath = "http://" + BUCKET_NAME + "." + ENDPOINT + "/" + filePath;
                System.out.println("图片地址" + picPath);
                OSSClient ossClient = new OSSClient(ENDPOINT, ACCESS_KEY_ID, Access_KEY_SECRET);
                ossClient.putObject(BUCKET_NAME, filePath, file.getInputStream());
                ossClient.shutdown();
            }
        }
    }
}


*/
