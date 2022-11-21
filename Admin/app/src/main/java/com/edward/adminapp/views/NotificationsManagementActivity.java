package com.edward.adminapp.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.EditText;

import com.edward.adminapp.R;
import com.edward.adminapp.adapters.NotificationsAdapter;
import com.edward.adminapp.model.Notifications;

import java.util.ArrayList;
import java.util.List;

public class NotificationsManagementActivity extends AppCompatActivity {

    CardView cvBackToHomeFromNotifications;
    EditText edtSearchNotifications;
    RecyclerView rcvNotificationsManagement;
    NotificationsAdapter notificationsAdapter;
    List<Notifications> ls;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications_management);
        initViews();
        ls = new ArrayList<>();
        loadRecyclerView();

    }

    private void initViews() {
        cvBackToHomeFromNotifications = findViewById(R.id.cvBackToHomeFromNotifications);
        edtSearchNotifications = findViewById(R.id.edtSearchNotifications);
        rcvNotificationsManagement = findViewById(R.id.rcvNotificationsManagement);
    }

    private void loadRecyclerView() {
        ls.add(new Notifications(1, "notifications1"));
        ls.add(new Notifications(1, "notifications2"));
        ls.add(new Notifications(1, "notifications3"));
        ls.add(new Notifications(1, "notifications4"));
        ls.add(new Notifications(1, "notifications5"));
        ls.add(new Notifications(1, "notifications6"));
        ls.add(new Notifications(1, "notifications7"));
        ls.add(new Notifications(1, "notifications8"));
        ls.add(new Notifications(1, "notifications1"));
        ls.add(new Notifications(1, "notifications2"));
        ls.add(new Notifications(1, "notifications3"));
        ls.add(new Notifications(1, "notifications4"));
        ls.add(new Notifications(1, "notifications5"));
        ls.add(new Notifications(1, "notifications6"));
        ls.add(new Notifications(1, "notifications7"));
        ls.add(new Notifications(1, "notifications8"));
        ls.add(new Notifications(1, "notifications1"));
        ls.add(new Notifications(1, "notifications2"));
        ls.add(new Notifications(1, "notifications3"));
        ls.add(new Notifications(1, "notifications4"));
        ls.add(new Notifications(1, "notifications5"));
        ls.add(new Notifications(1, "notifications6"));
        ls.add(new Notifications(1, "notifications7"));
        ls.add(new Notifications(1, "notifications8"));

        notificationsAdapter = new NotificationsAdapter(this, ls);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rcvNotificationsManagement.setLayoutManager(layoutManager);
        rcvNotificationsManagement.setAdapter(notificationsAdapter);
    }
}