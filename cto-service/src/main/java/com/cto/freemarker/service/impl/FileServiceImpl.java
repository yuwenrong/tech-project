package com.cto.freemarker.service.impl;

import com.cto.freemarker.entity.ReturnResponse;
import com.cto.freemarker.service.FileService;
import com.cto.freemarker.utils.ConfigUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

import java.io.*;

@Slf4j
@Service
public class FileServiceImpl implements FileService {

    private static String fileDir = ConfigUtils.getHomePath() + File.separator + "file" + File.separator;
    private final String demoDir = "demo";
    private final String demoPath = demoDir + File.separator;


    @SneakyThrows
    @Override
    public String upload(String fileName, InputStream inputStream) {
        //判断是否为IE浏览器的文件名，IE浏览器下文件名会带有盘符信息
        // Check for Unix-style path
        int unixSep = fileName.lastIndexOf('/');
        // Check for Windows-style path
        int winSep = fileName.lastIndexOf('\\');
        // Cut off at latest possible point
        int pos = (Math.max(winSep, unixSep));
        if (pos != -1) {
            fileName = fileName.substring(pos + 1);
        }
        // 判断是否存在同名文件
        if (existsFile(fileName)) {
            return new ObjectMapper().writeValueAsString(ReturnResponse.failure("存在同名文件，请先删除原有文件再次上传"));
        }
        File outFile = new File(fileDir + demoPath);
        if (!outFile.exists() && !outFile.mkdirs()) {
            log.error("创建文件夹【{}】失败，请检查目录权限！", fileDir + demoPath);
        }
        log.info("上传文件：{}", fileDir + demoPath + fileName);
        try (InputStream in = inputStream;
             OutputStream out = new FileOutputStream(fileDir + demoPath + fileName)) {
            StreamUtils.copy(in, out);
            return new ObjectMapper().writeValueAsString(ReturnResponse.success(null));
        } catch (IOException e) {
            log.error("文件上传失败", e);
            return new ObjectMapper().writeValueAsString(ReturnResponse.failure());
        }
    }

    @Override
    public void downLoad(String url) {

    }

    @SneakyThrows
    @Override
    public String deleteFile(String fileName) {
        if (fileName.contains("/")) {
            fileName = fileName.substring(fileName.lastIndexOf("/") + 1);
        }
        File file = new File(fileDir + demoPath + fileName);
        log.info("删除文件：{}", file.getAbsolutePath());
        if (file.exists() && !file.delete()) {
            log.error("删除文件【{}】失败，请检查目录权限！", file.getPath());
        }
        return new ObjectMapper().writeValueAsString(ReturnResponse.success());
    }


    private boolean existsFile(String fileName) {
        File file = new File(fileDir + demoPath + fileName);
        return file.exists();
    }
}
