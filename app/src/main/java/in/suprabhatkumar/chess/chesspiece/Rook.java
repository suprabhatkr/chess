package in.suprabhatkumar.chess.chesspiece;

import java.util.ArrayList;

import in.suprabhatkumar.chess.chessboard.ChessSquare;
import in.suprabhatkumar.chess.Game;
import in.suprabhatkumar.chess.R;

public class Rook extends ChessPiece {

    public Rook(int color, Game game, int initRow, int initColumn) {
        super(color, game, initRow, initColumn);
        if (isWhite())
            setPieceImageView(R.drawable.white_rook);
        else
            setPieceImageView(R.drawable.black_rook);
        this.points = 4;
        this.pieceName = "Rook";
    }

    @Override
    public ArrayList<ChessSquare> validMoves() {
        if (this.validMoves.size() != 0)
            return this.validMoves;
        // Check moves in all four directions
        int[][] directions = {{1,0}, {0,1}, {-1,0}, {0,-1}};
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
