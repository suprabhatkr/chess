package in.suprabhatkumar.chess.chesspiece;

import java.util.ArrayList;

import in.suprabhatkumar.chess.chessboard.ChessSquare;
import in.suprabhatkumar.chess.Game;
import in.suprabhatkumar.chess.R;

public class Queen extends ChessPiece {

    public Queen(int color, Game game, int initRow, int initColumn) {
        super(color, game, initRow, initColumn);
        if (isWhite())
            setPieceImageView(R.drawable.white_queen);
        else
            setPieceImageView(R.drawable.black_queen);
        this.points = 5;
        this.pieceName = "Queen";
    }

    @Override
    public ArrayList<ChessSquare> validMoves() {
        if (this.validMoves.size() != 0)
            return this.validMoves;
        // Check moves in all eight directions
        int[][] directions = {{1,0}, {0,1}, {-1,0}, {0,-1}, {1,1}, {-1,1}, {-1,-1}, {1,-1}};
        for (int[] direction : directions) {
            int newRow = this.currentRow + direction[0];
            int newColumn = this.currentColumn + direction[1];
            while (newRow >= 0 && newRow < 8 && newColumn >= 0 && newColumn < 8) {
                if (chessBoard.getChessSquare(newRow, newColumn).isEmpty()) {
                    this.validMoves.add(chessBoard.getChessSquare(newRow, newColumn));
                } else if (chessBoard.getChessSquare(newRow, newColumn).getChessPiece().isBlack() == this.isWhite()) {
                    this.validMoves.add(chessBoard.getChessSquare(newRow, newColumn));
                    break;
                } else {
                    break;
                }
                newRow += direction[0];
                newColumn += direction[1];
            }
        }
        return this.validMoves;
    }

}
