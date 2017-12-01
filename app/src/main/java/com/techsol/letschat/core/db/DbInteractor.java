package com.techsol.letschat.core.db;


import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.techsol.letschat.models.User;
import com.techsol.letschat.utils.Constants;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.techsol.letschat.utils.Constants.saveUserValues;

public class DbInteractor<T> {

    DbContract dbContract;
   public DbInteractor(DbContract saveOp)
    {
        dbContract = saveOp;
    }

    public void fetchAllUsers()
    {
        Log.i("fetchAllUsers","FetchAllUsers");
        FirebaseDatabase.getInstance().getReference().child(Constants.USER).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
              List<User> userList =new ArrayList<>();
               Iterator<DataSnapshot> dataSnapshotIterator = dataSnapshot.getChildren().iterator();
                while (dataSnapshotIterator.hasNext())
                {
                    DataSnapshot dataSnapshot1 =  dataSnapshotIterator.next();
                   User fireBaseUser = dataSnapshot1.getValue(User.class);
                    userList.add(fireBaseUser);
                }
                dbContract.onFetchUsers(userList);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                dbContract.onSaveFail(Constants.USER_FETCH);
            }
        });
    }

    public void saveUser(User user)
    {
       FirebaseDatabase.getInstance().getReference().child(Constants.USER).child(user.getUuId()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
           @Override
           public void onComplete(@NonNull Task<Void> task) {
               if(task.isSuccessful())
               {
                 dbContract.onSaveSuccess(Constants.USER_SAVE);
               }
               else
               {
                  dbContract.onSaveFail(Constants.USER_SAVE);
               }
           }
       });
        saveUserValues(user);
    }

}

