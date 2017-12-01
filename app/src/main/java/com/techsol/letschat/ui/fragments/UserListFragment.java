package com.techsol.letschat.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.techsol.letschat.R;
import com.techsol.letschat.adapters.UserAdapter;
import com.techsol.letschat.core.db.DbContractorImpl;
import com.techsol.letschat.core.db.ViewNotifierContract;
import com.techsol.letschat.core.login.LoginContract;
import com.techsol.letschat.core.login.LoginPresenter;
import com.techsol.letschat.models.User;
import com.techsol.letschat.ui.TabActivity;
import com.techsol.letschat.utils.Constants;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserListFragment extends Fragment implements ViewNotifierContract, LoginContract.View {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    DbContractorImpl dbContractor;

    UserAdapter userAdapter;

    LoginPresenter loginPresenter;

    ActivityFragmentBridge activityFragmentBridge;


    public UserListFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_active_users, container, false);
        ButterKnife.bind(this, view);
        init();
        return view;
    }

    private void init() {
        List<User> users = new ArrayList<>();
        userAdapter = new UserAdapter(users, getActivity());
        loginPresenter = new LoginPresenter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(userAdapter);
        userAdapter.notifyDataSetChanged();
        dbContractor = new DbContractorImpl(this);
        activityFragmentBridge.showMessage(Constants.FETCH_USERS);
        dbContractor.fetchUsers();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activityFragmentBridge = (TabActivity) getActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        activityFragmentBridge = null;
    }

    @Override
    public void onLoginFailure() {

    }

    @Override
    public void onLoginSuccess() {

    }

    @Override
    public void validateLogin(String message) {
        activityFragmentBridge.showMessage(message);
    }

    @Override
    public void notifyUsersFetch(List<User> userList) {
        activityFragmentBridge.showMessage(Constants.FETCH_USERS_COMPLETED);
        if (userList == null) {
            activityFragmentBridge.showMessage(Constants.USERS_FETCH_FAIL);
            return;
        }
       // activityFragmentBridge.showMessage("Size of the users " + userList.size());
        userAdapter.onDataChange(userList);
    }


}
