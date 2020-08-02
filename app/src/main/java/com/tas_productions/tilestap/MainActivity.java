package com.tas_productions.tilestap;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.CountDownTimer;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements  View.OnClickListener{
private ImageView iv_11,iv_12,iv_13,iv_14,
                  iv_21,iv_22,iv_23,iv_24,
                  iv_31,iv_32,iv_33,iv_34,
                  iv_41,iv_42,iv_43,iv_44,
                  iv_51,iv_52,iv_53,iv_54;
private Button play,wranking,music;
private TextView time,best,score;
private Random random;
MediaPlayer mediaPlayer,songPlayer;
int frameImage,pawInFrameImage,tapImage,emptyImage;
private int rockLocationRow1,
        rockLocationRow2,
        rockLocationRow3,
        rockLocationRow4,
        rockLocationRow5;
private AlertDialog.Builder builder;
int currentscore = 0,bestscore = 0,ct_songs = 0;
private LinearLayout gridLayout,bottomLayout;
private DatabaseReference databaseReference;
//doing today
   String userName;
  int[] songs = {R.raw.relaxing_background,R.raw.relaxing_ashiqui,R.raw.relaxing_piano,R.raw.relaxing_yalili};
   //
CountDownTimer timer;
//background
    RelativeLayout relativeLayout;
      @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
          songPlayer = songPlayer.create(MainActivity.this,songs[ct_songs]);
          songPlayer.start();
          songPlayer.setLooping(true);



          final SharedPreferences sharedPreferences = this.getSharedPreferences("GamePref", Context.MODE_PRIVATE);
          final SharedPreferences sharedPreferences1 = this.getSharedPreferences("userPref",Context.MODE_PRIVATE);
          bestscore = sharedPreferences.getInt("bestScore",0);
          userName = sharedPreferences1.getString("userName","UNKOWN");
          if(userName.equals("UNKOWN")){
              final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
              final EditText et = new EditText(this);
              et.setHint("Enter your UserName");
              et.setTextSize(24);
              alertDialogBuilder.setView(et);
              alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                  @Override
                  public void onClick(DialogInterface dialog, int which) {
                      if (!et.getText().toString().trim().isEmpty()) {
                          userName = et.getText().toString().trim();
                          SharedPreferences sharedPreferences1  = getSharedPreferences("userPref",0);
                          SharedPreferences.Editor editor  = sharedPreferences1.edit();
                          editor.putString("userName",userName).commit();
                          editor.apply();
                          dialog.dismiss();
                      }
                  }
              });
              alertDialogBuilder.setCancelable(false);
              AlertDialog dialog = alertDialogBuilder.create();
              dialog.show();
          }
          //TODO : YES I DID IT
          wranking = findViewById(R.id.wranking);
          music = findViewById(R.id.musicID);
          iv_11 = findViewById(R.id.iv_11);
          iv_21 = findViewById(R.id.iv_21);
          iv_31 = findViewById(R.id.iv_31);
          iv_41 = findViewById(R.id.iv_41);
          iv_51 = findViewById(R.id.iv_51);
          iv_12 = findViewById(R.id.iv_12);
          iv_22 = findViewById(R.id.iv_22);
          iv_32 = findViewById(R.id.iv_32);
          iv_42 = findViewById(R.id.iv_42);
          iv_52 = findViewById(R.id.iv_52);
          iv_13 = findViewById(R.id.iv_13);
          iv_23 = findViewById(R.id.iv_23);
          iv_33 = findViewById(R.id.iv_33);
          iv_43 = findViewById(R.id.iv_43);
          iv_53 = findViewById(R.id.iv_53);
          iv_14 = findViewById(R.id.iv_14);
          iv_24 = findViewById(R.id.iv_24);
          iv_34 = findViewById(R.id.iv_34);
          iv_44 = findViewById(R.id.iv_44);
          iv_54 = findViewById(R.id.iv_54);
        play = findViewById(R.id.bottombarPlayID);
        relativeLayout = findViewById(R.id.mainBackgID);
        time = findViewById(R.id.topbarTimeID);
          gridLayout=findViewById(R.id.include2ID);
          bottomLayout=findViewById(R.id.include3ID);
          time.setText("TIME : " + millisToTime(30000));
          score = findViewById(R.id.topbarScoreID);
          score.setText("SCORE : "+currentscore);
          best = findViewById(R.id.topbarBestID);
          best.setText("BEST : "+bestscore);
          databaseReference = FirebaseDatabase.getInstance().getReference("scores");
          random  = new Random();
          loadImages();
          timer = new CountDownTimer(30000,500) {
              @Override
              public void onTick(long millisUntilFinished) {
                  time.setText("TIME : " + millisToTime(millisUntilFinished));
              }

              @Override
              public void onFinish() {
                  wranking.setEnabled(true);
                  wranking.setVisibility(View.VISIBLE);
                  music.setEnabled(true);
                  music.setVisibility(View.VISIBLE);
                  relativeLayout.setBackgroundResource(R.drawable.back);
                  gridLayout.setBackgroundResource(R.drawable.back);
                  bottomLayout.setBackgroundResource(R.drawable.back);
                  time.setText("TIME : " + millisToTime(0));
                  iv_31.setEnabled(false);
                  iv_32.setEnabled(false);
                  iv_33.setEnabled(false);
                  iv_34.setEnabled(false);
                  play.setVisibility(View.VISIBLE);

                  iv_11.setImageResource(emptyImage);
                  iv_12.setImageResource(emptyImage);
                  iv_13.setImageResource(emptyImage);
                  iv_14.setImageResource(emptyImage);


                  iv_21.setImageResource(emptyImage);
                  iv_22.setImageResource(emptyImage);
                  iv_23.setImageResource(emptyImage);
                  iv_24.setImageResource(emptyImage);


                  iv_31.setImageResource(emptyImage);
                  iv_32.setImageResource(emptyImage);
                  iv_33.setImageResource(emptyImage);
                  iv_34.setImageResource(emptyImage);

                  iv_41.setImageResource(emptyImage);
                  iv_42.setImageResource(emptyImage);
                  iv_43.setImageResource(emptyImage);
                  iv_44.setImageResource(emptyImage);


                  iv_51.setImageResource(emptyImage);
                  iv_52.setImageResource(emptyImage);
                  iv_53.setImageResource(emptyImage);
                  iv_54.setImageResource(emptyImage);

                  Toast.makeText(MainActivity.this, "Game Over!", Toast.LENGTH_SHORT).show();

                  if(currentscore>bestscore){
                     bestscore=currentscore;
                     best.setText("BEST : "+bestscore);
                     mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.applause8);
                     mediaPlayer.start();
                     SharedPreferences.Editor editor  = sharedPreferences.edit();
                     editor.putInt("bestScore",bestscore).commit();
                     editor.apply();
                  }
              }
          };
          iv_31.setOnClickListener(this);
          iv_32.setOnClickListener(this);
          iv_33.setOnClickListener(this);
          iv_34.setOnClickListener(this);
          play.setOnClickListener(this);
         wranking.setOnClickListener(this);
         music.setOnClickListener(this);

      }

    @Override
    protected void onDestroy() {
          if(mediaPlayer.isPlaying() || songPlayer.isPlaying())
          {
              mediaPlayer.stop();
              songPlayer.stop();
              mediaPlayer = null;
              songPlayer = null;
          }

        super.onDestroy();
    }

    private void loadImages() {
          frameImage = R.drawable.frameimage;
          emptyImage = R.drawable.emptyimage;
          pawInFrameImage = R.drawable.pawinframeimage;
          tapImage = R.drawable.tapimage;
    }

    private int millisToTime(long i) {
          return (int) i/1000;
    }

    @Override
    public void onClick(View v) {
      switch(v.getId()){
          case R.id.iv_31:
              if(rockLocationRow3 == 1){
                  continueGame();
                  if(mediaPlayer != null && mediaPlayer.isPlaying())
                      mediaPlayer.stop();


                  mediaPlayer = MediaPlayer.create(this,R.raw.fart);

                  mediaPlayer.start();
              }else{
                  endGame();
              }
              break;
          case R.id.iv_32:
              if(rockLocationRow3 == 2){
                  continueGame();
                  if(mediaPlayer != null && mediaPlayer.isPlaying())
                      mediaPlayer.stop();


                  mediaPlayer = MediaPlayer.create(this,R.raw.fart);
                  mediaPlayer.start();
              }else{
                  endGame();
              }
              break;
          case R.id.iv_33:
              if(rockLocationRow3 == 3){
                  continueGame();
                  if(mediaPlayer != null && mediaPlayer.isPlaying())
                      mediaPlayer.stop();

                  mediaPlayer = MediaPlayer.create(this,R.raw.fart);
                  mediaPlayer.start();
              }else{
                  endGame();
              }
              break;
          case R.id.iv_34:
              if(rockLocationRow3 == 4){
                  continueGame();
                  if(mediaPlayer != null && mediaPlayer.isPlaying())
                      mediaPlayer.stop();


                  mediaPlayer = MediaPlayer.create(this,R.raw.fart);
                  mediaPlayer.start();
              }else{
                  endGame();
              }
              break;
          case R.id.bottombarPlayID:
              intigame();
              break;
         case R.id.wranking:
              startActivity(new Intent(MainActivity.this,WrankingActivity.class));
              break;
          case R.id.musicID:
              if(songPlayer.isPlaying())
                  songPlayer.stop();
              ct_songs++;
              songPlayer = songPlayer.create(MainActivity.this,songs[ct_songs%4]);
              songPlayer.start();
              songPlayer.setLooping(true);
              break;
      }
    }

    private void intigame() {
        wranking.setEnabled(false);
        wranking.setVisibility(View.INVISIBLE);
        music.setEnabled(false);
        music.setVisibility(View.INVISIBLE);
          if(mediaPlayer!=null && mediaPlayer.isPlaying()){
              mediaPlayer.stop();
          }
          songPlayer.start();
          iv_31.setEnabled(true);
        iv_32.setEnabled(true);
        iv_33.setEnabled(true);
        iv_34.setEnabled(true);
        play.setVisibility(View.INVISIBLE);

        currentscore=0;
        score.setText("SCORE : "+currentscore);

        best.setText("BEST : " + bestscore);
        timer.start();

        rockLocationRow4 = random.nextInt(4)+1;
        setRockLocation(rockLocationRow4,4);

        rockLocationRow3 = random.nextInt(4)+1;
        setRockLocation(rockLocationRow3,3);

        rockLocationRow2 = random.nextInt(4) + 1;
        setRockLocation(rockLocationRow2,2);

        rockLocationRow1 = random.nextInt(4) +1;
        setRockLocation(rockLocationRow1,1);
    }

    private void endGame() {
        wranking.setEnabled(true);
        wranking.setVisibility(View.VISIBLE);
        music.setEnabled(true);
        music.setVisibility(View.VISIBLE);
        relativeLayout.setBackgroundResource(R.drawable.back);
        gridLayout.setBackgroundResource(R.drawable.back);
        bottomLayout.setBackgroundResource(R.drawable.back);
        iv_31.setEnabled(false);
        iv_32.setEnabled(false);
        iv_33.setEnabled(false);
        iv_34.setEnabled(false);
        play.setVisibility(View.VISIBLE);

        iv_11.setImageResource(emptyImage);
        iv_12.setImageResource(emptyImage);
        iv_13.setImageResource(emptyImage);
        iv_14.setImageResource(emptyImage);


        iv_21.setImageResource(emptyImage);
        iv_22.setImageResource(emptyImage);
        iv_23.setImageResource(emptyImage);
        iv_24.setImageResource(emptyImage);


        iv_31.setImageResource(emptyImage);
        iv_32.setImageResource(emptyImage);
        iv_33.setImageResource(emptyImage);
        iv_34.setImageResource(emptyImage);

        iv_41.setImageResource(emptyImage);
        iv_42.setImageResource(emptyImage);
        iv_43.setImageResource(emptyImage);
        iv_44.setImageResource(emptyImage);


        iv_51.setImageResource(emptyImage);
        iv_52.setImageResource(emptyImage);
        iv_53.setImageResource(emptyImage);
        iv_54.setImageResource(emptyImage);

        Toast.makeText(MainActivity.this, "Game Over!", Toast.LENGTH_SHORT).show();
        timer.cancel();
        time.setText("TIME : " + 0);
         if(currentscore>85)
        {
             if(!(bestscore>currentscore+30))
            {

                            String id  = databaseReference.push().getKey();
                            Scores mYscore  = new Scores(userName,currentscore);
                            databaseReference.child(id).setValue(mYscore);

            }

        }

        if(currentscore>bestscore){
            bestscore=currentscore;
            best.setText("BEST : "+bestscore);

            SharedPreferences sharedPreferences  = getSharedPreferences("GamePref",0);
            SharedPreferences.Editor editor  = sharedPreferences.edit();
            editor.putInt("bestScore",bestscore).commit();
            editor.apply();
        }
    }

    private void continueGame() {
        wranking.setEnabled(false);
        wranking.setVisibility(View.INVISIBLE);
        music.setEnabled(false);
        music.setVisibility(View.INVISIBLE);
          rockLocationRow5 = rockLocationRow4;
          setRockLocation(rockLocationRow5,5);

        rockLocationRow4 = rockLocationRow3;
        setRockLocation(rockLocationRow4,4);

        rockLocationRow3 = rockLocationRow2;
        setRockLocation(rockLocationRow3,3);

        rockLocationRow2 = rockLocationRow1;
        setRockLocation(rockLocationRow2,2);

        rockLocationRow1 = random.nextInt(4) + 1;
        setRockLocation(rockLocationRow1,1);

        currentscore++;
        score.setText("SCORE : "+currentscore);
        if(currentscore>95){
         relativeLayout.setBackgroundResource(R.drawable.back3);
            gridLayout.setBackgroundResource(R.drawable.back3);
            bottomLayout.setBackgroundResource(R.drawable.back3);
        }else if(currentscore>50){
            relativeLayout.setBackgroundResource(R.drawable.back2);
            gridLayout.setBackgroundResource(R.drawable.back2);
            bottomLayout.setBackgroundResource(R.drawable.back2);
        }
    }

    private void setRockLocation(int place, int row) {
          if(row==1){
              iv_11.setImageResource(emptyImage);
              iv_12.setImageResource(emptyImage);
              iv_13.setImageResource(emptyImage);
              iv_14.setImageResource(emptyImage);

              switch(place){
                  case 1:
                      iv_11.setImageResource(pawInFrameImage);
                      break;
                  case 2:
                      iv_12.setImageResource(pawInFrameImage);
                      break;
                  case 3:
                      iv_13.setImageResource(pawInFrameImage);
                      break;
                  case 4:
                      iv_14.setImageResource(pawInFrameImage);
                      break;
              }
          }
        if(row==2){
            iv_21.setImageResource(emptyImage);
            iv_22.setImageResource(emptyImage);
            iv_23.setImageResource(emptyImage);
            iv_24.setImageResource(emptyImage);

            switch(place){
                case 1:
                    iv_21.setImageResource(pawInFrameImage);
                    break;
                case 2:
                    iv_22.setImageResource(pawInFrameImage);
                    break;
                case 3:
                    iv_23.setImageResource(pawInFrameImage);
                    break;
                case 4:
                    iv_24.setImageResource(pawInFrameImage);
                    break;
            }
        }
        if(row==3){
            iv_31.setImageResource(emptyImage);
            iv_32.setImageResource(emptyImage);
            iv_33.setImageResource(emptyImage);
            iv_34.setImageResource(emptyImage);

            switch(place){
                case 1:
                    iv_31.setImageResource(tapImage);
                    break;
                case 2:
                    iv_32.setImageResource(tapImage);
                    break;
                case 3:
                    iv_33.setImageResource(tapImage);
                    break;
                case 4:
                    iv_34.setImageResource(tapImage);
                    break;
            }
        }
        if(row==4){
            iv_41.setImageResource(emptyImage);
            iv_42.setImageResource(emptyImage);
            iv_43.setImageResource(emptyImage);
            iv_44.setImageResource(emptyImage);

            switch(place){
                case 1:
                    iv_41.setImageResource(frameImage);
                    break;
                case 2:
                    iv_42.setImageResource(frameImage);
                    break;
                case 3:
                    iv_43.setImageResource(frameImage);
                    break;
                case 4:
                    iv_44.setImageResource(frameImage);
                    break;
            }
        }
        if(row==5){
            iv_51.setImageResource(emptyImage);
            iv_52.setImageResource(emptyImage);
            iv_53.setImageResource(emptyImage);
            iv_54.setImageResource(emptyImage);

            switch(place){
                case 1:
                    iv_51.setImageResource(frameImage);
                    break;
                case 2:
                    iv_52.setImageResource(frameImage);
                    break;
                case 3:
                    iv_53.setImageResource(frameImage);
                    break;
                case 4:
                    iv_54.setImageResource(frameImage);
                    break;
            }
        }
    }
}
