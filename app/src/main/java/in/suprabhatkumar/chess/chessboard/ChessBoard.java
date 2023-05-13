package in.suprabhatkumar.chess.chessboard;

import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import java.util.ArrayList;

public class ChessBoard extends AppCompatActivity {

    private GridLayout chessBoardLayout;
    private ChessSquare[][] chessSquares;
    private ArrayList<ChessSquare> currentValidMoves;
    private int gameType;

    public ChessBoard(int gameType) {
        this.gameType = gameType;
    }

    public GridLayout getChessBoardLayout() {
        return this.chessBoardLayout;
    }

    public void setChessBoardLayout(GridLayout chessBoardLayout) {
        this.chessBoardLayout = chessBoardLayout;
        generateSquares();
    }

    public void generateSquares() {
        chessSquares = new ChessSquare[10][10];
        for (int column = 9; column >= 0; column --) {
            for (int row = 9; row >= 0; row--) {
                if (column == 0 || column == 9 || row == 0 || row == 9)
                    chessBoardLayout.addView(CellLayout.getCellView(chessBoardLayout.getContext(), row, column, gameType));
                else
                    chessSquares[row - 1][column -1 ] = new ChessSquare(row - 1, column - 1, this.chessBoardLayout);
            }
        }
    }

    public ChessSquare getChessSquare(int row, int column) {
        return chessSquares[row][column];
    }

    public void setCurrentValidMoves(ArrayList<ChessSquare> validMoves) {
        disableClickOnValidMoves();
        this.currentValidMoves = validMoves;
    }

    public ArrayList<ChessSquare> getCurrentValidMoves() {
        return currentValidMoves;
    }

    public void disableClickOnValidMoves() {
        if (getCurrentValidMoves() != null){
            for (ChessSquare chessSquare : getCurrentValidMoves()) {
                Log.w("DISABLE", chessSquare.getRow() + " " + chessSquare.getColumn());
                chessSquare.changeColor(chessSquare.getColor());
            }
            getCurrentValidMoves().clear();
        }
    }
}
