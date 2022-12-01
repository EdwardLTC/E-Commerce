package com.edward.myapplication.AppCustomer.fragments;

import android.app.Dialog;
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

import com.edward.myapplication.AppSeller.views.VouchersManagementActivity;
import com.edward.myapplication.LoginActivity;
import com.edward.myapplication.ProgressDialogCustom;
import com.edward.myapplication.R;
import com.edward.myapplication.api.ServiceAPI;
import com.edward.myapplication.model.modelrequest.PersonReq;
import com.edward.myapplication.model.modelrespon.PersonRes;
import com.edward.myapplication.model.modelrespon.Respon;
import com.saadahmedsoft.popupdialog.PopupDialog;
import com.saadahmedsoft.popupdialog.Styles;
import com.saadahmedsoft.popupdialog.listener.OnDialogButtonClickListener;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class UpdateProfileFragment extends Fragment {
    EditText name, phone, address;
    ImageView avatar;
    Button save;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_updateprofile, container, false);

        name = view.findViewById(R.id.YourName);
        avatar = view.findViewById(R.id.avatar);
        save = view.findViewById(R.id.save);
        phone = view.findViewById(R.id.Phone);
        address = view.findViewById(R.id.Address);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Name = name.getText().toString();
                String Phone = phone.getText().toString();
                String Address = address.getText().toString();

                if (Name.equals("")|| Phone.equals("") || Address.equals("")){
                    Toast.makeText(getContext(), "Please fill into the blank", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    updateProfile();
                }

            }
        });

        return view;
    }

    private void updateProfile() {
        String Name = name.getText().toString();
        String Phone = phone.getText().toString();
        String Address = address.getText().toString();
        // gán link ở đây nha
        String link = "https://www.shareicon.net/data/512x512/2016/05/24/770117_people_512x512.png";
        PersonReq personReq = new PersonReq(LoginActivity.PERSONRES.getId(), Name,
                LoginActivity.PERSONRES.getMail(), Phone, LoginActivity.PERSONRES.getRole(),
                link, Address);

        ServiceAPI.serviceApi.UpdatePerson(personReq)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Respon>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        ProgressDialogCustom.showProgressDialog(requireContext(), "Please wait");
                    }

                    @Override
                    public void onNext(Respon respon) {
                        if (respon.getRespone_code() == 200) {
                            PopupDialog.getInstance(requireContext())
                                    .setStyle(Styles.SUCCESS)
                                    .setHeading("Well Done")
                                    .setHeading("You have successfully"+
                                            " updated")
                                    .setCancelable(false)
                                    .showDialog(new OnDialogButtonClickListener() {
                                        @Override
                                        public void onDismissClicked(Dialog dialog1) {
                                            super.onDismissClicked(dialog1);
                                            LoginActivity.PERSONRES = new PersonRes(LoginActivity.PERSONRES.getId(),
                                                    personReq.getName(),
                                                    LoginActivity.PERSONRES.getMail(),
                                                    personReq.getPhoneNum(),
                                                    LoginActivity.PERSONRES.getRole(),
                                                    personReq.getImgUrl(),
                                                    personReq.getAddress());
                                        }
                                    });
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        ProgressDialogCustom.dismissProgressDialog();

                    }

                    @Override
                    public void onComplete() {
                        ProgressDialogCustom.dismissProgressDialog();
                    }
                });
    }
}
