package com.dbenjumea.restapiconsumer.service;

import com.dbenjumea.restapiconsumer.domain.Beer;
import com.dbenjumea.restapiconsumer.util.PropertiesManager;
import com.dbenjumea.restapiconsumer.util.RestComponentBuilder;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Random;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class BeerRestApiConsumerTest {

    private static final String HTTP_PROJECT_API_URL="http://localhost:8050";
    private static final String RESOURCE_GET_LIST_BEERS="/beers";
    private static final String RESOURCE_POST_CREATE_BEER="/beer/create";

    private PropertiesManager propertiesManager;
    private RestComponentBuilder restComponentBuilder;
    private RestTemplate restTemplate;

    @InjectMocks
    private BeerRestApiConsumer beerRestApiConsumer;

    @Before
    public void setUp() {
        propertiesManager = Mockito.mock(PropertiesManager.class);
        restComponentBuilder = Mockito.mock(RestComponentBuilder.class);
        restTemplate = Mockito.mock(RestTemplate.class);

        when(this.restComponentBuilder.buildCustomRestTemplate()).thenReturn(restTemplate);
        when(propertiesManager.getHttpProjectApiUrl()).thenReturn(HTTP_PROJECT_API_URL);
        when(propertiesManager.getResourceGetListBeers()).thenReturn(RESOURCE_GET_LIST_BEERS);
        when(propertiesManager.getResourcePostCreateBeer()).thenReturn(RESOURCE_POST_CREATE_BEER);

    }

    @Test
    public void shouldExecuteGetRequestToRestApiWithMessageAndHeaders() {
        // given the rest api consumer beerRestApiConsumer
        beerRestApiConsumer = new BeerRestApiConsumer(propertiesManager, restComponentBuilder);

        // when the rest api consumer executes getList of Beer
        Beer[] beers = new Beer[1];
        beers[0] = this.createRandomBeer();
        Assert.assertEquals(beers.length, 1);

        ResponseEntity<Beer[]> responseEntity = new ResponseEntity<>(beers, HttpStatus.OK);

        String urlGetListBeers = HTTP_PROJECT_API_URL + RESOURCE_GET_LIST_BEERS;
        when(restTemplate.exchange(
                Mockito.eq(urlGetListBeers),
                Mockito.eq(HttpMethod.GET),
                any(HttpEntity.class),
                Mockito.eq(Beer[].class))
        ).thenReturn(responseEntity);

        // then a list of beers has to be returned
        List<Beer> beerList = beerRestApiConsumer.getListWithHeaders();
        Assert.assertNotNull(beerList);
        Assert.assertEquals(beers.length, beerList.size());
    }

    @Test
    public void shouldExecuteGetRequestToRestApiWithMessage() {
        // given the rest api consumer beerRestApiConsumer
        beerRestApiConsumer = new BeerRestApiConsumer(propertiesManager, restComponentBuilder);

        // when the rest api consumer executes getList of Beer
        Beer[] beers = new Beer[1];
        beers[0] = this.createRandomBeer();
        Assert.assertEquals(beers.length, 1);

        ResponseEntity<Beer[]> responseEntity = new ResponseEntity<>(beers, HttpStatus.OK);

        String urlGetListBeers = HTTP_PROJECT_API_URL + RESOURCE_GET_LIST_BEERS;
        when(restTemplate.getForEntity(
                Mockito.eq(urlGetListBeers),
                Mockito.eq(Beer[].class))
        ).thenReturn(responseEntity);

        // then a list of beers has to be returned
        List<Beer> beerList = beerRestApiConsumer.getList();
        Assert.assertNotNull(beerList);
        Assert.assertEquals(beers.length, beerList.size());
    }

    @Test
    public void shouldExecutePostRequestToRestApiWithMessageAndHeaders() {
        // given the rest api consumer beerRestApiConsumer and a new beer
        beerRestApiConsumer = new BeerRestApiConsumer(propertiesManager, restComponentBuilder);
        Beer beer = this.createRandomBeer();
        Assert.assertNotNull(beer);

        // when the rest api consumer executes post for creating a new Beer

        ResponseEntity<Beer> responseEntity = new ResponseEntity<>(beer, HttpStatus.CREATED);

        String urlPostNewBeer = HTTP_PROJECT_API_URL + RESOURCE_POST_CREATE_BEER;
        when(restTemplate.exchange(
                Mockito.eq(urlPostNewBeer),
                Mockito.eq(HttpMethod.POST),
                any(HttpEntity.class),
                Mockito.eq(Beer.class))
        ).thenReturn(responseEntity);

        // then a created = true has to be returned
        boolean created = beerRestApiConsumer.postNewWithHeaders(beer);
        Assert.assertTrue(created);
    }

    @Test
    public void shouldExecutePostRequestToRestApiWithMessage() {
        // given the rest api consumer beerRestApiConsumer and a new beer
        beerRestApiConsumer = new BeerRestApiConsumer(propertiesManager, restComponentBuilder);
        Beer beer = this.createRandomBeer();
        Assert.assertNotNull(beer);

        // when the rest api consumer executes post for creating a new Beer
        ResponseEntity<Beer> responseEntity = new ResponseEntity<>(beer, HttpStatus.CREATED);

        String urlPostNewBeer = HTTP_PROJECT_API_URL + RESOURCE_POST_CREATE_BEER;

        when(restTemplate.postForEntity(
                Mockito.eq(urlPostNewBeer),
                Mockito.eq(beer),
                Mockito.eq(Beer.class))
        ).thenReturn(responseEntity);

        // then a created = true has to be returned
        boolean created = beerRestApiConsumer.postNew(beer);
        Assert.assertTrue(created);
    }

    private Beer createRandomBeer() {
        Random randomBeerId = new Random();
        Beer beer = new Beer();
        beer.setId(randomBeerId.nextLong());
        beer.setName("name");
        beer.setDescription("description");
        beer.setAbv(0.0);
        beer.setLocation("location");

        return beer;
    }
}
