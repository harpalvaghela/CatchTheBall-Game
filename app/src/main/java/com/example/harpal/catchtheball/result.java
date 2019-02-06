package com.example.harpal.catchtheball;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class result extends AppCompatActivity {


    String text,str;
    public  static  final  String TEXT = "text";
    Button exit,submit;
    TextView tvplayer;
    EditText edtid;
    TextView scoreLabel,highScoreLabel;
    public  int score,highScore ;
    public  static  final  String SHARED_PREFS = "sharedprefs";
    public  static  final  String PLAYER = "sharedprefss";
    SharedPreferences settings,settings1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        scoreLabel = (TextView) findViewById(R.id.scoreLabel);
        highScoreLabel = (TextView) findViewById(R.id.highScoreLabel);
        exit = (Button) findViewById(R.id.exitid);
        submit = (Button) findViewById(R.id.submitid);
        edtid=(EditText)findViewById(R.id.edtid);
        tvplayer = (TextView) findViewById(R.id.tvplayer);

        //get score from main.java
        score = getIntent().getIntExtra("SCORE", 0);
        scoreLabel.setText(score + "");

        LoadData();
        /*SharedPreferences settings = getSharedPreferences("HIGH_SCORE", Context.MODE_PRIVATE);
         highScore = settings.getInt("HIGH_SCORE", 0);
        text= settings.getString(TEXT,"");
*/
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                str=edtid.getText().toString();
                tvplayer.setText(str);
                saveData();
                UpdatePlayer();
                submit.setVisibility(View.GONE);
                edtid.setVisibility(View.GONE);
            }
        });

        if (score > highScore) {
            highScoreLabel.setText("High Score : " + score);

            submit.setVisibility(View.VISIBLE);
            edtid.setVisibility(View.VISIBLE);
    //        Toast.makeText(this, "if score > highscore", Toast.LENGTH_SHORT).show();
        }
        else
        {
            highScoreLabel.setText("High Score : " + highScore);
            tvplayer.setText(text);
            LoadData();
            submit.setVisibility(View.GONE);
            edtid.setVisibility(View.GONE);
        }


        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(result.this);
                alertDialogBuilder.setTitle("Confirm Exit?");
                alertDialogBuilder.setIcon(R.drawable.exit_icon);
                alertDialogBuilder
                        .setMessage("Are you sure you want to exit?")
                        .setCancelable(false)
                        .setPositiveButton("Yes",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        moveTaskToBack(true);
                                        android.os.Process.killProcess(android.os.Process.myPid());
                                        System.exit(1);
                                    }
                                })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });

    }//end of onCreate method

    private void UpdatePlayer() {
        settings1 = getSharedPreferences(PLAYER, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor1 = settings1.edit();

        editor1.putInt("HIGH_SCORE", score);
        editor1.apply();
        //Toast.makeText(this, "Score Saved", Toast.LENGTH_SHORT).show();
    }

    public  void saveData()
    {
        settings = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();

        editor.putString("TEXT",str);
        editor.apply();
        //Toast.makeText(this, "Name Saved", Toast.LENGTH_SHORT).show();
    }

    public  void LoadData()
    {
        settings = getSharedPreferences(SHARED_PREFS,Context.MODE_PRIVATE);
        settings1 = getSharedPreferences(PLAYER, Context.MODE_PRIVATE);

        text = settings.getString("TEXT","");
        highScore = settings1.getInt("HIGH_SCORE", 0);

        highScoreLabel.setText("High Score : " + highScore);
        tvplayer.setText(text);

        //Toast.makeText(this, "Loading...", Toast.LENGTH_SHORT).show();
    }


    public void tryAgain(View view) {
        startActivity(new Intent(getApplicationContext(), start.class));
    }

    @Override
    public void onBackPressed() {
    //    super.onBackPressed();
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(result.this);
        alertDialogBuilder.setTitle("Confirm Exit?");
        alertDialogBuilder.setIcon(R.drawable.exit_icon);
        alertDialogBuilder
                .setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                moveTaskToBack(true);
                                android.os.Process.killProcess(android.os.Process.myPid());
                                System.exit(1);
                            }
                        })

                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }
}

