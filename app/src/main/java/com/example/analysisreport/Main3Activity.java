package com.example.analysisreport;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.analysisreport.SharePreference.SharePreference;

public class Main3Activity extends AppCompatActivity {
    private TextView iai;
    private EditText lahd;
    private Button af;

    SharePreference session;
    private String KEY_NAME = "TambakUdang";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        session = new SharePreference(Main3Activity.this.getApplicationContext());

        lahd = findViewById(R.id.editText);
        af = findViewById(R.id.button);
        iai = findViewById(R.id.textView);
        String Liui = session.getDatas();
        iai.setText(Liui);


        af.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String safa = String.valueOf(lahd.getText());
                session.setDatas(safa);
                lahd.setText("");
                String Liui = session.getDatas();
                iai.setText(Liui);
            }
        });


    }
}
