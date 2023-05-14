package in.suprabhatkumar.chess;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

public class CustomiseActivity extends AppCompatActivity {

    protected int level = 0;
    protected SeekBar levelSlider;
    protected SwitchCompat timerSwitch, timeIncreaseSwitch;
    protected Spinner gameTimeSpinner, timeIncreaseSpinner;
    protected int gameTime, increaseTime;
    protected EditText player1Input, player2Input;
    protected TextView player1Label, player2Label;
    protected String player1Name, player2Name;
    protected Button startGameButton;
    int gameType;
    boolean timerEnabled, timeIncreaseEnabled;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customise);

        initializeViews();

        this.gameType = getIntent().getExtras().getInt("gameType", 0);

        switch (gameType) {
            case 0:
                enablePlayer1Input();
                enablePlayer2Input();
            case 1:
                enablePlayer1Input();
                enableLevelSeeker();
        }
        enableTimerSwitch();
        enableButtonClick();

    }

    private void initializeViews() {
        levelSlider = findViewById(R.id.levelBar);
        levelSlider.setVisibility(View.GONE);

        timerSwitch = findViewById(R.id.timer_switch);
        timerSwitch.setVisibility(View.GONE);

        timeIncreaseSwitch = findViewById(R.id.time_increase_switch);
        timeIncreaseSwitch.setVisibility(View.GONE);

        gameTimeSpinner = findViewById(R.id.gameTimeSpinner);
        gameTimeSpinner.setVisibility(View.GONE);

        timeIncreaseSpinner = findViewById(R.id.timeIncreaseOptions);
        timeIncreaseSpinner.setVisibility(View.GONE);

        player1Input = findViewById(R.id.player1Input);
        player2Input = findViewById(R.id.player2Input);
        player1Input.setVisibility(View.GONE);
        player2Input.setVisibility(View.GONE);

        player1Label = findViewById(R.id.player1Label);
        player2Label = findViewById(R.id.player2Label);
        player1Label.setVisibility(View.GONE);
        player2Label.setVisibility(View.GONE);

        startGameButton = findViewById(R.id.startGameButton);
    }

    private void enablePlayer1Input() {
        player1Label.setVisibility(View.VISIBLE);
        player1Input.setVisibility(View.VISIBLE);
    }

    private void enablePlayer2Input() {
        player2Label.setVisibility(View.VISIBLE);
        player2Input.setVisibility(View.VISIBLE);
    }

    private void enableTimerSwitch() {
        timerSwitch.setVisibility(View.VISIBLE);
        timerSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    timerEnabled = true;
                    enableGameTimeSpinner();
                    enableTimeIncreaseSwitch();
                } else {
                    timerEnabled = false;
                }
            }
        });
    }

    private void enableTimeIncreaseSwitch() {
        timeIncreaseSwitch.setVisibility(View.VISIBLE);
        timerSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked && timerEnabled) {
                    timeIncreaseEnabled = true;
                    enableTimeIncreaseSpinner();
                } else {
                    timeIncreaseEnabled = false;
                }
            }
        });
    }

    private void enableLevelSeeker() {
        levelSlider.setVisibility(View.VISIBLE);
        levelSlider.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // Read the value of the slider
                level = progress + 1;
                Log.d("Slider", "Value: " + level);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
                level = 1;
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }
        });
    }

    private void enableGameTimeSpinner() {
        gameTimeSpinner.setVisibility(View.VISIBLE);
        gameTimeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedValue = parent.getItemAtPosition(position).toString();
                gameTime = Integer.parseInt(selectedValue.split(" ")[0]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                gameTime = 3;
            }
        });
    }

    private void enableTimeIncreaseSpinner() {
        timeIncreaseSpinner.setVisibility(View.VISIBLE);
        timeIncreaseSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedValue = parent.getItemAtPosition(position).toString();
                increaseTime = Integer.parseInt(selectedValue.split(" ")[0]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                increaseTime = 3;
            }
        });
    }

    private void enableButtonClick() {
        startGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent chessBoardIntent = new Intent(CustomiseActivity.this, GameActivity.class);

                switch (gameType) {
                    case 0:
                        player1Name = player1Input.toString();
                        player2Name = player2Input.toString();
                        chessBoardIntent.putExtra("player1Name", player1Name);
                        chessBoardIntent.putExtra("player2Name", player2Name);
                    case 1:
                        player1Name = player1Input.toString();
                        chessBoardIntent.putExtra("player1Name", player1Name);
                        chessBoardIntent.putExtra("level", level);
                }

                if (timerEnabled) {
                    chessBoardIntent.putExtra("gameTime", gameTime);
                }
                if (timerEnabled && timeIncreaseEnabled) {
                    chessBoardIntent.putExtra("increaseTime", increaseTime);
                }

                chessBoardIntent.putExtra("gameType", gameType);
                startActivity(chessBoardIntent);
            }
        });
    }
}