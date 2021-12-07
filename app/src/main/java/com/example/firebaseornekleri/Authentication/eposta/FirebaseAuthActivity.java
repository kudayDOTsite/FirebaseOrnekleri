package com.example.firebaseornekleri.Authentication.eposta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.firebaseornekleri.R;
import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;

public class FirebaseAuthActivity extends AppCompatActivity {
    private FirebaseAuth auth;
    private static final int REQUEST_CODE = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_auth);

        auth = FirebaseAuth.getInstance();

        if (auth.getCurrentUser() != null) {
            startActivity(new Intent(this, SignedInActivity.class));
            finish();
        } else {
            authenticateUser();
        }
    }

    private void authenticateUser() {
        startActivityForResult(
                AuthUI.getInstance().createSignInIntentBuilder()
                        .setIsSmartLockEnabled(false)
                        .build(),
                REQUEST_CODE);
    }

}