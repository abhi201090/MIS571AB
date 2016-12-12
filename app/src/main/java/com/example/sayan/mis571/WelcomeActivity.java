package com.example.sayan.mis571;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;


public class WelcomeActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        View welcomebtn = findViewById(R.id.welcomebtn);

        welcomebtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
    //onClick method for welcome button, linked to Log in interface
//    public void onClick(View view){
//        Intent intent = new Intent(this, MainActivity.class);
//        startActivity(intent);
//    }
}
