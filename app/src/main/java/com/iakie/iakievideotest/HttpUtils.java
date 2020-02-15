package com.iakie.iakievideotest;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;

import java.net.URL;

/**
 * Author: iqiqiya
 * Date: 2020-02-15
 * Time: 15:09
 * Blog: blog.77sec.cn
 * Github: github.com/iqiqiya
 */
public class HttpUtils {

    public static String getJsonContent(String path) {

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        try {
            URL url = new URL(path);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            InputStream inputStream = connection.getInputStream();
            byte[]buf = new byte[1024];
            int hasRead = 0;
            while (((hasRead = inputStream.read(buf))!=-1)) {
                byteArrayOutputStream.write(buf,0,hasRead);
            }
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        return byteArrayOutputStream.toString();
    }
}
