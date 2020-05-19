package com.example.analysisreport;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.analysisreport.Model.Request;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText etUsername, etEmail, etPassowrd;
    private DatabaseReference database;
    private String sPid, sPnama, sPemail, sPdesk;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
        etEmail = findViewById(R.id.email);
        etPassowrd = findViewById(R.id.password);
        etUsername = findViewById(R.id.username);

        sPnama = getIntent().getStringExtra("title");
        sPemail = getIntent().getStringExtra("email");
        sPdesk = getIntent().getStringExtra("desk");

        etUsername.setText(sPnama);
        etEmail.setText(sPemail);
        etPassowrd.setText(sPdesk);

        database = FirebaseDatabase.getInstance().getReference();

        Button btnRegister = findViewById(R.id.buttonregister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString();
                String passwprd = etPassowrd.getText().toString();
                final String username = etUsername.getText().toString();

                if (email.equals("")){
                    etEmail.setError("Email is required");
                    etEmail.requestFocus();
                    return;
                }
                else if (passwprd.equals("")){
                    etPassowrd.setError("Email is required");
                    etPassowrd.requestFocus();
                }
                else if (username.equals("")){
                    etUsername.setError("Email is required");
                    etUsername.requestFocus();
                }

                else {
                    mAuth.createUserWithEmailAndPassword(email, passwprd)
                            .addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                               if (task.isSuccessful()){
                                   FirebaseUser user = mAuth.getCurrentUser();

                                   Toast.makeText(Register.this, "Register Successfully",
                                           Toast.LENGTH_SHORT).show();
                                   if (user !=null){
                                       UserProfileChangeRequest profile = new UserProfileChangeRequest.Builder()
                                               .setDisplayName(username)
                                               .build();

                                       user.updateProfile(profile)
                                               .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                   @Override
                                                   public void onComplete(@NonNull Task<Void> task) {
                                                       if (task.isSuccessful()){
                                                           Toast.makeText(Register.this, "Profile Updated", Toast.LENGTH_SHORT).show();
                                                       }
                                                   }
                                               });
                                   }
                               }
                               else {
                                   if (task.getException() instanceof FirebaseAuthUserCollisionException){
                                       Toast.makeText(getApplicationContext(), "You are already registered", Toast.LENGTH_SHORT).show();
                                   }
                                   else {
                                       Toast.makeText(Register.this, "Register Failed",
                                               Toast.LENGTH_SHORT).show();
                                   }
                               }
                                }
                            });
                    sumbmitUser(new Request(
                            username.toLowerCase(),
                            email.toLowerCase(),
                            passwprd.toLowerCase()));
                }



            }
        });
    }

    private void sumbmitUser(Request request){
        String username = etUsername.getText().toString();
        database.child("Data User")
                .child(username)
                .setValue(request)
                .addOnSuccessListener(this, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        etUsername.setText("");
                        etEmail.setText("");
                        etPassowrd.setText("");

                        Toast.makeText(Register.this,
                                "Data Berhasil ditambahkan",
                                Toast.LENGTH_SHORT).show();
                    }
                });

    }
}
