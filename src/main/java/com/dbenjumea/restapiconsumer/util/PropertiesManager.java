package com.dbenjumea.restapiconsumer.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class PropertiesManager {

    @Autowired
    private Environment env;

    private static final String HTTP_PROJECT_API_URL="http.project.api.url";
    private static final String RESOURCE_GET_LIST_BEERS="http.project.api.resource.get.list.beers";
    private static final String RESOURCE_POST_CREATE_BEER="http.project.api.resource.post.create.beer";

    public String getHttpProjectApiUrl() {
        return env.getProperty(HTTP_PROJECT_API_URL);
    }

    public String getResourceGetListBeers() {
        return env.getProperty(RESOURCE_GET_LIST_BEERS);
    }

    public String getResourcePostCreateBeer() {
        return env.getProperty(RESOURCE_POST_CREATE_BEER);
    }
}
