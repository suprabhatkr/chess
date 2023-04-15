package in.suprabhatkumar.chess;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView myImageView = findViewById(R.id.background_image_main);
        myImageView.setImageResource(R.drawable.chess_background_6);
        myImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

        Button newGameButton = findViewById(R.id.new_game_button);
        newGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent chessBoardIntent = new Intent(MainActivity.this, GameActivity.class);
                startActivity(chessBoardIntent);
            }
        });
    }
}