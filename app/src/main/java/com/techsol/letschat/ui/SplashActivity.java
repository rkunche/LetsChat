package com.techsol.letschat.ui;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.techsol.letschat.BaseActivity;
import com.techsol.letschat.R;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        if (FirebaseAuth.getInstance().getCurrentUser() == null) {
            navigateToLoginActivity();
        } else {
            navigateToMainActivity();
        }
    }

    private void navigateToLoginActivity() {
        printMessage("Login activity");
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private void navigateToMainActivity() {
        printMessage("Main activity");
        Intent intent = new Intent(this, TabActivity.class);
        startActivity(intent);
        finish();
    }


}
