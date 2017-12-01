package com.techsol.letschat.core.db;


import com.techsol.letschat.models.User;

import java.util.List;

public interface FetchUsers {
    public void onFetchUsers(List<User> userList);
}
