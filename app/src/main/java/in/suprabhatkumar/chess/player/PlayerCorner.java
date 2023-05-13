package in.suprabhatkumar.chess.player;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import in.suprabhatkumar.chess.GameActivity;
import in.suprabhatkumar.chess.R;
import in.suprabhatkumar.chess.player.Player;

public class PlayerCorner extends AppCompatActivity {

    protected GameActivity context;
    protected LinearLayout gameInfoLayout, gameProfileLayout;
    protected GridLayout deadPieceLayout;
    protected RelativeLayout playerCornerLayout;
    protected int gameType;

    public PlayerCorner(GameActivity gameActivity) {
        this.context = gameActivity;
        this.gameType = gameActivity.getGameType();
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        setPlayerCornerLayout(inflater);
        setGameInfoLayout(inflater);
        setGameProfileLayout(inflater);
        setDeadPieceLayout(inflater);
        ConstraintLayout c = getContext().findViewById(R.id.gameActivityLayout);
        c.addView(getPlayerCornerLayout());
    }

    public void setGameInfoLayout(LayoutInflater inflater) {
        View gameInfoView = inflater.inflate(R.layout.player_game_info, this.getPlayerCornerLayout());
        this.gameInfoLayout = gameInfoView.findViewById(R.id.playerGameInfo);
        if (gameType == 0)
            this.gameInfoLayout.setRotation(180);
    }

    public void setDeadPieceLayout(LayoutInflater inflater) {
        View deadPieceView = inflater.inflate(R.layout.dead_piece_layout, this.getPlayerCornerLayout());
        this.deadPieceLayout = deadPieceView.findViewById(R.id.deadPieceContainer);
        if (gameType == 0)
            this.deadPieceLayout.setRotation(180);
    }

    public void setGameProfileLayout(LayoutInflater inflater) {
        View gameProfileView = inflater.inflate(R.layout.player_game_profile_layout, this.getPlayerCornerLayout());
        this.gameProfileLayout = gameProfileView.findViewById(R.id.playerGameProfile);
        if (gameType == 0)
            this.gameProfileLayout.setRotation(180);
    }

    public void setPlayerCornerLayout(LayoutInflater inflater) {
        View playerCornerView = inflater.inflate(R.layout.player_corner_layout, null);
        this.playerCornerLayout = playerCornerView.findViewById(R.id.playerCorner);
    }

    public GridLayout getDeadPieceLayout() {
        return deadPieceLayout;
    }

    public RelativeLayout getPlayerCornerLayout() {
        return playerCornerLayout;
    }

    public LinearLayout getGameInfoLayout() {
        return gameInfoLayout;
    }

    public LinearLayout getGameProfileLayout() {
        return gameProfileLayout;
    }

    public GameActivity getContext() {
        return this.context;
    }

    public void rotate() {
        getPlayerCornerLayout().setRotation(180);
        getGameInfoLayout().setRotation(180);
        getGameProfileLayout().setRotation(180);
    }

    public void updatePoints(int point) {
        TextView pointView = getContext().findViewById(R.id.points);
        pointView.setText(Integer.toString(point));
    }

    public TextView getTimerView() {
        TextView timerView = getGameInfoLayout().findViewById(R.id.timer);
        return timerView;
    }
}
