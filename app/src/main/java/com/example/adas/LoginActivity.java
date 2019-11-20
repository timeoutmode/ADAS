package com.example.adas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.adas.Helper.Helpers;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class  LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private Button btnLogin;
    private TextView tvRegister;
    private EditText etUsername, etPassword;
    private ProgressBar progressBar;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initialiseObjects();
        setOnClickListeners();
        setFireBaseListener();

    }

    private void initialiseObjects() {
        btnLogin = findViewById(R.id.btn_login);
        etUsername = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
        tvRegister = findViewById(R.id.tv_register);
        progressBar = findViewById(R.id.progressBar);

        String text = "Register";
        SpannableString ss = new SpannableString(text);
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View view) {
                Intent intent = new Intent(getApplication(), RegisterActivity.class);
                startActivity(intent);
            }
            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(getResources().getColor(R.color.main_text_colour));
                ds.setUnderlineText(true);
            }
        };
        ss.setSpan(clickableSpan, 0, text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvRegister.setText(ss);
        tvRegister.setMovementMethod(LinkMovementMethod.getInstance()); // allows onclick on spanneble string
    }

    private void setOnClickListeners() {
        btnLogin.setOnClickListener(v -> {
            login();
        });
    }

    public void login() {
        Helpers.showDialog(progressBar);
        String email = Objects.requireNonNull(etUsername.getText().toString().trim());
        String password = Objects.requireNonNull(etPassword.getText().toString().trim());
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    Log.e(TAG, "onComplete");
                    Helpers.hideDialog(progressBar);
                })
                .addOnFailureListener(e -> {
                    Log.e(TAG, "onFailure");
                    Helpers.showToast(LoginActivity.this, e.getMessage());
        });
    }

    // set firebase listner
    public void setFireBaseListener() {
        mAuthListener = firebaseAuth -> {
            Log.e(TAG, "onAuthStateChanged!");
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            if (user != null) {
                if (user.isEmailVerified()) {
                    Log.e(TAG, "User Attemped: " + user.getUid());
                    Intent SignedIntent = new Intent(LoginActivity.this, HomeActivity.class);
                    SignedIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(SignedIntent);
                } else {
                    Helpers.showToast(LoginActivity.this,"Please Verify Your Email");
                    Log.e(TAG, "INSIDE MAUTHLISTENER");
                    FirebaseAuth.getInstance().signOut();
                    Helpers.hideDialog(progressBar);
                }
            } else {
                Log.e(TAG, "signed_out");
                Helpers.hideDialog(progressBar);
            }
        };
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseAuth.getInstance().addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            FirebaseAuth.getInstance().removeAuthStateListener(mAuthListener);
        }
    }

}
