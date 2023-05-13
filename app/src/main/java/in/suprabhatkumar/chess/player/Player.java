package in.suprabhatkumar.chess.player;

import android.graphics.Color;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import in.suprabhatkumar.chess.Game;
import in.suprabhatkumar.chess.Move;
import in.suprabhatkumar.chess.R;
import in.suprabhatkumar.chess.chessboard.ChessBoard;
import in.suprabhatkumar.chess.chessboard.ChessSquare;
import in.suprabhatkumar.chess.chesspiece.Bishop;
import in.suprabhatkumar.chess.chesspiece.ChessPiece;
import in.suprabhatkumar.chess.chesspiece.King;
import in.suprabhatkumar.chess.chesspiece.Knight;
import in.suprabhatkumar.chess.chesspiece.Pawn;
import in.suprabhatkumar.chess.chesspiece.Queen;
import in.suprabhatkumar.chess.chesspiece.Rook;

public class Player {

    protected String name;
    protected int points;
    protected int color;
    protected ArrayList<ChessPiece> currentChessPieces;
    protected Set<Move> allPossibleMoves;
    protected boolean isBot, isChecked;
    protected PlayerCorner playerCorner;
    protected int remainingSeconds;
    protected Timer timer;

    public Player(String name, int color, PlayerCorner playerCorner, int initTime, Game game) {
        this.name = name;
        this.points = 0;
        this.color = color;
        currentChessPieces = new ArrayList<>(0);
        this.isBot = false;
        setAllPossibleMoves();
        this.playerCorner = playerCorner;
        this.isChecked = false;
        initTimer(initTime, game);
    }

    public void initTimer(int initTime, Game game) {
        this.remainingSeconds = initTime;
        this.timer = new Timer(getPlayerCorner().getTimerView(), game);
    }

    public String getName() {
        return name;
    }

    public void disableClickOnPieces() {
        for (ChessPiece chessPiece:
             currentChessPieces) {
            chessPiece.disableClick();
        }
    }

    public boolean isBot() {
        return isBot;
    }

    public void addPoints(int points) {
        this.points += points;
        getPlayerCorner().updatePoints(points);
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public int getPoints() {
        return points;
    }

    public int getColor() {
        return  color;
    }

    public boolean isWhite() {
        return color == R.color.white;
    }

    public void addChessPiece(ChessPiece chessPiece) {
        this.currentChessPieces.add(chessPiece);
    }

    public PlayerCorner getPlayerCorner() {
        return playerCorner;
    }

    public void removeChessPiece(ChessPiece chessPiece) {
        this.currentChessPieces.remove(chessPiece);
    }

    protected void updateAllPossibleMoves(ArrayList<ChessSquare> validMoves, ChessPiece currentPiece) {
        for (ChessSquare validMove : validMoves) {
            Move currentMove = new Move(null, null, null, null);
            currentMove.setChessPiece(currentPiece);
            currentMove.setInitSquare(currentPiece.getCurrentSquare());
            currentMove.setDestSquare(validMove);
            if (!validMove.isEmpty()) {
                validMove.getChessPiece().markAsVulnerable();
                currentMove.setKilledPiece(validMove.getChessPiece());
            }
            allPossibleMoves.add(currentMove);
        }
    }

    public void setAllPossibleMoves() {
        if (allPossibleMoves != null){
            allPossibleMoves.clear();
        } else {
            allPossibleMoves = new HashSet<>(0);
        }

        for (ChessPiece chessPiece : this.currentChessPieces) {
            chessPiece.setCurrentMostValuableMove();
            updateAllPossibleMoves(chessPiece.validMoves(), chessPiece);
        }
    }

    public Set<Move> getAllPossibleMoves() {
        return allPossibleMoves;
    }

    public void clearAllPossibleMoves() {
        for (ChessPiece chessPiece : this.currentChessPieces) {
            chessPiece.validMoves().clear();
        }
    }

    public ChessPiece getBestMovePiece() {
        ChessPiece bestPiece = this.currentChessPieces.get(0);
        for (ChessPiece chessPiece : this.currentChessPieces) {
            if (bestPiece.getCurrentMostValuableMove()[2] < chessPiece.getCurrentMostValuableMove()[2]) {
                bestPiece = chessPiece;
            }
        }
        return bestPiece;
    }

    public void move() {
        Log.e("Move", "Wrong Move");
    }

    public void startTimer() {
        timer.startTimer(this.remainingSeconds);
    }

    public void stopTimer() {
        this.remainingSeconds = timer.stopTimer();
    }
}
