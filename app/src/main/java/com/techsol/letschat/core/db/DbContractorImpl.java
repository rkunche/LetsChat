package com.techsol.letschat.core.db;


import android.util.Log;

import com.techsol.letschat.models.User;

import java.util.List;

public class DbContractorImpl implements DbContract {
    DbInteractor dbInteractor;

    ViewNotifierContract viewNotifierContract;
    public DbContractorImpl(ViewNotifierContract viewNotifierContract)
    {
        dbInteractor = new DbInteractor(this);
        this.viewNotifierContract = viewNotifierContract;
    }
    @Override
    public void saveUser(User user) {
     dbInteractor.saveUser(user);
    }

    @Override
    public void fetchUsers() {
        dbInteractor.fetchAllUsers();
    }

    @Override
    public void onSaveSuccess(String msg) {
        Log.i("Message SUCCESS",msg);
    }

    @Override
    public void onSaveFail(String msg) {
        Log.i("Message FAIL",msg);
    }

    @Override
    public void onFetchUsers(List<User> userList) {

        if(userList == null)
        {
            Log.i("Message is null","null");
        }
        else
        {
            Log.i("Message","message list "+userList.size());
        }
        viewNotifierContract.notifyUsersFetch(userList);
    }
}
