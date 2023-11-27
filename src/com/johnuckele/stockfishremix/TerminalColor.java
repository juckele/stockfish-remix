package com.johnuckele.stockfishremix;

public class TerminalColor {
    public static final String COLOR_RESET = "\033[0m";
    public static final String COLOR_CYAN = "\033[0;36m";
    public static final String COLOR_RED = "\033[0;31m";
    public static final String COLOR_BLUE = "\033[0;34m";
    public static final String COLOR_GREEN = "\033[0;32m";
    public static final String COLOR_PURPLE = "\033[0;35m";
    public static final String COLOR_CODE(short code) {
        return "\033[38;5;"+code+"m";
    }

    public static void println(String color, String output) {
        System.out.print(color+output+COLOR_RESET+"\n");
    }

    public static void println(short color, String output) {
        System.out.print(COLOR_CODE(color)+output+COLOR_RESET+"\n");
    }

    public static void print(short color, String output) {
        System.out.print(COLOR_CODE(color)+output+COLOR_RESET);
    }
}
