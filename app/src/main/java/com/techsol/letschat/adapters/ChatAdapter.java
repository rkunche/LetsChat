package com.techsol.letschat.adapters;


import android.content.Context;
import android.support.v4.app.NotificationCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.techsol.letschat.R;
import com.techsol.letschat.models.Message;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<Message> messages;
    private final static int SENDER_TYPE = 1;
    private final static int RECEIVER_TYPE = 2;
    private static String currentUser = null;
    LayoutInflater layoutInflater;

    public ChatAdapter(List<Message> messages, Context context, String uuId) {
        this.messages = messages;
        layoutInflater = LayoutInflater.from(context);
        currentUser = uuId;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
          if(holder instanceof SenderViewHolder)
          {
           configureSenderAdapter((SenderViewHolder) holder,position);
              Log.i("adapter value","adapter value **************************************");
              return;
          }
        Log.i("adapter value","adapter value ##############################################3");
          configureReceiverAdapter((ReceiverViewHolder) holder,position);
    }

    @Override
    public int getItemViewType(int position) {
        Message message = messages.get(position);
        if (currentUser != null && message.getUuId() != null && message.getUuId().equals(currentUser)) {
            return SENDER_TYPE;
        }
        return RECEIVER_TYPE;
    }

    @Override
    public int getItemCount() {
      //  Log.i("adapter value","adapter value message size "+messages.size());
        return messages.size();
    }



    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        switch (viewType) {
            case SENDER_TYPE:
                View senderView = layoutInflater.inflate(R.layout.chat_adapter_item_sender, parent, false);
                viewHolder = new SenderViewHolder(senderView);
                break;
            case RECEIVER_TYPE:
                View receiver = layoutInflater.inflate(R.layout.chat_adapter_item_receiver, parent, false);
                viewHolder = new ReceiverViewHolder(receiver);
                break;

        }

        Log.i("adapter value","adapter value "+viewType);
        return viewHolder;
    }

    private void configureSenderAdapter(SenderViewHolder holder, int position) {
        Log.i("adapter value","adapter position "+messages.get(position).getDisplayName());
        holder.messageText.setText(messages.get(position).getDisplayName());
        holder.timeText.setText(messages.get(position).getTime());
    }

    private void configureReceiverAdapter(ReceiverViewHolder holder, int position) {
        holder.messageText.setText(messages.get(position).getDisplayName());
        holder.timeText.setText(messages.get(position).getTime());
    }

    private class SenderViewHolder extends RecyclerView.ViewHolder {
        TextView messageText;
        TextView timeText;
        ImageView notifiedImg;
        SenderViewHolder(View view) {
            super(view);
            messageText = (TextView)view.findViewById(R.id.sender_message_id);
            timeText = (TextView)view.findViewById(R.id.sender_time_id);
            notifiedImg = (ImageView)view.findViewById(R.id.sender_img_id);
        }
    }

    private class ReceiverViewHolder extends RecyclerView.ViewHolder {
        TextView messageText;
        TextView timeText;
        ImageView notifiedImg;
        ReceiverViewHolder(View view) {
            super(view);
            messageText = (TextView)view.findViewById(R.id.receiver__message_id);
            timeText = (TextView)view.findViewById(R.id.receiver_time_id);
            notifiedImg = (ImageView)view.findViewById(R.id.receiver_img_id);
        }
    }

    public void onMessageReceived(Message message)
    {
        messages.add(message);
        notifyDataSetChanged();
    }

    public int getSize()
    {
      return messages.size();
    }
}
