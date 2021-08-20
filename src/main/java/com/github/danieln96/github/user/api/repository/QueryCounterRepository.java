package com.github.danieln96.github.user.api.repository;

import com.github.danieln96.github.user.api.dao.QueryCounter;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QueryCounterRepository extends MongoRepository<QueryCounter, String> {

    QueryCounter findFirstByLogin(String login);
}
