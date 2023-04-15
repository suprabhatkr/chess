package in.suprabhatkumar.chess;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;


import android.os.Bundle;
import android.widget.ImageView;


public class GameActivity extends AppCompatActivity {

    PlayerCorner playerCorner0, playerCorner1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        ImageView myImageView = findViewById(R.id.background_image_game);
        myImageView.setImageResource(R.drawable.game_background);
        myImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

        ChessBoard chessBoard = new ChessBoard();
        chessBoard.setChessBoardLayout(findViewById(R.id.chess_board));
        chessBoard.generateSquares();
        Game game = new Game(chessBoard);

        playerCorner0 = new PlayerCorner(game.getPlayer(0), this);
        playerCorner1 = new PlayerCorner(game.getPlayer(1), this);
        playerCorner1.getPlayerCornerLayout().setRotation(180);
        ConstraintLayout c = findViewById(R.id.gameActivityLayout);
        c.addView(playerCorner0.getPlayerCornerLayout());
        c.addView(playerCorner1.getPlayerCornerLayout());

        //BoardHorizontalLayout boardHorizontalLayout = new BoardHorizontalLayout(this);
    }

}


