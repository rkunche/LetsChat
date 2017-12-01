package com.techsol.letschat.core.register;


import com.techsol.letschat.core.login.LoginContract;
import com.techsol.letschat.utils.Validation;

public class RegisterPresenter implements LoginContract.Presenter, LoginContract.loginEvents {
    LoginContract.View loginContract;
    RegisterContractor registerContractor;

    public RegisterPresenter(LoginContract.View loginContract) {
        this.loginContract = loginContract;
        registerContractor = new RegisterContractor(this);
    }

    @Override
    public void login(String email, String pwd, String name) {
        String validation = Validation.validate(email, pwd);
        if (null == validation) {
            registerContractor.performFirebaseRegistration(email, pwd);
        } else {
            loginContract.validateLogin(validation);
        }
    }

    @Override
    public void onLoginSuccess() {
        loginContract.onLoginSuccess();
    }

    @Override
    public void onLoginFailure() {
        loginContract.onLoginFailure();
    }

    @Override
    public void onSignOutSuccess() {

    }

    @Override
    public void onSignOutFail() {

    }
}
