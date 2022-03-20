package com.migros.ordermanagement.service;

public interface LogService {
    void log(String logId, String className, String title, String message, int code);
}
