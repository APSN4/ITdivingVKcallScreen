package com.apsn4.itdivingproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.Telephony;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).hide();
        LinearLayout firstCaller = (LinearLayout) findViewById(R.id.Layout_firstCaller);
        LinearLayout SecondCaller = (LinearLayout) findViewById(R.id.Layout_SecondCaller);
        int sizeInPixels = (int) getResources().getDimension(R.dimen.my_value);
        firstCaller.getLayoutParams().height = sizeInPixels;
        SecondCaller.getLayoutParams().height = sizeInPixels;
    }

    public static class ValuesGlobal {
        public static int isActiveCameraButton = 0;
        public static int isActiveMicButton = 0;
        public static int optionTileView = 0;
        public static String str;
    }

    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }

    public void onCameraButtonClick(View view)
    {
        // выводим сообщение
        // Toast.makeText(this, "Нажал на кнопку", Toast.LENGTH_SHORT).show();
        // ImageButton btn = (ImageButton) findViewById(R.id.imageButtonCamera);
        if (ValuesGlobal.isActiveCameraButton == 0) {
            //btn.setColorFilter(Color.argb(255, 54, 55, 56));
            ImageView img = (ImageView) findViewById(R.id.imageViewCamera);
            img.setBackgroundResource(R.drawable.videocam_fill1_wght700_grad0_opsz48);
            ValuesGlobal.isActiveCameraButton = 1;
        } else {
            //btn.setColorFilter(Color.argb(255, 255, 255, 255));
            ImageView img = (ImageView) findViewById(R.id.imageViewCamera);
            img.setBackgroundResource(R.drawable.videocam_off_fill1_wght700_grad0_opsz48);
            ValuesGlobal.isActiveCameraButton = 0;
        }
    }
    public void onMicButtonClick(View view)
    {
        if (ValuesGlobal.isActiveMicButton == 0) {
            ImageView img = (ImageView) findViewById(R.id.imageViewMicrophone);
            ImageView imgTile = (ImageView) findViewById(R.id.imageViewMicMe);
            imgTile.setBackgroundResource(R.drawable.mic_fill1_wght700_grad0_opsz48);
            img.setBackgroundResource(R.drawable.mic_fill1_wght700_grad0_opsz48);
            ValuesGlobal.isActiveMicButton = 1;
        } else {
            ImageView img = (ImageView) findViewById(R.id.imageViewMicrophone);
            ImageView imgTile = (ImageView) findViewById(R.id.imageViewMicMe);
            imgTile.setBackgroundResource(R.drawable.mic_off_fill1_wght700_grad0_opsz48);
            img.setBackgroundResource(R.drawable.mic_off_fill1_wght700_grad0_opsz48);
            ValuesGlobal.isActiveMicButton = 0;
        }
    }
    public void onHandButtonClick(View view)
    {
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.btn_star)
                .setTitle("VK Project")
                .setMessage("привет")
                .show();
    }
    public void onCancelButtonClick(View view)
    {
        this.finishAffinity();
    }

    public void onMessageButtonClick(View view)
    {
        //Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.package.address");
        //if (launchIntent != null) {
        //    startActivity(launchIntent);//null pointer check in case package name was not found
        Intent in = new Intent(Intent.ACTION_VIEW);
        in.setData(Uri.parse("content://messages"));
        startActivity(in);
    }

    public void onPeopleButtonClick(View view){
        startActivity(new Intent(this, ContactsActivity.class));
    }

    public void onTileButtonClick(View view) {
        LinearLayout layoutList = findViewById(R.id.layout_tiles);
        View tileOne = getLayoutInflater().inflate(R.layout.tileone, null, false);
        View tileIndent = getLayoutInflater().inflate(R.layout.tileindent, null, false);
        View tileTwo = getLayoutInflater().inflate(R.layout.tiletwo, null, false);
        layoutList.removeAllViews();
        if(ValuesGlobal.optionTileView != 0){
            layoutList.addView(tileOne);
            layoutList.addView(tileIndent);
            layoutList.addView(tileTwo);
            ValuesGlobal.optionTileView = 0;
            if(ValuesGlobal.isActiveMicButton == 0) {
                ImageView imgTile = (ImageView) findViewById(R.id.imageViewMicMe);
                imgTile.setBackgroundResource(R.drawable.mic_off_fill1_wght700_grad0_opsz48);
            } else {
                ImageView imgTile = (ImageView) findViewById(R.id.imageViewMicMe);
                imgTile.setBackgroundResource(R.drawable.mic_fill1_wght700_grad0_opsz48);
            }
        } else {
            layoutList.addView(tileTwo);
            layoutList.addView(tileIndent);
            layoutList.addView(tileOne);
            ValuesGlobal.optionTileView = 1;
            if(ValuesGlobal.isActiveMicButton == 0) {
                ImageView imgTile = (ImageView) findViewById(R.id.imageViewMicMe);
                imgTile.setBackgroundResource(R.drawable.mic_off_fill1_wght700_grad0_opsz48);
            } else {
                ImageView imgTile = (ImageView) findViewById(R.id.imageViewMicMe);
                imgTile.setBackgroundResource(R.drawable.mic_fill1_wght700_grad0_opsz48);
            }
        }
    }
}