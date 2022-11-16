package com.edward.adminapp.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.edward.adminapp.R;
import com.edward.adminapp.model.Notifications;

import java.util.List;

public class NotificationsAdapter extends RecyclerView.Adapter<NotificationsAdapter.ViewHolder> {
    Context context;
    List<Notifications> ls;
    int[] backgrounds = {R.drawable.background_notify_color_blue,
            R.drawable.background_notify_color_green,
            R.drawable.background_notify_color_red,
            R.drawable.background_notify_color_yellow};


    public NotificationsAdapter(Context context, List<Notifications> ls) {
        this.context = context;
        this.ls = ls;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.layout_notifications_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Notifications notifications = ls.get(position);
        holder.tvNameNotificationsItem.setText(notifications.getName());
        holder.clNotificationsItem.setBackgroundResource(backgrounds[(int)(Math.random()*4)]);




        holder.ivDeleteNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return ls.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ConstraintLayout clNotificationsItem;
        TextView tvNameNotificationsItem;
        ImageView ivDeleteNotification;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            clNotificationsItem = itemView.findViewById(R.id.clNotificationsItem);
            tvNameNotificationsItem = itemView.findViewById(R.id.tvNameNotificationsItem);
            ivDeleteNotification = itemView.findViewById(R.id.ivDeleteNotification);

        }
    }
}
