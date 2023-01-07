package work.icu007.learnactivity;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.ActivityResultRegistry;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView textView;

    Intent intent1;
    Intent intent2;

    // launcher 用于 接收处理 从第二个Activity传回来的数据
    // 调用registerForActivityResult()方法用于处理传回来的数据；registerForActivityResult()需要传进来两个参数；第一个 ActivityResultContract，
    // 第二个参数需要传入一个回调 ActivityResultCallback，而我们的数据就是在这里进行处理的；最后返回一个 ActivityResultLauncher 对象
    // 调用 ActivityResultLauncher的 launch方法可以跳转到另一个Activity或者发起权限请求

    ActivityResultLauncher launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if (result.getResultCode() == RESULT_OK) {
                String returnData = result.getData().getStringExtra("data_return");
                textView.setText(returnData);
            }
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 显式启动activity的intent
        intent1 = new Intent(this,SecondActivity.class);
        // 隐式启动activity的intent
        intent2 = new Intent(SecondActivity.ACTION);
        textView = findViewById(R.id.textView);
        // 创建 一个button实例用来启动 SecondActivity
        // 给button注册监听
        findViewById(R.id.btnStartActivity).setOnClickListener(this);
        findViewById(R.id.btnStartActivityName).setOnClickListener(this);
        findViewById(R.id.btnStartActivityResult).setOnClickListener(this);
        findViewById(R.id.btnStartActivityWithData).setOnClickListener(this);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    // 菜单栏按钮处理
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.add_item:
                Toast.makeText(this, "you clicked add", Toast.LENGTH_SHORT).show();
                textView.setText("add");
                break;
            case R.id.remove_item:
                Toast.makeText(this, "you clicked remove", Toast.LENGTH_SHORT).show();
                textView.setText("remove");
                break;
        }
        return true;
    }

    // 已废弃的startActivityForResult()方法

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        switch (requestCode) {
//            case 1:
//                if (requestCode == RESULT_OK){
//                    String returnData = data.getStringExtra("data_return");
//                    textView.setText(returnData);
//                }
//
//        }
//    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
                // 显式启动activity
            case R.id.btnStartActivity:
                startActivity(intent1);
                break;
                //隐式启动activity
            case R.id.btnStartActivityName:
                startActivity(intent2);
                break;
                // 启动activity并传递数据给下一个activity
            case R.id.btnStartActivityWithData:
                String data = "Hello Second Activity";
                intent1.putExtra("extra_data",data);
                startActivity(intent1);
                break;
                // 从下一个activity 返回数据
            case R.id.btnStartActivityResult:
                Intent intent = new Intent(MainActivity.this,SecondActivity.class);
//                startActivityForResult(intent,1);
                launcher.launch(intent);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + v.getId());
        }
    }
}