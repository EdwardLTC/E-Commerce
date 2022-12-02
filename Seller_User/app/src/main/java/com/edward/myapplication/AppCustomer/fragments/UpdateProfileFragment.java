package com.edward.myapplication.AppCustomer.fragments;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.cloudinary.android.MediaManager;
import com.cloudinary.android.callback.ErrorInfo;
import com.cloudinary.android.callback.UploadCallback;
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

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class UpdateProfileFragment extends Fragment {
    EditText name, phone, address;
    ImageView avatar;
    Button save;
    Uri img;
    TextView txtlink;
    HashMap config = new HashMap();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_updateprofile, container, false);

        name = view.findViewById(R.id.YourName);
        avatar = view.findViewById(R.id.avatar);
        save = view.findViewById(R.id.save);
        phone = view.findViewById(R.id.Phone);
        address = view.findViewById(R.id.Address);
        txtlink = view.findViewById(R.id.txtlink);

        avatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseImage();
            }

        });

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
                    configCloudinary();
                    upload();
                }

            }
        });

        txtlink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtlink.setVisibility(view.INVISIBLE);
            }
        });
        return view;
    }

    private void updateProfile() {
        String Name = name.getText().toString();
        String Phone = phone.getText().toString();
        String Address = address.getText().toString();
        // gán link ở đây nha
        String link =txtlink.getText().toString();
                //"https://www.shareicon.net/data/512x512/2016/05/24/770117_people_512x512.png";
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
    private void chooseImage() {

        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            someActivityResultLauncher.launch(intent);
        } else {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        }
    }

    ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if (result.getResultCode() == Activity.RESULT_OK) {
                // There are no request codes
                Intent data = result.getData();
                img = data.getData();
                Glide.with(UpdateProfileFragment.this).load(img).into(avatar);

            }
        }
    });
    private void upload() {
        MediaManager.get().upload(img).callback(new UploadCallback() {
            @Override
            public void onStart(String requestId) {
                Log.d("CHECK", "onStart");

            }

            @Override
            public void onProgress(String requestId, long bytes, long totalBytes) {
                Log.d("CHECK", "onProgress");

            }

            @Override
            public void onSuccess(String requestId, Map resultData) {
                Log.d("CHECK", "onSuccess");

                txtlink.setText(resultData.get("url").toString());
            }

            @Override
            public void onError(String requestId, ErrorInfo error) {

                Log.d("CHECK", "onError: " + error);
            }

            @Override
            public void onReschedule(String requestId, ErrorInfo error) {
                Log.d("CHECK", "onReschedule: " + error);
            }
        }).dispatch();
    }

    private void configCloudinary() {
        config.put("cloud_name", "mrsmci");
        config.put("api_key", "349364544734878");
        config.put("api_secret", "3jjrlkK2rWHzy71859iaJ9M1u-4");
        MediaManager.init(requireContext(), config);
    }
}
