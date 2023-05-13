package in.suprabhatkumar.chess.chesspiece;

import android.util.Log;

import java.util.ArrayList;

import in.suprabhatkumar.chess.chessboard.ChessSquare;
import in.suprabhatkumar.chess.Game;
import in.suprabhatkumar.chess.R;

public class King extends ChessPiece {

    public King(int color, Game game, int initRow, int initColumn) {
        super(color, game, initRow, initColumn);
        Log.w("KING", "==================================== KING");
        if (isWhite())
            setPieceImageView(R.drawable.white_king);
        else
            setPieceImageView(R.drawable.black_king);
        this.points = 100;
        this.pieceName = "KING";
    }

    @Override
    public ArrayList<ChessSquare> validMoves() {
        if (this.validMoves.size() != 0)
            return this.validMoves;
        int[][] offsets = {{1,0}, {1,1}, {0,1}, {-1,1}, {-1,0}, {-1,-1}, {0,-1}, {1,-1}};
        for (int[] offset : offsets) {
            int newRow = this.currentRow + offset[0];
            int newColumn = this.currentColumn + offset[1];
            if (newRow >= 0 && newRow < 8 && newColumn >= 0 && newColumn < 8 &&
                    (chessBoard.getChessSquare(newRow, newColumn).isEmpty() || chessBoard.getChessSquare(newRow, newColumn).getChessPiece().isBlack() == this.isWhite())) {
                if ((this.currentRow == 0 && this.currentColumn == 4 && newRow == 0 && newColumn == 2) || (this.currentRow == 7 && this.currentColumn == 4 && newRow == 7 && newColumn == 2)) {
                    // check if the rook is on its initial position and has not yet moved
                    ChessPiece rook = chessBoard.getChessSquare(newRow, 0).getChessPiece();
                    if (rook != null && rook instanceof Rook && !rook.hasBeenPlayed()) {
                        // check if the spaces between the rook and king are empty
                        boolean spacesEmpty = true;
                        int minCol = Math.min(this.currentColumn, newColumn);
                        int maxCol = Math.max(this.currentColumn, newColumn);
                        for (int col = minCol + 1; col < maxCol; col++) {
                            if (!chessBoard.getChessSquare(newRow, col).isEmpty()) {
                                spacesEmpty = false;
                                break;
                            }
                        }
                        if (spacesEmpty) {
                            this.validMoves.add(chessBoard.getChessSquare(newRow, newColumn));
                        }
                    }
                } else {
                    this.validMoves.add(chessBoard.getChessSquare(newRow, newColumn));
                }
            }
        }
        return this.validMoves;
    }


}
