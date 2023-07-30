package in.suprabhatkumar.chess;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button newGameButton = findViewById(R.id.new_game_button);
        newGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent chessBoardIntent = new Intent(MainActivity.this, CustomiseActivity.class);
                chessBoardIntent.putExtra("gameType", 0);
                startActivity(chessBoardIntent);
            }
        });

        Button botGameButton = findViewById(R.id.computer_button);
        botGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent chessBoardIntent = new Intent(MainActivity.this, CustomiseActivity.class);
                chessBoardIntent.putExtra("gameType", 1);
                startActivity(chessBoardIntent);
            }
        });
    }
}