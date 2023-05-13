package in.suprabhatkumar.chess;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import in.suprabhatkumar.chess.chessboard.ChessSquare;
import in.suprabhatkumar.chess.chesspiece.ChessPiece;
import in.suprabhatkumar.chess.player.Player;

public class Move {

    private ChessPiece chessPiece, killedPiece;
    private ChessSquare initSquare, destSquare;

    public Move(ChessPiece chessPiece, ChessSquare initSquare, ChessPiece killedPiece, ChessSquare destSquare) {
        setChessPiece(chessPiece);
        setInitSquare(initSquare);
        setKilledPiece(killedPiece);
        setDestSquare(destSquare);
    }

    public ChessPiece getChessPiece() {
        return chessPiece;
    }

    public void setChessPiece(ChessPiece chessPiece) {
        this.chessPiece = chessPiece;
    }

    public ChessPiece getKilledPiece() {
        return killedPiece;
    }

    public void setKilledPiece(ChessPiece killedPiece) {
        this.killedPiece = killedPiece;
    }

    public ChessSquare getInitSquare() {
        return initSquare;
    }

    public void setInitSquare(ChessSquare initSquare) {
        this.initSquare = initSquare;
    }

    public ChessSquare getDestSquare() {
        return destSquare;
    }

    public void setDestSquare(ChessSquare destSquare) {
        this.destSquare = destSquare;
    }

    public int getCurrentMoveValue() {
        return getChessPiece().getCurrentMoveValue(getDestSquare());
    }

    public static Move getBestMove(Player currentPlayer, Player otherPlayer, int level, Game game) {
        if (level <= 0) {
            return null;
        }
        Move bestMove = new Move(null, null, null, null);
        int bestScore = -102;
        HashMap<Move, Integer> moveScore = new HashMap<>(0);
        Set<Move> allPossibleMoves = new HashSet<>(currentPlayer.getAllPossibleMoves());
        for (Move currentMove : allPossibleMoves) {
            int initScore = currentMove.getCurrentMoveValue();
            moveScore.put(currentMove, initScore);
            int destRow = currentMove.getDestSquare().getRow();
            int destColumn = currentMove.getDestSquare().getColumn();
            currentMove.getChessPiece().move(destRow, destColumn);
            bestMove(otherPlayer, currentPlayer, level -1, moveScore, currentMove, false, game);
            game.undo();
            if (moveScore.get(currentMove) > bestScore ){
                bestScore = moveScore.get(currentMove);
                bestMove = currentMove;
            }
        }
        return bestMove;
    }

    public static void bestMove(
            Player currentPlayer,
            Player otherPlayer,
            int level,
            HashMap<Move, Integer> moveScore,
            Move firstMove,
            boolean firstPlayerTurn,
            Game game) {
        if (level <= 0) {
            return;
        }
        Set<Move> allPossibleMoves = new HashSet<>(currentPlayer.getAllPossibleMoves());
        for (Move currentMove : allPossibleMoves) {
            int score = moveScore.get(firstMove);
            if (firstPlayerTurn)
                score += currentMove.getCurrentMoveValue();
            else
                score -= currentMove.getCurrentMoveValue();
            moveScore.put(firstMove, score);
            int destRow = currentMove.getDestSquare().getRow();
            int destColumn = currentMove.getDestSquare().getColumn();
            currentMove.getChessPiece().move(destRow, destColumn);
            bestMove(otherPlayer, currentPlayer, level - 1, moveScore, firstMove, !firstPlayerTurn, game);
            game.undo();
        }
    }

}

