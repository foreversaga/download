package com.manga.download.request;

import lombok.Getter;
import lombok.Setter;
import org.apache.http.Header;
import org.apache.http.NameValuePair;
import org.apache.http.client.protocol.HttpClientContext;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class BasicHttp {
    private HttpClientContext context = HttpClientContext.create();
    private List<NameValuePair> nameValuePairs = new ArrayList<>();
    private List<Header> headers = new ArrayList<>();
    private Charset encode= StandardCharsets.UTF_8;
}
