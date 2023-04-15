package in.suprabhatkumar.chess.chesspiece;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

import in.suprabhatkumar.chess.ChessBoard;
import in.suprabhatkumar.chess.ChessSquare;
import in.suprabhatkumar.chess.Game;
import in.suprabhatkumar.chess.Player;
import in.suprabhatkumar.chess.R;

public class ChessPiece {

    protected Context context;
    protected boolean alive;
    protected int color;
    protected int currentRow;
    protected int currentColumn;
    protected ChessSquare currentSquare;
    protected ChessBoard chessBoard;
    protected ArrayList<ChessSquare> validMoves;
    protected ImageView pieceImageView;
    protected Player myPlayer, opponentPlayer;
    protected int points;
    protected Game game;
    protected String pieceName;
    public final boolean upMovingDirection;
    protected boolean played;

    public ChessPiece(int color, Game game, int initRow, int initColumn){
        this.alive = true;
        this.color = color;
        this.chessBoard = game.getChessBoard();
        this.context = chessBoard.getChessBoardLayout().getContext();
        this.currentRow = initRow;
        this.currentColumn = initColumn;
        setCurrentSquare();
        this.validMoves = new ArrayList<>();
        this.game = game;
        setMyPlayer();
        this.upMovingDirection = initializeUpMovingDirection();
        played = false;
    }

    public void setMyPlayer() {
        if (game.getPlayer(0).getColor() == this.color) {
            this.myPlayer = game.getPlayer(0);
            this.opponentPlayer = game.getPlayer(1);
        } else {
            this.myPlayer = game.getPlayer(1);
            this.opponentPlayer = game.getPlayer(0);
        }
    }

    public void setUsualOnClick() {
        Log.w("USUAL", "usual Click");
        if (game.getCurrentPlayer() == myPlayer) {
            chessBoard.setCurrentValidMoves(validMoves());
            enableClickOnValidMoves();
        }
    }

    public void setPieceImageView(int pieceImageId) {
        pieceImageView = new ImageView(context);
        pieceImageView.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        pieceImageView.setImageResource(pieceImageId);
        pieceImageView.setOnClickListener(v -> {
            setUsualOnClick();
        });
    }

    public ImageView getPieceImageView() {
        return this.pieceImageView;
    }

    public ArrayList<ChessSquare> validMoves() {
        return this.validMoves;
    }

    public boolean isValidMove(int destinationRow, int destinationColumn) {
        for (ChessSquare chessSquare:this.validMoves()) {
            if (destinationRow == chessSquare.getRow() && destinationColumn == chessSquare.getColumn())
                return true;
        }
        return false;
    }

    public int getPoints() {
        return points;
    }

    public boolean isAlive() {
        return this.alive;
    }

    public void setDead() {
        this.alive = false;
    }

    public void setCurrentSquare() {
        this.currentSquare = chessBoard.getChessSquare(currentRow, currentColumn);
    }

    public ChessSquare getCurrentSquare() {
        return this.currentSquare;
    }

    public boolean isWhite() {
        return (this.color == R.color.white);
    }

    public boolean isBlack() {
        return (this.color == R.color.black);
    }

    public String getPieceName() {
        return pieceName;
    }

    protected boolean initializeUpMovingDirection() {
        if ((isWhite() && game.getCurrentPlayer() == game.getPlayer(1)) || (isBlack() && game.getCurrentPlayer() == game.getPlayer(0)))
            return true;
        else
            return false;
    }

    public void enableClickOnValidMoves() {
        for(ChessSquare chessSquare:validMoves()) {
            chessSquare.changeColor(Color.RED);
            if (chessSquare.isEmpty()) {
                chessSquare.getCellLayout().setOnClickListener(v -> {
                    move(chessSquare.getRow(), chessSquare.getColumn());
                });
            } else {
                chessSquare.getChessPiece().getPieceImageView().setOnClickListener(v -> {
                    move(chessSquare.getRow(), chessSquare.getColumn());
                });
            }
        }
    }

    protected boolean isUpMovingDirection() {
        return this.upMovingDirection;
    }

    public void killOpponentPiece() {
        ChessPiece opponentPiece = getCurrentSquare().getChessPiece();
        this.myPlayer.addPoints(opponentPiece.getPoints());
        if (opponentPiece.getPieceName() == "KING") {
            this.game.setWinner(myPlayer);
            this.game.endGame();
        }
        opponentPiece.setDead();
        Log.w("KILL", "Killed opponent piece");
    }

    public void move(int destinationRow, int destinationColumn) {
        Log.w("JUST MOVES", Integer.toString(destinationRow) + " " + Integer.toString(destinationColumn));
        if (this.isValidMove(destinationRow, destinationColumn)) {
            Log.w("VALID MOVES", Integer.toString(destinationRow) + " " + Integer.toString(destinationColumn));
            this.getCurrentSquare().setChessPiece(null);
            this.getCurrentSquare().markAsEmpty();
            this.currentRow = destinationRow;
            this.currentColumn = destinationColumn;
            this.setCurrentSquare();
            if (!getCurrentSquare().isEmpty())
                killOpponentPiece();
            this.getCurrentSquare().markAsFilled();
            this.getCurrentSquare().setChessPiece(this);
            this.chessBoard.disableClickOnValidMoves();
            this.played = true;
            this.game.setCurrentPlayer(this.opponentPlayer);
        }
    }

    public boolean hasBeenPlayed() {
        return this.played;
    }
}
