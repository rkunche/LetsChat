package com.techsol.letschat.core.login;


import android.app.Activity;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GetTokenResult;
import com.google.firebase.iid.FirebaseInstanceId;
import com.techsol.letschat.core.db.DbContractorImpl;
import com.techsol.letschat.models.User;
import com.techsol.letschat.utils.Constants;

public class LoginInteractor implements LoginContract.LoginInteractor {
    LoginContract.loginEvents loginEvents;

    LoginInteractor(LoginContract.loginEvents loginEvents) {
        this.loginEvents = loginEvents;
    }

    @Override
    public void performFireBaseLogin(final String email, final String password, final String name) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        Log.i("login", "login complete");
                        if (task.isSuccessful()) {
                            Log.i("login", "login success");
                            final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            final String uuId = user.getUid();
                            Log.i("login", "login success uuid " + uuId);
                            Task<GetTokenResult> token = user.getToken(true);


                            token.addOnCompleteListener(new OnCompleteListener<GetTokenResult>() {
                                @Override
                                public void onComplete(@NonNull Task<GetTokenResult> task) {
                                    String token = FirebaseInstanceId.getInstance().getToken();
                                    //String token = task.getResult().getToken();
                                    User buildUser = new User();
                                    buildUser.setName(name);
                                    buildUser.setEmail(user.getEmail());
                                    buildUser.setUuId(uuId);
                                    buildUser.setToken(token);
                                    DbContractorImpl dbContractor = new DbContractorImpl(null);
                                    dbContractor.saveUser(buildUser);
                                    Log.i("login", "login success toke " + token);
                                }
                            });
                            loginEvents.onLoginSuccess();
                        } else {
                            //Log.i("login", "login failed "+task.getResult().toString());

                            performFirebaseRegistration(email,password,name);
                        }
                    }
                });
    }

    public void signOut() {
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            FirebaseAuth.getInstance().signOut();
            loginEvents.onSignOutSuccess();
        } else {
            loginEvents.onLoginFailure();
        }
    }


    public void performFirebaseRegistration(final String email, String password, final String name) {
        FirebaseAuth.getInstance()
                .createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.e(Constants.TAG, "performFirebaseRegistration:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Log.i("login", "login success");
                            final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            final String uuId = user.getUid();
                            Log.i("login", "login success uuid " + uuId);
                            Task<GetTokenResult> token = user.getToken(true);


                            token.addOnCompleteListener(new OnCompleteListener<GetTokenResult>() {
                                @Override
                                public void onComplete(@NonNull Task<GetTokenResult> task) {
                                    String token = FirebaseInstanceId.getInstance().getToken();
                                    //String token = task.getResult().getToken();
                                    User buildUser = new User();
                                    buildUser.setName(name);
                                    buildUser.setEmail(user.getEmail());
                                    buildUser.setUuId(uuId);
                                    buildUser.setToken(token);
                                    DbContractorImpl dbContractor = new DbContractorImpl(null);
                                    dbContractor.saveUser(buildUser);
                                    Log.i("login", "login success toke " + token);
                                }
                            });
                            loginEvents.onLoginSuccess();
                        } else {
                            loginEvents.onLoginFailure();
                            Log.i("login", "Registration failed "+task.getResult().toString());
                            // Add the user to users table.
                            /*DatabaseReference database= FirebaseDatabase.getInstance().getReference();
                            User user = new User(task.getResult().getUser().getUid(), email);
                            database.child("users").push().setValue(user);*/

                           // mOnRegistrationListener.onSuccess(task.getResult().getUser());
                        }
                    }
                });
    }
}
