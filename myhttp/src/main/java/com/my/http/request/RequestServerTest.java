package com.my.http.request;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpConnectionManager;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.SimpleHttpConnectionManager;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RequestServerTest {

    //请求地址
    public static final String REQ_URL = "http://192.168.10.10:8888/httpweb/hello";
//        public static final String REQ_URL = "http://127.0.0.1:8888/hello";

    public static final String DEFAULT_CHARSET = "UTF-8";

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        for (int i = 0; i < 40; i++) {
            executorService.execute(new Runnable() {
                public void run() {
                    HttpClient client = new HttpClient();
                    HttpConnectionManager connectionManager = client.getHttpConnectionManager();
                    HttpConnectionManagerParams params = connectionManager.getParams();
                    params.setDefaultMaxConnectionsPerHost(100);
                    params.setMaxTotalConnections(500);
                    params.setConnectionTimeout(3000);
                    params.setSoTimeout(5000);
                    long start = System.currentTimeMillis();
                    getMethod(client);
//            postMethod(client);
                    System.out.println(Thread.currentThread().getName() + "count time:" + (System.currentTimeMillis() - start));
                }
            });
        }
//        HttpClient client = new HttpClient(new SimpleHttpConnectionManager(true) );
//        HttpClient client = new HttpClient();
//        HttpConnectionManager connectionManager = client.getHttpConnectionManager();
//        HttpConnectionManagerParams params = connectionManager.getParams();
//        params.setDefaultMaxConnectionsPerHost(100);
//        params.setMaxTotalConnections(500);
//        params.setConnectionTimeout(3000);
//        params.setSoTimeout(5000);
//
//        for (int i = 0; i < 100; i++) {
//            long start = System.currentTimeMillis();
//            getMethod(client);
////            postMethod(client);
//            System.out.println("count time:" + (System.currentTimeMillis() - start));
//        }
    }

    public static void getMethod(HttpClient client) {
        GetMethod method = new GetMethod(REQ_URL);
        NameValuePair[] data = {new NameValuePair("name", "123")};
        method.setQueryString(data);
        try {
            int statusCode = client.executeMethod(method);
            if (statusCode != HttpStatus.SC_OK) {
                System.err.println("Method failed: " + method.getStatusLine());
            }
            byte[] responseBody = method.getResponseBody();
            System.out.println(new String(responseBody));
        } catch (HttpException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            method.releaseConnection();
//            ((SimpleHttpConnectionManager)client.getHttpConnectionManager()).shutdown();
//            client.getHttpConnectionManager().closeIdleConnections(0);
        }
    }

    public static void postMethod(HttpClient client) {
        PostMethod method = new PostMethod(REQ_URL);
        NameValuePair[] data = {new NameValuePair("name", "123")};
        method.setRequestBody(data);
        try {
            int statusCode = client.executeMethod(method);
            if (statusCode != HttpStatus.SC_OK) {
                System.err.println("Method failed: " + method.getStatusLine());
            }
            byte[] responseBody = method.getResponseBody();
            System.out.println(new String(responseBody));
        } catch (HttpException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            method.releaseConnection();
        }

    }


}
