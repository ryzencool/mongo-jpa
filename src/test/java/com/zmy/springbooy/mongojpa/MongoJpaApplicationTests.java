package com.zmy.springbooy.mongojpa;

import com.zmy.springbooy.mongojpa.config.MongoConfiguration;
import com.zmy.springbooy.mongojpa.enity.HeroResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MongoJpaApplicationTests {


	@Autowired
	private ReactiveMongoTemplate reactiveMongoTemplate;

	public void test() {

	}



}
