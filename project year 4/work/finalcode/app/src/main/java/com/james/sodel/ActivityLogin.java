package com.james.sodel;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
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

public class ActivityLogin extends AppCompatActivity implements View.OnClickListener {
    private EditText username, Password;
    private ProgressBar progressbar;
    private FirebaseAuth firebaseAuth;
    public Button bt_login;
    private Button pass_reset;
     protected Button reg_ruser;
    String email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*pass_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ActivityLogin.this, ActivityPasswordReset.class));
            }

        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = username.getText().toString();
                final String password = Password.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                progressbar.setVisibility(View.VISIBLE);

                firebaseAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(ActivityLogin.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                progressbar.setVisibility(View.GONE);
                                if (!task.isSuccessful()) {
                                    // there was an error
                                    if (Password.length() < 6) {
                                        Password.setError(getString(R.string.minimum_password));
                                    } else {
                                        Toast.makeText(ActivityLogin.this, getString(R.string.auth_failed), Toast.LENGTH_LONG).show();
                                    }
                                } else {
                                    Intent intent = new Intent(ActivityLogin.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        });
            }
        });
        case R.id.btn_login:
        Toast.makeText(getApplicationContext(),"ty log",Toast.LENGTH_LONG).show();
                ?authenticateUsers();
*/
        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() != null) {
            startActivity(new Intent(ActivityLogin.this, MainActivity.class));
            finish();
        }
        setContentView(R.layout.login_activity);
        getSupportActionBar().setTitle("Login ");
        username = findViewById(R.id.username);
        Password = findViewById(R.id.password);
        bt_login = findViewById(R.id.btn_login);
        pass_reset = findViewById(R.id.btn_reset_password);
        progressbar = findViewById(R.id.loading);
        bt_login.setOnClickListener(this);
        pass_reset.setOnClickListener(this);
        reg_ruser.setOnClickListener(this);
        firebaseAuth = FirebaseAuth.getInstance();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_reset_password:
                resetPass();
                break;
            case R.id.btn_register:
                user_reg();
                break;
            case R.id.btn_login:
                authenticateUsers();
                break;
        }
    }

    public void resetPass() {
        startActivity(new Intent(getApplicationContext(), ActivityPasswordReset.class));
    }

    public void user_reg() {
        startActivity(new Intent(getApplicationContext(), ActivityRegister.class));
    }

    public void authenticateUsers() {

        firebaseAuth = FirebaseAuth.getInstance();
        if (TextUtils.isEmpty(email) && TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), "Enter valid details!", Toast.LENGTH_SHORT).show();
            return;
        }

       /* if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
            return;
        }*/
        email = username.getText().toString().trim();
        password = Password.getText().toString().trim();

        progressbar.setVisibility(View.VISIBLE);

        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(ActivityLogin.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        progressbar.setVisibility(View.GONE);
                        /*if (!task.isSuccessful()) {
                            // there was an error
                            if (Password.length() < 6) {
                                Password.setError(getString(R.string.minimum_password));
                            } else {
                                Toast.makeText(ActivityLogin.this, getString(R.string.auth_failed), Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Intent intent = new Intent(ActivityLogin.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }*/
                        if (task.isSuccessful()) {
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        } else {
                            Toast.makeText(ActivityLogin.this, getString(R.string.auth_failed), Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}


