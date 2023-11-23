package com.johnuckele.stockfishremix;

import java.io.PrintStream;

/**
 * Represents a Chess board.
 * <p>
 * A single board is meant to be used through an entire game, with undo functionality to avoid object allocations.
 */
public class Board {

private final GamePiece[] _board = {GamePiece.BLACK_ROOK, GamePiece.BLACK_KNIGHT, GamePiece.BLACK_BISHOP, GamePiece.BLACK_QUEEN, GamePiece.BLACK_KING, GamePiece.BLACK_BISHOP,GamePiece.BLACK_KNIGHT, GamePiece.BLACK_ROOK,
        GamePiece.BLACK_PAWN, GamePiece.BLACK_PAWN, GamePiece.BLACK_PAWN, GamePiece.BLACK_PAWN, GamePiece.BLACK_PAWN, GamePiece.BLACK_PAWN,GamePiece.BLACK_PAWN, GamePiece.BLACK_PAWN,
        GamePiece.EMPTY, GamePiece.EMPTY, GamePiece.EMPTY, GamePiece.EMPTY, GamePiece.EMPTY, GamePiece.EMPTY,GamePiece.EMPTY, GamePiece.EMPTY,
        GamePiece.EMPTY, GamePiece.EMPTY, GamePiece.EMPTY, GamePiece.EMPTY, GamePiece.EMPTY, GamePiece.EMPTY,GamePiece.EMPTY, GamePiece.EMPTY,
        GamePiece.EMPTY, GamePiece.EMPTY, GamePiece.EMPTY, GamePiece.EMPTY, GamePiece.EMPTY, GamePiece.EMPTY,GamePiece.EMPTY, GamePiece.EMPTY,
        GamePiece.EMPTY, GamePiece.EMPTY, GamePiece.EMPTY, GamePiece.EMPTY, GamePiece.EMPTY, GamePiece.EMPTY,GamePiece.EMPTY, GamePiece.EMPTY,
        GamePiece.WHITE_PAWN, GamePiece.WHITE_PAWN, GamePiece.WHITE_PAWN, GamePiece.WHITE_PAWN, GamePiece.WHITE_PAWN, GamePiece.WHITE_PAWN,GamePiece.WHITE_PAWN, GamePiece.WHITE_PAWN,
        GamePiece.WHITE_ROOK, GamePiece.WHITE_KNIGHT, GamePiece.WHITE_BISHOP, GamePiece.WHITE_QUEEN, GamePiece.WHITE_KING, GamePiece.WHITE_BISHOP,GamePiece.WHITE_KNIGHT, GamePiece.WHITE_ROOK
};

    /**
     * Generates a string describing the game state in a FEN string suitable for consumption by a UCI engine.
     *
     * @return Returns the game state in a FEN string suitable for consumption by a UCI engine.
     */
    public String toFenString() {
        return "FEN";
    }

    public void render(PrintStream out, int indent) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < indent; j++) {
                out.print(' ');
            }
            for (int j = 0; j < 8; j++) {
                out.print(_board[i * 8 + j]);
            }
            out.print('\n');
        }
    }
}
