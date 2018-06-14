package com.dbenjumea.restapiconsumer.service;

import com.dbenjumea.restapiconsumer.util.RestComponentBuilder;
import org.springframework.stereotype.Service;

@Service("restApiConsumerService")
public class RestApiConsumer {

    private RestComponentBuilder restComponentBuilder;

    public RestApiConsumer(RestComponentBuilder restComponentBuilder) {
        this.restComponentBuilder = restComponentBuilder;
    }
}
