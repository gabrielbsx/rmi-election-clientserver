package com.utfpr.app.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.utfpr.app.contracts.LoggerContract;

public class LoggerServiceImpl implements LoggerContract {

    private static final Logger _logger = LogManager.getLogger(LoggerServiceImpl.class);

    @Override
    public void log(String message) {
        _logger.log(_logger.getLevel(), message);
    }

    @Override
    public void error(String message) {
        _logger.error(message);
    }

    @Override
    public void warning(String message) {
        _logger.warn(message);
    }

    @Override
    public void info(String message) {
        _logger.info(message);
    }

}
