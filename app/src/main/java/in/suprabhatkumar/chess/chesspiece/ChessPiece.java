package in.suprabhatkumar.chess.chesspiece;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Comparator;

import in.suprabhatkumar.chess.Move;
import in.suprabhatkumar.chess.chessboard.ChessBoard;
import in.suprabhatkumar.chess.chessboard.ChessSquare;
import in.suprabhatkumar.chess.Game;
import in.suprabhatkumar.chess.player.Player;
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
    protected int[] currentMostValuableMove;
    protected boolean isVulnerable;

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
        currentMostValuableMove = new int[3];
        isVulnerable = false;
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

    public void markAsVulnerable() {
        this.isVulnerable = true;
    }

    public static Comparator<ChessPiece> powerComparator = new Comparator<ChessPiece>() {
        @Override
        public int compare(ChessPiece c1, ChessPiece c2) {
            return Integer.compare(c2.getPoints(), c1.getPoints());
        }
    };

    public int getCurrentMoveValue(ChessSquare currentMove) {
        int currentValue = 0;
        if (!currentMove.isEmpty()) {
            currentValue += currentMove.getChessPiece().getPoints();
        }
        if (opponentPlayer.getAllPossibleMoves().contains(currentMove)) {
            currentValue -= getPoints();
        }

        if (this.isVulnerable) {
            if (this.pieceName == "KING") {
                this.myPlayer.setChecked(true);
            }
            currentMostValuableMove[2] += getPoints();
        }

        return currentValue;
    }

    public void setCurrentMostValuableMove() {
        currentMostValuableMove[0] = -1;
        currentMostValuableMove[1] = -1;
        currentMostValuableMove[2] = -101;

        for (ChessSquare currentMove : validMoves()) {
            int currentValue = getCurrentMoveValue(currentMove);
            if (currentValue > currentMostValuableMove[2]) {
                currentMostValuableMove[0] = currentMove.getRow();
                currentMostValuableMove[1] = currentMove.getColumn();
                currentMostValuableMove[2] = currentValue;
            }
        }
    }

    public int[] getCurrentMostValuableMove() {
        return this.currentMostValuableMove;
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
        if (game.getGameType() == 0 && myPlayer == game.getPlayer(1))
            pieceImageView.setRotation(180);
        pieceImageView.setOnClickListener(v -> {
            setUsualOnClick();
        });
    }

    public Game getGame() {
        return game;
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
        myPlayer.removeChessPiece(this);
        this.alive = false;
    }

    public void setAlive() {
        this.alive = false;
        myPlayer.addChessPiece(this);
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

    public void disableClick() {
        getPieceImageView().setOnClickListener(null);
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

    public void setCurrentRow(int currentRow) {
        this.currentRow = currentRow;
    }

    public void setCurrentColumn(int currentColumn) {
        this.currentColumn = currentColumn;
    }

    public void move(int destinationRow, int destinationColumn) {
        Log.w("JUST MOVES", Integer.toString(destinationRow) + " " + Integer.toString(destinationColumn));
        if (this.isValidMove(destinationRow, destinationColumn)) {
            if (myPlayer.isChecked() && this.game.isKingOnCheck()) {
                return;
            }
            Move currentMove = new Move(this, this.getCurrentSquare(), null, null);
            this.getCurrentSquare().setChessPiece(null);
            this.getCurrentSquare().markAsEmpty();
            this.setCurrentRow(destinationRow);
            this.setCurrentColumn(destinationColumn);
            this.setCurrentSquare();
            if (!getCurrentSquare().isEmpty()) {
                currentMove.setKilledPiece(getCurrentSquare().getChessPiece());
                killOpponentPiece();
            }
            this.getCurrentSquare().markAsFilled();
            this.getCurrentSquare().setChessPiece(this);
            currentMove.setDestSquare(this.getCurrentSquare());
            this.chessBoard.disableClickOnValidMoves();
            this.played = true;
            this.game.addMove(currentMove);
            this.game.switch_turn();
        }
    }

    public boolean hasBeenPlayed() {
        return this.played;
    }
}
