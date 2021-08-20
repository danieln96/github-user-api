package com.github.danieln96.github.user.api.dao;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Builder
@Document
public class QueryCounter {

    @Field("LOGIN")
    @Id
    private String login;
    @Field("REQUEST_COUNT")
    private long requestCount;
}
