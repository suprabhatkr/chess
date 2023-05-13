package in.suprabhatkumar.chess.player;

import android.widget.TextView;

import in.suprabhatkumar.chess.Game;

public class Timer implements Runnable {

    private TextView textView;
    private boolean isRunning;
    private int remainingSeconds;
    private Game game;

    public Timer(TextView textView, Game game) {
        this.textView = textView;
        this.isRunning = false;
        this.remainingSeconds = 180;
        this.game = game;
    }

    public void startTimer(int remainingSeconds) {
        if (!isRunning) {
            this.remainingSeconds = remainingSeconds;
            isRunning = true;
            Thread thread = new Thread(this);
            thread.start();
        }
    }

    public int stopTimer() {
        isRunning = false;
        return this.remainingSeconds;
    }

    @Override
    public void run() {
        while (isRunning) {
            try {
                Thread.sleep(remainingSeconds * 1000);
                remainingSeconds --;
                updateTextView();
                if (remainingSeconds <= 0) {
                    isRunning = false;
                    game.endGame();
                    break;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void updateTextView() {
        textView.post(new Runnable() {
            @Override
            public void run() {
                int minutes = remainingSeconds / 60;
                int remainderSeconds = remainingSeconds % 60;
                textView.setText(String.format("%02d:%02d", minutes, remainderSeconds));
            }
        });
    }
}

