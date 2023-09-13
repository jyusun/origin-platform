package com.jyusun.origin.base.launch.rest.config;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.jyusun.origin.base.launch.rest.core.interceptor.RestTemplateHeaderModifierInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.http.HttpMessageConvertersAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.Locale;

@AutoConfiguration(after = HttpMessageConvertersAutoConfiguration.class)
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
@ConditionalOnClass(RestTemplate.class)
@RequiredArgsConstructor
public class RestTemplateConfiguration {

    private final FastJsonHttpMessageConverter fastJsonHttpMessageConverter;

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        // 设置请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);

        headers.setConnection("Keep-Alive");
        headers.setAcceptLanguage(Locale.LanguageRange.parse("zh-CN"));
        headers.set("User-Agent", "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) "
                + "Chrome/31.0.1650.16 Safari/537.36");
        headers.set("Accept-Encoding", "gzip,deflate");
        restTemplate.setInterceptors(Collections.singletonList(new RestTemplateHeaderModifierInterceptor(headers)));

        // //换上fastjson
        // List<HttpMessageConverter<?>> httpMessageConverters =
        // restTemplate.getMessageConverters();
        // Iterator<HttpMessageConverter<?>> iterator = httpMessageConverters.iterator();
        // if (iterator.hasNext()) {
        // HttpMessageConverter<?> converter = iterator.next();
        // //原有的String是ISO-8859-1编码 去掉
        // if (converter instanceof StringHttpMessageConverter) {
        // iterator.remove();
        // }
        // }
        // StringHttpMessageConverter stringHttpMessageConverter = new
        // StringHttpMessageConverter(StandardCharsets.UTF_8);
        // stringHttpMessageConverter.setWriteAcceptCharset(true);
        // httpMessageConverters.add(stringHttpMessageConverter);
        //
        // httpMessageConverters.add(0, fastJsonHttpMessageConverter);
        // restTemplate.setMessageConverters(httpMessageConverters);

        // 设置请求和响应的消息转换器
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN));
        restTemplate.setMessageConverters(Collections.singletonList(converter));

        restTemplate.setRequestFactory(this.clientHttpRequestFactory());
        return restTemplate;
    }

    /**
     * 设置请求工厂
     * @return
     */
    // public ClientHttpRequestFactory clientHttpRequestFactory(CloseableHttpClient
    // closeableHttpClient) {
    public ClientHttpRequestFactory clientHttpRequestFactory() {
        HttpComponentsClientHttpRequestFactory httpClientFactory = new HttpComponentsClientHttpRequestFactory();
        // httpClientFactory.setHttpClient(closeableHttpClient);
        httpClientFactory.setConnectTimeout(10000);
        httpClientFactory.setConnectionRequestTimeout(60 * 1000);
        return httpClientFactory;
    }

}