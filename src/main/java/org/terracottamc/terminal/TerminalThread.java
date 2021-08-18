package org.terracottamc.terminal;

/**
 * @author Kaooot
 * @version 1.0
 */
public class TerminalThread extends Thread {

    private final Terminal terminal;

    /**
     * Creates a new {@link org.terracottamc.terminal.TerminalThread}
     * for the given {@link org.terracottamc.terminal.Terminal}
     *
     * @param terminal which is the holder of the {@link org.terracottamc.terminal.TerminalThread}
     */
    public TerminalThread(final Terminal terminal) {
        this.terminal = terminal;
    }

    @Override
    public void run() {
        this.terminal.start();
    }
}