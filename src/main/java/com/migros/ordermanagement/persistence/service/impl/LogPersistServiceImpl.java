package com.migros.ordermanagement.persistence.service.impl;

import com.migros.ordermanagement.persistence.entity.LogEntity;
import com.migros.ordermanagement.persistence.repository.LogRepository;
import com.migros.ordermanagement.persistence.service.LogPersistService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogPersistServiceImpl implements LogPersistService {

    private final LogRepository logRepository;

    @Override
    public void persist(String logId, String className, String title, String message, int code) {
            LogEntity log = new LogEntity();
            log.setLogId(logId);
            log.setClassName(className);
            log.setLogCode(code);
            log.setTitle(title);
            log.setMessage(message);
            logRepository.save(log);
    }
}
