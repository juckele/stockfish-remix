package com.johnuckele.stockfishremix;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        System.out.println("Stockfish Remix");
        System.out.println("Creating board");
        Board board = new Board();
        System.out.println("Rendering board");
        board.render(System.out, 2);
        System.out.println("FEN notation");
        System.out.println(board.toFenString());

        try {
            UCIHandler stockfish = new UCIHandler();
            Thread stockfishThread = new Thread(stockfish);
            stockfishThread.start();
            System.out.println("Joining");
            stockfishThread.join();
            System.out.println("Joined");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
