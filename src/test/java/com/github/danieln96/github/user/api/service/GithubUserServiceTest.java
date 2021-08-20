package com.github.danieln96.github.user.api.service;

import com.github.danieln96.github.user.api.client.GithubUserClient;
import com.github.danieln96.github.user.api.dao.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
public class GithubUserServiceTest {

    @MockBean
    private GithubUserClient githubUserClient;
    @Autowired
    private GithubUserService githubUserService;

    @Test
    void followersNumberIsZero() {

        final User user = new User();

        when(githubUserClient.getUserByLogin(anyString())).thenReturn(user);


        Assertions.assertThrows(ArithmeticException.class, () -> githubUserService.getUserByLogin(""));
    }

    @Test
    void correctNumber() {

        final User user = new User();

        user.setFollowers(1);

        when(githubUserClient.getUserByLogin(anyString())).thenReturn(user);

        Assertions.assertEquals(3, githubUserService.getUserByLogin("sample_user").getCalculations());
    }

    @Test
    void correctNumberWithFraction() {

        final User user = new User();

        user.setFollowers(2);

        when(githubUserClient.getUserByLogin(anyString())).thenReturn(user);

        Assertions.assertEquals(1.5, githubUserService.getUserByLogin("sample_user").getCalculations());
    }
}
