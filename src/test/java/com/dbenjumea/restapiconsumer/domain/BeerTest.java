package com.dbenjumea.restapiconsumer.domain;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Random;

@RunWith(SpringRunner.class)
public class BeerTest {

    private static final Random random = new Random();
    private static final String NAME = "name";
    private static final String NAME_UPDATE = "nameUpdate";
    private static final String DESCRIPTION = "description";
    private static final String DESCRIPTION_UPDATE = "descriptionUpdate";
    private static final String LOCATION = "location";
    private static final String LOCATION_UPDATE = "locationUpdate";
    private static final Double ABV = 0.0;
    private static final Double ABV_UPDATE = 0.1;

    @Test
    public void shouldCreateABeerWithTheFilledVariables() {
        Long randomBeerId = random.nextLong();

        // given a new beer instance
        Beer beer = new Beer();
        Assert.assertNotNull(beer);

        // when the variables are set
        beer.setId(randomBeerId);
        beer.setName(NAME);
        beer.setDescription(DESCRIPTION);
        beer.setAbv(ABV);
        beer.setLocation(LOCATION);

        // then the get of each variable have to return the value
        Assert.assertEquals(randomBeerId, beer.getId());
        Assert.assertEquals(NAME, beer.getName());
        Assert.assertEquals(DESCRIPTION, beer.getDescription());
        Assert.assertEquals(ABV, beer.getAbv());
        Assert.assertEquals(LOCATION, beer.getLocation());
    }

    @Test
    public void shouldUpdateTheVariablesOfABeer() {
        Long randomBeerId = random.nextLong();

        // given a new beer instance and with the filled variables
        Beer beer = new Beer();
        beer.setId(randomBeerId);
        beer.setName(NAME);
        beer.setDescription(DESCRIPTION);
        beer.setAbv(ABV);
        beer.setLocation(LOCATION);

        // when the variables are set again with new values
        beer.setId(randomBeerId);
        beer.setName(NAME_UPDATE);
        beer.setDescription(DESCRIPTION_UPDATE);
        beer.setAbv(ABV_UPDATE);
        beer.setLocation(LOCATION_UPDATE);

        // then the get of each variable have to return the new value
        Assert.assertEquals(randomBeerId, beer.getId());
        Assert.assertEquals(NAME_UPDATE, beer.getName());
        Assert.assertEquals(DESCRIPTION_UPDATE, beer.getDescription());
        Assert.assertEquals(ABV_UPDATE, beer.getAbv());
        Assert.assertEquals(LOCATION_UPDATE, beer.getLocation());
    }

    @Test
    public void shouldReturnToStringMethodWithAllTheFilledValues() {
        Long randomBeerId = random.nextLong();

        // given a new beer instance with the filled variables and the expected toString() value
        Beer beer = new Beer();
        beer.setId(randomBeerId);
        beer.setName(NAME);
        beer.setDescription(DESCRIPTION);
        beer.setAbv(ABV);
        beer.setLocation(LOCATION);

        String expectedBeerString = "Beer{" +
                "id=" + randomBeerId +
                ", name='" + NAME + '\'' +
                ", description='" + DESCRIPTION + '\'' +
                ", abv=" + ABV +
                ", location='" + LOCATION + '\'' +
                '}';

        // when the toString() method is executed
        String beerString = beer.toString();

        // then the expected toString() has to be the same than the beer toString() obtained
        Assert.assertEquals(expectedBeerString, beerString);
    }
}
