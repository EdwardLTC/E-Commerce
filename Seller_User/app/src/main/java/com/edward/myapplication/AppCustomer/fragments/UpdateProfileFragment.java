package com.edward.myapplication.AppCustomer.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.edward.myapplication.R;
import com.edward.myapplication.api.ServiceAPI;

public class UpdateProfileFragment extends Fragment {
    EditText name, email, phone, address;
    ImageView avatar;
    Button save;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_updateprofile, container, false);

        name = view.findViewById(R.id.YourName);
        email = view.findViewById(R.id.Email);
        avatar = view.findViewById(R.id.avatar);
        save = view.findViewById(R.id.save);
        phone = view.findViewById(R.id.Phone);
        address = view.findViewById(R.id.Address);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Name = name.getText().toString();
                String Email = email.getText().toString();
                String Phone = phone.getText().toString();
                String Address = address.getText().toString();
                String EmailPattern = "/^([a-z0-9_\\.-]+)@([\\da-z\\.-]+)\\.([a-z\\.]{2,6})$/";

                if (Name.equals("") || Email.equals("") || Phone.equals("") || Address.equals("")){
                    Toast.makeText(getContext(), "Please fill into the blank", Toast.LENGTH_SHORT).show();
                    return;
                }else if (!Email.matches(EmailPattern)){
                    Toast.makeText(getContext(), "Email invalid", Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    updateProfile();
                }

            }
        });

        return view;
    }

    private void updateProfile() {
        String Name = name.getText().toString();
        String Email = email.getText().toString();
        String Phone = phone.getText().toString();
        String Address = address.getText().toString();

//        ServiceAPI.serviceApi.Up
    }
}
