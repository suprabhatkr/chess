package in.suprabhatkumar.chess.chessboard;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.gridlayout.widget.GridLayout;

public class CellLayout {

    private static String getTextValue(int row, int column) {
        String text;
        if (column == 0 || column == 9) {
            text = Character.toString((char) (73 - row));
        } else {
            text = Integer.toString(column);
        }
        return  text;
    }

    public static ViewGroup.LayoutParams getParams(int row, int column){
        GridLayout.LayoutParams params = new GridLayout.LayoutParams();
        params.width = 0;
        params.height = 0;
        params.setMargins(1, 1, 1, 1);
        if (column == 0 || column == 9)
            params.columnSpec = GridLayout.spec(column, 0.4f);
        else
            params.columnSpec = GridLayout.spec(column, 1f);
        if (row == 0 || row == 9)
            params.rowSpec = GridLayout.spec(row, 0.4f);
        else
            params.rowSpec = GridLayout.spec(row, 1f);
        return  params;
    }

    public static int getDefaultColor(int row, int column) {
        if (column == 0 || column == 9 || row == 0 || row == 9) {
            return Color.RED;
        } else {
            if (row%2 == column%2){
                return Color.WHITE;
            } else {
                return Color.YELLOW;
            }
        }
    }

    public static LinearLayout getCellLayout(Context context, int row, int column) {
        LinearLayout cellLayout = new LinearLayout(context);
        cellLayout.setLayoutParams(getParams(row, column));
        cellLayout.setBackground(new ColorDrawable(getDefaultColor(row, column)));
        return cellLayout;
    }

    public static TextView getCellView(Context context, int row, int column, int gameType) {
            TextView textView = new TextView(context);
            if ((row != column) && (Math.abs(row - column) != 9))
                textView.setText(getTextValue(row, column));
            textView.setLayoutParams(getParams(row, column));
            textView.setBackground(new ColorDrawable(getDefaultColor(row, column)));
            if (gameType == 0 && (row == 0 || column == 9))
                textView.setRotation(180);
            return textView;
    }
}
