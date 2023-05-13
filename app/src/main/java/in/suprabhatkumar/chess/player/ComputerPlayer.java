package in.suprabhatkumar.chess.player;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

import in.suprabhatkumar.chess.Game;
import in.suprabhatkumar.chess.chessboard.ChessBoard;
import in.suprabhatkumar.chess.chessboard.ChessSquare;
import in.suprabhatkumar.chess.chesspiece.ChessPiece;

public class ComputerPlayer extends Player{

    public ComputerPlayer(int color, PlayerCorner playerCorner, int initSeconds, Game game) {
        super("computer", color, playerCorner, initSeconds, game);
        this.isBot = true;
    }

    @Override
    public void move() {
        ChessPiece bestMovePiece = getBestMovePiece();
        int destinationRow = bestMovePiece.getCurrentMostValuableMove()[0];
        int destinationColumn = bestMovePiece.getCurrentMostValuableMove()[1];
        bestMovePiece.move(destinationRow, destinationColumn);
    }

}
