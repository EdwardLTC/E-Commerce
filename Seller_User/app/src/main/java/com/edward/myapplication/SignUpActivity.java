package com.edward.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.edward.myapplication.AppCustomer.views.MainActivity;
import com.edward.myapplication.AppSeller.views.VouchersManagementActivity;
import com.edward.myapplication.api.ServiceAPI;
import com.edward.myapplication.helper.MyHelper;
import com.edward.myapplication.model.modelrequest.PersonReq;
import com.edward.myapplication.model.modelrespon.Respon;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.saadahmedsoft.popupdialog.PopupDialog;
import com.saadahmedsoft.popupdialog.Styles;
import com.saadahmedsoft.popupdialog.listener.OnDialogButtonClickListener;

import java.util.Calendar;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;



public class SignUpActivity extends AppCompatActivity {
    private Button btSignup;
    private EditText edtEmailsignup;
    private EditText edtPasswordreply;
    private EditText edtPassword;
    private TextView txtlogin;
    private RadioButton rdoSeller, rdoCustomer;
    private Calendar calendar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        calendar = Calendar.getInstance();
        btSignup = findViewById(R.id.btSignUp);
        edtPasswordreply =findViewById(R.id.edtPasswordReply);
        edtEmailsignup = findViewById(R.id.edtEmailSignup);
        edtPassword = findViewById(R.id.edtPasswordSignup);

        txtlogin=findViewById(R.id.txtlogin);
        rdoCustomer = findViewById(R.id.rdoCustomers);
        rdoSeller = findViewById(R.id.rdoSeller);

        txtlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(),LoginActivity.class);
                startActivity(intent);
            }
        });

        btSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = edtEmailsignup.getText().toString();
                String email = edtEmailsignup.getText().toString();
                String password = edtPassword.getText().toString();
                String passwordReply = edtPasswordreply.getText().toString();

                if (email.trim().equals("") || passwordReply.trim().equals("") || password.trim().equals("")) {
                    Toast.makeText(SignUpActivity.this, "Please fill in the blank", Toast.LENGTH_SHORT).show();

                } else {
                    if (!isValidEmail(email)) {
                        Toast.makeText(SignUpActivity.this, "Wrong format email", Toast.LENGTH_SHORT).show();
                        return;
                    } else if (!password.equals(passwordReply)) {
                        Toast.makeText(SignUpActivity.this, "Password doesn't match", Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        registerUser(userName, password, email);
//                        signup(email, password);
                    }
                }
            }
        });
    }

    private void registerUser(String userName, String password, String email) {

        // on below line we are creating
        // a new user using parse user.
        ParseUser user = new ParseUser();

        // Set the user's username, user email and password,
        // which can be obtained from edit text
        user.setUsername(userName);
        user.setEmail(email);
        user.setPassword(password);

        // calling a method to register the user.
        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                // on user registration checking
                // if the error is null or not.
                if (e == null) {
                    signup(email, password);
                } else {
                    // if we get any error then we are logging out
                    // our user and displaying an error message
                    ParseUser.logOut();
                    Toast.makeText(SignUpActivity.this, "Fail to Register User..", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void signup(String email, String password) {
        int role = rdoCustomer.isChecked() ? 3 : 2;
        String name = "#" + calendar.getTimeInMillis();
        PersonReq personReq = new PersonReq(1, name, email, MyHelper.MD5(password), "", role, "https://cdn-icons-png.flaticon.com/512/147/147133.png", "");
        ServiceAPI.serviceApi.CreatePerson(personReq)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Respon>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        ProgressDialogCustom.showProgressDialog(SignUpActivity.this, "Please wait");
                    }

                    @Override
                    public void onNext(Respon respon) {
                        if (respon.getRespone_code() == 200) {
                            PopupDialog.getInstance(SignUpActivity.this)
                                    .setStyle(Styles.SUCCESS)
                                    .setHeading("User Registered successfully \n Please verify your email")
                                    .setCancelable(false)
                                    .showDialog(new OnDialogButtonClickListener() {
                                        @Override
                                        public void onDismissClicked(Dialog dialog1) {
                                            super.onDismissClicked(dialog1);
                                            Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                                            startActivity(intent);
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

    public boolean isValidEmail(String email) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }
}
