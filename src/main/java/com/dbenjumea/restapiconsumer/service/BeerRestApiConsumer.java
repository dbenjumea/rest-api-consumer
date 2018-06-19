package com.dbenjumea.restapiconsumer.service;

import com.dbenjumea.restapiconsumer.domain.Beer;
import com.dbenjumea.restapiconsumer.exception.BeerRestApiServiceException;
import com.dbenjumea.restapiconsumer.i18n.HttpCodeManager;
import com.dbenjumea.restapiconsumer.util.PropertiesManager;
import com.dbenjumea.restapiconsumer.util.RestComponentBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

//TODO: Implement Feign as a way to consume a REST API??

@Service("beerRestApiConsumerService")
public class BeerRestApiConsumer implements RestApiConsumer<Beer> {

    private PropertiesManager propertiesManager;

    private RestComponentBuilder restComponentBuilder;

    private static ResourceBundle MESSAGE_BUNDLE  =
            ResourceBundle.getBundle(HttpCodeManager.class.getName());

    @Autowired
    public BeerRestApiConsumer(PropertiesManager propertiesManager, RestComponentBuilder restComponentBuilder) {
        this.propertiesManager = propertiesManager;
        this.restComponentBuilder = restComponentBuilder;
    }

    @Override
    public HttpHeaders createHttpHeaders() {

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON }));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("my_other_key", "my_other_value");

        return headers;
    }

    @Override
    public HttpEntity<Beer[]> createHttpEntityGetList(HttpHeaders headers) {
        return new HttpEntity<Beer[]>(headers);
    }

    @Override
    public HttpEntity<Beer> createHttpEntityPost(Beer body, HttpHeaders headers) {
        return new HttpEntity<Beer>(body, headers);
    }

    @Override
    public List<Beer> getListWithHeaders() {
        HttpEntity<Beer[]> entity = this.createHttpEntityGetList(this.createHttpHeaders());

        RestTemplate restTemplate = this.restComponentBuilder.buildCustomRestTemplate();

        String serviceUrl = propertiesManager.getHttpProjectApiUrl();
        String urlGetListBeers = serviceUrl + propertiesManager.getResourceGetListBeers();
        ResponseEntity<Beer[]> response = restTemplate.exchange(urlGetListBeers, HttpMethod.GET, entity, Beer[].class);
        Optional<Beer[]> optionalBeersList = Optional.of(response.getBody());

        if(this.getStatus(response) == HttpStatus.OK && optionalBeersList.isPresent()) {
            return Arrays.asList(optionalBeersList.get());
        }
        else {
            String httpcode = String.valueOf(this.getStatus(response).value());
            throw new BeerRestApiServiceException(MESSAGE_BUNDLE.getString(httpcode));
        }
    }

    @Override
    public List<Beer> getList() {
        RestTemplate restTemplate = this.restComponentBuilder.buildCustomRestTemplate();
        String serviceUrl = propertiesManager.getHttpProjectApiUrl();
        String urlGetListBeers = serviceUrl + propertiesManager.getResourceGetListBeers();

        ResponseEntity<Beer[]> response = restTemplate.getForEntity(urlGetListBeers, Beer[].class);

        Optional<Beer[]> optionalBeersList = Optional.of(response.getBody());

        if(this.getStatus(response) == HttpStatus.OK && optionalBeersList.isPresent()) {
            return Arrays.asList(optionalBeersList.get());
        }
        else {
            String httpcode = String.valueOf(this.getStatus(response).value());
            throw new BeerRestApiServiceException(MESSAGE_BUNDLE.getString(httpcode));
        }
    }

    @Override
    public boolean postNewWithHeaders(Beer object) {
        RestTemplate restTemplate = this.restComponentBuilder.buildCustomRestTemplate();

        HttpEntity<Beer> entity = this.createHttpEntityPost(object, this.createHttpHeaders());
        String serviceUrl = propertiesManager.getHttpProjectApiUrl();
        String urlPostNewBeer = serviceUrl + propertiesManager.getResourcePostCreateBeer();

        ResponseEntity<Beer> response = restTemplate.exchange(urlPostNewBeer, HttpMethod.POST, entity, Beer.class);
        Optional<Beer> optional = Optional.of(response.getBody());

        return (response.getStatusCode() == HttpStatus.CREATED) ? true : false;
    }

    @Override
    public boolean postNew(Beer object) {
        RestTemplate restTemplate = this.restComponentBuilder.buildCustomRestTemplate();

        String serviceUrl = propertiesManager.getHttpProjectApiUrl();
        String urlPostNewBeer = serviceUrl + propertiesManager.getResourcePostCreateBeer();

        ResponseEntity<Beer> response = restTemplate.postForEntity(urlPostNewBeer, object, Beer.class);
        Optional<Beer> optional = Optional.of(response.getBody());

        return (response.getStatusCode() == HttpStatus.CREATED) ? true : false;
    }

    @Override
    public boolean checkStatus(ResponseEntity<Beer[]> response) {
        HttpStatus statusCode = this.getStatus(response);
        System.out.println("Response Status Code: " + statusCode);

        if (statusCode == HttpStatus.OK) {
            return true;
        }

        else {
            return false;
        }
    }

    @Override
    public HttpStatus getStatus(ResponseEntity<Beer[]> response) {
       return response.getStatusCode();
    }
}
