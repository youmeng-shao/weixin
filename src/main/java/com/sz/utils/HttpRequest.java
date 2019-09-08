package com.sz.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

public class HttpRequest {
    public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        String urlNameString = url + "?"+param;
        System.out.println("请求的微信连接："+urlNameString);
        try {
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的链接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept","*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际连接
            connection.connect();
            // 获取所有相应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的相应字段
            for (String key : map.keySet()) {
                System.out.println(key+"-------->"+map.get(key));
            }
            // 定义BufferReader 输入流来读取url的相应
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = in.readLine())!=null) {
                result += line;
            }

        } catch (Exception e) {
            System.out.println("get请求异常"+e);
            e.printStackTrace();
        } finally {
            try {
                if (in!=null) {
                    in.close();
                }
            } catch (IOException e) {
                System.out.println("关闭异常");
                e.printStackTrace();
            }
        }
        return result;
    }
}
