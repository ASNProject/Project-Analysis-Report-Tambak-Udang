package com.example.analysisreport.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.analysisreport.R;
import com.example.analysisreport.SharePreference.SharePreference;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Main2Activity extends AppCompatActivity {
private FirebaseAuth mAuth;
private String KEY_NAME = "TambakUdang";
private EditText Username, Password;
private TextView daftar;
SharePreference session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();

        Username = findViewById(R.id.Username);
        Password = findViewById(R.id.Password);
        daftar = findViewById(R.id.daftar);
        String tusername = Username.getText().toString();

        session = new SharePreference((Main2Activity.this.getApplicationContext()));
        String email = session.getEmail();
        String password = session.getPassword();
        Username.setText(email);
        Password.setText(password);

        if (Username.getText().toString().length() > 0 && Password.getText().toString().length() > 0){
            Intent intent = new Intent(Main2Activity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

        else if ((Username.getText().toString().length()==0) && (Password.getText().toString().length()==0)){
            Button login = findViewById(R.id.btnLogin);
            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final String statusname = Username.getText().toString();
                    String setpassword = Password.getText().toString();

                    if (statusname.equals("")){
                        Toast.makeText(Main2Activity.this, "Please Input Email", Toast.LENGTH_SHORT).show();
                    }
                    else if (setpassword.equals("")){
                        Toast.makeText(Main2Activity.this, "Please Input Password", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        mAuth.signInWithEmailAndPassword(statusname, setpassword)
                                .addOnCompleteListener(Main2Activity.this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()){
                                            FirebaseUser user = mAuth.getCurrentUser();

                                            Intent intent = new Intent(Main2Activity.this, MainActivity.class);
                                            startActivity(intent);
                                            String Email = String.valueOf(Username.getText());
                                            String Passwors = String.valueOf(Password.getText());
                                            session.setEmail(Email);
                                            session.setPassword(Passwors);
                                            finish();
                                            // Username.setText("");
                                            // Password.setText("");
                                        }
                                        else {
                                            Toast.makeText(Main2Activity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                    }
                }
            });
        }
        daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent daftar = new Intent(Main2Activity.this, Register.class);
                startActivity(daftar);
                finish();
            }
        });

    }


}
