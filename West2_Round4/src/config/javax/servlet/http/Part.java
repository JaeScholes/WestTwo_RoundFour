package config.javax.servlet.http;

import java.io.InputStream;
import java.util.Collection;

public abstract interface Part {
    InputStream getInputStream() throws java.io.IOException;
    String getContentType();
    String getName();
    String getSubmittedFileName();
    long getSize();
    void write(String arg0) throws java.io.IOException;
    void delete() throws java.io.IOException;
    String getHeader(String arg0);
    Collection getHeaders(String arg0);
    Collection getHeaderNames();
}
