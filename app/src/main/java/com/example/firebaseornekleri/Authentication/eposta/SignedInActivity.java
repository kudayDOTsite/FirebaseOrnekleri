package com.example.firebaseornekleri.Authentication.eposta;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.firebaseornekleri.R;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignedInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signed_in);

        FirebaseUser currentUser =
                FirebaseAuth.getInstance().getCurrentUser();

        if (currentUser == null) {
            startActivity(new Intent(this, FirebaseAuthActivity.class));
            finish();
            return;
        }

        TextView textViewEposta = (TextView) findViewById(R.id.textViewEposta);
        TextView textViewIsim = (TextView) findViewById(R.id.textViewIsim);
        Button btnSingOut = (Button)findViewById(R.id.btnSingOut);
        Button btnDelete = (Button)findViewById(R.id.btnDelete);


        textViewEposta.setText(currentUser.getEmail());
        textViewIsim.setText(currentUser.getDisplayName());


        btnSingOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteAccount();
            }
        });


    }


    public void signOut() {
        AuthUI.getInstance()
                .signOut(this)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            startActivity(new Intent(
                                    SignedInActivity.this,
                                    FirebaseAuthActivity.class));
                            finish();
                        } else {
                            // Report error to user
                        }
                    }
                });
    }


    public void deleteAccount() {
        AuthUI.getInstance()
                .delete(this)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            startActivity(new Intent(SignedInActivity.this,
                                    FirebaseAuthActivity.class));
                            finish();
                        } else {
                            // Notify user of error
                        }
                    }
                });
    }
}