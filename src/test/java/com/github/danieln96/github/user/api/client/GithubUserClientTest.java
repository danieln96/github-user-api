package com.github.danieln96.github.user.api.client;

import com.github.danieln96.github.user.api.dao.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
public class GithubUserClientTest {

    @MockBean
    private RestTemplate restTemplate;

    @Autowired
    private GithubUserClient githubUserClient;

    @Test
    void restTemplateException() {

        when(restTemplate.getForEntity(anyString(), any())).thenThrow(HttpClientErrorException.class);

        Assertions.assertThrows(ResponseStatusException.class, () -> githubUserClient.getUserByLogin("user"));

    }

    @Test
    void loginIsEmptyOrNull() {
        Assertions.assertThrows(ResponseStatusException.class, () -> githubUserClient.getUserByLogin(null));
        Assertions.assertThrows(ResponseStatusException.class, () -> githubUserClient.getUserByLogin(""));
    }

    @Test
    void correctRestTemplateResponse() {
        User user = new User();

        when(restTemplate.getForEntity(anyString(), any())).thenReturn(new ResponseEntity<>(user, HttpStatus.OK));

        Assertions.assertEquals(user, githubUserClient.getUserByLogin("any"));
    }
}
