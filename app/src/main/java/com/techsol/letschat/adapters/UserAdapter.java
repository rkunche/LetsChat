package com.techsol.letschat.adapters;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.techsol.letschat.R;
import com.techsol.letschat.core.login.LoginContract;
import com.techsol.letschat.models.User;
import com.techsol.letschat.ui.ChatActivity;
import com.techsol.letschat.utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<User> userList;
    Context context;
    public UserAdapter(List<User> users,Context context)
    {

        userList = users;
        this.context = context;
        Log.i("getItemCount","itme count** "+userList.size());
    }
    @Override
    public int getItemCount() {
        Log.i("getItemCount","itme count"+userList.size());
        return userList.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View viewChatMine = layoutInflater.inflate(R.layout.adapter_item_user, parent, false);
        RecyclerView.ViewHolder  viewHolder = new UserViewHolder(viewChatMine);
        Log.i("onCreateViewHolder","onCreateViewHolder ");
        return viewHolder;
    }

    @Override
    public int getItemViewType(int position) {
        return 1;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            Log.i("onBindViewHolder","onBIndViewHolder "+position);
            configureUserAdapter((UserViewHolder) holder,position);
    }

    private void configureUserAdapter(UserViewHolder holder, final int position)
    {
        String name = userList.get(position).getName();
        String gmail = userList.get(position).getEmail();
        String quote = userList.get(position).getQuote();
        Log.i("user name ","User name "+userList.get(position).getName());
        Log.i("user name ","User name "+userList.get(position).getEmail());
        if(quote != null) {
            holder.profileQouteId.setText(userList.get(position).getQuote());
        }

        if(name != null && name.length() != 0) {
            holder.textView.setText(userList.get(position).getName());
            char c = name.charAt(0);
            String letter = "" + c;
            holder.nameLetterView.setText(letter);
            int pos = position % 3;
            if (pos == 0) {
                holder.nameLetterView.setBackgroundResource(R.drawable.round_text);
            }
            if (pos == 1) {
                holder.nameLetterView.setBackgroundResource(R.drawable.rounded_green_text);
            }
            if (pos == 2) {
                holder.nameLetterView.setBackgroundResource(R.drawable.round_pink_text);
            }
        }

        holder.viewHolderId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ChatActivity.class);
                intent.putExtra(Constants.IntentParamConstant.USER_TOKEN_PARAM,userList.get(position).getToken());
                intent.putExtra(Constants.IntentParamConstant.USER_UUID_PARAM,userList.get(position).getUuId());
                intent.putExtra(Constants.IntentParamConstant.USER_ID_PARAM,"");
                context.startActivity(intent);
            }
        });

    }

    private static class UserViewHolder extends RecyclerView.ViewHolder
    {
        ImageView imageView;
        TextView textView;
        LinearLayout viewHolderId;
        TextView nameLetterView;
        TextView profileQouteId;
        UserViewHolder(View viewItem)
        {
            super(viewItem);
            imageView = (ImageView)viewItem.findViewById(R.id.profile_img_id);
            textView = (TextView)viewItem.findViewById(R.id.profile_name_id);
            nameLetterView = (TextView)viewItem.findViewById(R.id.name_letter_id);
            profileQouteId = (TextView)viewItem.findViewById(R.id.profile_qoute_id);
            viewHolderId = (LinearLayout)viewItem.findViewById(R.id.view_holder_id);
        }
    }

    public void onDataChange(List<User> list)
    {
        if(list != null)
        {
           userList = new ArrayList<>(list);

            notifyDataSetChanged();
        }
    }



}
