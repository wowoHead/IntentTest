package com.example.ssh.intenttest;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button)findViewById(R.id.button);
        editText = (EditText)findViewById(R.id.editText);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String url = "http://"+editText.getText().toString();
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_DEFAULT);
                intent.putExtra("url",url);
                PackageManager packageManager = getPackageManager();
                List<ResolveInfo> resolveInfoList = packageManager.queryIntentActivities(intent,PackageManager.MATCH_ALL);
                Log.i("MainActivity","resolveInfoList size:"+resolveInfoList.size());
                if (resolveInfoList.size() > 0) {
                    String title = "choose application";
                    Intent intentChooser = Intent.createChooser(intent,title);
                    startActivity(intentChooser);
                } else {
                    Toast.makeText(MainActivity.this,"no match activity to start!",Toast.LENGTH_SHORT).show();
                }
                startActivity(intent);
            }
        });

    }
}
