package com.example.mirza.testfirebase;

import android.app.ProgressDialog;
import android.icu.text.UnicodeSetSpanner;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.net.PasswordAuthentication;

public class FirstActivity extends AppCompatActivity implements View.OnClickListener {

    private Button b1;
    private EditText Email, Password;
    private TextView text;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        progressDialog = new ProgressDialog(this);
        firebaseAuth = FirebaseAuth.getInstance();

        b1 = findViewById(R.id.btnRegister);
        Email = findViewById(R.id.textEmail);
        Password = findViewById(R.id.textPassword);
        text = findViewById(R.id.textViewSignin);

        b1.setOnClickListener(this);
        text.setOnClickListener(this);
    }


    private void registerUser() {

        String email = Email.getText().toString().trim();
        String password = Password.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Please Enter Email ", Toast.LENGTH_SHORT).show();
            return;
            //empty  email
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please Enter Password ", Toast.LENGTH_SHORT).show();
            return;
            // emptyu password
        }
        progressDialog.setMessage("Registering user ... ");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // daregistrirda
                            Toast.makeText(FirstActivity.this, "Registered Successfully", Toast.LENGTH_LONG).show();

                        } else {
                            Toast.makeText(FirstActivity.this, "Registered NOT Successfully", Toast.LENGTH_LONG).show();
                        }
                    }
                });


    }

    @Override
    public void onClick(View view) {
        if (view == b1) {
            registerUser();
        }
        if (view == text) {

        }

    }
}
