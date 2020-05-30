package net.yxlyz.jumpapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
/**
 * @Author Saraph1nes
 * @Date 2020/5/21 14:30
 * @Version 1.0
 */
public class WelcomeActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btnStart;
    private Button btnRank;
    private Button btnQuit;
    private TextView userName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        btnStart=findViewById(R.id.startgame);
        btnRank=findViewById(R.id.topscore);
        btnQuit=findViewById(R.id.quitgame);
        userName=findViewById(R.id.userName);
        btnStart.setOnClickListener(this);
        btnRank.setOnClickListener(this);
        btnQuit.setOnClickListener(this);

        User currentUser = LoginActivity.CURRENT_USER;
        String name = currentUser.getUname();
        userName.setText(name);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.startgame:
                Intent intent=new Intent(this,GameActivity.class);
                startActivity(intent);
                break;
            case R.id.topscore:
                Intent intent1=new Intent(this,TopScoreActivity.class);
                startActivity(intent1);
                break;
            case R.id.quitgame:
                finish();
                break;
        }
    }
}
