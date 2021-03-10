package config;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

//用于文件交互设置
public abstract interface MultipartFile {
    String getName();
    String getOriginalFilename();
    String getContentType();
    boolean isEmpty();
    long getSize();
    byte[] getBytes() throws IOException;
    InputStream getInputStream() throws IOException;
    void transferTo(File arg0) throws IOException,IllegalStateException;
}
