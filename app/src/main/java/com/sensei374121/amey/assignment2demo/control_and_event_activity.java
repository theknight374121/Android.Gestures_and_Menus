package com.sensei374121.amey.assignment2demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Toast;
import java.util.HashMap;


public class control_and_event_activity extends AppCompatActivity {

    int image_counter=0;
    MovieData moviedata = null;

    private GestureDetectorCompat mDetector;

    @Override
    public boolean onTouchEvent(MotionEvent event){
        this.mDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mymenu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.home:
                startActivity(new Intent(this,MainActivity.class));
                return true;
            case R.id.linear:
                Intent intent = new Intent(this, linear_activity.class);
                startActivity(intent);
                return true;
            case R.id.relative:
                startActivity(new Intent(this,relative_activity.class));
                return true;
            case R.id.grid:
                startActivity(new Intent(this, grid_activity.class));
                return true;
            case R.id.control_and_event:
                startActivity(new Intent(this, control_and_event_activity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.control_and_event_activity);



        final SeekBar myseekbar = (SeekBar) findViewById(R.id.myseekbar);
        final ImageView displayimage = (ImageView) findViewById(R.id.myimage);
        moviedata = new MovieData();
        HashMap movie = moviedata.getItem(image_counter);
        int imageid = (Integer)movie.get("image");

        ViewGroup.LayoutParams params;

        displayimage.setImageResource(imageid);
        params = displayimage.getLayoutParams();
        final int og_width=params.width;
        final int og_height=params.height;
        /*
        Toast.makeText(control_and_event_activity.this, "VAlue of og width"+String.valueOf(og_width), Toast.LENGTH_SHORT).show();
        params.width = og_width/2;
        Toast.makeText(control_and_event_activity.this, "VAlue of params width"+String.valueOf(params.width), Toast.LENGTH_SHORT).show();
        params.height = og_height/2;
        */
        displayimage.setLayoutParams(params);


        mDetector = new GestureDetectorCompat(this, new GestureDetector.SimpleOnGestureListener(){
        @Override
        public boolean onDown(MotionEvent event){
            return true;
        }
            @Override
        public boolean onFling(MotionEvent event1, MotionEvent event2, float velocityX, float velocityY){
                boolean result=false;
                float x1=event1.getX();
                float x2=event2.getX();
                float y1=event1.getY();
                float y2=event2.getY();
               // Toast.makeText(control_and_event_activity.this,"value of x1="+String.valueOf(x1),Toast.LENGTH_SHORT).show();
              //  Toast.makeText(control_and_event_activity.this,"value of x2="+String.valueOf(x2),Toast.LENGTH_SHORT).show();
               // Toast.makeText(control_and_event_activity.this,"value of y1="+String.valueOf(y1),Toast.LENGTH_SHORT).show();
              //  Toast.makeText(control_and_event_activity.this,"value of y2="+String.valueOf(y2),Toast.LENGTH_SHORT).show();
                float diffx=x2-x1;
                float diffy=y2-y1;
              //  Toast.makeText(control_and_event_activity.this,"value of diffx="+String.valueOf(diffx),Toast.LENGTH_SHORT).show();
              //  Toast.makeText(control_and_event_activity.this,"value of diffy="+String.valueOf(diffy),Toast.LENGTH_SHORT).show();
              //  Toast.makeText(control_and_event_activity.this,"value of velocityx="+String.valueOf(velocityX),Toast.LENGTH_SHORT).show();
                if(Math.abs(diffx)>Math.abs(diffy)){
                    if(Math.abs(diffx)>100){
                        if(diffx<0){
                            //right
                            if(image_counter==19){
                                HashMap movie = moviedata.getItem(image_counter);
                                int imageid = (Integer)movie.get("image");
                                displayimage.setImageResource(imageid);
                                Toast.makeText(control_and_event_activity.this,"This is the last image",Toast.LENGTH_SHORT).show();
                            }else{
                                image_counter++;
                                HashMap movie = moviedata.getItem(image_counter);
                                int imageid = (Integer)movie.get("image");
                                displayimage.setImageResource(imageid);
                            }

                        }else{
                            //left
                            if(image_counter==0){
                                HashMap movie = moviedata.getItem(image_counter);
                                int imageid = (Integer)movie.get("image");
                                displayimage.setImageResource(imageid);
                                Toast.makeText(control_and_event_activity.this,"This is the first image.",Toast.LENGTH_SHORT).show();
                            }else{
                                image_counter--;
                                HashMap movie = moviedata.getItem(image_counter);
                                int imageid = (Integer)movie.get("image");
                                displayimage.setImageResource(imageid);
                            }
                        }
                        result= true;
                    }
                }

                return result;
            }
        });
        myseekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
            @Override
            public void onProgressChanged(SeekBar seekbar, int progress,boolean fromUser){
                ViewGroup.LayoutParams params = displayimage.getLayoutParams();
                params.width=progress*7;
                params.height=progress*7;
                displayimage.setLayoutParams(params);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar){

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar){

            }

        });

        displayimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(control_and_event_activity.this, "This is a sample Toast", Toast.LENGTH_SHORT).show();
                Snackbar.make(v, "This is a snackbar message", Snackbar.LENGTH_SHORT).show();

            }
        });
        displayimage.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                myseekbar.setProgress(50);
                ViewGroup.LayoutParams params = displayimage.getLayoutParams();
                params.width = og_width;
                params.height = og_height;
                return true;
            }
        });


    }






}
