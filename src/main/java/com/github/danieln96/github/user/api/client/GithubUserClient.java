package com.github.danieln96.github.user.api.client;

import com.github.danieln96.github.user.api.dao.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@Component
@RequiredArgsConstructor
public class GithubUserClient {

    private final RestTemplate restTemplate;
    @Value("${github.user.api.url}")
    private String url;

    public User getUserByLogin(final String login) {

        if (login == null || login.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Login is empty");
        }

        final String uriString = UriComponentsBuilder
                .fromHttpUrl(url.replace("{login}", login))
                .toUriString();

        try {
            final ResponseEntity<User> userResponseEntity = restTemplate.getForEntity(uriString, User.class);

            return userResponseEntity.getBody();

        } catch (final RestClientException exc) {
            log.error("Exception for login [{}]", login, exc);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("User with login [%s] not found", login));
        }
    }
}
