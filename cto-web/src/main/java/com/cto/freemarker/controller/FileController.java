package com.cto.freemarker.controller;

import com.cto.freemarker.controller.base.BaseController;
import com.cto.freemarker.service.FileService;
import com.cto.freemarker.utils.ConfigUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Objects;

@Slf4j
@Controller
@RequestMapping("/file")
public class FileController extends BaseController {

    private static String fileDir = ConfigUtils.getHomePath() + File.separator + "file" + File.separator;
    private final String demoDir = "demo";
    private final String demoPath = demoDir + File.separator;

    @Autowired
    private FileService fileService;

    @SneakyThrows
    @RequestMapping(value = "fileUpload", method = RequestMethod.POST)
    public String fileUpload(@RequestParam("file") MultipartFile file) throws JsonProcessingException {
        // 获取文件名
        String fileName = file.getOriginalFilename();
        return fileService.upload(fileName, file.getInputStream());
    }

    @RequestMapping(value = "deleteFile", method = RequestMethod.GET)
    public String deleteFile(String fileName) throws JsonProcessingException {
        return fileService.deleteFile(fileName);
    }

//    @RequestMapping(value = "listFiles", method = RequestMethod.GET)
//    public String getFiles() throws JsonProcessingException {
//        List<Map<String, String>> list = new ArrayList<>();
//        File file = new File(fileDir + demoPath);
//        if (file.exists()) {
//            Arrays.stream(Objects.requireNonNull(file.listFiles())).forEach(file1 -> {
//                Map<String, String> fileName = new HashMap<>();
//                fileName.put("fileName", demoDir + "/" + file1.getName());
//                list.add(fileName);
//            });
//        }
//        return new ObjectMapper().writeValueAsString(list);
//    }


    public void downLoad(HttpServletResponse response, String downloadUrl) throws Throwable {
        String filePath = fileDir + demoPath + downloadUrl;
        if (Objects.isNull(filePath)) {
            // 如果接收参数为空则抛出异常，由全局异常处理类去处理。
            throw new NullPointerException("下载地址为空");
        }
        // 读文件
        File file = new File(filePath);
        if (!file.exists()) {
            log.error("下载文件的地址不存在:{}", file.getPath());
            // 如果不存在则抛出异常，由全局异常处理类去处理。
            throw new HttpMediaTypeNotAcceptableException("文件不存在");
        }
        // 获取用户名
        String fileName = file.getName();
        // 重置response
        response.reset();
        // ContentType，即告诉客户端所发送的数据属于什么类型
        response.setContentType("application/octet-stream; charset=UTF-8");
        // 获得文件的长度
        response.setHeader("Content-Length", String.valueOf(file.length()));
        // 设置编码格式
        response.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(fileName, "UTF-8"));
        // 发送给客户端的数据
        OutputStream outputStream = response.getOutputStream();
        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        // 读取文件
        bis = new BufferedInputStream(new FileInputStream(new File(filePath)));
        int i = bis.read(buff);
        // 只要能读到，则一直读取
        while (i != -1) {
            // 将文件写出
            outputStream.write(buff, 0, buff.length);
            // 刷出
            outputStream.flush();
            i = bis.read(buff);
        }
    }
}
