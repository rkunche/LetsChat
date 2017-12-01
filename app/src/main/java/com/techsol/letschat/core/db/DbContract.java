package com.techsol.letschat.core.db;


import com.techsol.letschat.models.User;

import java.util.List;

public interface DbContract extends FetchUsers,SaveStatus {
    public void saveUser(User user);
    public void fetchUsers();
}
