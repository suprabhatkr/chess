package in.suprabhatkumar.chess.chesspiece;

import java.util.ArrayList;

import in.suprabhatkumar.chess.ChessSquare;
import in.suprabhatkumar.chess.Game;
import in.suprabhatkumar.chess.R;
import in.suprabhatkumar.chess.chesspiece.ChessPiece;

public class Bishop extends ChessPiece {

    public Bishop(int color, Game game, int initRow, int initColumn) {
        super(color, game, initRow, initColumn);
        if (isWhite())
            setPieceImageView(R.drawable.white_bishop);
        else
            setPieceImageView(R.drawable.black_bishop);
        this.points = 3;
        this.pieceName = "Bishop";
    }

    @Override
    public ArrayList<ChessSquare> validMoves() {
        if (this.validMoves.size() != 0)
            return this.validMoves;
        int row = this.currentRow;
        int col = this.currentColumn;
        while (++row <= 7 && ++col <= 7) {
            if (chessBoard.getChessSquare(row, col).isEmpty()) {
                this.validMoves.add(chessBoard.getChessSquare(row, col));
            } else if (chessBoard.getChessSquare(row, col).getChessPiece().isBlack() == this.isWhite()) {
                this.validMoves.add(chessBoard.getChessSquare(row, col));
                break;
            } else {
                break;
            }
        }
        row = this.currentRow;
        col = this.currentColumn;
        while (++row <= 7 && --col >= 0) {
            if (chessBoard.getChessSquare(row, col).isEmpty()) {
                this.validMoves.add(chessBoard.getChessSquare(row, col));
            } else if (chessBoard.getChessSquare(row, col).getChessPiece().isBlack() == this.isWhite()) {
                this.validMoves.add(chessBoard.getChessSquare(row, col));
                break;
            } else {
                break;
            }
        }
        row = this.currentRow;
        col = this.currentColumn;
        while (--row >= 0 && ++col <= 7) {
            if (chessBoard.getChessSquare(row, col).isEmpty()) {
                this.validMoves.add(chessBoard.getChessSquare(row, col));
            } else if (chessBoard.getChessSquare(row, col).getChessPiece().isBlack() == this.isWhite()) {
                this.validMoves.add(chessBoard.getChessSquare(row, col));
                break;
            } else {
                break;
            }
        }
        row = this.currentRow;
        col = this.currentColumn;
        while (--row >= 0 && --col >= 0) {
            if (chessBoard.getChessSquare(row, col).isEmpty()) {
                this.validMoves.add(chessBoard.getChessSquare(row, col));
            } else if (chessBoard.getChessSquare(row, col).getChessPiece().isBlack() == this.isWhite()) {
                this.validMoves.add(chessBoard.getChessSquare(row, col));
                break;
            } else {
                break;
            }
        }
        return this.validMoves;
    }
}
