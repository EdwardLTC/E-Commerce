package com.edward.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

//import com.edward.adminapp.API.ServiceAPI;
//import com.edward.adminapp.helpers.MyHelpers;
//import com.edward.adminapp.model.modelrequest.PersonReq;
//import com.edward.adminapp.model.modelrespon.ResGetPerson;
//import com.edward.adminapp.model.modelrespon.Respon;
import com.edward.myapplication.AppCustomer.views.MainActivity;
import com.edward.myapplication.api.ServiceAPI;
import com.edward.myapplication.helper.MyHelper;
import com.edward.myapplication.model.modelrequest.PersonReq;
import com.edward.myapplication.model.modelrespon.ResGetPerson;
import com.edward.myapplication.model.modelrespon.Respon;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LoginActivity extends AppCompatActivity {

    private Button btLogin;
    private EditText edtEmailLogin;
    private EditText edtPassword;
    private TextView loi1 ,loi2, txtsignup;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btLogin = findViewById(R.id.btLogin);
        edtEmailLogin = findViewById(R.id.edtEmailLogin);
        edtPassword = findViewById(R.id.edtPassword);
        loi1=findViewById(R.id.textView16);
        loi2=findViewById(R.id.textView17);
        txtsignup=findViewById(R.id.txtsignup);
        txtsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(),SignUpActivity.class);
                startActivity(intent);
            }
        });
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyHelper.addClickEffect(view);
                login();
                String emaill = edtEmailLogin.getText().toString();
                String password = edtPassword.getText().toString();


                if (emaill.trim().equals("")) {
//                    loi1.setError("");
//                    loi1.setText("Email cannot be blank!");
                    Toast.makeText(LoginActivity.this, "Email cannot be blank!", Toast.LENGTH_SHORT).show();

                } else if (!isValidEmail(emaill)) {
//                    loi1.setText("Wrong data format");
                    Toast.makeText(LoginActivity.this, "Wrong data format", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                } else {
                   login();
                }

                if (password.trim().equals("")) {
//                    loi2.setError("");
//                    loi2.setText("PassWord cannot be blank!");
                    Toast.makeText(LoginActivity.this, "PassWord cannot be blank!", Toast.LENGTH_SHORT).show();
                } else {
//                    loi2.setText("");
                    Toast.makeText(LoginActivity.this, "", Toast.LENGTH_SHORT).show();

                }
            }



        });


        // create person
//        String pass = MyHelpers.getHashPassword("abc");
//        Log.d("pass ", pass);
//        boolean verified = MyHelpers.isVerifiedHash("abc", pass);
//        Log.d("pass ", verified+"");
    }


    private void login() {
        ServiceAPI.serviceApi.Login(edtEmailLogin.getText().toString(), edtPassword.getText().toString())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResGetPerson>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        ProgressDialogCustom.showProgressDialog(LoginActivity.this, "please wait");
                    }

                    @Override
                    public void onNext(ResGetPerson resGetPerson) {
                        if (resGetPerson._Respon.getRespone_code() != 200) {
                            Toast.makeText(LoginActivity.this, "Check username, Psw pls", Toast.LENGTH_SHORT).show();
                        } else {
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        ProgressDialogCustom.dismissProgressDialog();
                        Toast.makeText(LoginActivity.this, "non null field", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {
                        ProgressDialogCustom.dismissProgressDialog();
                    }
                });
    }
    public boolean isValidEmail(String email) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }
}
