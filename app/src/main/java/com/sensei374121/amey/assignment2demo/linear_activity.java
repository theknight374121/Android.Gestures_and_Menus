package com.sensei374121.amey.assignment2demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class linear_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linear_activity);
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
}
