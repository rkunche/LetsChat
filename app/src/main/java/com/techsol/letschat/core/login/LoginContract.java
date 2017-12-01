package com.techsol.letschat.core.login;



public interface LoginContract {

   public interface View
    {
        public void onLoginSuccess();
        public void onLoginFailure();
        void validateLogin(String message);
    }

  public   interface Presenter
    {
      public void login(String email, String pwd, String name);
    }



  public   interface LoginInteractor
    {
        public void performFireBaseLogin(String email, String pwd, String name);
    }

   public interface loginEvents{
        public void onLoginSuccess();
        public void onLoginFailure();
        public void onSignOutSuccess();
        public void onSignOutFail();
    }


}
