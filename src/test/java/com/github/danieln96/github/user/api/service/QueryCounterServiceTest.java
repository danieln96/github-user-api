package com.github.danieln96.github.user.api.service;

import com.github.danieln96.github.user.api.dao.QueryCounter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;

@SpringBootTest
public class QueryCounterServiceTest {

    private static final String SAMPLE_USER_LOGIN = "sample_user_login";

    @Autowired
    private QueryCounterService queryCounterService;
    @Autowired
    private MongoTemplate mongoTemplate;

    @BeforeEach
    void setUp() {
        mongoTemplate.dropCollection(QueryCounter.class);
    }

    @Test
    void checkCounterIncreasing() {
        queryCounterService.increaceTheQueryCounterForLogin(SAMPLE_USER_LOGIN);
        queryCounterService.increaceTheQueryCounterForLogin(SAMPLE_USER_LOGIN);
        queryCounterService.increaceTheQueryCounterForLogin(SAMPLE_USER_LOGIN);

        Assertions.assertEquals(1, mongoTemplate.findAll(QueryCounter.class).size());
    }

    @Test
    void emptyLogin() {
        queryCounterService.increaceTheQueryCounterForLogin("");
        queryCounterService.increaceTheQueryCounterForLogin(null);
        Assertions.assertEquals(0, mongoTemplate.findAll(QueryCounter.class).size());
    }
}
