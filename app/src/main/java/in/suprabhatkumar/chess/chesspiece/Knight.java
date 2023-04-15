package in.suprabhatkumar.chess.chesspiece;

import java.util.ArrayList;

import in.suprabhatkumar.chess.ChessSquare;
import in.suprabhatkumar.chess.Game;
import in.suprabhatkumar.chess.R;
import in.suprabhatkumar.chess.chesspiece.ChessPiece;

public class Knight extends ChessPiece {

    public Knight(int color, Game game, int initRow, int initColumn) {
        super(color, game, initRow, initColumn);
        if (isWhite())
            setPieceImageView(R.drawable.white_knight);
        else
            setPieceImageView(R.drawable.black_knight);
        this.points = 3;
        this.pieceName = "Knight";
    }

    @Override
    public ArrayList<ChessSquare> validMoves() {
        if (this.validMoves.size() != 0)
            return this.validMoves;
        int[][] offsets = {{2,1}, {1,2}, {-1,2}, {-2,1}, {-2,-1}, {-1,-2}, {1,-2}, {2,-1}};
        for (int[] offset : offsets) {
            int newRow = this.currentRow + offset[0];
            int newColumn = this.currentColumn + offset[1];
            if (newRow >= 0 && newRow < 8 && newColumn >= 0 && newColumn < 8 &&
                    (chessBoard.getChessSquare(newRow, newColumn).isEmpty() || chessBoard.getChessSquare(newRow, newColumn).getChessPiece().isBlack() == this.isWhite())) {
                this.validMoves.add(chessBoard.getChessSquare(newRow, newColumn));
            }
        }
        return this.validMoves;
    }

}
