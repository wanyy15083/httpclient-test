package com.my.http.request;

import com.my.http.connection.IdleConnectionEvictor;
import com.my.http.util.Utils;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class RequestServerTest {

    //请求地址
    public static final String REQ_URL = "http://192.168.10.10:8888/httpweb/hello";
//        public static final String REQ_URL = "http://127.0.0.1:8888/hello";

    public static final String DEFAULT_CHARSET = "UTF-8";

    public static void main(String[] args) throws Exception {

        final CloseableHttpClient httpClient = HttpClients.createDefault();

        long start = System.currentTimeMillis();

        for (int i = 0; i < 100; i++) {
            long start1= System.currentTimeMillis();
            RequestServer.doPost(null, httpClient);
//            RequestServer.doGet(null, httpClient);
            System.out.println("total:" + (System.currentTimeMillis() - start1));
        }
        System.out.println("total:" + (System.currentTimeMillis() - start));

    }

}
