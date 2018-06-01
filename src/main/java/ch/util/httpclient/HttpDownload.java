package ch.util.httpclient;

import com.trm.util.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Component("httpDownload")
public class HttpDownload {
    public static final int cache = 10 * 1024;
    @Autowired
    private HttpConnectionManager httpConnectionManager;

    public String download(String url, String filepath) throws IOException,ClientProtocolException {
        try{
            CloseableHttpClient httpClient = httpConnectionManager.getHttpClient();
            HttpGet httpGet = new HttpGet(url);
            httpGet.setHeader("Content-Type", "text/xml;charset=UTF-8");
            CloseableHttpResponse response =  httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            InputStream is = entity.getContent();
            if (StringUtils.isBlank(filepath)){
                return "";
            }
            File file = new File(filepath);
            file.getParentFile().mkdirs();
            FileOutputStream fileout = new FileOutputStream(file);
            /**
             * 根据实际运行效果 设置缓冲区大小
             * */
            byte[] buffer = new byte[cache];
            int ch = 0;
            while ((ch = is.read(buffer)) != -1) {
                fileout.write(buffer,0,ch);
            }
            is.close();
            fileout.flush();
            fileout.close();
            return file.getPath();
        }catch (Exception e){
            throw e;
        }
    }
}
