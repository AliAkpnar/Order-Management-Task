package com.migros.ordermanagement.service.impl;

import com.migros.ordermanagement.persistence.service.LogPersistService;
import com.migros.ordermanagement.service.LogService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogServiceImpl implements LogService {

    private final LogPersistService logPersistService;

    @Async
    public void log(String logId, String className, String title, String message, int code) {
            logPersistService.persist(logId, className, title, message, code);
    }
}
