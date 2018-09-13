package ch.common.http;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component("httpClientUtil")
public class HttpClientUtil {

    @Autowired
    private HttpConnectionManager httpConnectionManager;

    /** 
    * 发送 post请求 
    * @param httpUrl 地址 
    * @throws IOException
    * @throws ClientProtocolException
    */
    public String sendHttpPost(String httpUrl) throws ClientProtocolException, IOException {
        HttpPost httpPost = new HttpPost(httpUrl);// 创建httpPost
        return sendHttpPost(httpPost);
    }

    /** 
     * 发送 post请求 
     * @param httpUrl 地址 
     * @param params 参数(格式:key1=value1&key2=value2) 
     * @throws IOException
     * @throws ClientProtocolException
     */
    public String sendHttpPost(String httpUrl, String params) throws ClientProtocolException, IOException {
        HttpPost httpPost = new HttpPost(httpUrl);// 创建httpPost
        //设置参数  
        StringEntity stringEntity = new StringEntity(params, "UTF-8");
        stringEntity.setContentType("application/x-www-form-urlencoded");
        httpPost.setEntity(stringEntity);
        return sendHttpPost(httpPost);
    }

    /** 
     * 发送 post请求 
     * @param httpUrl 地址 
     * @param maps 参数 
     * @throws IOException
     * @throws ClientProtocolException
     */
    public String sendHttpPost(String httpUrl, Map<String, String> maps) throws ClientProtocolException, IOException {
        HttpPost httpPost = new HttpPost(httpUrl);// 创建httpPost
        // 创建参数队列    
        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        for (String key : maps.keySet()) {
            nameValuePairs.add(new BasicNameValuePair(key, maps.get(key)));
        }
        httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
        return sendHttpPost(httpPost);
    }

    /** 
     * 发送 post请求（带文件） 
     * @param httpUrl 地址 
     * @param maps 参数 
     * @param fileLists 附件 
     * @throws IOException
     * @throws ClientProtocolException
     */
    public String sendHttpPost(String httpUrl, Map<String, String> maps, List<File> fileLists)
            throws ClientProtocolException, IOException {
        HttpPost httpPost = new HttpPost(httpUrl);// 创建httpPost
        MultipartEntityBuilder meBuilder = MultipartEntityBuilder.create();
        for (String key : maps.keySet()) {
            meBuilder.addPart(key, new StringBody(maps.get(key), ContentType.TEXT_PLAIN));
        }
        for (File file : fileLists) {
            FileBody fileBody = new FileBody(file);
            meBuilder.addPart("files", fileBody);
        }
        HttpEntity reqEntity = meBuilder.build();
        httpPost.setEntity(reqEntity);
        return sendHttpPost(httpPost);
    }

    /** 
     * 发送Post请求 
     * @param httpPost 
     * @return 
     * @throws IOException
     * @throws ClientProtocolException
     */
    private String sendHttpPost(HttpPost httpPost) throws ClientProtocolException, IOException {

        CloseableHttpClient httpClient = httpConnectionManager.getHttpClient();
        CloseableHttpResponse response = null;
        response = httpClient.execute(httpPost);
        HttpEntity entity = null;
        String responseContent = null;
        // 执行请求  
        entity = response.getEntity();
        responseContent = EntityUtils.toString(entity, "UTF-8");
        return responseContent;
    }


    /** 
     * 发送Get请求 
     * @param httpGet
     * @return 
     * @throws IOException
     * @throws ClientProtocolException
     */
    private String sendHttpGet(HttpGet httpGet) throws ClientProtocolException, IOException {
        CloseableHttpClient httpClient = httpConnectionManager.getHttpClient();
        httpGet.setHeader("Content-Type", "text/xml;charset=UTF-8");
        CloseableHttpResponse response = null;
        HttpEntity entity = null;
        String responseContent = null;
        // 执行请求  
        response = httpClient.execute(httpGet);
        entity = response.getEntity();
        responseContent = EntityUtils.toString(entity, "UTF-8");
        return responseContent;
    }


}
