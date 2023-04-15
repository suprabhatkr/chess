package in.suprabhatkumar.chess;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class PlayerCorner extends AppCompatActivity {

    protected final Player player;
    protected GameActivity context;
    protected LinearLayout gameInfoLayout, gameProfileLayout;
    protected GridLayout deadPieceLayout;
    protected RelativeLayout playerCornerLayout;

    public PlayerCorner(Player player, GameActivity gameActivity) {
        this.player = player;
        this.context = gameActivity;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        setPlayerCornerLayout(inflater);
        setGameInfoLayout(inflater);
        setGameProfileLayout(inflater);
        setDeadPieceLayout(inflater);
    }

    public void setGameInfoLayout(LayoutInflater inflater) {
        View gameInfoView = inflater.inflate(R.layout.player_game_info, this.getPlayerCornerLayout());
        this.gameInfoLayout = gameInfoView.findViewById(R.id.playerGameInfo);
        this.gameInfoLayout.setRotation(180);
    }

    public void setDeadPieceLayout(LayoutInflater inflater) {
        View deadPieceView = inflater.inflate(R.layout.dead_piece_layout, this.getPlayerCornerLayout());
        this.deadPieceLayout = deadPieceView.findViewById(R.id.deadPieceContainer);
        this.deadPieceLayout.setRotation(180);
    }

    public void setGameProfileLayout(LayoutInflater inflater) {
        View gameProfileView = inflater.inflate(R.layout.player_game_profile_layout, this.getPlayerCornerLayout());
        this.gameProfileLayout = gameProfileView.findViewById(R.id.playerGameProfile);
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

    public GameActivity getContext() {
        return this.context;
    }
}
