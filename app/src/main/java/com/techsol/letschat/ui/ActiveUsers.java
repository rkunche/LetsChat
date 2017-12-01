package com.techsol.letschat.ui;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.techsol.letschat.BaseActivity;
import com.techsol.letschat.R;
import com.techsol.letschat.adapters.UserAdapter;
import com.techsol.letschat.core.db.DbContractorImpl;
import com.techsol.letschat.core.db.ViewNotifierContract;
import com.techsol.letschat.core.login.LoginContract;
import com.techsol.letschat.core.login.LoginPresenter;
import com.techsol.letschat.models.User;
import com.techsol.letschat.utils.Constants;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ActiveUsers extends BaseActivity implements ViewNotifierContract,LoginContract.View {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    DbContractorImpl dbContractor;

    UserAdapter userAdapter;

    LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_active_users);
        ButterKnife.bind(this);
        bindView();
        printMessage("ActiveUse on create");
        init();
    }

    private void init() {
        List<User> users = new ArrayList<>();
        userAdapter = new UserAdapter(users,this);
        loginPresenter  = new LoginPresenter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(userAdapter);
        userAdapter.notifyDataSetChanged();
        dbContractor = new DbContractorImpl(this);
        dbContractor.fetchUsers();
    }

    private void bindView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // setSupportActionBar(toolbar);

    }

    @Override
    public void notifyUsersFetch(List<User> userList) {
        if (userList == null) {
            showToast(Constants.USERS_FETCH_FAIL);
            return;
        }
        showToast("Size of the users "+userList.size());
        userAdapter.onDataChange(userList);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_user_listing, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_logout:
                logout();
                break;
        }
        return super.onOptionsItemSelected(item);

    }

    private void logout() {
        new AlertDialog.Builder(this)
                .setTitle(R.string.sign_out)
                .setMessage(R.string.sign_out_confirm)
                .setPositiveButton(R.string.sign_out, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        loginPresenter.signOut();
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }

    @Override
    public void onLoginSuccess() {
        showToast(getResources().getString(R.string.sign_out_success_message));
    }

    @Override
    public void onLoginFailure() {
        showToast(getResources().getString(R.string.sign_out_fail_message));
    }

    @Override
    public void validateLogin(String message) {

    }


}
