package in.suprabhatkumar.chess.chessboard;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import in.suprabhatkumar.chess.chesspiece.ChessPiece;
import in.suprabhatkumar.chess.chessboard.CellLayout;

public class ChessSquare extends AppCompatActivity {

    private final int row;
    private final int column;
    private final boolean white;
    private boolean empty;
    private final LinearLayout cellLayout;
    private ChessPiece chessPiece;
    private final Context context;

    public ChessSquare(int row, int column, GridLayout chessBoardLayout){
        this.row = row;
        this.column = column;
        this.white = (row%2 == column%2);
        this.empty = (row > 1 && row < 6);
        this.context = chessBoardLayout.getContext();
        this.cellLayout = CellLayout.getCellLayout(this.context, row + 1, column + 1);
        chessBoardLayout.addView(getCellLayout());
    }

    public LinearLayout getCellLayout() {
        return cellLayout;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public boolean isWhite() {
        return white;
    }

    public boolean isBlack() {
        return !white;
    }

    public int getColor() {
        if (isWhite())
            return Color.WHITE;
        else
            return Color.YELLOW;
    }

    public void changeColor(int color) {
        if (color != getColor()) {
            if (isEmpty()) {
                getCellLayout().setBackground(new ColorDrawable(color));
                getCellLayout().setAlpha(0.6f);
            } else {
                ImageView pieceImageView = chessPiece.getPieceImageView();
                pieceImageView.setBackgroundColor(color);
            }
        } else {
            Log.w("CHANGE COLOR", Integer.toString(getRow()) + " " + Integer.toString(getColumn()));
            getCellLayout().setBackground(new ColorDrawable(color));
            getCellLayout().setAlpha(1f);
            getCellLayout().setOnClickListener(null);
//            if (!isEmpty() && chessPiece.getPieceImageView()!=null) {
//                ImageView pieceImageView = chessPiece.getPieceImageView();
//                setChessPiece(chessPiece);
//
//            }
        }
    }


    public boolean isEmpty() {
        return empty;
    }

    public void markAsFilled() {
        empty = false;
    }

    public void markAsEmpty() {
        empty = true;
    }

    public void setChessPiece(ChessPiece chessPiece) {
        getCellLayout().removeAllViews();
        if (chessPiece != null) {
            View imageView = chessPiece.getPieceImageView();
            ViewGroup parentView = (ViewGroup) imageView.getParent();
            if (parentView != null) {
                parentView.removeView(imageView);
            }
            imageView.setBackgroundColor(getColor());
            getCellLayout().addView(imageView);
            imageView.setOnClickListener(v -> {
                if (this.getChessPiece() != null)
                    this.getChessPiece().setUsualOnClick();
                else
                    Log.e("NULL CHESSPIECE", "Chess Piece is Null Please set it!");
            });
        }

        this.chessPiece = chessPiece;
    }

    public ChessPiece getChessPiece() {
        return chessPiece;
    }
}
