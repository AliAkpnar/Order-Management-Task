package com.migros.ordermanagement.persistence.service;

public interface LogPersistService {
    void persist(String logId, String className, String title, String message, int code);
}
