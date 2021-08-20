package com.github.danieln96.github.user.api.service;

import com.github.danieln96.github.user.api.dao.QueryCounter;
import com.github.danieln96.github.user.api.repository.QueryCounterRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class QueryCounterService {

    private final QueryCounterRepository repository;

    public void increaceTheQueryCounterForLogin(final String login) {

        if (login == null || login.isEmpty()) {
            log.error("Cannot find QueryCounter for empty login");
            return;
        }

        final QueryCounter queryCounter = repository.findFirstByLogin(login);

        if (queryCounter != null) {

            queryCounter.setRequestCount(queryCounter.getRequestCount() + 1);

            log.info("Query counter for login [{}] is [{}]", login, queryCounter.getRequestCount());

            repository.save(queryCounter);
        } else {

            final QueryCounter newQueryCounter = QueryCounter.builder()
                    .login(login)
                    .requestCount(1)
                    .build();

            log.info("Create new QueryCounter for login: [{}]", login);

            repository.save(newQueryCounter);
        }
    }
}
