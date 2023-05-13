package in.suprabhatkumar.chess;

import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
import java.util.Stack;

import in.suprabhatkumar.chess.chessboard.ChessBoard;
import in.suprabhatkumar.chess.chessboard.ChessSquare;
import in.suprabhatkumar.chess.chesspiece.Bishop;
import in.suprabhatkumar.chess.chesspiece.ChessPiece;
import in.suprabhatkumar.chess.chesspiece.King;
import in.suprabhatkumar.chess.chesspiece.Knight;
import in.suprabhatkumar.chess.chesspiece.Pawn;
import in.suprabhatkumar.chess.chesspiece.Queen;
import in.suprabhatkumar.chess.chesspiece.Rook;
import in.suprabhatkumar.chess.player.ComputerPlayer;
import in.suprabhatkumar.chess.player.Player;
import in.suprabhatkumar.chess.player.PlayerCorner;

public class Game {

    protected ChessBoard chessBoard;
    protected final Player[] players = new Player[2];
    protected final PlayerCorner[] playerCorners = new PlayerCorner[2];
    protected Player currentPlayer, oppositePlayer, winner;
    protected boolean gameEnd;
    protected int gameType;
    protected Stack<Move> moveStack;
    protected int level;

    public Game(ChessBoard chessBoard, int gameType, PlayerCorner[] playerCorners) {
        this.chessBoard = chessBoard;
        this.gameEnd = false;
        this.gameType = gameType;
        for (int i = 0; i < 2; i++) {
            this.playerCorners[i] = playerCorners[i];
        }
        setPlayers("Suprabhat", "Aparna");
        initialize_pieces();
        level = 2;
        moveStack = new Stack<>();
        getCurrentPlayer().startTimer();
    }

    public Game(ChessBoard chessBoard, int gameType, PlayerCorner[] playerCorners, int level) {
        this(chessBoard, gameType, playerCorners);
        this.level = level;
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

    protected void setPlayers(String firstPlayer, String secondPlayer) {
        Random random = new Random();
        int firstChance = random.nextInt(2);
        players[firstChance] = new Player(firstPlayer, R.color.white, playerCorners[firstChance], 3 * 60, this);
        if (this.gameType == 0) {
            players[1 - firstChance] = new Player(secondPlayer, R.color.black, playerCorners[1 - firstChance], 3 * 60, this);
        } else {
            players[1 - firstChance] = new ComputerPlayer(R.color.black, playerCorners[1 - firstChance], 3 * 60, this);
        }
        setCurrentPlayer(players[firstChance]);
        setOppositePlayer(players[1 - firstChance]);
    }

    public void endGame() {

        this.gameEnd = true;
        if (getOppositePlayer().isChecked()){
            setWinner(getCurrentPlayer());
        } else {
            setWinner(getOppositePlayer());
        }
        this.getCurrentPlayer().disableClickOnPieces();
        this.getOppositePlayer().disableClickOnPieces();
        Log.w("WINNER", this.winner.getName());
        TextView winnerMessage = new TextView(this.getChessBoard().getChessBoardLayout().getContext());
        winnerMessage.setText("Winner : " + getCurrentPlayer().getName());
        this.getChessBoard().getChessBoardLayout().addView(winnerMessage);

    }

    public void setOppositePlayer(Player oppositePlayer) {
        this.oppositePlayer = oppositePlayer;
    }

    public Player getOppositePlayer() {
        return oppositePlayer;
    }

    public void setCurrentPlayer (Player player) {
        this.currentPlayer = player;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public int getGameType() { return gameType; }

    protected void initialize_player_pieces() {
        int sideAdjuster, row, color;
        ChessPiece chessPiece;

        if (getCurrentPlayer() == getPlayer(0)) {
            sideAdjuster = 7;
        } else {
            sideAdjuster = 0;
        }
        for (int i=0; i < 2; i++) {
            if (getPlayer(i).isWhite()) {
                row = Math.abs(sideAdjuster);
            } else {
                row = Math.abs(sideAdjuster - 7);
            }

            color = getPlayer(i).getColor();

            chessPiece = new Rook(color, this, row, 0);
            getPlayer(i).addChessPiece(chessPiece);
            chessBoard.getChessSquare(row, 0).setChessPiece(chessPiece);

            chessPiece = new Knight(color, this, row, 1);
            getPlayer(i).addChessPiece(chessPiece);
            chessBoard.getChessSquare(row, 1).setChessPiece(chessPiece);

            chessPiece = new Bishop(color, this, row, 2);
            getPlayer(i).addChessPiece(chessPiece);
            chessBoard.getChessSquare(row, 2).setChessPiece(chessPiece);

            chessPiece = new Queen(color, this, row, 3);
            getPlayer(i).addChessPiece(chessPiece);
            chessBoard.getChessSquare(row, 3).setChessPiece(chessPiece);

            chessPiece = new King(color, this, row, 4);
            getPlayer(i).addChessPiece(chessPiece);
            chessBoard.getChessSquare(row, 4).setChessPiece(chessPiece);

            chessPiece = new Bishop(color, this, row, 5);
            getPlayer(i).addChessPiece(chessPiece);
            chessBoard.getChessSquare(row, 5).setChessPiece(chessPiece);

            chessPiece = new Knight(color, this, row, 6);
            getPlayer(i).addChessPiece(chessPiece);
            chessBoard.getChessSquare(row, 6).setChessPiece(chessPiece);

            chessPiece = new Rook(color, this, row, 7);
            getPlayer(i).addChessPiece(chessPiece);
            chessBoard.getChessSquare(row, 7).setChessPiece(chessPiece);

            for (int col = 0; col < 8; col++) {
                chessPiece = new Pawn(color, this, Math.abs(row - 1), col);
                getPlayer(i).addChessPiece(chessPiece);
                chessBoard.getChessSquare(Math.abs(row - 1), col).setChessPiece(chessPiece);
            }

        }
    }

    protected void initialize_pieces() {
        initialize_player_pieces();
        // Set the middle squares to null
        for (int row = 2; row < 6; row++) {
            for (int col = 0; col < 8; col++) {
                chessBoard.getChessSquare(row, col).setChessPiece(null);
            }
        }
    }

    public Move getBestMove() {
        int originalGameType = this.gameType;
        if (gameType == 1) {
            this.gameType = 0;
        }
        Move bestMove = new Move(null, null, null, null);
        bestMove = Move.getBestMove(getCurrentPlayer(), getOppositePlayer(), level, this);
        this.gameType = originalGameType;
        return bestMove;
    }

    public boolean isKingOnCheck() {
        getOppositePlayer().clearAllPossibleMoves();
        getOppositePlayer().setAllPossibleMoves();
        for (Move move : getOppositePlayer().getAllPossibleMoves()) {
            if (move.getKilledPiece().getPieceName() == "KING") {
                Log.w("CHECK", "King is Still on Check!");
                Toast.makeText(getChessBoard().getBaseContext(), "Your message here", Toast.LENGTH_SHORT).show();
                return true;
            }
        }
        return false;
    }

    public void switch_turn() {
        getCurrentPlayer().stopTimer();
        getCurrentPlayer().clearAllPossibleMoves();
        getCurrentPlayer().setAllPossibleMoves();
        Player oppositePlayer = getOppositePlayer();
        setOppositePlayer(getCurrentPlayer());
        setCurrentPlayer(oppositePlayer);
        getCurrentPlayer().clearAllPossibleMoves();
        getCurrentPlayer().setAllPossibleMoves();
        getCurrentPlayer().startTimer();

        if (gameType == 1 && getCurrentPlayer().isBot()) {
            Log.e("BOT", "bot player");
            Move bestMove = getBestMove();
            int destRow = bestMove.getDestSquare().getRow();
            int destColumn = bestMove.getDestSquare().getColumn();
            bestMove.getChessPiece().move(destRow, destColumn);
        }
    }

    public void addMove(Move move) {
        this.moveStack.push(move);
    }

    public void undo() {
        Move lastMove = moveStack.pop();

        ChessPiece currentPiece = lastMove.getChessPiece();
        ChessSquare initSquare = lastMove.getInitSquare();

        currentPiece.setCurrentRow(initSquare.getRow());
        currentPiece.setCurrentColumn(initSquare.getColumn());
        currentPiece.setCurrentSquare();
        initSquare.setChessPiece(currentPiece);
        initSquare.markAsFilled();

        ChessSquare destSquare = lastMove.getDestSquare();
        if (lastMove.getKilledPiece() != null) {
            ChessPiece killedPiece = lastMove.getKilledPiece();

            killedPiece.setAlive();
            destSquare.setChessPiece(killedPiece);
            destSquare.markAsFilled();
            killedPiece.setCurrentRow(destSquare.getRow());
            killedPiece.setCurrentColumn(destSquare.getColumn());
            killedPiece.setCurrentSquare();
        } else {
            destSquare.markAsEmpty();
            destSquare.setChessPiece(null);
        }

        switch_turn();
    }

}
