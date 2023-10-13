package com.xuda.yygh.oss.service;

import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author ：程序员徐大大
 * @description：TODO
 * @date ：2022-04-15 16:09
 */
@Repository
public interface FileService {
    //上传文件到阿里云oss
    String upload(MultipartFile file);

}
