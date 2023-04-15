package in.suprabhatkumar.chess;

import java.util.Random;

import in.suprabhatkumar.chess.chesspiece.Bishop;
import in.suprabhatkumar.chess.chesspiece.King;
import in.suprabhatkumar.chess.chesspiece.Knight;
import in.suprabhatkumar.chess.chesspiece.Pawn;
import in.suprabhatkumar.chess.chesspiece.Queen;
import in.suprabhatkumar.chess.chesspiece.Rook;

public class Game {

    protected ChessBoard chessBoard;
    protected final Player[] players = new Player[2];
    protected Player currentPlayer, winner;
    protected boolean gameEnd;

    public Game(ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
        this.gameEnd = false;
        getPlayers("Suprabhat", "Aparna");
        initialize_pieces();
    }

    public Player getPlayer(int index) {
        return players[index];
    }

    public ChessBoard getChessBoard() {
        return chessBoard;
    }

    public  void setWinner(Player player) {
        this.winner = player;
    }

    public void endGame() {
        this.gameEnd = true;
    }

    protected void getPlayers(String firstPlayer, String secondPlayer) {
        Random random = new Random();
        int randomFirstChance = random.nextInt(2);
        players[randomFirstChance] = new Player(firstPlayer, R.color.white);
        players[1-randomFirstChance] = new Player(secondPlayer, R.color.black);
        setCurrentPlayer(players[randomFirstChance]);
    }

    public void setCurrentPlayer (Player player) {
        this.currentPlayer = player;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    protected void initialize_pieces() {
        // Set up the back rank for white
        int sideAdjuster;
        if (getCurrentPlayer() == getPlayer(0))
            sideAdjuster = 7;
        else
            sideAdjuster = 0;

        chessBoard.getChessSquare(Math.abs(sideAdjuster), 0).setChessPiece(new Rook(R.color.white, this, Math.abs(sideAdjuster), 0));
        chessBoard.getChessSquare(Math.abs(sideAdjuster), 1).setChessPiece(new Knight(R.color.white, this, Math.abs(sideAdjuster), 1));
        chessBoard.getChessSquare(Math.abs(sideAdjuster), 2).setChessPiece(new Bishop(R.color.white, this, Math.abs(sideAdjuster), 2));
        chessBoard.getChessSquare(Math.abs(sideAdjuster), 3).setChessPiece(new Queen(R.color.white, this, Math.abs(sideAdjuster), 3));
        chessBoard.getChessSquare(Math.abs(sideAdjuster), 4).setChessPiece(new King(R.color.white, this, Math.abs(sideAdjuster), 4));
        chessBoard.getChessSquare(Math.abs(sideAdjuster), 5).setChessPiece(new Bishop(R.color.white, this, Math.abs(sideAdjuster), 5));
        chessBoard.getChessSquare(Math.abs(sideAdjuster), 6).setChessPiece(new Knight(R.color.white, this, Math.abs(sideAdjuster), 6));
        chessBoard.getChessSquare(Math.abs(sideAdjuster), 7).setChessPiece(new Rook(R.color.white, this, Math.abs(sideAdjuster), 7));

        // Set up the pawns for white
        for (int col = 0; col < 8; col++) {
            chessBoard.getChessSquare(Math.abs(sideAdjuster - 1), col).setChessPiece(new Pawn(R.color.white, this, Math.abs(sideAdjuster - 1), col));
        }

        // Set the middle squares to null
        for (int row = 2; row < 6; row++) {
            for (int col = 0; col < 8; col++) {
                chessBoard.getChessSquare(row, col).setChessPiece(null);
            }
        }

        // Set up the pawns for black
        for (int col = 0; col < 8; col++) {
            chessBoard.getChessSquare(Math.abs(sideAdjuster - 6), col).setChessPiece(new Pawn(R.color.black, this, Math.abs(sideAdjuster - 6), col));
        }

        // Set up the back rank for black
        chessBoard.getChessSquare(Math.abs(sideAdjuster - 7), 0).setChessPiece(new Rook(R.color.black, this, Math.abs(sideAdjuster - 7), 0));
        chessBoard.getChessSquare(Math.abs(sideAdjuster - 7), 1).setChessPiece(new Knight(R.color.black, this, Math.abs(sideAdjuster - 7), 1));
        chessBoard.getChessSquare(Math.abs(sideAdjuster - 7), 2).setChessPiece(new Bishop(R.color.black, this, Math.abs(sideAdjuster - 7), 2));
        chessBoard.getChessSquare(Math.abs(sideAdjuster - 7), 3).setChessPiece(new Queen(R.color.black, this, Math.abs(sideAdjuster - 7), 3));
        chessBoard.getChessSquare(Math.abs(sideAdjuster - 7), 4).setChessPiece(new King(R.color.black, this, Math.abs(sideAdjuster - 7), 4));
        chessBoard.getChessSquare(Math.abs(sideAdjuster - 7), 5).setChessPiece(new Bishop(R.color.black, this, Math.abs(sideAdjuster - 7), 5));
        chessBoard.getChessSquare(Math.abs(sideAdjuster - 7), 6).setChessPiece(new Knight(R.color.black, this, Math.abs(sideAdjuster - 7), 6));
        chessBoard.getChessSquare(Math.abs(sideAdjuster - 7), 7).setChessPiece(new Rook(R.color.black, this, Math.abs(sideAdjuster - 7), 7));

    }

}
