package com.example.sayan.mis571;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.sayan.mis571.constant.SQLCommand;
import com.example.sayan.mis571.util.DBOperator;

public class MainActivity extends AppCompatActivity {

    private int UserID = 0;
    private int UserType = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn1 = (Button)findViewById(R.id.button);
        Button btn2 = (Button)findViewById(R.id.button2);
        final EditText username = (EditText)findViewById(R.id.EditTxtUsername);
        final EditText password = (EditText) findViewById(R.id.EditTxtPass);

        try{
            DBOperator.copyDB(getBaseContext());
        }catch(Exception e){
            e.printStackTrace();
        }
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Cursor cursor=DBOperator.getInstance().execQuery(SQLCommand.GetUser(username.getText(),password.getText()));
                while(cursor.moveToNext()){
                    UserID = Integer.parseInt(cursor.getString(0));
                    UserType = Integer.parseInt(cursor.getString(1));
                    Bundle b = new Bundle();
                    b.putInt("UserID",UserID);
                    switch (UserType){
                        case 1 : break;
                        case 2 :
                            Intent i = new Intent(MainActivity.this, Student_Home.class);
                            i.putExtras(b);
                            startActivity(i);
                            break;
                        case 3 :
                            Intent intent = new Intent(MainActivity.this, Prof_Home.class);
                            intent.putExtras(b);
                            startActivity(intent);
                            break;
                    }
                    break;
                }
                //startActivity(new Intent(MainActivity.this, Student_Home.class));
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Prof_Home.class));
            }
        });
    }
}
