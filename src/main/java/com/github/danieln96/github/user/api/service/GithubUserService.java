package com.github.danieln96.github.user.api.service;

import com.github.danieln96.github.user.api.client.GithubUserClient;
import com.github.danieln96.github.user.api.dao.User;
import com.github.danieln96.github.user.api.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GithubUserService {

    private final GithubUserClient client;

    public UserDto getUserByLogin(final String login) {

        final User user = client.getUserByLogin(login);

        return transformUser(user);
    }

    private UserDto transformUser(final User user) {
        return UserDto.builder()
                .id(user.getId())
                .login(user.getLogin())
                .name(user.getName())
                .type(user.getType())
                .avatarUrl(user.getAvatarUrl())
                .createdAt(user.getCreateDateTime())
                .calculations(makeCalculations(user))
                .build();
    }

    private Double makeCalculations(final User user) {

        final long dividerNumber = user.getFollowers() * (2 + user.getPublicRepos());

        if (dividerNumber != 0) {
            return (double) 6 / dividerNumber;
        } else {
            throw new ArithmeticException("User followers number is 0!");
        }

    }
}
