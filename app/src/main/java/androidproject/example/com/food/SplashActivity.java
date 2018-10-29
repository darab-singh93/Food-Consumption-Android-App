package androidproject.example.com.food;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidproject.example.com.food.Login.LoginActivity;

public class SplashActivity extends AppCompatActivity {
    ImageView img_splash;
    TextView txtfoodholic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        txtfoodholic=findViewById(R.id.txtfc);

        img_splash=findViewById(R.id.img_splash);
        Animation animation=(AnimationUtils.loadAnimation(SplashActivity.this,R.anim.animfirst));
        txtfoodholic.startAnimation(animation);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {


                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                finish();
            }
        },3000);
    }
}