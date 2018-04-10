package com.mmall.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IFileService {

    String upload(MultipartFile file, String path);

    String uploadToOSS(MultipartFile file) throws IOException;
}
