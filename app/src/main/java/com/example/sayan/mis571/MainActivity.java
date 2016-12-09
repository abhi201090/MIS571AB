package com.example.sayan.mis571;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.sayan.mis571.constant.SQLCommand;
import com.example.sayan.mis571.util.DBOperator;

public class MainActivity extends Activity {

    private int UserID = 0;
    private int UserType = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View btn1 = findViewById(R.id.button);
        View btn2 = findViewById(R.id.button2);
        final EditText username = (EditText) findViewById(R.id.EditTxtUsername);
        final EditText password = (EditText) findViewById(R.id.EditTxtPass);
        AlertDialog.Builder dlgAlert = new AlertDialog.Builder(this);

        try {
            DBOperator.copyDB(getBaseContext());
        } catch (Exception e) {
            e.printStackTrace();
        }
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get username and password from user input and store in a cursor.
                Cursor cursor = DBOperator.getInstance().execQuery(SQLCommand.GetUser(username.getText(), password.getText()));
                //two columns and one row will be retrieved, UserID is 0, UserType is 1.
                if (cursor.getCount() == 0){
                    Context context = getApplicationContext();
                    Toast toast = Toast.makeText(context,"Wrong Username or Password!", Toast.LENGTH_LONG);
                    toast.show();
                }
                else {
                    while (cursor.moveToNext()) {
                        UserID = Integer.parseInt(cursor.getString(0));
                        UserType = Integer.parseInt(cursor.getString(1));
                        Bundle b = new Bundle();
                        b.putInt("UserID", UserID);
                        switch (UserType) {
                            //Administrator
                            case 1:
                                dlgAlert.setMessage("Administrator Account not available");
                                dlgAlert.setPositiveButton("OK", null);
                                dlgAlert.setCancelable(true);
                                dlgAlert.setIcon(R.drawable.icon);
                                dlgAlert.create().show();
                                break;
                            //Student account
                            case 2:
                                Intent i = new Intent(MainActivity.this, Student_Home.class);
                                i.putExtras(b);
                                startActivity(i);
                                break;
                            //Professor account
                            case 3:
                                Intent intent = new Intent(MainActivity.this, Prof_Home.class);
                                intent.putExtras(b);
                                startActivity(intent);
                                break;
                        }
                        break;
                    }
                }
                //startActivity(new Intent(MainActivity.this, Student_Home.class));
            }
        });

//        btn2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this, Prof_Home.class));
//            }
//        });
    }
}

