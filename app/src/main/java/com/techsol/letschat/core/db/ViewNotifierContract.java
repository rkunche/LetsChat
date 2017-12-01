package com.techsol.letschat.core.db;


import com.techsol.letschat.models.User;

import java.util.List;

public interface ViewNotifierContract {
    public void notifyUsersFetch(List<User> userList);
}
