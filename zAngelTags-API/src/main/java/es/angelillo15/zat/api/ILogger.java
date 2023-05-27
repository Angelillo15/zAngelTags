package es.angelillo15.zat.api;

public interface ILogger {
    void info(String message);
    void warn(String message);
    void error(String message);
    void debug(String message);
}
