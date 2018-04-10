package com.mmall.service.impl;

import com.aliyun.oss.OSSClient;
import com.google.common.collect.Lists;
import com.mmall.service.IFileService;
import com.mmall.util.FTPUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

@Service("iFileService")
public class FileServiceImpl implements IFileService {

    private Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);

    public static final String ENDPOINT = "oss-cn-shenzhen.aliyuncs.com";
    //阿里云api的密钥Access Key ID
    public static final String ACCESS_KEY_ID = "LTAIYnp0eNL9HOxW";
    //阿里云api的密钥Access Key Secret
    public static final String Access_KEY_SECRET = "p8KioGqjgIpoGRVh6ySfq8tkTivdjK";
    //阿里云api的bucket名称
    public static final String BUCKET_NAME = "uploadsomefiles";
    //阿里云api的文件夹名称
    public static final String FOLDER = "jay/";

    @Override
    public String uploadToOSS(MultipartFile file) throws IOException {
        /**
         * 上传图片到阿里云OSS
         */
        String filePath = null;
        String picPath = null;
        if (file != null) {
            //原始图片名称
            String originalFileName = file.getOriginalFilename();
            System.out.println("原始图片名:" + originalFileName);
            System.out.println(file.getName());
            String newFileName = null;
            if (StringUtils.isNotBlank(originalFileName)) {
                newFileName = UUID.randomUUID() +
                        originalFileName.substring(originalFileName.lastIndexOf("."));
                 filePath = LocalDateTime.now().getMonth().toString() + "/" + newFileName;
                //在OSS上存储图片的地址
                picPath= "http://" + BUCKET_NAME + "." + ENDPOINT + "/" + filePath;
                System.out.println("图片地址" + picPath);
                OSSClient ossClient = new OSSClient(ENDPOINT, ACCESS_KEY_ID, Access_KEY_SECRET);
                ossClient.putObject(BUCKET_NAME, filePath, file.getInputStream());
                ossClient.shutdown();

            }
        }
        return picPath;
    }

    @Override
    public String upload(MultipartFile file,String path){

        String fileName = file.getOriginalFilename();
        //扩展名
        //abc.jpg
        String fileExtensionName = fileName.substring(fileName.lastIndexOf(".")+1);
        String uploadFileName = UUID.randomUUID().toString()+"."+fileExtensionName;
        logger.info("开始上传文件,上传的文件名:{},上传的路径:{},新文件名:{}",fileName,path,uploadFileName);

        File fileDir = new File(path);
        if(!fileDir.exists()){
            fileDir.setWritable(true);
            fileDir.mkdirs();
        }
        File targetFile = new File(path,uploadFileName);
        try {
            file.transferTo(targetFile);
            //文件已经上传到tomcat成功
            FTPUtil.uploadFile(Lists.newArrayList(targetFile));
            //已经上传到ftp服务器上
            targetFile.delete();
        } catch (IOException e) {
            //e.printStackTrace();
            logger.error("上传文件异常");
            return null;
        }
        return targetFile.getName();
    }
}
