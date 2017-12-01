package com.techsol.letschat.utils;


import android.content.Context;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Validation {
    public static String validate(String email, String pwd)
    {
        if(email == null || email.length() == 0 || !email.contains("@"))
        {
            return "Eneter Valid Email Id";
        }
        if(pwd == null || pwd.length() == 0 )
        {
            return "Eneter Valid pwd";
        }
        return null;
    }
    public static boolean isEmpty(EditText etText) {
        return etText.getText().toString().trim().length() == 0;
    }
    public static boolean isEmpty(TextView etText) {
        return etText.getText().toString().trim().length() == 0;
    }
    public static void showToast(Context context,String message)
    {
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show();
    }
}
