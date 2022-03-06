package com.cto.freemarker.service;

import java.io.InputStream;

public interface FileService {

    String upload(String url, InputStream inputStream);

    void downLoad(String url);

    String deleteFile(String url);
}
