package com.james.sodel;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class ActivityRegister extends AppCompatActivity implements View.OnClickListener {
    private EditText username, Password;
    private ProgressBar progressbar;
    protected FirebaseAuth firebaseAuth;
    public Button btn_login;
    private Button pass_reset;
    private Button register_user;
    String email, password;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().setTitle("Register ");
        username = findViewById(R.id.username);
        Password = findViewById(R.id.pass);
        btn_login = findViewById(R.id.bt_login);
        pass_reset = findViewById(R.id.bt_reset_password);
        progressbar = findViewById(R.id.loading);
        register_user = findViewById(R.id.bt_register);
        btn_login.setOnClickListener(this);
        pass_reset.setOnClickListener(this);
        register_user.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_reset_password:
                resetPass();
                break;
            case R.id.bt_login:
                authenticateUsers();
                break;
            case R.id.bt_register:
                user_reg();
                break;
        }
    }

    public void resetPass() {
        startActivity(new Intent(getApplicationContext(), ActivityPasswordReset.class));
    }

    public void authenticateUsers() {
        startActivity(new Intent(getApplicationContext(), ActivityLogin.class));
    }

    public void user_reg() {
        email = username.getText().toString().trim();
        password = Password.getText().toString().trim();
        firebaseAuth = FirebaseAuth.getInstance();

        progressbar.setVisibility(View.VISIBLE);
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (password.length() < 6) {
            Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
            return;

        }

        //create user
        progressbar.setTitle("Creating New Account");
        progressbar.setMessage("Please wait, while we are creating new account for you...");
        progressbar.setCanceledOnTouchOutside(true);
     progressbar.show();
        firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(ActivityRegister.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Toast.makeText(ActivityRegister.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                        progressbar.setVisibility(View.GONE);

                        if (task.isSuccessful()) {
                            startActivity(new Intent(ActivityRegister.this, MainActivity.class));
                            finish();
                        } else {

                            Toast.makeText(ActivityRegister.this, "Authentication failed." + task.getException(),
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });


    }
}
