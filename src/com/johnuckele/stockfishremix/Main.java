package com.johnuckele.stockfishremix;

public class Main {

    private static final String DEFAULT_STOCKFISH_PATH = "/home/juckele/stockfish/stockfish-ubuntu-x86-64-avx2";

    public static void main(String[] args) {
        System.out.println("Stockfish Remix");
        System.out.println("Creating board");
        Board board = new Board();
        System.out.println("Rendering board");
        board.render(System.out, 2);
        System.out.println("FEN notation");
        System.out.println(board.toFenString());
    }
}
