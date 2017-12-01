package com.techsol.letschat.core.db;



public interface SaveStatus {
    public void onSaveSuccess(String msg);
    public void onSaveFail(String msg);
}
