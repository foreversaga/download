package com.manga.download.web;


import com.manga.download.request.BasicHttp;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
public class MainController {
    @RequestMapping("/")
    public String index(Model model) {
        String domain = "https://tw.manhuagui.com";
        String url = "https://tw.manhuagui.com/comic/19430/";
        String response = getResponse(url);
        //取得漫畫第一話URL
        String div = response.split("<div class=\"book-btn\">")[1];
        String href = div.split("</div>")[0];
        url = href.split("<a href=\"")[1];
        url = url.split("\" target=")[0];
        String pageOneResponse = getResponse(domain + url);
        model.addAttribute("response", pageOneResponse);
        return "index";
    }

    public String getResponse(String url) {
        BasicHttp basicHttp = new BasicHttp();
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        CloseableHttpResponse response = null;
        String responseStr = "";
        try {
            //取得回應
            response = httpClient.execute(httpGet, basicHttp.getContext());
            int state = response.getStatusLine().getStatusCode();
            if (state == 200) {
                HttpEntity httpEntity = response.getEntity();
                if (httpEntity != null) {
                    responseStr = EntityUtils.toString(httpEntity);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                if (httpClient != null) {
                    httpClient.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return responseStr;
    }
}