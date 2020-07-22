package com.example.gatewaydemo;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * @author zhangfanghao
 * @version 1.0
 * @since 2020/7/21 14:29
 */
public class Test {
    public static void main(String[] args) {
        CloseableHttpClient client = HttpClientBuilder.create().build();
        HttpGet get = new HttpGet("http://localhost:8501/api/demo/test/hello?name=z123");

        for (int i = 0; i < 1; i++) {
            new Thread(() -> {
                for (int j = 0; j < 100; j ++) {
                    try {
                        CloseableHttpResponse response = client.execute(get);
                        System.out.println("结果：" + response.getStatusLine().getStatusCode() + ", " + EntityUtils.toString(response.getEntity()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}
