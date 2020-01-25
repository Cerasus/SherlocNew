package com.oleksiisaienko.sherlocnew;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    private static final int CHOOSE_THIEF = 0;
    public static final String ACTION_TRAFFIC_LIGHT = "com.oleksiisaienko.sherlocnew.TrafficLightActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public  void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonChoose :
                Intent questionIntent = new Intent(MainActivity.this, ChooseActivity.class);
                startActivityForResult(questionIntent, CHOOSE_THIEF);
                break;
            case R.id.buttonTrafficLight :
                startActivity(new Intent(ACTION_TRAFFIC_LIGHT));
                break;
            case R.id.buttonCounterCrow :
                startActivity(new Intent(MainActivity.this, CounterCrowActivity.class));
                break;
            case R.id.buttonAboutProgram :
                startActivity(new Intent(MainActivity.this, AboutProgramActivity.class));
                break;
            case R.id.activityButtonShowMessage :
                Toast toast = Toast.makeText(getApplicationContext(),R.string.mainActivityToastMessage, Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP, 0, 0);

                LinearLayout toastContainer = (LinearLayout) toast.getView();
                ImageView imageView = new ImageView(getApplicationContext());
                imageView.setImageResource(R.drawable.scary_images);

                toastContainer.addView(imageView, 0);
                toastContainer.setBackgroundColor(Color.TRANSPARENT);
                toast.show();
                break;
            case R.id.activityButtonShowMessage2 :
                LayoutInflater inflater = getLayoutInflater();
                View layout = inflater.inflate(R.layout.custom_layout_toast, (ViewGroup) findViewById(R.id.toast_layout));

                Toast toast1 = new Toast(getApplicationContext());
                toast1.setGravity(Gravity.TOP, 0 , 0);
                toast1.setDuration(Toast.LENGTH_SHORT);

                toast1.setView(layout);
                toast1.show();
                break;
            case R.id.buttonDialog :
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Dialog Window");
                builder.setMessage("Покормить кота?");
                builder.setPositiveButton("OK",null);
                builder.setNegativeButton("Cancel", null);
                builder.show();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        TextView infoTextView = findViewById(R.id.textViewInfoMainActivity);
        if (requestCode == CHOOSE_THIEF) {
            if (resultCode == RESULT_OK) {
                String thiefName = data.getStringExtra(ChooseActivity.THIEF);
                infoTextView.setText(thiefName);
            } else {
                infoTextView.setText("не сделан выбор");
            }
        }
    }
}
