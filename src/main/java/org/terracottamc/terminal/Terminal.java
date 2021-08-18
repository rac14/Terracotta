package org.terracottamc.terminal;

import net.minecrell.terminalconsole.SimpleTerminalConsole;
import org.terracottamc.server.Server;

/**
 * @author Kaooot
 * @version 1.0
 */
public class Terminal extends SimpleTerminalConsole {

    private final TerminalThread terminalThread;

    public Terminal() {
        this.terminalThread = new TerminalThread(this);
    }

    @Override
    protected boolean isRunning() {
        return Server.getInstance().isRunning();
    }

    @Override
    protected void runCommand(final String command) {
        if (command.equalsIgnoreCase("stop")) {
            this.shutdown();
        }
    }

    @Override
    protected void shutdown() {
        Server.getInstance().shutdown();

        System.exit(0);
    }

    /**
     * Retrieves the {@link org.terracottamc.terminal.TerminalThread}
     * of this {@link org.terracottamc.terminal.Terminal}
     *
     * @return a fresh {@link org.terracottamc.terminal.TerminalThread}
     */
    public TerminalThread getThread() {
        return this.terminalThread;
    }
}