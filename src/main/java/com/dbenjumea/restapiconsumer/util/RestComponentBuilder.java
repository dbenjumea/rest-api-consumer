package com.dbenjumea.restapiconsumer.util;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component("restComponentBuilder")
public class RestComponentBuilder {

    private RestTemplateBuilder restTemplateBuilder;

    public RestComponentBuilder(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    public RestTemplate buildCustomRestTemplate(HttpMessageConverter messageConverter) {
        if (messageConverter == null) {
            return this.restTemplateBuilder.build();
        } else {
            return this.restTemplateBuilder.additionalMessageConverters(messageConverter).build();
        }

    }

    //private List<HttpMessageConverter> buildCustomizeMessageConverters() { ... }
}
