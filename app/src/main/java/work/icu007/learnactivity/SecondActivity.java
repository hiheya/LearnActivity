package work.icu007.learnactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    public static final String ACTION = "work.icu007.learnactivity.ACTION_START";
    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Intent intent = getIntent();
        String data = intent.getStringExtra("extra_data");
        TextView textView = findViewById(R.id.textView1);
        textView.setText(data);
        Button button = findViewById(R.id.btnBackData);
//        Button btnShowCount = findViewById(R.id.btnShowCount);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent();
                intent1.putExtra("data_return","I am from SecondActivity");
                setResult(RESULT_OK,intent1);
                finish();
            }
        });
    }

    public void showToast(View view) {
        count++;
        Toast toast = Toast.makeText(this,"the count is " + count , Toast.LENGTH_SHORT);
        toast.show();
    }
}