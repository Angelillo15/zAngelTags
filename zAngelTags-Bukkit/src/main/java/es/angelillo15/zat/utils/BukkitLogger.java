package es.angelillo15.zat.utils;

import es.angelillo15.zat.TagsPlugin;
import es.angelillo15.zat.api.ILogger;

import java.util.logging.Logger;

public class BukkitLogger implements ILogger {
    private Logger logger;

    public BukkitLogger(Logger logger) {
        this.logger = logger;
    }

    @Override
    public void info(String message) {
        logger.info(message);
    }

    @Override
    public void warn(String message) {
        logger.warning(message);
    }

    @Override
    public void error(String message) {
        logger.severe(message);
    }

    @Override
    public void debug(String message) {
        if (TagsPlugin.getInstance().isDebug()) logger.info("[DEBUG] " + message);
    }
}
