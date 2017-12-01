package com.techsol.letschat.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;

import com.techsol.letschat.BaseActivity;
import com.techsol.letschat.R;
import com.techsol.letschat.core.db.DbContractorImpl;
import com.techsol.letschat.core.login.LoginContract;
import com.techsol.letschat.core.login.LoginPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements LoginContract.View {

    LoginPresenter loginPresenter;

    DbContractorImpl dbContractor;

    @BindView(R.id.email_edit_id)
    EditText emailEditText;

    @BindView(R.id.pwd_edit_id)
    EditText pwdEditText;

    @BindView(R.id.name_edit_id)
    EditText nameText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        bindView();
        init();

    }

    private void bindView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void init() {
        loginPresenter = new LoginPresenter(this);
        dbContractor = new DbContractorImpl(null);
    }

    @Override
    public void onLoginSuccess() {
        hideProgressDialog();
        showToast(getResources().getString(R.string.login_success_message));
        Intent intent = new Intent(this, TabActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onLoginFailure() {
        showToast(getResources().getString(R.string.login_fail_message));
        hideProgressDialog();
    }

    @OnClick(R.id.login_id)
    public void loginClick() {
        showProgressDialog();
        String email = emailEditText.getText().toString();
        String pwd = pwdEditText.getText().toString();
        String name = nameText.getText().toString();
        loginPresenter.login(email, pwd, name);
    }


    @Override
    public void validateLogin(String validateMessage) {
        showToast(validateMessage);
        hideProgressDialog();
    }
}
