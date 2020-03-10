package com.example.njoroapp;
//
//import android.app.ProgressDialog;
//import android.content.Intent;
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//import android.os.Bundle;
//import android.text.TextUtils;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.google.android.gms.common.SignInButton;
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.FirebaseApp;
//import com.google.firebase.auth.AuthResult;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//import com.google.firebase.iid.FirebaseInstanceId;
//
//import java.util.HashMap;
//
//public class Sign extends AppCompatActivity {
//    private EditText UserEmail, UserPassword;
//    private TextView txtl, log1;
//    TextView btn11;
//    SignInButton logInButton;
//    private final static int RC_SIGN_IN = 123;
//
//    //private FirebaseAuth mAuth;
//   // private DatabaseReference RootRef;
//    Button btn1;
//    //FirebaseAuth.AuthStateListener mAuthListner;
//    private ProgressDialog loadingBar;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_sign);
//
//        // signInButton = findViewById(R.id.sign_in_button);
//
////        mAuth = FirebaseAuth.getInstance();
////        RootRef = FirebaseDatabase.getInstance().getReference();
//
//
//        InitializeFields();
//        btn11.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(Sign.this, LoginActivity.class));
//            }
//        });
//
//        txtl.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
//            }
//        });
//
//
//        btn1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                CreateNewAccount();
//            }
//        });
//    }
//
//    private void CreateNewAccount() {
//        String email = UserEmail.getText().toString();
//
//        String password = UserPassword.getText().toString();
//
//        if (TextUtils.isEmpty(email)) {
//            Toast.makeText(this, "Please write your email...", Toast.LENGTH_SHORT).show();
//        } else if (TextUtils.isEmpty(password)) {
//            Toast.makeText(this, "Please write your password...", Toast.LENGTH_SHORT).show();
//        } else {
//            loadingBar.setTitle("Create Account");
//            loadingBar.setMessage("Please wait, while we are checking the credentials.");
//            loadingBar.setCanceledOnTouchOutside(false);
//            loadingBar.show();
//
//            ValidateEmail(email, password);
//        }
//    }
//
//
//    private void ValidateEmail(final String email,  final String password) {
//        final DatabaseReference RootRef;
//        RootRef = FirebaseDatabase.getInstance().getReference();
//
//        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                if (!(dataSnapshot.child("users").child(email).exists())) {
//                    HashMap<String, Object> userdataMap = new HashMap<>();
//                    userdataMap.put("email", email);
//                    userdataMap.put("password", password);
//
//
//                    RootRef.child("users").child(email).updateChildren(userdataMap)
//                            .addOnCompleteListener(new OnCompleteListener<Void>() {
//                                @Override
//                                public void onComplete(@NonNull Task<Void> task) {
//                                    if (task.isSuccessful()) {
//                                        Toast.makeText(Sign.this, "Congratulations, your account has been created.", Toast.LENGTH_SHORT).show();
//                                        loadingBar.dismiss();
//
//                                        Intent intent = new Intent(Sign.this, LoginActivity.class);
//                                        startActivity(intent);
//                                    } else {
//                                        loadingBar.dismiss();
//                                        Toast.makeText(Sign.this, "Network Error: Please try again after some time...", Toast.LENGTH_SHORT).show();
//                                    }
//                                }
//                            });
//                } else {
//                    Toast.makeText(Sign.this, "This " + email + " already exists.", Toast.LENGTH_SHORT).show();
//                    loadingBar.dismiss();
//                    Toast.makeText(Sign.this, "Please try again using another email.", Toast.LENGTH_SHORT).show();
//
//                    Intent intent = new Intent(Sign.this, MainActivity.class);
//                    startActivity(intent);
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//    }
//
//
////    @Override
////    protected void onStart() {
////        super.onStart();
////
////        FirebaseUser currentUser = mAuth.getCurrentUser();
////        if (currentUser != null) {
////            SendUserToMainActivity();
////        }
////    }
//
//
//    private void InitializeFields() {
//        btn1 = findViewById(R.id.register_button);
//        UserEmail = (EditText) findViewById(R.id.register_email);
//        UserPassword = (EditText) findViewById(R.id.register_password);
//        btn11 = (TextView) findViewById(R.id.log1);
//        loadingBar = new ProgressDialog(this);
//        txtl = findViewById(R.id.txtl);
//        FirebaseApp.initializeApp(this);
//    }
//
//
////    private void SendUserToLoginActivity() {
////        Intent loginIntent = new Intent(Sign.this, Sign.class);
////        startActivity(loginIntent);
////    }
//}
//
////    private void SendUserToMainActivity() {
////
////        Intent mainIntent = new Intent(Sign.this,MainActivity.class);
////        mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
////        startActivity(mainIntent);
////        finish();
////    }
////
//