package com.github.danieln96.github.user.api.dto;

import java.util.Date;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UserDto {

    long id;

    String login;
    String name;
    String type;
    String avatarUrl;

    Date createdAt;

    Double calculations;

}
