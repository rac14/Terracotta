package org.terracottamc.logging;

import lombok.extern.log4j.Log4j2;

/**
 * @author Kaooot
 * @version 1.0
 */
@Log4j2
public class Logger {

    /**
     * Prints an information message on the terminal
     *
     * @param message which should be printed
     */
    public void info(final String message) {
        Logger.log.info(message);
    }

    /**
     * Prints a warning message on the terminal
     *
     * @param message which should be printed
     */
    public void warn(final String message) {
        Logger.log.warn(message);
    }

    /**
     * Prints a debug message on the terminal
     *
     * @param message which should be printed
     */
    public void debug(final String message) {
        Logger.log.debug(message);
    }

    /**
     * Prints an error message on the terminal
     *
     * @param message which should be printed
     */
    public void error(final String message) {
        Logger.log.error(message);
    }
}