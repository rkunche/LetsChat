package com.techsol.letschat.core.register;


import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.techsol.letschat.core.login.LoginContract;

public class RegisterContractor{
    public RegisterPresenter mRegisterPresenter;
    public RegisterContractor(RegisterPresenter mRegisterPresenter)
    {
      this.mRegisterPresenter = mRegisterPresenter;
    }
    public void performFirebaseRegistration(String email, String pwd)
    {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                   mRegisterPresenter.onLoginSuccess();
                }
                else
                {
                    mRegisterPresenter.onLoginFailure();
                }
            }
        });
    }
}
