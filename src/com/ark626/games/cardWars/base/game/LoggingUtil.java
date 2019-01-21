package com.ark626.games.cardWars.base.game;
import org.slf4j.LoggerFactory;

public class LoggingUtil {

    /**
     * Return a slf4j logger named corresponding to the class passed as parameter.
     *
     * @param clazz the returned logger will be named after clazz
     * @return logger
     */
    public static org.slf4j.Logger getLogger(Class<?> clazz) {
        return LoggerFactory.getLogger(clazz);
    }

}