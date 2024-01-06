package com.example.studentexpensetracker;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import org.checkerframework.checker.nullness.qual.NonNull;

public class ResetActivity extends AppCompatActivity {

    private EditText mEmailReset;
    private Button btnReset;
    private FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset);

        mAuth = FirebaseAuth.getInstance();

        mEmailReset = findViewById(R.id.email_reset);
        btnReset = findViewById(R.id.btn_reset);

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetPassword();
            }
        });
    }

    private void resetPassword() {
        String email = mEmailReset.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            mEmailReset.setError("Email Required!..");
            return;
        }

        mAuth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(ResetActivity.this, "Password reset email sent", Toast.LENGTH_SHORT).show();
                            // Redirect the user to the login screen or any other appropriate action
                            Intent intent = new Intent(ResetActivity.this, MainActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(ResetActivity.this, "Failed to send reset email", Toast.LENGTH_SHORT).show();
                        }
                    }
                });


    }

}
