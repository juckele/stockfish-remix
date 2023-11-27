package com.johnuckele.stockfishremix;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

/**
 * Handles a UCI compatible process.
 */
public class UCIHandler implements Runnable {
    private static final String DEFAULT_STOCKFISH_PATH = "/home/juckele/stockfish/stockfish-ubuntu-x86-64-avx2";

    private static final String STOCKFISH_WELCOME_MESSAGE = "Stockfish 16 by the Stockfish developers (see AUTHORS file)";
    private static final String STOCKFISH_UCI_OK_MESSAGE = "uciok";

    private final static String COMMAND_START_UCI = "uci";
    private final static String SET_POSITION_START = "position startpos";
    private final Process p;
    private final PrintStream pOut;
    private final InputStream pIn;
    private final InputStream pErr;

    UCIHandler() throws IOException {
        this(UCIHandler.DEFAULT_STOCKFISH_PATH);
    }

    UCIHandler(String processPath) throws IOException {
        p = new ProcessBuilder(processPath).start();
        pOut = new PrintStream(p.getOutputStream());
        pIn = p.getInputStream();
//        pIn = new InputStreamReader(p.getInputStream());
        pErr = p.getErrorStream();
    }

    private final static String SET_POSITION(Board b) {
        return "position " + b.toFenString();
    }

    public void run() {
        try {
            System.out.println("Starting UCIHandler");
            while (true) {
                if (pIn.available() > 0) {
                    TerminalColor.println(TerminalColor.COLOR_PURPLE, "Reading from pIn!");
                    String pString = new String(pIn.readNBytes(pIn.available()), StandardCharsets.UTF_8);
                    TerminalColor.print((short) 227, pString);
                    String[] lines = pString.split("\n");
                    for (String line : lines) {
                        if (line.equals(STOCKFISH_WELCOME_MESSAGE)) {
                            TerminalColor.println(TerminalColor.COLOR_CYAN, "Received the welcome message, the process is ready!");
                            TerminalColor.println(TerminalColor.COLOR_GREEN, "Writing to pOut!");
                            pOut.println(COMMAND_START_UCI);
                            TerminalColor.println(TerminalColor.COLOR_BLUE, COMMAND_START_UCI);
                            pOut.flush();
                            TerminalColor.println(TerminalColor.COLOR_GREEN, "Done writing to pOut!");
                        }
                        if (line.equals(STOCKFISH_UCI_OK_MESSAGE)) {
                            TerminalColor.println(TerminalColor.COLOR_CYAN, "Received the UCI ok message!");
                        }
                    }
                    TerminalColor.println(TerminalColor.COLOR_PURPLE, "Done reading from pIn!");
                }
                if (pErr.available() > 0) {
                    TerminalColor.println(TerminalColor.COLOR_PURPLE , "Reading from pErr!\n" );
                    TerminalColor.println(TerminalColor.COLOR_RED , pErr.readAllBytes().toString() );
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
