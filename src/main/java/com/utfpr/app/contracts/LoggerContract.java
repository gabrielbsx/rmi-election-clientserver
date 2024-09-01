package com.utfpr.app.contracts;

public interface LoggerContract {

    void log(String message);

    void error(String message);

    void warning(String message);

    void info(String message);

}
