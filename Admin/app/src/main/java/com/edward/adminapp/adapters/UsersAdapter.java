package com.edward.adminapp.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.edward.adminapp.MainActivity;
import com.edward.adminapp.R;
import com.edward.adminapp.SellersManagementActivity;
import com.edward.adminapp.Users;

import net.cachapa.expandablelayout.ExpandableLayout;

import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.ViewHolder> {
    Context context;
    List<Users> ls;
    RecyclerView rcv;
    private static final int UNSELECTED = -1;
    private int selectedItem = UNSELECTED;

    public UsersAdapter(Context context, List<Users> ls, RecyclerView rcv) {
        this.context = context;
        this.ls = ls;
        this.rcv = rcv;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.layout_sellers_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Users user = ls.get(position);
        holder.tvNameUserItem.setText(user.getName());
        holder.tvAddressUserItem.setText(user.getAddress());
        holder.tvMailUserItem.setText(user.getMail());
        holder.tvPhoneUserItem.setText(user.getPhone());
        holder.bind();
        holder.ivDeleteUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((SellersManagementActivity)view.getContext()).showDialogDeleteUser() ;
            }
        });


    }

    @Override
    public int getItemCount() {
        return ls.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, ExpandableLayout.OnExpansionUpdateListener {
        ConstraintLayout clLayoutSellersItem;
        ExpandableLayout expandableLayout;
        ImageView ivAvatarUserItem, ivDeleteUser;
        TextView tvNameUserItem, tvAddressUserItem, tvMailUserItem, tvPhoneUserItem;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            expandableLayout = itemView.findViewById(R.id.expandable_layout);
            expandableLayout.setInterpolator(new OvershootInterpolator());
            expandableLayout.setOnExpansionUpdateListener(this);




            clLayoutSellersItem = itemView.findViewById(R.id.clLayoutSellersItem);
            clLayoutSellersItem.setOnClickListener(this);

            ivAvatarUserItem = itemView.findViewById(R.id.ivAvatarUserItem);
            ivDeleteUser = itemView.findViewById(R.id.ivDeleteUser);
            tvNameUserItem = itemView.findViewById(R.id.tvNameUserItemInfo);
            tvMailUserItem = itemView.findViewById(R.id.tvMailUserItemInfo);
            tvPhoneUserItem = itemView.findViewById(R.id.tvPhoneUserItemInfo);
            tvAddressUserItem = itemView.findViewById(R.id.tvAddressUserItemInfo);
        }

        public void bind() {
            int position = getAdapterPosition();
            boolean isSelected = position == selectedItem;

            clLayoutSellersItem.setSelected(isSelected);
            expandableLayout.setExpanded(isSelected, false);
        }

        @Override
        public void onClick(View view) {

            ViewHolder holder = (ViewHolder) rcv.findViewHolderForAdapterPosition(selectedItem);
            if (holder != null) {
                holder.clLayoutSellersItem.setSelected(false);
                holder.expandableLayout.collapse();
            }

            int position = getAdapterPosition();
            if (position == selectedItem) {
                selectedItem = UNSELECTED;

            } else {
                clLayoutSellersItem.setSelected(true);
                expandableLayout.expand();
                selectedItem = position;
            }
        }

        @Override
        public void onExpansionUpdate(float expansionFraction, int state) {
            Log.d("ExpandableLayout", "State: " + state);
            if (state == ExpandableLayout.State.EXPANDING) {
                rcv.smoothScrollToPosition(getAdapterPosition());
            }
        }
    }
}
