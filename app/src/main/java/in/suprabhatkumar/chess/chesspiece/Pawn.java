package in.suprabhatkumar.chess.chesspiece;

import java.util.ArrayList;

import in.suprabhatkumar.chess.chessboard.ChessSquare;
import in.suprabhatkumar.chess.Game;
import in.suprabhatkumar.chess.R;

public class Pawn extends ChessPiece {

    public Pawn(int color, Game game, int initRow, int initColumn) {
        super(color, game, initRow, initColumn);
        if (isWhite())
            setPieceImageView(R.drawable.white_pawn);
        else
            setPieceImageView(R.drawable.black_pawn);
        this.points = 1;
        this.pieceName = "Pawn";
    }

    @Override
    public ArrayList<ChessSquare> validMoves() {
        if (this.validMoves.size() != 0)
            return this.validMoves;
        if (isUpMovingDirection()) {
            if (this.currentRow < 7 && chessBoard.getChessSquare(this.currentRow + 1, this.currentColumn).isEmpty()) {
                this.validMoves.add(chessBoard.getChessSquare(this.currentRow + 1, this.currentColumn ));
            }
            if (this.currentRow < 7 && this.currentColumn < 7 &&
                    (!chessBoard.getChessSquare(this.currentRow + 1, this.currentColumn + 1).isEmpty() && chessBoard.getChessSquare(this.currentRow + 1, this.currentColumn + 1).getChessPiece().isBlack() == this.isWhite())
            ){
                this.validMoves.add(chessBoard.getChessSquare(this.currentRow + 1, this.currentColumn + 1));
            }
            if (this.currentRow < 7 && this.currentColumn > 0 &&
                    (!chessBoard.getChessSquare(this.currentRow + 1, this.currentColumn - 1).isEmpty() && chessBoard.getChessSquare(this.currentRow + 1, this.currentColumn - 1).getChessPiece().isBlack() == this.isWhite())
            ){
                this.validMoves.add(chessBoard.getChessSquare(this.currentRow + 1, this.currentColumn - 1));
            }
        }
        else {
            if (this.currentRow > 0 && chessBoard.getChessSquare(this.currentRow - 1, this.currentColumn).isEmpty()) {
                this.validMoves.add(chessBoard.getChessSquare(this.currentRow - 1, this.currentColumn ));
            }
            if (this.currentRow > 0 && this.currentColumn > 0 &&
                    (!chessBoard.getChessSquare(this.currentRow - 1, this.currentColumn - 1).isEmpty() && chessBoard.getChessSquare(this.currentRow - 1, this.currentColumn - 1).getChessPiece().isBlack() == this.isWhite())
            ){
                this.validMoves.add(chessBoard.getChessSquare(this.currentRow - 1, this.currentColumn - 1));
            }
            if (this.currentRow > 0 && this.currentColumn < 7 &&
                    (!chessBoard.getChessSquare(this.currentRow - 1, this.currentColumn + 1).isEmpty() && chessBoard.getChessSquare(this.currentRow - 1, this.currentColumn + 1).getChessPiece().isBlack() == this.isWhite())
            ){
                this.validMoves.add(chessBoard.getChessSquare(this.currentRow - 1, this.currentColumn + 1));
            }
        }
        return this.validMoves;
    }
}
