package com.github.danieln96.github.user.api.controller;

import com.github.danieln96.github.user.api.dto.UserDto;
import com.github.danieln96.github.user.api.service.GithubUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class GithubUserController {

    private final GithubUserService githubUserService;

    @GetMapping("/users/{login}")
    public UserDto getUserDtoByLogin(@PathVariable("login") final String login) {

        return githubUserService.getUserByLogin(login);
    }
}
