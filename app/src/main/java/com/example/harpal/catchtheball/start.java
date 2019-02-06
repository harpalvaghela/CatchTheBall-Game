package com.example.harpal.catchtheball;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

public class start extends AppCompatActivity {

    //private InterstitialAd interstitial;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
/*


        // Create the interstitial.
        interstitial = new InterstitialAd(this);

        // Set your unit id. THIS IS TEST ID!!
        interstitial.setAdUnitId("ca-app-pub-3940256099942544/1033173712");

        // Create request.
        AdRequest adRequest = new AdRequest.Builder().build();

        // Start loading...
        interstitial.loadAd(adRequest);

        // Once request is loaded, display ad.
        interstitial.setAdListener(new AdListener() {
            public void onAdLoaded() {
                displayInterstitial();
            }
        });




    }


    private void displayInterstitial() {

        if (interstitial.isLoaded()) {
            interstitial.show();
        }
    }
*/
}
    public void startGame(View view) {
        startActivity(new Intent(getApplicationContext(), main.class));
    }

    // Disable Return Button
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {

        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (event.getKeyCode()) {
                case KeyEvent.KEYCODE_BACK:

                        break;
            }
        }

        return super.dispatchKeyEvent(event);
    }


    @Override
    public void onBackPressed() {
     //   super.onBackPressed();
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(start.this);
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
    }//end of onBackPressed()

    }//end of class




