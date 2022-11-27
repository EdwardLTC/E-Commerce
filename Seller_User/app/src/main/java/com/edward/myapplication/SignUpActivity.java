package com.edward.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.edward.myapplication.AppCustomer.views.MainActivity;

public class SignUpActivity extends AppCompatActivity {
    private Button btSignup;
    private EditText edtname;
    private EditText edtEmailsignup;
    private EditText edtPassword;
    private TextView loi3 ,loi4,loi5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        btSignup = findViewById(R.id.btSignUp);
        edtname =findViewById(R.id.edtName);
        edtEmailsignup = findViewById(R.id.edtEmailSignup);
        edtPassword = findViewById(R.id.edtPassword);
        loi3=findViewById(R.id.textView18);
        loi4=findViewById(R.id.textView19);
        loi5=findViewById(R.id.textView20);

        btSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emaill = edtEmailsignup.getText().toString();
                String password = edtPassword.getText().toString();
                String name = edtname.getText().toString();

                if (name.trim().equals("")) {
                    loi3.setError("");
                    loi3.setText("UserName cannot be blank!");
                } else {
                    loi3.setText("");
                }
                if (password.trim().equals("")) {
                    loi5.setError("");
                    loi5.setText("PassWord cannot be blank!");
                } else {
                    loi5.setText("");
                }

                if (emaill.trim().equals("")) {
                    loi4.setError("");
                    loi4.setText("Email cannot be blank!");
                } else if (!isValidEmail(emaill)) {
                    loi4.setText("Wrong data format");

                } else {
                    User user = new User();
                    user.setEmail(edtEmailsignup.getText().toString());
                    user.setName(edtname.getText().toString());
                    user.setPass(edtPassword.getText().toString());
                }

            }
        });
    }
    public boolean isValidEmail(String email) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }
}