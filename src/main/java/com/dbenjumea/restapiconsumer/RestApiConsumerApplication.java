package com.dbenjumea.restapiconsumer;

import com.dbenjumea.restapiconsumer.domain.Beer;
import com.dbenjumea.restapiconsumer.service.BeerRestApiConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class RestApiConsumerApplication implements CommandLineRunner{

	@Autowired
	BeerRestApiConsumer beerRestApiConsumerService;

	public static void main(String[] args) {
		SpringApplication.run(RestApiConsumerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		List<Beer> list = beerRestApiConsumerService.getList();
		List<Beer> list = beerRestApiConsumerService.getListWithHeaders();
		list.stream().forEach(beer -> System.out.println(beer.toString()));
		Beer beer = list.get(0);
//		beer.setId(10L);
		beer.setName("TEST");
		beer.setAbv(3.0);
		beer.setDescription("THIS IS A TEST");
		beer.setLocation("TEST LOCATION");
//		beerRestApiConsumerService.postNew(beer);
		beerRestApiConsumerService.postNewWithHeaders(beer);
	}
}
