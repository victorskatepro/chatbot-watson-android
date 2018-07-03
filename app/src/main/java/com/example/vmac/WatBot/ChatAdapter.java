package com.example.vmac.WatBot;

/**
 * Created by VMac on 17/11/16.
 */

import android.graphics.Typeface;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;


import java.util.ArrayList;



public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private int SELF = 100;
    private ArrayList<Message> messageArrayList;


    public ChatAdapter(ArrayList<Message> messageArrayList) {
        this.messageArrayList=messageArrayList;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;

        // view type is to identify where to render the chat message
        // left or right
        if (viewType == SELF) {
            // self message
            itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.chat_item_self, parent, false);
        } else {
            // WatBot message
            itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.chat_item_watson, parent, false);
        }


        return new ViewHolder(itemView);
    }

    @Override
    public int getItemViewType(int position) {
        Message message = messageArrayList.get(position);
        if (message.getId()!=null && message.getId().equals("1")) {
            return SELF;
        }

        return position;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        Message message = messageArrayList.get(position);
        message.setMessage(message.getMessage());

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
        {
            String code = "<html>" + message.getMessage() + "</html>";
            ((ViewHolder) holder).message.loadData(code, "text/html", "UTF-8");
        }else {
            String code = "<html>" + message.getMessage() + "</html>";
            ((ViewHolder) holder).message.loadData(code, "text/html", "UTF-8");
        }
        }

    @Override
    public int getItemCount() {
            return messageArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        WebView message;

        public ViewHolder(View view) {
            super(view);
            message = (WebView) itemView.findViewById(R.id.message);

            //TODO: Uncomment this if you want to use a custom Font
            /*String customFont = "Montserrat-Regular.ttf";
            Typeface typeface = Typeface.createFromAsset(itemView.getContext().getAssets(), customFont);
            message.setTypeface(typeface);*/

        }
    }


}