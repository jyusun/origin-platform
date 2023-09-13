package com.jyusun.origin.base.launch.rest.core.interceptor;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

public class RestTemplateHeaderModifierInterceptor implements ClientHttpRequestInterceptor {

    private final HttpHeaders headers;

    public RestTemplateHeaderModifierInterceptor(HttpHeaders headers) {
        this.headers = headers;
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
            throws IOException {
        headers.forEach((key, value) -> request.getHeaders().put(key, value));
        return execution.execute(request, body);
    }

}