package com.johnuckele.stockfishremix;

public enum GamePiece {
    EMPTY(Owner.NONE, Piece.NONE, "", " "),
    BLACK_PAWN(Owner.BLACK, Piece.PAWN, "p", "♟"),
    BLACK_KNIGHT(Owner.BLACK, Piece.KNIGHT, "n", "♞"),
    BLACK_BISHOP(Owner.BLACK, Piece.BISHOP, "b", "♝"),
    BLACK_ROOK(Owner.BLACK, Piece.ROOK, "r", "♜"),
    BLACK_QUEEN(Owner.BLACK, Piece.QUEEN, "q", "♛"),
    BLACK_KING(Owner.BLACK, Piece.KING, "k", "♚"),
    WHITE_PAWN(Owner.WHITE, Piece.PAWN, "P", "♙"),
    WHITE_KNIGHT(Owner.WHITE, Piece.KNIGHT, "N", "♘"),
    WHITE_BISHOP(Owner.WHITE, Piece.BISHOP, "B", "♗"),
    WHITE_ROOK(Owner.WHITE, Piece.ROOK, "R", "♖"),
    WHITE_QUEEN(Owner.WHITE, Piece.QUEEN, "Q", "♕"),
    WHITE_KING(Owner.WHITE, Piece.KING, "K", "♔");

    public final Owner owner;
    public final Piece piece;
    public final String fenString;
    public final String unicodeString;

    GamePiece(Owner owner, Piece piece, String fenString, String unicodeString) {
        this.owner = owner;
        this.piece = piece;
        this.fenString = fenString;
        this.unicodeString = unicodeString;
    }

    @Override
    public String toString() {
        return unicodeString;
    }


}

