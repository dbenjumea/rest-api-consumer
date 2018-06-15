package com.dbenjumea.restapiconsumer.util;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component("restComponentBuilder")
public class RestComponentBuilder {

    private RestTemplateBuilder restTemplateBuilder;

    public RestComponentBuilder(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    public RestTemplate buildCustomRestTemplate() {
            return this.restTemplateBuilder.build();
    }
}
