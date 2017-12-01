package com.techsol.letschat.core.login;


import com.techsol.letschat.utils.Validation;

public class LoginPresenter implements LoginContract.Presenter,LoginContract.loginEvents {

    LoginContract.View loginContract;
    LoginInteractor loginInteractor;
   public LoginPresenter(LoginContract.View loginContract)
    {
        this.loginContract = loginContract;
        loginInteractor = new LoginInteractor(this);
    }
    @Override
    public void login(String email, String pwd, String name) {
        String validation = Validation.validate(email,pwd);
        if(null == validation) {
            loginInteractor.performFireBaseLogin(email, pwd, name);
        }
        else
        {
            loginContract.validateLogin(validation);
        }
    }

    public void signOut()
{
    loginInteractor.signOut();
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
    public void onSignOutFail() {

    }

    @Override
    public void onSignOutSuccess() {

    }
}
