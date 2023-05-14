package in.suprabhatkumar.chess;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;


import android.os.Bundle;
import android.widget.ImageView;

import in.suprabhatkumar.chess.chessboard.ChessBoard;
import in.suprabhatkumar.chess.player.PlayerCorner;


public class GameActivity extends AppCompatActivity {

    PlayerCorner[] playerCorners;
    int gameType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        ImageView myImageView = findViewById(R.id.background_image_game);
        myImageView.setImageResource(R.drawable.game_background);
        myImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

        Bundle gameData = getIntent().getExtras();
        this.gameType = gameData.getInt("gameType", 0);
        ChessBoard chessBoard = new ChessBoard(gameType);
        chessBoard.setChessBoardLayout(findViewById(R.id.chess_board));

        playerCorners = new PlayerCorner[2];
        playerCorners[0] = new PlayerCorner(this);
        playerCorners[1] = new PlayerCorner(this);
        playerCorners[1].rotate();

        Game game = new Game(chessBoard, gameData, playerCorners);

    }

    public int getGameType() {
        return gameType;
    }
}


