package work.icu007.learnactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class LearnLayout extends AppCompatActivity {

    Button btnShowCount;
    TextView showNumber;

    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.relative_layout);
        showNumber = findViewById(R.id.textShowNumber);
        btnShowCount = findViewById(R.id.btnShowCount);
    }

    public void changeColor(View view){
        count ++;
        if (count % 2 == 0){
            showNumber.setBackgroundColor(getResources().getColor(R.color.teal_700));
            btnShowCount.setBackgroundColor(getResources().getColor(R.color.purple_500));
        }else{
            showNumber.setBackgroundColor(getResources().getColor(R.color.purple_500));
            btnShowCount.setBackgroundColor(getResources().getColor(com.google.android.material.R.color.material_deep_teal_500));
        }
        if (showNumber != null){
            showNumber.setText(Integer.toString(count));
        }

    }

    public void showToast(View view) {
        //count++;

        Toast toast = Toast.makeText(this,"the count is " + count , Toast.LENGTH_SHORT);
        toast.show();
    }
}