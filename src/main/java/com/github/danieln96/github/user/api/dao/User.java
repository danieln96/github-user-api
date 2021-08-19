package com.github.danieln96.github.user.api.dao;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties
public class User {

    private long id;

    private String login;
    private String name;
    private String type;
    @JsonProperty("avatar_url")
    private String avatarUrl;

    private long followers;
    @JsonProperty("public_repos")
    private long publicRepos;

    @JsonProperty("created_at")
    private Date createDateTime;



}

