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
                    System.out.print(TerminalColor.COLOR_PURPLE + "Reading from pIn!\n" + TerminalColor.COLOR_RESET);
                    String pString = new String(pIn.readNBytes(pIn.available()), StandardCharsets.UTF_8);
                    System.out.print(TerminalColor.COLOR_CODE((short) 227) + pString + TerminalColor.COLOR_RESET);
                    String[] lines = pString.split("\n");
                    for (String line : lines) {
                        if (line.equals(STOCKFISH_WELCOME_MESSAGE)) {
                            System.out.println(TerminalColor.COLOR_CYAN + "Received the welcome message, the process is ready!" + TerminalColor.COLOR_RESET);
                            System.out.println(TerminalColor.COLOR_GREEN + "Writing to pOut!" + TerminalColor.COLOR_RESET);
                            pOut.println(COMMAND_START_UCI);
                            System.out.println(TerminalColor.COLOR_BLUE + COMMAND_START_UCI + TerminalColor.COLOR_RESET);
                            pOut.flush();
                            System.out.println(TerminalColor.COLOR_GREEN + "Done writing to pOut!" + TerminalColor.COLOR_RESET);
                        }
                        if (line.equals(STOCKFISH_UCI_OK_MESSAGE)) {
                            System.out.print(TerminalColor.COLOR_CYAN + "Received the UCI ok message!\n" + TerminalColor.COLOR_RESET);
                        }
                    }
                    System.out.print(TerminalColor.COLOR_PURPLE + "Done reading from pIn!\n" + TerminalColor.COLOR_RESET);
                }
                if (pErr.available() > 0) {
                    System.out.print(TerminalColor.COLOR_PURPLE + "Reading from pErr!\n" + TerminalColor.COLOR_RESET);
                    System.out.print(TerminalColor.COLOR_RED + pErr.readAllBytes() + TerminalColor.COLOR_RESET);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
